package com.local.project.controller.Superadmin;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.local.project.message.NotFoundException;
import com.local.project.model.Superadmin.ActFormat;
import com.local.project.model.Superadmin.ActFormatHeading;
import com.local.project.model.Superadmin.Activities;
import com.local.project.repository.Superadmin.ActFormatHeadingRepository;
import com.local.project.repository.Superadmin.ActformatRepository;
import com.local.project.repository.Superadmin.ActivitiesRepository;
import com.local.project.repository.Superadmin.ActsRepository;
import com.local.project.service.Auth.AuthorDataService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/actformat")
public class ActFormatController {
	
	
	@Autowired
	ActformatRepository actFormatrepository;
	
	@Autowired
	AuthorDataService authorservice;
	
	@Autowired
	ActFormatHeadingRepository actFormatHeadingRepository;
	
	@Autowired
	ActsRepository actsRepository;
	
	@Autowired
	ActivitiesRepository activitiesRepository;

	/*add act_format*/
	@PostMapping("/add")
	public ResponseEntity<?> addActFormat(@Valid @RequestBody ActFormat actFormat){
		
		Long createdby = authorservice.findCurrentUser();
		actFormat.setCreated_by(createdby);
		actFormat.setUpdated_by(createdby);
		actFormat.setCreated_at(LocalDateTime.now());
		actFormat.setUpdated_at(LocalDateTime.now());
		actFormatrepository.save(actFormat);
		return ResponseEntity.ok(new MessageResponse("ActFormat Added successfully"));
	}
	
	/*get forms name based on act_id*/
	@GetMapping("/get-form/{id}")
    public List<String> getForm(@PathVariable Long id) {
        return actFormatrepository.findFormByActId(id);
    }
	
	/*get form data based on act_id and form name*/
	@GetMapping("/get-form/{id}/{act_form_name}")
    public List<ActFormat> getFormByActId(
    			@PathVariable Long id,
    			@PathVariable String act_form_name) {
        return actFormatrepository.findFormByActIds(id,act_form_name);
    }
	
//	/*get form data based on act_id and form name*/
//	@GetMapping("/get-summarycol/{summaryname}")
//    public List<?> getSummary(@PathVariable String summaryname) {
//        return actFormatrepository.findSummary(summaryname);
//    }
	
	/*get form data based on act_id and form name*/
	@GetMapping("/get-summarycol")
    public List<?> getSummary() {
        return actFormatrepository.findSummary();
    }
	
	/*update form*/
    @PutMapping("/modify/{act_format_id}")
    public ResponseEntity<?> updateActFormat(
    						@PathVariable(value = "act_format_id") Long actformatId,
                            @RequestBody ActFormat actFDetails) throws NotFoundException {

    		Long updatedby = authorservice.findCurrentUser();
    		ActFormat actFormat = actFormatrepository.findById(actformatId)
			                .orElseThrow(() -> new NotFoundException(actformatId));		
    				actFormat.setAct_form_desc(actFDetails.getAct_form_desc());
    				actFormat.setAct_form_name(actFDetails.getAct_form_name());
    				actFormat.setIs_calculate(actFDetails.isIs_calculate());
    				actFormat.setCopy_act_id(actFDetails.getCopy_act_id());
    				actFormat.setCopy_format_id(actFDetails.getCopy_format_id());
//    				actFormat.setActList(actFDetails.getActList());
    				actFormat.setUpdated_by(updatedby);
    				actFormat.setUpdated_at(LocalDateTime.now());
			
    				actFormatrepository.save(actFormat);
			
			return ResponseEntity.ok(new MessageResponse("ActFormat Updated successfully"));

    }
    
    
    /*update form*/
    @PutMapping("/modify2/{act_format_id}")
    public ResponseEntity<?> updateActFormat2(
    						@PathVariable(value = "act_format_id") Long actformatId,
                            @RequestBody ActFormat actFDetails) throws NotFoundException {

    		Long updatedby = authorservice.findCurrentUser();
    		ActFormat actFormat = actFormatrepository.findById(actformatId)
			                .orElseThrow(() -> new NotFoundException(actformatId));
    				actFormat.setActList(actFDetails.getActList());
    				actFormat.setUpdated_by(updatedby);
    				actFormat.setUpdated_at(LocalDateTime.now());
			
    				actFormatrepository.save(actFormat);
			
			return ResponseEntity.ok(new MessageResponse("Format Updated successfully"));

    }
    
    /*update single heading*/
    @PutMapping("/edit-heading/{act_format_headingid}")
    public ResponseEntity<?> updateFormatHeadings(
    						@PathVariable(value = "act_format_headingid") Long actformatId,
                            @RequestBody ActFormatHeading actFDetails) throws NotFoundException {

    		Long updatedby = authorservice.findCurrentUser();
    		ActFormatHeading actFormat = actFormatHeadingRepository.findById(actformatId)
			                .orElseThrow(() -> new NotFoundException(actformatId));
		    		actFormat.setAct_format_content(actFDetails.getAct_format_content());
					actFormat.setAct_format_input_type(actFDetails.getAct_format_input_type());
					actFormat.setAct_format_input_type_col_name(actFDetails.getAct_format_input_type_col_name());
					actFormat.setAct_format_content_align(actFDetails.getAct_format_content_align());
					actFormat.setHas_sub_column(actFDetails.isHas_sub_column());
    				actFormat.setUpdated_by(updatedby);
    				actFormat.setUpdated_at(LocalDateTime.now());
			
    				actFormatHeadingRepository.save(actFormat);
			
			return ResponseEntity.ok(new MessageResponse("Format Updated successfully"));

    }
    
    /*copy act*/
    @PutMapping("/copyFormat/{act_format_id}")
    public ResponseEntity<?> copyActFormat(
    						@PathVariable(value = "act_format_id") Long actformatId,
                            @RequestBody ActFormat actFDetails) throws NotFoundException {

    		Long updatedby = authorservice.findCurrentUser();
    		ActFormat actFormat = actFormatrepository.findById(actformatId)
			                .orElseThrow(() -> new NotFoundException(actformatId));		
    				actFormat.setCopy_act_id(actFDetails.getCopy_act_id());
    				actFormat.setCopy_format_id(actFDetails.getCopy_format_id());
    				actFormat.setActList(actFDetails.getActList());
    				actFormat.setUpdated_by(updatedby);
    				actFormat.setUpdated_at(LocalDateTime.now());
			
    				actFormatrepository.save(actFormat);
			
			return ResponseEntity.ok(new MessageResponse("ActFormat Copied successfully"));

    }
    
    /*Delete Form*/
    @DeleteMapping("/delete/{act_format_id}")
	  public ResponseEntity<?> deleteTutorial(@PathVariable("act_format_id") long id) {

//    		actFormatrepository.deleteById(id);
    		actFormatrepository.deleteById(id);
	    	return ResponseEntity.ok(new MessageResponse("ActForm Deleted successfully"));

	  }    
    
    
    /*get form headings based on act_id and formname*/
	@GetMapping("/get-headings/{id}/{act_format_id}")
    public List<ActFormatHeading> getHeadingsByActId(
    			@PathVariable Long id,
    			@PathVariable Long act_format_id) {
        return actFormatrepository.findHeadingsByActIds(id,act_format_id);
    }
	
	
	/*update act format heading*/
    @PutMapping("/modify-heading/{act_format_heading_id}")
    public ResponseEntity<?> updateActFormatHeading(
    						@PathVariable(value = "act_format_heading_id") Long actformatHeadingId,
                            @RequestBody ActFormatHeading actFDetails) throws NotFoundException {

    		Long updatedby = authorservice.findCurrentUser();
    		ActFormatHeading actFormat = actFormatHeadingRepository.findById(actformatHeadingId)
			                .orElseThrow(() -> new NotFoundException(actformatHeadingId));		
    				actFormat.setAct_format_col_type(actFDetails.getAct_format_col_type());
    				actFormat.setAct_format_seq_no(actFDetails.getAct_format_seq_no());
    				actFormat.setAct_format_content(actFDetails.getAct_format_content());
    				actFormat.setAct_format_input_type(actFDetails.getAct_format_input_type());
    				actFormat.setAct_format_input_type_col_name(actFDetails.getAct_format_input_type_col_name());
    				actFormat.setAct_format_content_align(actFDetails.getAct_format_content_align());
    				actFormat.setUpdated_by(updatedby);
    				actFormat.setUpdated_at(LocalDateTime.now());
			
    				actFormatHeadingRepository.save(actFormat);
			
			return ResponseEntity.ok(new MessageResponse("Act Format Heading Updated successfully"));

    }
    
    /*get activities based on act_id*/
    @GetMapping("/get-activities/{act_id}")
    public List<Activities> updatenoimage(
			@PathVariable(value = "act_id") Long actId) throws IOException, NotFoundException{
		List<Activities> act = actsRepository.findByActId(actId);
		return act;
	}
    
    
//    /*Update act_format_id in activities table--configure from act format heading*/
//    @PutMapping("/modify-actformatid/{activity_id}")
//    public ResponseEntity<?> updateactivities(
//			@PathVariable(value = "activity_id") Long activityId,
//			@RequestBody Activities activity) throws IOException, NotFoundException{
//    	
//    	Activities activities = activitiesRepository.findById(activityId)
//                .orElseThrow(() -> new NotFoundException(activityId));	
//    	activities.setAct_format_id(activity.getAct_format_id());    	
//		return ResponseEntity.ok(new MessageResponse("Activity Updated successfully"));
//	}
    
    
    /*Update act_format_id in activities table--configure from act format heading*/
    @PutMapping("/modify-actformatid/{act_id}")
    public ResponseEntity<?> updateactivities(
			@PathVariable(value = "act_id") Long actId,
			@RequestParam int  act_format_id,
			@RequestParam String  activity_name) throws IOException, NotFoundException{
    	
    		List<Activities> items = actsRepository.findByActId(actId);
            items
            .stream()
            .filter(s -> s.getActivity_name().matches(activity_name))
            .forEach(s -> s.setAct_format_id(act_format_id));
            activitiesRepository.saveAll(items);
            return ResponseEntity.ok(new MessageResponse("Activity Updated Successfully"));
	}
    
}
