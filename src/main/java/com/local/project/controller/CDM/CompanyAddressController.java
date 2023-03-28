package com.local.project.controller.CDM;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.local.project.jwts.MessageResponse;
import com.local.project.message.ResourceNotFoundException;
import com.local.project.model.CDM.CompanyAddress;
import com.local.project.model.Superadmin.Acts;
import com.local.project.model.Superadmin.ActsStates;
import com.local.project.model.Superadmin.Company;
import com.local.project.repository.CDM.CompanyAddressRepository;
import com.local.project.repository.Superadmin.ActStateRepository;
import com.local.project.repository.Superadmin.ActsRepository;
import com.local.project.repository.Superadmin.CityRepository;
import com.local.project.repository.Superadmin.CompanyRepository;
import com.local.project.repository.Superadmin.LocationTypeRepository;
import com.local.project.repository.Superadmin.StateRepository;
import com.local.project.service.Auth.AuthorDataService;
import com.local.project.service.CDM.CompanyAddressSerivces;
import com.local.project.service.Superadmin.CompanyAddressService;
import com.local.project.service.Superadmin.companyservice;


@RestController
@RequestMapping("/api/company-address")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CompanyAddressController {
	
	@Autowired
	CompanyAddressRepository cmpaddress ;
	
	@Autowired
	LocationTypeRepository locrepo;
	
	@Autowired
	StateRepository staterepo;
	
	@Autowired
	CityRepository cityrepo;
	
	@Autowired
	CompanyRepository cmprepo;
	
	@Autowired
	AuthorDataService authorservice;
	
	@Autowired
	companyservice compservice;
	
	@Autowired
	ActStateRepository actstaterepo;
	
	@Autowired
	ActsRepository actsrepo;
	
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	//add address
		@PostMapping(value={"/add/{companyId}"},consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
		public ResponseEntity<?> createcompany(@PathVariable(value ="companyId") Long companyId ,
				                                 @RequestParam(value="stateId") Long stateId, 
				                                 @RequestParam(value="cityId") Long cityId, 
				                                 @RequestParam(value="jsondata")String jsondata)throws ResourceNotFoundException, JsonMappingException, JsonProcessingException
		{
			CompanyAddress companyaddress = objectMapper.readValue(jsondata,CompanyAddress.class);
			cmprepo.findById(companyId).map(company->{
				companyaddress.setCompany(company);
				return cmpaddress.save(companyaddress);
			}).orElseThrow(()->new ResourceNotFoundException("could not add!!"));
			
			 
			 staterepo.findById(stateId).map(state->{
				 companyaddress.setState(state);
				 return cmpaddress.save(companyaddress);
			 }).orElseThrow(()->new ResourceNotFoundException("could not add!!")); 
			 
			 cityrepo.findById(cityId).map(city->{
				 companyaddress.setCity(city);
				 return cmpaddress.save(companyaddress);
			 }).orElseThrow(()->new ResourceNotFoundException("could not add!!"));
			 
			   Long createdby = authorservice.findCurrentUser();
			   companyaddress.setCreated_by(createdby);
			   companyaddress.setUpdated_by(createdby);
			   companyaddress.setCreated_at(LocalDateTime.now());
			   companyaddress.setUpdated_at(LocalDateTime.now());
			   CompanyAddress savedAddress = cmpaddress.save(companyaddress);
			   
			   List<Acts> companyaddressacts=compservice.getcompanyaddressacts(savedAddress);
			   savedAddress.setActs(companyaddressacts);
			   cmpaddress.save(savedAddress);
			   Integer addressId=savedAddress.getCompany_address_id().intValue();
			   compservice.saveclientuser(companyaddress.getMailId(),addressId,companyId);
			   if (cmpaddress.findById(savedAddress.getCompany_address_id()).isPresent())
				   //add user to user table
					 return ResponseEntity.ok(new MessageResponse("Address Added Successfully!"));
				  
				  else return ResponseEntity.badRequest().body(new MessageResponse("Error: Address Could not be Added!"));
		}
	
	@PutMapping(value={"/edit/{companyId}"},consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public  ResponseEntity<?> updatecompany(@PathVariable(value ="companyId") Long companyId,
			                                @RequestParam(value ="addressId") Long addressId ,
			                                 @RequestParam(value="stateId") Long stateId, 
			                                 @RequestParam(value="cityId") Long cityId, 
			                                 @RequestParam(value="jsondata")String jsondata)throws ResourceNotFoundException, JsonMappingException, JsonProcessingException
	{
		CompanyAddress editcompanyaddress = objectMapper.readValue(jsondata,CompanyAddress.class);
		CompanyAddress companyaddress = cmpaddress.findById(addressId).orElseThrow();
		cmprepo.findById(companyId).map(company->{
			companyaddress.setCompany(company);
			return cmpaddress.save(companyaddress);
		}).orElseThrow(()->new ResourceNotFoundException("could not add!!"));
		
		 
		 staterepo.findById(stateId).map(state->{
			 companyaddress.setState(state);
			 return cmpaddress.save(companyaddress);
		 }).orElseThrow(()->new ResourceNotFoundException("could not add!!")); 
		 
		 cityrepo.findById(cityId).map(city->{
			 companyaddress.setCity(city);
			 return cmpaddress.save(companyaddress);
		 }).orElseThrow(()->new ResourceNotFoundException("could not add!!"));
		 
		   Long createdby = authorservice.findCurrentUser();
		   companyaddress.setUpdated_by(createdby);
		   companyaddress.setUpdated_at(LocalDateTime.now());
		   companyaddress.setCompany_location(editcompanyaddress.getCompany_location());
		   companyaddress.setCompany_loc_startdate(editcompanyaddress.getCompany_loc_startdate());
		   companyaddress.setCompany_loc_enddate(editcompanyaddress.getCompany_loc_enddate());
		   
		   companyaddress.setAddress(editcompanyaddress.getAddress());
		   companyaddress.setCity_office(1);
		   companyaddress.setCountry_office(1);
		   companyaddress.setState_office(1);
		   
		   companyaddress.setMailId(editcompanyaddress.getMailId());
		   
		   companyaddress.setCompany_staff_male(editcompanyaddress.getCompany_staff_male());
		   companyaddress.setCompany_staff_female(editcompanyaddress.getCompany_staff_female());
		   companyaddress.setCompany_staff_apprentices(editcompanyaddress.getCompany_staff_apprentices());
		   companyaddress.setCompany_staff_contract(editcompanyaddress.getCompany_staff_contract());
		   
		   companyaddress.setLocation_types(editcompanyaddress.getLocation_types());
		   
		   
		   
		   //saving
		   CompanyAddress savedAddress = cmpaddress.save(companyaddress);
		   List<Acts> companyaddressacts=compservice.getcompanyaddressacts(savedAddress);
			   compservice.saveclientuser(companyaddress.getMailId(),addressId.intValue(),companyId);
			  if (cmpaddress.findById(savedAddress.getCompany_address_id()).isPresent())
				  return ResponseEntity.ok(new MessageResponse("Address Updated Successfully!"));
			  
			  else return ResponseEntity.badRequest().body(new MessageResponse("Error: Address Could not be Updated!"));
		
	}
	@GetMapping("/getaddressbyId/{addressId}")
    public ResponseEntity <CompanyAddress > getAddressById(@PathVariable Long addressId) throws ResourceNotFoundException {
        CompanyAddress cmp = cmpaddress.findById(addressId)
            .orElseThrow();
        return ResponseEntity.ok(cmp);
    }
	

	@GetMapping("/getaddress")
    public List<CompanyAddress> getAllCompany() {
        return cmpaddress.findAll();
    }


}
