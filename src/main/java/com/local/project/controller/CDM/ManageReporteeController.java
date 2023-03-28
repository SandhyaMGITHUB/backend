package com.local.project.controller.CDM;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.local.project.jwts.MessageResponse;
import com.local.project.message.ResourceNotFoundException;
import com.local.project.model.Auth.Role;
import com.local.project.model.Auth.User;
import com.local.project.model.CDM.CompanyAddress;
import com.local.project.model.Superadmin.Acts;
import com.local.project.model.Superadmin.Company;
import com.local.project.repository.Auth.UserRepository;
import com.local.project.repository.Superadmin.CompanyRepository;
import com.local.project.service.Auth.AuthorDataService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import nonapi.io.github.classgraph.utils.CollectionUtils;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)

public class ManageReporteeController {
	
	@Autowired
	AuthorDataService authorservice;
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	CompanyRepository comrepo;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@GetMapping("/get-manage-reportee")
	public List<User> getmanagereportee() {
		//finding currently logged in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean RelationHOD = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_RELATION_HEAD"));
		boolean LiasonHOD = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_LIASION_HEAD"));
		List<User> clientuser = new ArrayList <User>();
		if(RelationHOD) {
			clientuser=userrepo.findAllUsers2(12);
			return clientuser;
		}
		if(LiasonHOD) {
			List<User> liason_north = userrepo.findAllUsers2(13);
			List<User> liason_south = userrepo.findAllUsers2(14);
			clientuser = Stream.of(liason_north,liason_south)
                    .flatMap(x -> x.stream())
                    .collect(Collectors.toList());
			return clientuser;
		}
		return clientuser;
	}
	
	@GetMapping("/getclient")
	public List<Company> getclient() {
		//finding currently logged in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean RelationHOD = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_RELATION_HEAD"));
		boolean LiasonHOD = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_LIASION_HEAD"));
		List<Company> assigned = new ArrayList<>();
		List<Company> allclient = comrepo.findAll();
		List<Company> finalclient=new ArrayList<>();
		List<User> clientuser = new ArrayList <User>();
		if(RelationHOD) {
			clientuser=userrepo.findAllUsers2(12);
			for(int i=0;i<clientuser.size();i++) {
				List<Integer>c_a=clientuser.get(i).getCompany();
				for(int j=0; j<c_a.size();j++) {
					Long companyId=Long.valueOf(c_a.get(j));
					Company client =comrepo.findById(companyId).orElseThrow();
					System.out.println(client);
					assigned.add(client);
					
					}
				}
			}
		if(LiasonHOD) {
			 List<User> liason_north = userrepo.findAllUsers2(13);
			 List<User> liason_south = userrepo.findAllUsers2(14);
			 clientuser = Stream.of(liason_north,liason_south).flatMap(x -> x.stream()).collect(Collectors.toList());
			 for(int i=0;i<clientuser.size();i++) {
					List<Integer>c_a=clientuser.get(i).getCompany();
					for(int j=0; j<c_a.size();j++) {
						Long companyId=Long.valueOf(c_a.get(j));
						Company client =comrepo.findById(companyId).orElseThrow();
						assigned.add(client);
						
						}
					}
			}
		
		//finding difference between assigned client and all client
		finalclient = allclient.stream()
		        .filter(company -> !assigned.contains(company))
		        .collect(Collectors.toList());
		
		return finalclient;	
	}
	
	@PostMapping(value={"/assign/{UserId}"})
	public ResponseEntity<?> assign(@PathVariable(value ="UserId") Long UserId,
			             @RequestParam(value="jsondata") String jsondata) throws JsonMappingException, JsonProcessingException {
		User user = objectMapper.readValue(jsondata,User.class);
		List<Integer>company=user.getCompany();
		User reportee=userrepo.findById(UserId).orElse(null);
		reportee.setCompany(company);
		userrepo.save(reportee);
		return  ResponseEntity.ok(new MessageResponse("Successfully!"));
	}

	//unassign client
	@GetMapping("/unassign/{userId}")
	   public ResponseEntity<?> unassign(@PathVariable Long userId)throws ResourceNotFoundException {
		 List<Integer> emptylist=new ArrayList<>();
		 User reportee=userrepo.findById(userId).orElseThrow();
		  reportee.setCompany(emptylist);
		  userrepo.save(reportee);
		 return ResponseEntity.ok(new MessageResponse("Unassigned Successfully!"));
			
	}
	
	@GetMapping("/getreporteebyId/{userId}")
	public User getreporteebyId(@PathVariable Long userId) {
		User reportee=userrepo.findById(userId).orElseThrow();
		return reportee;	
	}
	
  //get assigned client
	@GetMapping("/getassignedclient")
	public List<Company> getassignedclient() {
		//finding currently logged in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean RelationHOD = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_RELATION_HEAD"));
		boolean LiasonHOD = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_LIASION_HEAD"));
		List<Company> assigned = new ArrayList<>();
		List<Company> allclient = comrepo.findAll();
		List<Company> finalclient=new ArrayList<>();
		List<User> clientuser = new ArrayList <User>();
		if(RelationHOD) {
			clientuser=userrepo.findAllUsers2(12);
			for(int i=0;i<clientuser.size();i++) {
				List<Integer>c_a=clientuser.get(i).getCompany();
				for(int j=0; j<c_a.size();j++) {
					Long companyId=Long.valueOf(c_a.get(j));
					Company client =comrepo.findById(companyId).orElseThrow();
					System.out.println(client);
					assigned.add(client);
					
					}
				}
			}
		if(LiasonHOD) {
			 List<User> liason_north = userrepo.findAllUsers2(13);
			 List<User> liason_south = userrepo.findAllUsers2(14);
			 clientuser = Stream.of(liason_north,liason_south).flatMap(x -> x.stream()).collect(Collectors.toList());
			 for(int i=0;i<clientuser.size();i++) {
					List<Integer>c_a=clientuser.get(i).getCompany();
					for(int j=0; j<c_a.size();j++) {
						Long companyId=Long.valueOf(c_a.get(j));
						Company client =comrepo.findById(companyId).orElseThrow();
						assigned.add(client);
						
						}
					}
			}
		
		
		
		return assigned;	
	}
		
}
