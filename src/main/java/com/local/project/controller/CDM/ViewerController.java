package com.local.project.controller.CDM;
//package com.local.complyindia.controller.CDM;
//
//import java.io.UnsupportedEncodingException;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.mail.MessagingException;
//import javax.validation.Valid;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.local.complyindia.enumeration.ERole;
//import com.local.complyindia.jwts.MessageResponse;
//import com.local.complyindia.model.Auth.Role;
//import com.local.complyindia.model.Auth.User;
//import com.local.complyindia.model.CDM.CompanyAddress;
//import com.local.complyindia.model.Superadmin.Acts;
//import com.local.complyindia.repository.Auth.RoleRepository;
//import com.local.complyindia.repository.Auth.UserRepository;
//import com.local.complyindia.service.Auth.AuthorDataService;
//
//import net.bytebuddy.utility.RandomString;
//@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RequestMapping("/api/company-user")
//public class ViewerController {
//	
//	@Autowired
//	RoleRepository roleRepository;
//	
//	@Autowired
//	ViewerService viewservice;
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
//	@PostMapping("/addviewer/{companyId}")
//	public List<Integer> addopshead(@PathVariable( value="companyId" )Long companyId ,@Valid @RequestBody ViewerForm viewform) throws MessagingException, UnsupportedEncodingException {
//		//email already present just add address list
//		List<Integer> newlist1= new ArrayList<Integer>();
//		
//	
//		return newlist1;
//		
//	}
//
//	//get viewer
//	@GetMapping("/getviewer/{companyId}")
//	public List<?> getviewer(@PathVariable (value="companyId") Long companyId){
//		List<?> newlist1= new ArrayList<Integer>();
//		List<?>addressid=new ArrayList<Integer>();
//		List<?>finallist=new ArrayList<Integer>();
//		newlist1=userRepository.findByCompanyId(companyId);
//		for(int i=0;i<newlist1.size(); i++){
//			Long x = (Long) newlist1.get(i);
//		    addressid = userRepository.findCompanyAddressByUserId(x);
//		    
//		    
//		    
//		    
//		}
//		
//		
//		return  addressid;
//	}
//
//}
