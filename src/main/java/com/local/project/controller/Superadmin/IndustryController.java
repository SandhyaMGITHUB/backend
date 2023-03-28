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
import com.local.project.model.Superadmin.Industry;
import com.local.project.repository.Superadmin.IndustryRepository;
import com.local.project.service.Auth.AuthorDataService;
import com.local.project.service.Superadmin.IndustryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/industry")
public class IndustryController {

	@Autowired
	IndustryRepository repository;
	
	@Autowired
	IndustryService service;
	
	@Autowired
	AuthorDataService authorservice;
	
	/*add new industry*/
	@PostMapping("/add")
	public ResponseEntity<?> addIndustry(@Valid @RequestBody Industry industry){
		
		Long createdby = authorservice.findCurrentUser();
		industry.setCreated_by(createdby);
		industry.setUpdated_by(createdby);
		industry.setCreated_at(LocalDateTime.now());
		industry.setUpdated_at(LocalDateTime.now());
		repository.save(industry);
		return ResponseEntity.ok(new MessageResponse("Industry added successfully"));
		
	}
	
	/*get Industry based on id*/	
	@GetMapping("/get/{id}")
	public ResponseEntity<Industry> get(@PathVariable Long id) {
		try {		
				Industry indus = service.get(id);
				return new ResponseEntity<Industry>(indus,HttpStatus.OK);
			}
		catch(NoSuchElementException e) {
				return new ResponseEntity<Industry>(HttpStatus.NOT_FOUND);
			}	
		
	}
	
	/*get all industries*/	
	@GetMapping("/get-all")
    public List<Industry> getAllNotes() {
        return repository.findAll();
    }
	
	// Update  industries
    @PutMapping("/modify/{industry_id}")
    public ResponseEntity<?> updateNote(@PathVariable(value = "industry_id") Long Id,
                           @Valid @RequestBody Industry indDetails) throws NotFoundException {

    	Long updatedby = authorservice.findCurrentUser();
    	
    	Industry industry = repository.findById(Id)
			                .orElseThrow(() -> new NotFoundException(Id));		
    	industry.setIndustry_name(indDetails.getIndustry_name());
    	industry.setUpdated_by(updatedby);
    	industry.setUpdated_at(LocalDateTime.now());
    	repository.save(industry);
			
		return ResponseEntity.ok(new MessageResponse("Industry Updated successfully"));

    }
    
    @DeleteMapping("/delete/{id}")
	  public ResponseEntity<?> delete(@PathVariable("id") long id) {
  			repository.deleteById(id);
	    	return ResponseEntity.ok(new MessageResponse("Industry Deleted successfully"));
	  }
}
