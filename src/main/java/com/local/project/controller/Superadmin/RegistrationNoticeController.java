package com.local.project.controller.Superadmin;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
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
import com.local.project.jwts.MessageResponse;
import com.local.project.jwts.SignupRequest;
import com.local.project.message.NotFoundException;
import com.local.project.model.Auth.Role;
import com.local.project.model.Auth.User;
import com.local.project.repository.Auth.RoleRepository;
import com.local.project.repository.Auth.UserRepository;
import com.local.project.service.Auth.AuthorDataService;
import com.local.project.service.Auth.UserDetailsServiceImpl;

import net.bytebuddy.utility.RandomString;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reg")
public class RegistrationNoticeController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
    private JavaMailSender mailSender; 
	
	@Autowired
    UserDetailsServiceImpl userservice;
	
	@Autowired
	AuthorDataService authorservice;
	
	/*Add TEAM*/
	@PostMapping("/add")
	public ResponseEntity<?> addCDM(@Valid @RequestBody SignupRequest signUpRequest) throws MessagingException, UnsupportedEncodingException{
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		Long createdby = authorservice.findCurrentUser();
		String password = RandomString.make(8);
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
			Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT_USER);
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "registration head":
					Role adminRole = roleRepository.findByName(ERole.ROLE_REGISTRATION_HEAD);
					roles.add(adminRole);

					break;
				case "registration team":
					Role subadminRole = roleRepository.findByName(ERole.ROLE_REGISTRATION_TEAM);
					roles.add(subadminRole);

					break;
				case "notice head":
					Role dept_hodRole = roleRepository.findByName(ERole.ROLE_NOTICES_HEAD);
					roles.add(dept_hodRole);

					break;
				case "notice team":
					Role dept_reporteeRole = roleRepository.findByName(ERole.ROLE_NOTICES_TEAM);
					roles.add(dept_reporteeRole);

					break;
				}
			});
		}
		

		user.setRoles(roles);
			
			
		//Send mail to new user
//		MimeMessage message = mailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(message);
//		
//		String from = "ashwathy.h@complyindia.com";
//		String to = user.getEmail();
//		String portal_name = "Complyindia";
//		String mailContent = "<h1>Welcome to Our Portal " + portal_name + "</h1></br><h4>Username:" + to +"</h4></br><h4>Password:" + password +"</h4></br>";
//		 
//		helper.setSubject("Account Activation Mail");
//		helper.setFrom(from,"Complyindia");
//		helper.setTo(to);
//		helper.setText(mailContent,true);
//		 
//		mailSender.send(message);
		
		userRepository.save(user);	

		return ResponseEntity.ok(new MessageResponse("User Added successfully!"));
	}
	
	/*Delete Old RelationsHead*/
	@DeleteMapping("/delete/{id}")
	  public ResponseEntity<?> deleteTutorial(@PathVariable("id") long id) {
			
	    	userRepository.deleteById(id);
	    	return ResponseEntity.ok(new MessageResponse("Deleted Successfully!"));	    
	  }
	
	
	/*Fetch Relations Head*/
	@GetMapping("/get-reghead")
	public ResponseEntity<User> get() {
		try {		
				int roleName = 15; 
				User user = userservice.listUsers(roleName);
				return new ResponseEntity<User>(user,HttpStatus.OK);
			}
		catch(NoSuchElementException e) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}	
	} 	
	
	/*Update Relations Head*/
	@PutMapping("/update-hod/{id}")
    public ResponseEntity<?> updateNote(@PathVariable(value = "id") Long Id,
                           @Valid @RequestBody User userDetails) throws NotFoundException {

    	Long updatedby = authorservice.findCurrentUser();
    	
    	User users = userRepository.findById(Id)
			                .orElseThrow(() -> new NotFoundException(Id));		
    			users.setUsername(userDetails.getUsername());
		    	users.setUpdated_by(updatedby);
		    	users.setUpdated_at(LocalDateTime.now());
		    	userRepository.save(users);
			
			return ResponseEntity.ok(new MessageResponse("Username Updated successfully"));

    }
	
	/*Get Relations Team*/
	@GetMapping("/get-regteam")
    public List<User> getAllNotes() {
		int roleName = 16;
        return userservice.getRelationsHead(roleName);
    }
	
	
	/*Get Liasion Head*/
	@GetMapping("/get-noticehead")
	public ResponseEntity<User> getLiasionHead() {
		try {		
				int roleName = 17; 
				User user = userservice.listUsers(roleName);
				return new ResponseEntity<User>(user,HttpStatus.OK);
			}
		catch(NoSuchElementException e) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}	
	}
	
	
	/*Get Liasion Team-North*/
	@GetMapping("/get-noticeteam")
    public List<User> getAllNTeam() {
		int roleName = 18;
        return userservice.getRelationsHead(roleName);
    }
	
	
}
