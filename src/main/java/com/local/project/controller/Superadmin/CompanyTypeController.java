package com.local.project.controller.Superadmin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.local.project.jwts.MessageResponse;
import com.local.project.message.NotFoundException;
import com.local.project.model.Superadmin.CompanyType;
import com.local.project.repository.Superadmin.CompanyTypeRepository;
import com.local.project.service.Auth.AuthorDataService;
import com.local.project.service.Superadmin.CompanyTypeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/companytype")
public class CompanyTypeController {

	@Autowired
	CompanyTypeRepository comptypeRepository;
	
	@Autowired
	CompanyTypeService service;
	
	@Autowired
	AuthorDataService authorservice;
		
	 
	/*create a new companytype*/
	@PostMapping("/add")
	public ResponseEntity<?> addCompanyType(@Valid @RequestBody CompanyType comptype){
		
		Long createdby = authorservice.findCurrentUser();
		comptype.setCreated_by(createdby);
		comptype.setUpdated_by(createdby);
		comptype.setCreated_at(LocalDateTime.now());
		comptype.setUpdated_at(LocalDateTime.now());
		comptypeRepository.save(comptype);
		return ResponseEntity.ok(new MessageResponse("CompanyType added successfully"));
		
	}
	
	/*get companytype based on id*/	
	@GetMapping("/get/{id}")
	public ResponseEntity<CompanyType> get(@PathVariable Long id) {
		try {		
				CompanyType c_type = service.get(id);
				return new ResponseEntity<CompanyType>(c_type,HttpStatus.OK);
			}
		catch(NoSuchElementException e) {
				return new ResponseEntity<CompanyType>(HttpStatus.NOT_FOUND);
			}	
		
	}
	
	/*get all companytypes*/	
	@GetMapping("/get-all")
    public List<CompanyType> getAllNotes() {
        return comptypeRepository.findAll();
    }
	
	// Update a companytype
    @PutMapping("/modify/{company_type_id}")
    public ResponseEntity<?> updateNote(@PathVariable(value = "company_type_id") Long Id,
                           @Valid @RequestBody CompanyType compDetails) throws NotFoundException {

    	Long updatedby = authorservice.findCurrentUser();
    	
    	CompanyType comptype = comptypeRepository.findById(Id)
			                .orElseThrow(() -> new NotFoundException(Id));		
    				comptype.setCompany_type_name(compDetails.getCompany_type_name());
    				comptype.setUpdated_by(updatedby);
    				comptype.setUpdated_at(LocalDateTime.now());
			    	comptypeRepository.save(comptype);
			
			return ResponseEntity.ok(new MessageResponse("Company Type Updated successfully"));

    }
    
    @DeleteMapping("/delete-type/{id}")
	  public ResponseEntity<?> delete(@PathVariable("id") long id) {
    		comptypeRepository.deleteById(id);
	    	return ResponseEntity.ok(new MessageResponse("Company Type Deleted successfully"));
	  }
}