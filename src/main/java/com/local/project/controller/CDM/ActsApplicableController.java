package com.local.project.controller.CDM;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.local.project.jwts.MessageResponse;
import com.local.project.message.ResourceNotFoundException;
import com.local.project.model.CDM.CompanyAddress;
import com.local.project.model.Superadmin.Acts;
import com.local.project.model.Superadmin.BusinessArea;
import com.local.project.model.Superadmin.Company;
import com.local.project.repository.Auth.UserRepository;
import com.local.project.repository.CDM.CompanyAddressRepository;
import com.local.project.repository.Superadmin.CityRepository;
import com.local.project.repository.Superadmin.CompanyRepository;
import com.local.project.repository.Superadmin.StateRepository;
import com.local.project.service.Auth.AuthorDataService;
import com.local.project.service.Superadmin.companyservice;

import net.bytebuddy.utility.RandomString;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class ActsApplicableController {
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
	
	
   //get applicable acts
	@GetMapping("/actsapplicable/{companyId}")
	public List<Acts> actsapplicable(@PathVariable(value="companyId") Long companyId){
		List <Acts> applicableacts=new ArrayList<Acts>();
		//get company by id
		Company c = compservice.get(companyId);
		//get company acts
		List<Acts> c_acts=c.getActs();
		//get company address of company
		List<CompanyAddress> ca=c.getCompanyaddress();
		
		//two empty list
		List<Acts>ca_acts=new ArrayList<Acts>();
		List<Acts>listofacts=new ArrayList<Acts>();
		
		//finding common acts from company and company address
		for(int i=0; i<ca.size(); i++) {
			CompanyAddress calist=ca.get(i);
			ca_acts=calist.getActs();
			listofacts.addAll(ca_acts);
			
		}
		 List<Acts> actslist = c_acts.stream()
                 .filter(listofacts::contains)
                 .collect(Collectors
                              .toList());
		
		
		return ca_acts;

	}
	
	
}
