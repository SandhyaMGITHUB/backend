	package com.local.project.controller.Superadmin;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.local.project.model.Superadmin.State;
import com.local.project.repository.Superadmin.StateRepository;
import com.local.project.service.Auth.AuthorDataService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/state")
public class StateController {
	
	@Autowired
	StateRepository staterepo;
	
	@Autowired
	AuthorDataService authorservice;
	
	@GetMapping("/stateget")
	public String saystate() {
		return "sttehello";
	}
	
	@PostMapping("/addstate")
	public  ResponseEntity<?>  createstate(@Valid @RequestBody State state)throws ResourceNotFoundException {
		if (staterepo.existsByName(state.getName())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: state Already Exits!"));
		}
		Long createdby = authorservice.findCurrentUser();
		state.setCreated_by(createdby);
		state.setUpdated_by(createdby);
		state.setCreated_at(LocalDateTime.now());
		state.setUpdated_at(LocalDateTime.now());
		staterepo.save(state);
	 return ResponseEntity.ok(new MessageResponse("State Added Successfully!"));
		
	}
	
	
	@PutMapping("/editstate/{stateId}")
    public ResponseEntity<MessageResponse> updateState(@PathVariable Long stateId, @Valid @RequestBody State staterequest)throws ResourceNotFoundException {
		return staterepo.findById(stateId).map(state -> {
			String displaystate = staterequest.getName();
            state.setName(staterequest.getName());
            staterepo.save(state);
            return  ResponseEntity.ok(new MessageResponse("State Updated as"+ displaystate +" Successfully!"));
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + stateId + " not found"));
    }
	

    @GetMapping("/getstates")
    public List<State> getAllPosts() {
        return staterepo.findAll(Sort.by(Sort.Direction.ASC,"name"));
    }
    
    
    @DeleteMapping("/deletestate/{stateId}")
    public ResponseEntity<?> deletePost(@PathVariable Long stateId)throws ResourceNotFoundException {
        return staterepo.findById(stateId).map(state -> {
            staterepo.delete(state);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("StateId " + stateId + " not found"));
    }
    
    @GetMapping("/getstatebyId/{stateId}")
    public ResponseEntity <State > getEmployeeById(@PathVariable Long stateId) throws ResourceNotFoundException {
        State state = staterepo.findById(stateId)
            .orElseThrow();
        return ResponseEntity.ok(state);
    }
 
	

}
