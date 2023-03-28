package com.local.project.service.CDM;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.local.project.controller.CDM.ViewerForm;
import com.local.project.model.Auth.User;
import com.local.project.repository.Auth.RoleRepository;
import com.local.project.repository.Auth.UserRepository;
import com.local.project.service.Auth.AuthorDataService;

@Service
public class ViewerService {
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	ViewerService viewservice;
	
	@Autowired
	UserRepository userRepository;
	

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	AuthorDataService authorservice;
	
	
//	@Transactional
//	public List<Integer> saveviewer(String Email,Long companyId, Long addressId) {
//		if(userRepository.existsByEmail(Email); 
//		{   User olduser =new User();
//			olduser=userRepository.findByEmail(Email).orElseThrow();
//			List<Integer> ca_list=olduser.getCompanyaddress();
//			List<Integer> newlist= viewform.getCompanyaddress();
//			for(int i = 0; i<ca_list.size(); i++) {
//				newlist.add(ca_list.get(i));
//			
//			}
//			olduser.setCompanyaddress(newlist);
//			userRepository.save(olduser);
//			return newlist;
//		}
//		
//		//if new email add user with company id and company address
//		
//			String password = RandomString.make(8);
//			Long createdby = authorservice.findCurrentUser();
//			User user =new User(viewform.getEmail(),
//					             encoder.encode(password),
//					             viewform.getCompanyaddress(),
//					             viewform.setCreated_by(createdby),
//					             viewform.setUpdated_by(createdby),
//					             viewform.getCreated_at(),
//					             viewform.getUpdated_at()
//					            );
//			
//			
//			Set<Role> roles = new HashSet<>();
//			Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT_USER);
//			roles.add(userRole);
//			user.setRoles(roles);
//			user.setIsenabled(true);
//				
//			userRepository.save(user);
//	}

}
