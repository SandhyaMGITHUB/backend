package com.local.project.controller.CDM;
//package com.local.complyindia.controller.CDM;
//
//import javax.transaction.Transactional;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.local.complyindia.jwts.SignupRequest;
//import com.local.complyindia.model.Auth.User;
//import com.local.complyindia.model.Superadmin.BusinessArea;
//import com.local.complyindia.repository.Auth.RoleRepository;
//import com.local.complyindia.repository.Auth.UserRepository;
//import com.local.complyindia.service.Auth.AuthorDataService;
//
//
//@Service
//public class ViewerService {
//	@Autowired
//	RoleRepository roleRepository;
//	
//	@Autowired
//	UserRepository userRepository;
//	
//	
//	@Autowired
//	private JavaMailSender mailSender;
//	
//	@Autowired
//	PasswordEncoder encoder;
//	
//	@Autowired
//	AuthorDataService authorservice;
//	
//	
//	@Transactional
//     public void addviewer(@Valid User user, Long companyId) {
//		user.setCompany_id(companyId);
//		userRepository.save(user);
//	}
//
//}
