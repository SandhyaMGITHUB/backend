package com.local.project.controller.Superadmin;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.local.project.jwts.MessageResponse;
import com.local.project.message.NotFoundException;
import com.local.project.model.Superadmin.Acts;
import com.local.project.model.Superadmin.Company;
import com.local.project.repository.Auth.UserRepository;
import com.local.project.repository.Superadmin.CompanyRepository;
import com.local.project.service.Auth.AuthorDataService;
import com.local.project.service.Auth.UserDetailsImpl;
import com.local.project.service.Superadmin.companyservice;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.bytebuddy.utility.RandomString;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.hibernate.engine.jdbc.StreamUtils;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/company")
public class CompanyController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	companyservice companyservice;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	AuthorDataService authorservice;
	
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	private static String imageDirectory = System.getProperty("user.dir") + "/images/";

	/*fetching subdomain of currently logged-in user*/
	@GetMapping("/subdomains")
	public String fetch_subdomains(@AuthenticationPrincipal UserDetailsImpl userDetails) {	
		Long comp_id = userDetails.getCompany_id();
		String subdomain = userRepository.getsubdomains(comp_id);
		return subdomain;
	}
	
	
	/*fetch full list of subdomain*/
	@GetMapping("/allsubdomain")
	public List<Company> list(){
		return companyservice.listAll();
	}
	

	
	/*adding a new company*/
	@PostMapping(value="/add", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addCompany(@RequestParam(value="file",required = false)MultipartFile file, 
										@RequestParam(value="jsondata")String jsondata) throws IOException{

		
		Company company1 = objectMapper.readValue(jsondata,Company.class);
		Long createdby = authorservice.findCurrentUser();
		company1.setCreated_by(createdby);
		company1.setUpdated_by(createdby);
		company1.setCreated_at(LocalDateTime.now());
		company1.setUpdated_at(LocalDateTime.now());
		
		String rand = RandomString.make(6);		
		if(file!=null) {
			String fileName= rand+file.getOriginalFilename();
//    		String path = new File("src\\main\\resources\\static\\uploads\\Company_Logo").getAbsolutePath() + "\\" + fileName;
//    		FileOutputStream output = new FileOutputStream(path);
//    		output.write(file.getBytes());
//    		output.close();
    		
//    		String reactpath = new File("C:\\Users\\Admin\\React-Project\\ComplyIndiaLocal\\public\\images\\Company_Logo").getAbsolutePath() + "\\" + fileName;
////    		String reactpath = new File("src\\main\\resources\\static\\uploads\\Company_Logo").getAbsolutePath()+ "\\" + fileName;
//    		FileOutputStream reactoutput = new FileOutputStream(reactpath);
//    		reactoutput.write(file.getBytes());
//    		reactoutput.close();
			
			
			makeDirectoryIfNotExist(imageDirectory);
	        Path fileNamePath = Paths.get(imageDirectory,
	        		fileName.concat(".").concat(FilenameUtils.getExtension(file.getOriginalFilename())));
	       
            Files.write(fileNamePath, file.getBytes());
            company1.setUpload_dir("");
    		company1.setFile_name(fileName);
    		company1.setDocument_type(file.getContentType());
	        company1.setData(file.getBytes());
			
    		    		
    		
		}
		else {
    		company1.setUpload_dir("");
    		company1.setFile_name("");
    		company1.setDocument_type("");
    		company1.setData(null);
		} 
		
		companyRepository.save(company1);
		return ResponseEntity.ok(new MessageResponse("Company Data added successfully"));
	}
	
	
	/*add without image*/
	@PostMapping(value="/add-no-image")
	public ResponseEntity<?> addCompanynoimage(@RequestParam(value="jsondata")String jsondata) throws IOException{

		Company company1 = objectMapper.readValue(jsondata,Company.class);
		Long createdby = authorservice.findCurrentUser();
		company1.setCreated_by(createdby);
		company1.setUpdated_by(createdby);
		company1.setCreated_at(LocalDateTime.now());
		company1.setUpdated_at(LocalDateTime.now());
		company1.setUpload_dir("");
		company1.setFile_name("");
		company1.setDocument_type("");
		Company savedAddress = companyRepository.save(company1);
//		List<Acts> companyaddressacts=compservice.getcompanyaddressacts(savedAddress);
//		cmpaddress.save(savedAddress);
		return ResponseEntity.ok(new MessageResponse("Company Data added successfully"));
	}
	
	@GetMapping("/getpath")
	public String getpath() {
		String reactpath = imageDirectory;
		return reactpath;
	}
	
	
	/*get company based on id*/	
	@GetMapping("/get/{id}")
	public ResponseEntity<Company> get(@PathVariable Long id) {
		try {		
				Company company = companyservice.get(id);
				return new ResponseEntity<Company>(company,HttpStatus.OK);
			}
		catch(NoSuchElementException e) {
				return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
			}	
	}
	
	/*get all companies*/	
	@GetMapping("/get-all")
    public List<Company> getAllNotes() {
        return companyservice.getIfNotDeleted();
    }
	
	/*update*/
	@PutMapping(value="/modify/{id}", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(@PathVariable(value = "id") Long compId,
    						@RequestParam(value="file",required = false)MultipartFile file,
    						@RequestParam(value="jsondata")String jsondata) throws NotFoundException, IOException {

    		Long updatedby = authorservice.findCurrentUser();
    		Company comp = companyRepository.findById(compId)
			                .orElseThrow(() -> new NotFoundException(compId));
    		
    		if(file==null) {
    			comp.setUpload_dir("");
        		comp.setFile_name("");
        		comp.setDocument_type("");
        		comp.setData(null);
    		}
    		else {
    			String rand = RandomString.make(6);
        		String fileName= rand+file.getOriginalFilename();
//        		String path = new File("src\\main\\resources\\static\\uploads\\Company_Logo").getAbsolutePath() + "\\" + fileName;
//        		FileOutputStream output = new FileOutputStream(path);
//        		output.write(file.getBytes());
//        		output.close();
        		
        		makeDirectoryIfNotExist(imageDirectory);
    	        Path fileNamePath = Paths.get(imageDirectory,
    	        		fileName.concat(".").concat(FilenameUtils.getExtension(file.getOriginalFilename())));
    	       
                Files.write(fileNamePath, file.getBytes());
                
                comp.setUpload_dir("");
                comp.setFile_name(fileName);
                comp.setDocument_type(file.getContentType());
                comp.setData(file.getBytes());
    		}
    		
    		
    		Company company1 = objectMapper.readValue(jsondata,Company.class);
    		comp.setCompany_name(company1.getCompany_name());
    		comp.setDomain_name(company1.getDomain_name());
    		comp.setCompany_group_name(company1.getCompany_group_name());
    		comp.setCompany_short_name(company1.getCompany_short_name());
    		comp.setCompany_type_id(company1.getCompany_type_id());
    		comp.setIndustry_id(company1.getIndustry_id());
    		comp.setCompany_trade_union(company1.getCompany_trade_union());
    		comp.setCompany_turnover(company1.getCompany_turnover());
    		comp.setCompany_incorporation_date(company1.getCompany_incorporation_date());
    		comp.setCompany_contract_date(company1.getCompany_contract_date());
    		comp.setCompany_contractend_date(company1.getCompany_contractend_date());
    		comp.setImportance_level(company1.getImportance_level());
    		comp.setIs_selfservice(company1.getIs_selfservice());			    	
    		comp.setUpdated_by(updatedby);
    		comp.setUpdated_at(LocalDateTime.now());
    		comp.setPf(company1.getPf());
    		comp.setEsi(company1.getEsi());
    		comp.setPt(company1.getPt());
    		comp.setLwf(company1.getLwf());
    		comp.setLc(company1.getLc());
    		comp.setIc(company1.getIc());
    		comp.setClra(company1.getClra());
    		comp.setFc(company1.getFc());
			companyRepository.save(comp);    		
    		
			return ResponseEntity.ok(new MessageResponse("Company Data Updated successfully"));   	
    }
	
	/*update without image*/
	@PutMapping(value="/modify-no-img/{id}", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateoldimg(@PathVariable(value = "id") Long compId,
    						@RequestParam(value="file",required = false)MultipartFile file,
    						@RequestParam(value="jsondata")String jsondata) throws NotFoundException, IOException {

    		Long updatedby = authorservice.findCurrentUser();
    		Company comp = companyRepository.findById(compId)
			                .orElseThrow(() -> new NotFoundException(compId));
    		
    		
    		Company company1 = objectMapper.readValue(jsondata,Company.class);
    		comp.setCompany_name(company1.getCompany_name());
    		comp.setDomain_name(company1.getDomain_name());
    		comp.setCompany_group_name(company1.getCompany_group_name());
    		comp.setCompany_short_name(company1.getCompany_short_name());
    		comp.setCompany_type_id(company1.getCompany_type_id());
    		comp.setIndustry_id(company1.getIndustry_id());
    		comp.setCompany_trade_union(company1.getCompany_trade_union());
    		comp.setCompany_turnover(company1.getCompany_turnover());
    		comp.setCompany_incorporation_date(company1.getCompany_incorporation_date());
    		comp.setCompany_contract_date(company1.getCompany_contract_date());
    		comp.setCompany_contractend_date(company1.getCompany_contractend_date());
    		comp.setImportance_level(company1.getImportance_level());
    		comp.setIs_selfservice(company1.getIs_selfservice());			    	
    		comp.setUpdated_by(updatedby);
    		comp.setUpdated_at(LocalDateTime.now());    		
    		comp.setPf(company1.getPf());
    		comp.setEsi(company1.getEsi());
    		comp.setPt(company1.getPt());
    		comp.setLwf(company1.getLwf());
    		comp.setLc(company1.getLc());
    		comp.setFc(company1.getFc());
    		comp.setClra(company1.getClra());
    		comp.setIc(company1.getIc());
    		
			companyRepository.save(comp);
    		
    		
			return ResponseEntity.ok(new MessageResponse("Company Data Updated successfully"));
   	
    }
	
	@DeleteMapping("/deletes/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long compId) throws NotFoundException {

    		Company comp = companyRepository.findById(compId)
			                .orElseThrow(() -> new NotFoundException(compId));		
    		comp.setIs_deleted(true);
			
			 companyRepository.save(comp);
			
			return ResponseEntity.ok(new MessageResponse("Company data Deleted successfully"));
   	
    }
	
	/*Delete Only Image*/
	@DeleteMapping("/delete-image/{id}")
    public ResponseEntity<?> deleteimage(@PathVariable("id") Long compId) throws NotFoundException {

    		Company comp = companyRepository.findById(compId)
			                .orElseThrow(() -> new NotFoundException(compId));		
    		comp.setUpload_dir("");
    		comp.setFile_name("");
    		comp.setDocument_type("");
    		comp.setData(null);
			
			companyRepository.save(comp);			
			return ResponseEntity.ok(new MessageResponse("Company Logo Deleted successfully"));
   	
    }
	
	
	
//	private static String imageDirectory = System.getProperty("user.dir") + "/images/";
//	
//	@RequestMapping(value = "/image", produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
//    public ResponseEntity<?> uploadImage(@RequestParam("imageFile")MultipartFile file,
//                                         @RequestParam("imageName") String name) {
//        makeDirectoryIfNotExist(imageDirectory);
//        Path fileNamePath = Paths.get(imageDirectory,
//                name.concat(".").concat(FilenameUtils.getExtension(file.getOriginalFilename())));
//        try {
//            Files.write(fileNamePath, file.getBytes());
//            return new ResponseEntity<>(name, HttpStatus.CREATED);
//        } catch (IOException ex) {
//            return new ResponseEntity<>("Image is not uploaded", HttpStatus.BAD_REQUEST);
//        }
//    }

    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
    
    
    
    @RequestMapping(value = "/logo/{img_name}", method = RequestMethod.GET,
            produces = MediaType.MULTIPART_FORM_DATA_VALUE)

    public void getImage(HttpServletResponse response,@PathVariable("img_name") String img_name) throws IOException {

        var imgFile = new ClassPathResource("images/"+img_name);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }
	
    	
    /*get logo from id*/
	@GetMapping("/getcomplogo/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
       Company fileObject =  companyRepository.findById(id).get();
       byte[] base64encodedData = Base64.getEncoder().encode(fileObject.getData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + 
                 fileObject.getFile_name() + "\"")
                .body(base64encodedData);
    }
    
}
