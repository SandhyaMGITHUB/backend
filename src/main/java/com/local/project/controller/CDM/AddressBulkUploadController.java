package com.local.project.controller.CDM;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;

import com.local.project.jwts.MessageResponse;
import com.local.project.model.CDM.CompanyAddress;
import com.local.project.model.Superadmin.Acts;
import com.local.project.model.Superadmin.City;
import com.local.project.model.Superadmin.Company;
import com.local.project.model.Superadmin.LocationType;
import com.local.project.model.Superadmin.State;
import com.local.project.repository.CDM.CompanyAddressRepository;
import com.local.project.repository.Superadmin.CityRepository;
import com.local.project.repository.Superadmin.CompanyRepository;
import com.local.project.repository.Superadmin.LocationTypeRepository;
import com.local.project.repository.Superadmin.StateRepository;
import com.local.project.service.Auth.AuthorDataService;
import com.local.project.service.CDM.BulkUploadService;
import com.local.project.service.Superadmin.companyservice;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/bulkaddress")
public class AddressBulkUploadController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	BulkUploadService uploadservices;
	static String SHEET = "CompanyAddress";
	
	@Autowired
	CompanyRepository comprepo;
	
	@Autowired
	companyservice compservice;
	
	@Autowired
	AuthorDataService authorservice;
	
	@Autowired
	StateRepository staterepo;
	
	@Autowired
	CityRepository cityrepo;
	
	@Autowired
	LocationTypeRepository locrepo;
	
	@Autowired
	CompanyAddressRepository comaddressrepo;
	

	@PostMapping(value="/testexcel" ,consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	 public ResponseEntity addressexcel(@RequestParam("file") MultipartFile addressfile) throws IOException{
		
		//creating a workbook by streaming address file
		 Workbook addressworkbook = new XSSFWorkbook(addressfile.getInputStream());
		 
		 CompanyAddress cmpaddress = new CompanyAddress();
		 
		 //intializing a list of companyaddress
		 List<CompanyAddress> companyaddress = new ArrayList<CompanyAddress>();
			
		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();
		
		//iterating through sheet in address workbook 
		Iterator<Sheet> sheetIterator = addressworkbook.sheetIterator();
		
		//setting colour to green colour to row
		CellStyle sucessStyle = addressworkbook.createCellStyle();
		sucessStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		sucessStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		
		CellStyle errorStyle = addressworkbook.createCellStyle();
		errorStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
		errorStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		int numberOfCells = 0;
		int newnumber=0;
		
		while (sheetIterator.hasNext()) {
			Sheet sheet = sheetIterator.next();
	           //iterating through row in address workbook
	            Iterator<Row> rowIterator = sheet.rowIterator();
	            int rowNumber = 0;
	            while (rowIterator.hasNext()) {
	            	boolean status = true;
	            }
		}
		comaddressrepo.saveAll(companyaddress);
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		addressworkbook.write(os);
		addressworkbook.close();

		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(
				MediaType.parseMediaType("MediaType.APPLICATION_OCTET_STREAM"));
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=StatusReport.xlsx");

		ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(is), headers,
				HttpStatus.OK);

		return response;
	}

	
	//test function
	@RequestMapping(value="/addressexcel",consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity addressexcel1(@RequestParam("file") MultipartFile addressfile) throws IOException {
		
		//intializing a list of companyaddress
		 List<CompanyAddress> companyaddress = new ArrayList<CompanyAddress>();
		
		//creating a workbook by streaming address file
		 Workbook addressworkbook = new XSSFWorkbook(addressfile.getInputStream());
		 
		//iterating through sheet in address workbook 
		Iterator<Sheet> sheetIterator = addressworkbook.sheetIterator();
		
		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();
		
		//setting colour to green colour to row
		 CellStyle sucessStyle = addressworkbook.createCellStyle();
		 sucessStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		 sucessStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				
		CellStyle errorStyle = addressworkbook.createCellStyle();
		errorStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
		errorStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		int numberOfCells = 0;
		int newnumber=0;
		while (sheetIterator.hasNext()) {
			Sheet sheet = sheetIterator.next();
	        //iterating through row in address workbook
	         Iterator<Row> rowIterator = sheet.rowIterator();
	         int rowNumber = 0;
	         while (rowIterator.hasNext()) {
	        	 Row row = rowIterator.next();
	            	row.setRowStyle(errorStyle);
	            	Iterator<Cell> cellIterator = row.cellIterator();
	                // creating status header
	            	boolean status = true;
	            	//company address object
	        		CompanyAddress cmpaddress = new CompanyAddress();
	                if (rowNumber == 0) {
	                  rowNumber++;
	                  numberOfCells = row.getPhysicalNumberOfCells();
	                  row.createCell(numberOfCells).setCellValue("status");
	                  newnumber=row.getPhysicalNumberOfCells();
	                  row.createCell(newnumber).setCellValue("error");
	                  continue;
	                }
	                int cellIdx = 0;
	                String error="";
	                while (cellIterator.hasNext()) {
	                	Cell cell = cellIterator.next();
	                	//switch
	                	switch(cellIdx) {
	                	
	                	case 0:
	                		if(comprepo.existsByCompanyName(cell.getStringCellValue()))
	                		{
	                			String companyname=cell.getStringCellValue();
	                			Company cmp= comprepo.findByCompanyName(companyname);
	                			cmpaddress.setCompany(cmp);
	                			cell.setCellStyle(sucessStyle);
	                			break;
	                		}
	                		else {
	                			cell.setCellStyle(errorStyle);
	                			error="company does not exist";
	                			status=false;
	                			System.out.println(error);
	                			break;
	                		}
	                       
	                   case 1:
		    	          String z =cell.getStringCellValue();
		    	          if(comaddressrepo.existByLocationName(z)) {
			    	        	cell.setCellStyle(errorStyle);
			                	error="company location name already exist";
			                	status=false;
			                	System.out.println(error);
			                	break;
		    	          }
		    	        else {
		    	        	cmpaddress.setCompany_location(z);
		                	cell.setCellStyle(sucessStyle);
		                	break;
		    	         }
		    	        //start date
	                	case 2:
	              		   String value = dataFormatter.formatCellValue(cell);
	              		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	              		   try {
	              			    Date date = formatter.parse(value);
	                 	            String pattern = "yyyy-MM-dd";
	                 	            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	                 	            String finaldate = simpleDateFormat.format(new Date());
	                 	            cmpaddress.setCompany_loc_startdate(finaldate);
	                 	            cell.setCellStyle(sucessStyle);
	              	           }
	              		   catch (ParseException e) {
	              			  cell.setCellStyle(errorStyle);
	              			  status=false;
	              	        }
	              		   break;
	                	case 3:
		              		   String value1 = dataFormatter.formatCellValue(cell);
		              		   SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
		              		   try {
		              			   if(value1.equals("null")) {
		              				   cmpaddress.setCompany_loc_enddate(null);
		              				  cell.setCellStyle(sucessStyle);
		              			   }
		              			   else {
		              				  Date date = formatter1.parse(value1);
		                 	              String pattern = "yyyy-MM-dd";
		                 	              SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		                 	              String finaldate = simpleDateFormat.format(new Date());
		                 	              cmpaddress.setCompany_loc_enddate(finaldate);
		                 	              cell.setCellStyle(sucessStyle);
		              			   }
		              	           
		              	        } catch (ParseException e) {
		              	        	cell.setCellStyle(errorStyle);
		              	        	status=false;
		              	        }
		              		   break;
	                	case 4:
		              		   String address = cell.getStringCellValue();
		              		   cmpaddress.setAddress(cell.getStringCellValue());
		              		   break;
	                	case 5:
	              		   if (staterepo.existsByName(cell.getStringCellValue())) {
	              			   String statename = cell.getStringCellValue();
	              			   //System.out.println("State exist");
	              			   State state = staterepo.findByName(statename);
	              			    cmpaddress.setState(state);
	              			   cell.setCellStyle(sucessStyle);
	              			    break;
	              			}
	              		   else {
	              			  cell.setCellStyle(errorStyle);
	              			  error=error + "State does not exist";
	              			  status=false;
	              			   break;
	              		   }
	              		   //checking for city name in city table
	                	case 6:
	              		   if (cityrepo.existsByCityName(cell.getStringCellValue())) {
	              			   String cityname = cell.getStringCellValue();
	              			   City city = cityrepo.findByCityName(cityname);
	              			   cmpaddress.setCity(city);
	              			   cell.setCellStyle(sucessStyle);
	              			    break;
	              			}
	              		   else {
	              			  cell.setCellStyle(errorStyle);
	              			 error= error +"city does not exist";
	              			  status=false;
	              			   break;
	              		   }
	              		 //checking for location type in location_type table in db
	                	case 7:
	              		   Set<Integer> set4 = new HashSet<>();
	              		   //since its a multiple value checking for commo in between
	              		   set4=getlocationtypefromexcel(cell.getStringCellValue());
	              		   if(!(set4.isEmpty())) {
	              			   cmpaddress.setLocation_types(set4);
	              			  cell.setCellStyle(sucessStyle);
	              				break;	
	              				}
	              		   
	              		  else {
	              			 cell.setCellStyle(errorStyle);
	              			 error= error + "location type does not exist";
	              			 status=false;
	             			   break;
	             		   }
	              		   
	              		//country office
	                	case 8:
	              		   String countryoffice = cell.getStringCellValue().trim().toLowerCase();;
	              		   if(countryoffice.equals("yes")){
	              			   cmpaddress.setCountry_office(1);
	              			  cell.setCellStyle(sucessStyle);
	              		   }
	              		   else if(countryoffice.equals("no")) {
	              			   cmpaddress.setCountry_office(0);
	              			  cell.setCellStyle(sucessStyle);
	              		   }
	              		   break;
	              		   //state office
	                	case 9:
	              		   String stateoffice = cell.getStringCellValue().trim().toLowerCase();;
	              		   if(stateoffice.equals("yes")){
	              			   cmpaddress.setState_office(1);
	              			  cell.setCellStyle(sucessStyle);
	              		   }
	              		   else if(stateoffice.equals("no")) {
	              			   cmpaddress.setState_office(0);
	              			  cell.setCellStyle(sucessStyle);
	              		   }
	              		   break;
	              		   //city office
	              	   case 10:
	              		   String cityoffice = cell.getStringCellValue().trim().toLowerCase();;
	              		   if(cityoffice.equals("yes")){
	              			   cmpaddress.setCity_office(1);;
	              			  cell.setCellStyle(sucessStyle);
	              		   }
	              		   else if(cityoffice.equals("no")) {
	              			   cmpaddress.setCity_office(0);
	              			  cell.setCellStyle(sucessStyle);
	              		   }
		              		   break;
		               case 11: 
		             		   String mobile = dataFormatter.formatCellValue(cell);
		             		   int length = mobile.length();
		             		   cmpaddress.setCompany_mobile(dataFormatter.formatCellValue(cell));
		             		  cell.setCellStyle(sucessStyle);
		             		   break;
		              	case 12:
		             		   cmpaddress.setMailId(cell.getStringCellValue());
		             		   cell.setCellStyle(sucessStyle);
		             		   break;
		              	case 13:
		             		  cmpaddress.setCompany_staff_male((int) cell.getNumericCellValue());
		             		  cell.setCellStyle(sucessStyle);
		             		   break;
		             	case 14:
		             		   cmpaddress.setCompany_staff_female((int) cell.getNumericCellValue());
		             		   cell.setCellStyle(sucessStyle);
		             		   break;
		             	 case 15:
		             		   cmpaddress.setCompany_staff_apprentices((int) cell.getNumericCellValue());
		             		   cell.setCellStyle(sucessStyle);
		             		   break;
		             	 case 16:
		             		   cmpaddress.setCompany_staff_contract((int) cell.getNumericCellValue());
		             		   cell.setCellStyle(sucessStyle);
		             		   break;
	                	default:
	                        break;
	                	}
	                	cellIdx++;	
	                }
	                //if all condition are true
	                if(status) {
	                	
	                	Long createdby = authorservice.findCurrentUser();
	         		    cmpaddress.setCreated_by(createdby);
	         		    cmpaddress.setUpdated_by(createdby);
	         		    cmpaddress.setCreated_at(LocalDateTime.now());
	         		    cmpaddress.setUpdated_at(LocalDateTime.now());
	         		    //saving address details to company address table in db
	         		   CompanyAddress savedAddress = comaddressrepo.save(cmpaddress);
	         		   //calculating act for the saved address
	    			   List<Acts> companyaddressacts=compservice.getcompanyaddressacts(savedAddress);
	    			   savedAddress.setActs(companyaddressacts);
	    			   //saving act list in company address act applicable table
	    			   comaddressrepo.save(savedAddress);
	    			  //email verification if new user the add to database or update user 
	    			   Integer addressId=savedAddress.getCompany_address_id().intValue();
	    			   compservice.saveclientuser(cmpaddress.getMailId(),addressId,cmpaddress.getCompany().getId());
	    			   //if added sucessfully then set status cell success
	    			   if (comaddressrepo.findById(savedAddress.getCompany_address_id()).isPresent())
	    			   {
	    				   Cell statuscell = row.createCell(numberOfCells);
		        			statuscell.setCellValue("success");
		        			statuscell.setCellStyle(sucessStyle);
		        			companyaddress.add(cmpaddress); 
	    			   }
	    			else {
	    				Cell statuscell = row.createCell(numberOfCells);
	        		    statuscell.setCellValue("unsuccess");
	        		    statuscell.setCellStyle(errorStyle);
	    				
	    			}
	    	
	              }
	                //if ends here
	                else {
	                	Cell statuscell = row.createCell(numberOfCells);
	        		    statuscell.setCellValue("unsuccess");
	        		    statuscell.setCellStyle(errorStyle);
	        			Cell errorcell = row.createCell(numberOfCells+1);
	        		    errorcell.setCellValue(error);
	        			errorcell.setCellStyle(errorStyle);
	                }
	                //cellIterator
	                
	         }
	         //rowIterator
		}
		//sheetIterator
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		addressworkbook.write(os);
		addressworkbook.close();

		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(
				MediaType.parseMediaType("application/vnd.ms-excel"));
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=StatusReport.xlsx");

		ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(is), headers,
				HttpStatus.OK);

		return response; 
		
	}
	
	
//find location type based on excel value	
	public Set<Integer> getlocationtypefromexcel(String text) {
		String[] splitted = text.trim().split("\\s*,\\s*");
		Set<Integer> set4 = new HashSet<>();
		for (String s: splitted) {           
			if(locrepo.existsByLocationName(s)) {
				LocationType location = locrepo.findByLocationName(s);
				Integer id=location.getLocation_id().intValue();;
				set4.add(id);
				//cmpaddress.setLocation_types(set4);
					
				} 
		}
		return set4;
		
		
	}
}