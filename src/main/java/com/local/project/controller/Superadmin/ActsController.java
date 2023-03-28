package com.local.project.controller.Superadmin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.local.project.jwts.MessageResponse;
import com.local.project.message.NotFoundException;
import com.local.project.model.Superadmin.*;
import com.local.project.repository.Superadmin.*;
import com.local.project.service.Auth.AuthorDataService;
import com.local.project.service.Superadmin.ActsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.bytebuddy.utility.RandomString;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/acts")
public class ActsController {

	@Autowired
	ActsRepository actsRepository;	
	
	@Autowired
	ActivitiesRepository activitiesRepository;
	
	@Autowired
	ActStateRepository actStateRepository;
	
	@Autowired
	AuthorDataService authorservice;
	
	@Autowired
	ActsService actsService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
//	private static String imageDirectory = System.getProperty("catalina.base") + "/ActAbstract/";
//	
//	public static final String BASE_PATH = "/usr/local/tomcat/ActAbstract/";
	private static String imageDirectory = System.getProperty("user.dir") + "/ActAbstract/";

	
	/*add withimage*/
	@PostMapping(value="/add", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addActs(@RequestParam(value="file",required = false)MultipartFile file,
									@RequestParam(value="jsondata")String jsondata) throws IOException{

		Acts acts = objectMapper.readValue(jsondata,Acts.class);
		
		Long createdby = authorservice.findCurrentUser();
		acts.setCreated_by(createdby);
		acts.setUpdated_by(createdby);
		acts.setCreated_at(LocalDateTime.now());
		acts.setUpdated_at(LocalDateTime.now());
		acts.setAbstract_file("");
		
		String rand = RandomString.make(6);		
		if(file!=null) {
			String fileName= rand+file.getOriginalFilename();
//    		String path = new File("src\\main\\resources\\static\\uploads\\Acts_Abstract").getAbsolutePath() + "\\" + fileName;
//    		FileOutputStream output = new FileOutputStream(path);
//    		output.write(file.getBytes());
//    		output.close();
//    		
//    		String reactpath = new File("C:\\Users\\Admin\\React-Project\\ComplyIndia\\public\\images\\Acts_Abstract").getAbsolutePath() + "\\" + fileName;
//    		FileOutputStream reactoutput = new FileOutputStream(reactpath);
//    		reactoutput.write(file.getBytes());
//    		reactoutput.close();
			makeDirectoryIfNotExist(imageDirectory);
	        Path fileNamePath = Paths.get(imageDirectory,
	        		fileName.concat(".").concat(FilenameUtils.getExtension(file.getOriginalFilename())));
	       
            Files.write(fileNamePath, file.getBytes());
    		
    		
    		acts.setAbstract_path("");
    		acts.setAbstract_file(fileName);
    		acts.setDocument_type(file.getContentType());
    		acts.setData(file.getBytes());
		}
		else {
			acts.setAbstract_path("");
			acts.setAbstract_file("");
			acts.setDocument_type("");
			acts.setData(null);
		} 
		
		actsRepository.save(acts);		
		return ResponseEntity.ok(new MessageResponse("Act Added Successfully"));
	}
	
	/*add without image*/
	@PostMapping(value="/add-no-image")
	public ResponseEntity<?> addActsnoimage(@RequestParam(value="jsondata")String jsondata) throws IOException{

		Acts acts = objectMapper.readValue(jsondata,Acts.class);
		
		Long createdby = authorservice.findCurrentUser();
		acts.setCreated_by(createdby);
		acts.setUpdated_by(createdby);
		acts.setCreated_at(LocalDateTime.now());
		acts.setUpdated_at(LocalDateTime.now());
		acts.setAbstract_file("");
		
		actsRepository.save(acts);
		
		return ResponseEntity.ok(new MessageResponse("Act Added Successfully"));
	}
	
	
	/*update with image*/
	@PutMapping(value="/update/{id}", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updatewithimage(
			@PathVariable(value = "id") Long actId,
			@RequestParam(value="file",required = false)MultipartFile file,
			@RequestParam(value="jsondata")String jsondata) throws IOException, NotFoundException{

		Long updatedby = authorservice.findCurrentUser();
		
//		Acts actstates = actsStatesRepository.findByActId(actId)
//                .orElseThrow(() -> new NotFoundException(actId));
		
		Acts act = actsRepository.findById(actId)
                .orElseThrow(() -> new NotFoundException(actId));
		
		
		if(file==null) {
			act.setAbstract_path("");
    		act.setAbstract_file("");
    		act.setDocument_type("");
    		act.setData(null);
		}
		else {
			String rand = RandomString.make(6);
    		String fileName= rand+file.getOriginalFilename();
    		
    		makeDirectoryIfNotExist(imageDirectory);
	        Path fileNamePath = Paths.get(imageDirectory,
	        		fileName.concat(".").concat(FilenameUtils.getExtension(file.getOriginalFilename())));
	       
            Files.write(fileNamePath, file.getBytes());
    		
            act.setAbstract_path("");
    		act.setAbstract_file(fileName);
    		act.setDocument_type(file.getContentType());
    		act.setData(file.getBytes());
		}
		
		Acts acts = objectMapper.readValue(jsondata,Acts.class);
//		act.setAbstract_file(acts.getAbstract_file());
		act.setAct_applicable_state(acts.getAct_applicable_state());
		act.setAct_end_date(acts.getAct_end_date());
		act.setAct_group_name(acts.getAct_group_name());
		act.setAct_start_date(acts.getAct_start_date());
		act.setActList(
				acts.getActList()
				);
		act.setAll_company_types(acts.isAll_company_types());
		act.setAll_industries(acts.isAll_industries());
		act.setBusiness_area_id(acts.getBusiness_area_id());
		act.setCentral_or_state(acts.getCentral_or_state());
		act.setCompany_type_id(acts.getCompany_type_id());
		act.setHas_abstract(acts.isHas_abstract());
		act.setHas_password(acts.isHas_password());
		act.setIndustry_id(acts.getIndustry_id());
		act.setLocation_types(acts.getLocation_types());
		act.setUpdated_by(updatedby);
		act.setCreated_at(LocalDateTime.now());
		act.setUpdated_at(LocalDateTime.now());
//		act.setAbstract_file("");
		
		
		actsRepository.save(act);
		
		return ResponseEntity.ok(new MessageResponse("Act Updated Successfully"));
	}
	
	/*update without image*/
	@PutMapping(value="/update-no-image/{id}")
	public ResponseEntity<?> updatenoimage(
			@PathVariable(value = "id") Long actId,
			@RequestParam(value="jsondata")String jsondata) throws IOException, NotFoundException{

		Long updatedby = authorservice.findCurrentUser();
		Acts act = actsRepository.findById(actId)
                .orElseThrow(() -> new NotFoundException(actId));
		
		Acts acts = objectMapper.readValue(jsondata,Acts.class);
//		act.setAbstract_file(acts.getAbstract_file());
		act.setAct_applicable_state(acts.getAct_applicable_state());
		act.setAct_end_date(acts.getAct_end_date());
		act.setAct_group_name(acts.getAct_group_name());
		act.setAct_start_date(acts.getAct_start_date());
		act.setActList(acts.getActList());
		act.setAll_company_types(acts.isAll_company_types());
		act.setAll_industries(acts.isAll_industries());
		act.setBusiness_area_id(acts.getBusiness_area_id());
		act.setCentral_or_state(acts.getCentral_or_state());
		act.setCompany_type_id(acts.getCompany_type_id());
		act.setHas_abstract(acts.isHas_abstract());
		act.setHas_password(acts.isHas_password());
		act.setIndustry_id(acts.getIndustry_id());
		act.setLocation_types(acts.getLocation_types());
		act.setUpdated_by(updatedby);
		act.setCreated_at(LocalDateTime.now());
		act.setUpdated_at(LocalDateTime.now());
//		act.setAbstract_file("");
		
		actsRepository.save(act);
		
		return ResponseEntity.ok(new MessageResponse("Act Updated Successfully"));
	}
	
	
	/*get all*/	
	@GetMapping("/get-all")
    public List<Acts> getAllNotes() {
        return actsRepository.findAll();
    }
	
	/* get acts list*/
	
	/*get based on id*/	
	@GetMapping("/get/{id}")
	public ResponseEntity<Acts> get(@PathVariable Long id) {
		try {		
				Acts act = actsService.get(id);
				return new ResponseEntity<Acts>(act,HttpStatus.OK);
			}
		
		catch(NoSuchElementException e) {
				return new ResponseEntity<Acts>(HttpStatus.NOT_FOUND);
			}	
		
	}
	
	
	/*Delete*/
	  @DeleteMapping("/delete/{id}")
	  public ResponseEntity<?> deleteTutorial(@PathVariable("id") long id) {
			
			actsRepository.deleteById(id);
	    	return ResponseEntity.ok(new MessageResponse("Deleted Successfully!"));	    
	  }
	  
	  /*Delete Only Abstract*/
		@DeleteMapping("/delete-abstract/{id}")
	    public ResponseEntity<?> deleteimage(@PathVariable("id") Long actId) throws NotFoundException {

	    		Acts act = actsRepository.findById(actId)
				                .orElseThrow(() -> new NotFoundException(actId));		
	    		act.setAbstract_path("");
				act.setAbstract_file("");
				
	    		actsRepository.save(act);			
				return ResponseEntity.ok(new MessageResponse("Abstract Deleted successfully"));
	   	
	    }
		
		
	/*Delete Only Activities*/
		@DeleteMapping("/delete-activity/{id}")
		public ResponseEntity<?> deleteActivities(@PathVariable("id") long id) {
			
			activitiesRepository.deleteById(id);
	    	return ResponseEntity.ok(new MessageResponse("Activity Deleted Successfully!"));	    
		}
		
		
		/*get all activities based on act_id*/	
//		@GetMapping("/get-activities")
//	    public List<Acts> getAllActivities() {
//	        return actsRepository.findAllActivities();
//	    }
		
		
		
		private void makeDirectoryIfNotExist(String imageDirectory) {
	        File directory = new File(imageDirectory);
	        if (!directory.exists()) {
	            directory.mkdir();
	        }
	    }
	    
	 
	    
	    /*get logo from id*/
	   	@GetMapping("/getabstract/{id}")
	       public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
	          Acts fileObject =  actsRepository.findById(id).get();
	          byte[] base64encodedData = Base64.getEncoder().encode(fileObject.getData());
	           return ResponseEntity.ok()
	                   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + 
	                    fileObject.getAbstract_file() + "\"")
	                   .body(base64encodedData);
	       }
	       
	       
	       
	   /*delete applicable state*/
       @PutMapping("/delete-actstate/{id}")
       public ResponseEntity<?> deleteActState(
   			@PathVariable(value = "id") Long actId,
   			@RequestParam(value="jsondata")String jsondata) throws IOException, NotFoundException{

   		Long updatedby = authorservice.findCurrentUser();
   		Acts act = actsRepository.findById(actId)
                   .orElseThrow(() -> new NotFoundException(actId));
   		
   		Acts acts = objectMapper.readValue(jsondata,Acts.class);
   		
   		act.setActList(acts.getActList());
   		act.setUpdated_by(updatedby);
   		act.setCreated_at(LocalDateTime.now());
   		act.setUpdated_at(LocalDateTime.now());
   		
   		actsRepository.save(act);
   		
   		return ResponseEntity.ok(new MessageResponse("State Deleted Successfully"));    
		}  
	       
	  
		
}

