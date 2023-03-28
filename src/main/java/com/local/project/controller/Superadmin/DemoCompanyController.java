package com.local.project.controller.Superadmin;

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
@RequestMapping("/api/demo")
public class DemoCompanyController {
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
	
	ObjectMapper objectMapper = new ObjectMapper();

	
	@PostMapping(value="/add-no-image")
	public ResponseEntity<?> addCompanynoimage(@RequestParam(value="jsondata")String jsondata) throws IOException{
		
		Company company1 = objectMapper.readValue(jsondata,Company.class);
		Long createdby = authorservice.findCurrentUser();
		company1.setCreated_by(createdby);
		company1.setUpdated_by(createdby);
		company1.setCreated_at(LocalDateTime.now());
		company1.setUpdated_at(LocalDateTime.now());
		company1.setUpload_dir("");
		company1.setFile_name("");
		company1.setDocument_type("");
		companyRepository.save(company1);
		Company savedcompany=companyRepository.save(company1);
		List<Acts> returnString=companyservice.getcompanyacts(savedcompany);
		
		company1.setActs(returnString);
		companyRepository.save(company1);
		return ResponseEntity.ok(new MessageResponse("Company Data added successfully"));
	}
	
	
	
}
