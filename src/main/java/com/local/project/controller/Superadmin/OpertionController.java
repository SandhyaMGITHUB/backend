package com.local.project.controller.Superadmin;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.local.project.enumeration.ERole;
import com.local.project.jwts.*;
import com.local.project.message.NotFoundException;
import com.local.project.message.ResourceNotFoundException;
import com.local.project.model.Auth.Role;
import com.local.project.model.Auth.User;
import com.local.project.repository.Auth.RoleRepository;
import com.local.project.repository.Auth.UserRepository;
import com.local.project.service.Auth.AuthorDataService;
import com.local.project.service.Auth.UserDetailsServiceImpl;

import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestParam;

import net.bytebuddy.utility.RandomString;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/ops")
public class OpertionController {

	
	@Autowired
	RoleRepository roleRepository;
	
//	@Autowired
//	GenerateSecurePassword password;
	
	@Autowired
	UserRepository userRepository;
	
//	@Autowired
//	UserServiceImpl userService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	AuthorDataService authorservice;
	
	/*Update Relations Head*/
	@PutMapping("/update-hod/{id}")
	    public ResponseEntity<?> updateNote(@PathVariable(value = "id") Long Id,
	                           @Valid @RequestBody SignupRequest signUpRequest) {

	   
	    User users = userRepository.findById(Id)
	               .orElseThrow();
	    users.setUsername(signUpRequest.getUsername());
	    userRepository.save(users);
	    return ResponseEntity.ok(new MessageResponse("Username Updated successfully"));

	    }
	
	
	//get user by designation
	@GetMapping("/getbydesignation/{designation}")
		public User getbydesigantion(@PathVariable String designation) {
		return userRepository.findByDesignation(designation);
	}
	
	
	//get ops
	@GetMapping("/get-operation-head")
		public ResponseEntity<User> get() {
		try {
				int roleName = 6;
				User user = userRepository.findUserByRole(roleName);
				return new ResponseEntity<User>(user,HttpStatus.OK);
			}
		catch(NoSuchElementException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//delete user
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<?> deletePost(@PathVariable Long userId)throws ResourceNotFoundException {
	       return userRepository.findById(userId).map(user -> {
	           userRepository.delete(user);
	           return ResponseEntity.ok().build();
	       }).orElseThrow(() -> new ResourceNotFoundException("userid " + userId + " not found"));
	}
	
	
	//adding ops and pc lc manager
	@PostMapping("/addops")
	public ResponseEntity<?> addopshead(@Valid @RequestBody SignupRequest signUpRequest) throws MessagingException, UnsupportedEncodingException {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("NOTE: User Already Exist,Please Delete And Add IN OPS "));
		}
		else {
			String password = RandomString.make(8);
			Long createdby = authorservice.findCurrentUser();
			// Create new user's account
			User user = new User(signUpRequest.getUsername(), 
				 signUpRequest.getEmail(),
				 encoder.encode(password),
				 signUpRequest.setisEnabled(true),
				 signUpRequest.getDesignation(),
				 signUpRequest.setCreated_by(createdby),
				 signUpRequest.setUpdated_by(createdby),
				 signUpRequest.getCreated_at(),
				 signUpRequest.getUpdated_at()
				 );

			Set<String> strRoles = signUpRequest.getRole();
			Set<Role> roles = new HashSet<>();
			if (strRoles == null) {
				Role userRole = roleRepository.findByName(ERole.ROLE_OPS_HEAD);
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
						case "opsHead":
						Role adminRole = roleRepository.findByName(ERole.ROLE_OPS_HEAD);
						roles.add(adminRole);
					
						break;
						case "manager":
						Role modRole = roleRepository.findByName(ERole.ROLE_OPS_MANAGER);
						roles.add(modRole);
					
						break;
					}
				});
			}
	
	
			user.setRoles(roles);
			user.setHOD(false);
			userRepository.save(user);
//	MimeMessage message = mailSender.createMimeMessage();
//	MimeMessageHelper helper = new MimeMessageHelper(message);
//	String from = "ashwathy.h@complyindia.com";
//	String to = user.getEmail();
//	String portal_name = "Complyindia";
//	String mailContent = "<h1>Welcome to Our Portal " + portal_name + "</h1></br><h4>Username:" + to +"</h4></br><h4>Password:" + password +"</h4></br>";
//	helper.setSubject("Account Activation Mail");
//	helper.setFrom(from,"Complyindia");
//	helper.setTo(to);
//	helper.setText(mailContent,true);
//	mailSender.send(message);

			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}
	}
	@PostMapping("/addteam")
	    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) throws MessagingException, UnsupportedEncodingException {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("NOTE: User Already Exist,Please Delete And Add IN OPS "));
		}
		String password = RandomString.make(8);
		Long createdby = authorservice.findCurrentUser();
		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
			 signUpRequest.getEmail(),
			 encoder.encode(password),
			 signUpRequest.setisEnabled(true),
			 signUpRequest.getDesignation(),
			 signUpRequest.setCreated_by(createdby),
			 signUpRequest.setUpdated_by(createdby),
			 signUpRequest.getCreated_at(),
			 signUpRequest.getUpdated_at()
			 );


		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_OPS_HEAD);
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "pfteam":
					Role adminRole = roleRepository.findByName(ERole.ROLE_OPS_PF_TEAM);
					roles.add(adminRole);
					break;
				case "esiteam":
					Role modRole = roleRepository.findByName(ERole.ROLE_OPS_ESI_TEAM);
					roles.add(modRole);
					break;
				case "lwtteam":
					Role LWTRole = roleRepository.findByName(ERole.ROLE_OPS_LWT_TEAM);
					roles.add(LWTRole);
					break;
				case "lcteam":
					Role LCRole = roleRepository.findByName(ERole.ROLE_OPS_LC_TEAM);
					roles.add(LCRole);
					break;
				case "clrateam":
					Role CLRARole = roleRepository.findByName(ERole.ROLE_OPS_CLRA_TEAM);
					roles.add(CLRARole);
					break;
				}
			});
		}
		user.setIsteamLead(false);
		user.setRoles(roles);
		user.setHOD(false);
		userRepository.save(user);
//	MimeMessage message = mailSender.createMimeMessage();
//	MimeMessageHelper helper = new MimeMessageHelper(message);
//	String from = "ashwathy.h@complyindia.com";
//	String to = user.getEmail();
//	String portal_name = "Complyindia";
//	String mailContent = "<h1>Welcome to Our Portal " + portal_name + "</h1></br><h4>Username:" + to +"</h4></br><h4>Password:" + password +"</h4></br>";
//	helper.setSubject("Account Activation Mail");
//	helper.setFrom(from,"Complyindia");
//	helper.setTo(to);
//	helper.setText(mailContent,true);
//	mailSender.send(message);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	
	//adding team lead
	@PostMapping("/addteamlead")
		public ResponseEntity<?> addingteamlead(@Valid @RequestBody SignupRequest signUpRequest) throws MessagingException, UnsupportedEncodingException {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("NOTE: User Already Exist,Please Delete And Add IN OPS "));
		}
		else {
			String password = RandomString.make(8);
			Long createdby = authorservice.findCurrentUser();
		// Create new user's account
			User user = new User(signUpRequest.getUsername(), 
					 signUpRequest.getEmail(),
					 encoder.encode(password),
					 signUpRequest.setisEnabled(true),
					 signUpRequest.getDesignation(),
					 signUpRequest.setCreated_by(createdby),
					 signUpRequest.setUpdated_by(createdby),
					 signUpRequest.getCreated_at(),
					 signUpRequest.getUpdated_at()
					 );

			Set<String> strRoles = signUpRequest.getRole();
			Set<Role> roles = new HashSet<>();
			if (strRoles == null) {
				Role userRole = roleRepository.findByName(ERole.ROLE_OPS_HEAD);
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
				switch (role) {
				case "pfteam":
				Role adminRole = roleRepository.findByName(ERole.ROLE_OPS_PF_TEAM);
				roles.add(adminRole);
				break;
				case "esiteam":
				Role modRole = roleRepository.findByName(ERole.ROLE_OPS_ESI_TEAM);
				roles.add(modRole);
				break;
				case "lwtteam":
				Role LWTRole = roleRepository.findByName(ERole.ROLE_OPS_LWT_TEAM);
				roles.add(LWTRole);
				break;
				case "lcteam":
				Role LCRole = roleRepository.findByName(ERole.ROLE_OPS_LC_TEAM);
				roles.add(LCRole);
				break;
				case "clrateam":
				Role CLRARole = roleRepository.findByName(ERole.ROLE_OPS_CLRA_TEAM);
				roles.add(CLRARole);
				break;
			}
			});
		}
		
		user.setRoles(roles);
		user.setIsteamLead(true);
		user.setHOD(false);
		userRepository.save(user);
//	MimeMessage message = mailSender.createMimeMessage();
//	MimeMessageHelper helper = new MimeMessageHelper(message);
//	String from = "ashwathy.h@complyindia.com";
//	String to = user.getEmail();
//	String portal_name = "Complyindia";
//	String mailContent = "<h1>Welcome to Our Portal " + portal_name + "</h1></br><h4>Username:" + to +"</h4></br><h4>Password:" + password +"</h4></br>";
//	helper.setSubject("Account Activation Mail");
//	helper.setFrom(from,"Complyindia");
//	helper.setTo(to);
//	helper.setText(mailContent,true);
//	mailSender.send(message);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}
	}
	
	
	@GetMapping("/teamlist")
	public List<User> list(@RequestParam(value ="roleId") Integer roleId){
		List<User> userlist;
		Integer roleid = roleId;
		userlist= userRepository.findAllUsers2(roleid)
				.stream()
				.filter(task -> task.isIsteamLead()==false)
				.collect(Collectors.toList());
		return userlist;
	}
}
