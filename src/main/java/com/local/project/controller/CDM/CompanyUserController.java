package com.local.project.controller.CDM;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.local.project.enumeration.ERole;
import com.local.project.message.ResourceNotFoundException;
import com.local.project.model.Auth.Role;
import com.local.project.model.Auth.User;
import com.local.project.model.CDM.CompanyAddress;
import com.local.project.model.CDM.CompanyUserDetails;
import com.local.project.model.Superadmin.Acts;
import com.local.project.model.Superadmin.Company;
import com.local.project.repository.Auth.RoleRepository;
import com.local.project.repository.Auth.UserRepository;
import com.local.project.repository.CDM.CompanyAddressRepository;
import com.local.project.repository.Superadmin.CityRepository;
import com.local.project.repository.Superadmin.CompanyRepository;
import com.local.project.repository.Superadmin.StateRepository;
import com.local.project.service.Auth.AuthorDataService;
import com.local.project.service.Superadmin.companyservice;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import net.bytebuddy.utility.RandomString;


@RestController
@RequestMapping("/api/company-user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CompanyUserController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	companyservice companyservice;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	StateRepository staterepo;
	
	@Autowired
	CityRepository cityrepo;
	
	@Autowired
	CompanyAddressRepository addressrepo;
	
	@Autowired
	companyservice compservice;
	
	@Autowired
	AuthorDataService authorservice;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;

	
	@GetMapping("/adduser/{companyId}")
    public User getAllCompany(@PathVariable(value="companyId") Long companyId,
    		                    @RequestParam(value="email") String email, 
                                @RequestParam(value="addressId") Integer addressId ) {
		User olduser =new User();
		User newuser =new User();
		List<Integer> newaddresslist= new ArrayList<Integer>();
		//check email already exist
		if(userRepository.existsByEmail(email)){
			//get the user
			olduser=userRepository.findByEmail(email).orElseThrow();
			// get user present company address id
			 List<Integer> ca_list=olduser.getCompanyaddress();
			 if(ca_list.contains(addressId)) {
				newaddresslist=ca_list; 
				
			 }
			 else {
				 ca_list.add(addressId);
				 newaddresslist=ca_list;
				
			 }
//			//saving the value
     			olduser.setCompany_id(companyId);;
				olduser.setCompanyaddress(newaddresslist);
				newuser=userRepository.save(olduser);
				return newuser;
		}
		else {
			//user new list
			newaddresslist.add(addressId);			
			LocalDateTime created_at=LocalDateTime.now();
			//password generated
			String password = RandomString.make(32);
			String passworduser = encoder.encode(password);
			Long createdby = authorservice.findCurrentUser();
			//creating new user
			User user =new User(email,
					            passworduser,
					            newaddresslist,
					            createdby,
					            created_at);
			Set<Role> roles = new HashSet<>();
			//setting client role to user
     		Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT_USER);
			roles.add(userRole);
			user.setRoles(roles);
			user.setIsenabled(true);
			user.setCompany_id(companyId);
			newuser=userRepository.save(user);
			return newuser;
			
		}
			
		
		
    }
	
	@GetMapping("/getuser/{compId}")
	public List<?> getuser(@PathVariable(value="compId") Long compId) {
		List<User> x=userRepository.findAllUsers2(3);
		ArrayList<HashMap<String, List<String>>> finalnamelist = new ArrayList<HashMap<String, List<String>>>();
		List<String> y=new ArrayList<>();
		List<User> userlist = x.stream()
                   .filter(s -> s.getCompany_id()==compId)
                    .collect(Collectors.toList());
		
		return userlist;
	}
	
	@GetMapping("/getcompanyaddress/{comId}")
	public List<CompanyAddress> getcompaddress(@PathVariable(value="comId") Long comId) {
		List<CompanyAddress> companyaddress = companyRepository.findByCompanyId(comId).getCompanyaddress();
		return companyaddress;
	}
	
	@GetMapping("/getuserbyemail/{comId}")
	public List<User>  getuserbyemail(@PathVariable(value="comId") Long comId) {
		List<CompanyAddress> companyaddress = companyRepository.findByCompanyId(comId).getCompanyaddress();
		List<User> finaluserlist= new ArrayList <User>();
		User olduser =new User();
		int x=0;
		for(int i=0;i<companyaddress.size();i++) {
			String email = companyaddress.get(i).getMailId();
			 olduser = userRepository.findByEmail(email).orElseThrow();
			 finaluserlist.add(olduser);
		}
		
		return finaluserlist;
	}
	//delete user
		@DeleteMapping("/deletefromuserlist/{userId}")
		public ResponseEntity<?> deleteuser(@PathVariable Long userId)throws ResourceNotFoundException {
		       return userRepository.findById(userId).map(user -> {
		           userRepository.delete(user);
		           return ResponseEntity.ok().build();
		       }).orElseThrow(() -> new ResourceNotFoundException("userid " + userId + " not found"));
		}
	
}
