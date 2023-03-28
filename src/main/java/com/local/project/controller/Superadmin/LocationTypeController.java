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
import com.local.project.model.Superadmin.LocationType;
import com.local.project.repository.Superadmin.LocationTypeRepository;
import com.local.project.service.Auth.AuthorDataService;
import com.local.project.service.Superadmin.LocationTypeService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/locationtype")
public class LocationTypeController {

	@Autowired
	LocationTypeRepository loctypeRepository;
	
	@Autowired
	LocationTypeService service;
	
	@Autowired
	AuthorDataService authorservice;
	
	/*create a new location type*/
	@PostMapping("/add")
	public ResponseEntity<?> addLocationType(@Valid @RequestBody LocationType loctype){
		
		Long createdby = authorservice.findCurrentUser();
		loctype.setCreated_by(createdby);
		loctype.setUpdated_by(createdby);
		loctype.setCreated_at(LocalDateTime.now());
		loctype.setUpdated_at(LocalDateTime.now());
		loctypeRepository.save(loctype);
		return ResponseEntity.ok(new MessageResponse("Location Type added successfully"));
		
	}
	
	/*get location type based on id*/	
	@GetMapping("/get/{id}")
	public ResponseEntity<LocationType> get(@PathVariable Long id) {
		try {		
				LocationType c_type = service.get(id);
				return new ResponseEntity<LocationType>(c_type,HttpStatus.OK);
			}
		catch(NoSuchElementException e) {
				return new ResponseEntity<LocationType>(HttpStatus.NOT_FOUND);
			}	
		
	}
	
	/*get all locationtypes*/	
	@GetMapping("/get-all")
    public List<LocationType> getAllNotes() {
        return loctypeRepository.findAll();
    }
	
	// Update a location type
    @PutMapping("/modify/{location_id}")
    public ResponseEntity<?> updateNote(@PathVariable(value = "location_id") Long Id,
                           @Valid @RequestBody LocationType locDetails) throws NotFoundException {

    	Long updatedby = authorservice.findCurrentUser();
    	
    	LocationType loctype = loctypeRepository.findById(Id)
			                .orElseThrow(() -> new NotFoundException(Id));		
    				loctype.setLocation_name(locDetails.getLocation_name());
    				loctype.setUpdated_by(updatedby);
    				loctype.setUpdated_at(LocalDateTime.now());
			    	loctypeRepository.save(loctype);
			
			return ResponseEntity.ok(new MessageResponse("Location Type Updated successfully"));

    }
    
    @DeleteMapping("/delete-type/{id}")
	  public ResponseEntity<?> delete(@PathVariable("id") long id) {
    		loctypeRepository.deleteById(id);
	    	return ResponseEntity.ok(new MessageResponse("Location Type Deleted successfully"));
	  }
}

