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
import com.local.project.model.Superadmin.RegistrationCompany;
import com.local.project.repository.Superadmin.RegistrationCompanyRepository;
import com.local.project.service.Auth.AuthorDataService;
import com.local.project.service.Superadmin.RegistrationCompanyService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/regcomp")
public class RegistrationCompanyController {

	@Autowired
	RegistrationCompanyRepository regcompRepository;
	
	@Autowired
	RegistrationCompanyService service;
	
	@Autowired
	AuthorDataService authorservice;
	
	
	/*create a new reg company type*/
	@PostMapping("/add")
	public ResponseEntity<?> addRegComp(@Valid @RequestBody RegistrationCompany regcomp){
		
		Long createdby = authorservice.findCurrentUser();
		regcomp.setCreated_by(createdby);
		regcomp.setUpdated_by(createdby);
		regcomp.setCreated_at(LocalDateTime.now());
		regcomp.setUpdated_at(LocalDateTime.now());
		regcompRepository.save(regcomp);
		return ResponseEntity.ok(new MessageResponse("Registration Company added successfully"));
		
	}
	
	/*get regcomp based on id*/	
	@GetMapping("/get/{id}")
	public ResponseEntity<RegistrationCompany> get(@PathVariable Long id) {
		try {		
			RegistrationCompany regcomp = service.get(id);
				return new ResponseEntity<RegistrationCompany>(regcomp,HttpStatus.OK);
			}
		catch(NoSuchElementException e) {
				return new ResponseEntity<RegistrationCompany>(HttpStatus.NOT_FOUND);
			}			
	}
	
	/*get all locationtypes*/	
	@GetMapping("/get-all")
    public List<RegistrationCompany> getAllNotes() {
        return regcompRepository.findAll();
    }
	
	// Update a location type
    @PutMapping("/modify/{reg_comp_id}")
    public ResponseEntity<?> updateNote(@PathVariable(value = "reg_comp_id") Long Id,
                           @Valid @RequestBody RegistrationCompany regcompDetails) throws NotFoundException {

    	Long updatedby = authorservice.findCurrentUser();
    	
    	RegistrationCompany regdata = regcompRepository.findById(Id)
			                .orElseThrow(() -> new NotFoundException(Id));		
    	regdata.setReg_comp_name(regcompDetails.getReg_comp_name());
    	regdata.setUpdated_by(updatedby);
    	regdata.setUpdated_at(LocalDateTime.now());
    	regcompRepository.save(regdata);
			
			return ResponseEntity.ok(new MessageResponse("Registration Company Updated successfully"));

    }
    
    @DeleteMapping("/delete/{id}")
	  public ResponseEntity<?> delete(@PathVariable("id") long id) {
    		regcompRepository.deleteById(id);
	    	return ResponseEntity.ok(new MessageResponse("Registration Company Deleted successfully"));
	  }
}
