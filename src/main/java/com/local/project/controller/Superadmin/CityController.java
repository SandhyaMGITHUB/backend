package com.local.project.controller.Superadmin;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.local.project.jwts.MessageResponse;
import com.local.project.message.ResourceNotFoundException;
import com.local.project.model.Superadmin.BusinessArea;
import com.local.project.model.Superadmin.City;
import com.local.project.repository.Superadmin.CityRepository;
import com.local.project.repository.Superadmin.StateRepository;
import com.local.project.service.Auth.AuthorDataService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/city")
public class CityController {
	
	@Autowired 
	StateRepository staterepo;
	
	@Autowired
	CityRepository cityrepo;
	
	@Autowired
	AuthorDataService authorservice;
	
	
	@GetMapping("/saycity")
	public String saycity() {
		return "cty";
	}

	 @PostMapping("/addcity")
	    public ResponseEntity<?> createCity(@RequestParam (value = "stateId") Long stateId,@Valid @RequestBody City city)throws ResourceNotFoundException {
//		 if (cityrepo.existsByCity_name(city.getCity_name())) {
//				return ResponseEntity
//						.badRequest()
//						.body(new MessageResponse("Error: City Already Exits!"));
//			}
	        return staterepo.findById(stateId).map(state -> {
	            city.setState(state);
	            Long createdby = authorservice.findCurrentUser();
	            city.setCreated_by(createdby);
	            city.setUpdated_by(createdby);
	            city.setCreated_at(LocalDateTime.now());
	            city.setUpdated_at(LocalDateTime.now());
	            cityrepo.save(city);
	            return  ResponseEntity.ok(new MessageResponse("City Added Successfully!"));
	        }).orElseThrow(() -> new ResourceNotFoundException("State Not Found !!"));
	    }
	 
	 @GetMapping("/getcity")
	 public List<City> getcity(){
		 return cityrepo.findAll();

//		 return cityrepo.findAll(Sort.by(Sort.Direction.ASC,"city_name"));
	 }
	 
	 @GetMapping("/getcitybystate")
	 public List <City> getcityByStateId(@RequestParam (value = "stateId") Long stateId) {
	        return cityrepo.findByStateId(stateId);
	    }
	 
//	 @GetMapping("/getcitybystate/{stateId}")
//	 public List <City> getcityByStateId(@PathVariable (value = "stateId") Long stateId) {
//	        return cityrepo.findByStateId(stateId);
//	    }
	 
	 @PutMapping("/state/{stateId}/city/{cityId}")
	    public ResponseEntity<MessageResponse> updatecity(@PathVariable (value = "stateId") Long stateId, @PathVariable (value = "cityId") Long cityId,
	                                 @Valid @RequestBody City cityRequest)throws ResourceNotFoundException  {
//	        if(!staterepo.existsById(newstateid)) {
//	            throw new ResourceNotFoundException("CityId " + stateId + " not found");
//	        }

	        return cityrepo.findById(cityId).map(city -> {
//	        	city.setState(cityRequest.);
	            city.setCity_name(cityRequest.getCity_name());
	            cityrepo.save(city);
	            return  ResponseEntity.ok(new MessageResponse("City updated Successfully!"));
	        }).orElseThrow(() -> new ResourceNotFoundException("CityId " + cityId + "not found"));
	    }
	 
	 
	 @DeleteMapping("/state/{stateId}/city/{cityId}")
	    public ResponseEntity<?> deleteCity(@PathVariable (value = "stateId") Long stateId, @PathVariable (value = "cityId") Long cityId)throws ResourceNotFoundException {
	        return cityrepo.findByIdAndStateId(cityId, stateId).map(city -> {
	            cityrepo.delete(city);
	            return ResponseEntity.ok().build();
	        }).orElseThrow(() -> new ResourceNotFoundException("City not found with id " + cityId + " and stateId " + stateId));
	    }
	 
	 @GetMapping("/getcitybyId/{cityId}")
	    public ResponseEntity <City> getEmployeeById(@PathVariable Long cityId) throws ResourceNotFoundException {
	        City city = cityrepo.findById(cityId)
	            .orElseThrow();
	        return ResponseEntity.ok(city);
	    }
	 
}
