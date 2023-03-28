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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.local.project.jwts.MessageResponse;
import com.local.project.message.NotFoundException;
import com.local.project.model.Superadmin.BusinessArea;
import com.local.project.repository.Superadmin.BusinessAreaRepository;
import com.local.project.service.Auth.AuthorDataService;
import com.local.project.service.Superadmin.BusinessAreaService;

import org.springframework.web.bind.annotation.PathVariable;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/businessarea")
public class BusinessAreaController {
	
	@Autowired
	BusinessAreaRepository arearepository;
	
	@Autowired
	BusinessAreaService service;
	
	@Autowired
	AuthorDataService authorservice;

	/*create a new businessarea*/
	@PostMapping("/add")
	public ResponseEntity<?> addBusinessArea(@Valid @RequestBody BusinessArea area){
		
		Long createdby = authorservice.findCurrentUser();
		area.setCreated_by(createdby);
		area.setUpdated_by(createdby);
		area.setCreated_at(LocalDateTime.now());
		area.setUpdated_at(LocalDateTime.now());
		arearepository.save(area);
		return ResponseEntity.ok(new MessageResponse("BusinessArea added successfully"));
	}

	/*get area based on id*/	
	@GetMapping("/get/{id}")
	public ResponseEntity<BusinessArea> get(@PathVariable Long id) {
		try {		
				BusinessArea product = service.get(id);
				return new ResponseEntity<BusinessArea>(product,HttpStatus.OK);
			}
		catch(NoSuchElementException e) {
				return new ResponseEntity<BusinessArea>(HttpStatus.NOT_FOUND);
			}	
		
	}

	/*get all areas*/	
	@GetMapping("/get-all")
    public List<BusinessArea> getAllNotes() {
        return arearepository.findAll();
    }
	
	// Update a Note
    @PutMapping("/modify/{business_area_id}")
    public ResponseEntity<?> updateNote(@PathVariable(value = "business_area_id") Long bookId,
                           @Valid @RequestBody BusinessArea bookDetails) throws NotFoundException {

//    	try {
    		Long updatedby = authorservice.findCurrentUser();
	    	BusinessArea book = arearepository.findById(bookId)
			                .orElseThrow(() -> new NotFoundException(bookId));		
			    	book.setBusiness_area_name(bookDetails.getBusiness_area_name());
			    	book.setUpdated_by(updatedby);
			    	book.setUpdated_at(LocalDateTime.now());
			
			    	arearepository.save(book);
			
			return ResponseEntity.ok(new MessageResponse("BusinessArea Updated successfully"));
//    	}catch (Exception e) {
//	    	return ResponseEntity.ok(new MessageResponse("BusinessArea could not be updated"));
//	    }
    }

	
	@DeleteMapping("/delete-area/{id}")
	  public ResponseEntity<?> deleteTutorial(@PathVariable("id") long id) {
//	    try {
	    	arearepository.deleteById(id);
	    	return ResponseEntity.ok(new MessageResponse("BusinessArea Deleted successfully"));
//	    } catch (Exception e) {
//	    	return ResponseEntity.ok(new MessageResponse("BusinessArea could not be deleted"));
//	    }
	  }
}
