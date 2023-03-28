package com.local.project.controller.CDM;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.local.project.model.CDM.CompanyActivity;
import com.local.project.model.CDM.CompanyAddress;
import com.local.project.model.CDM.TaskList;
import com.local.project.model.Superadmin.Acts;
import com.local.project.model.Superadmin.Company;
import com.local.project.repository.CDM.CompanyActivityRepository;
import com.local.project.repository.CDM.CompanyAddressRepository;
import com.local.project.repository.Superadmin.ActivitiesRepository;
import com.local.project.repository.Superadmin.ActsRepository;
import com.local.project.repository.Superadmin.CompanyRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TasklistController {
	
	@Autowired
	CompanyRepository comprepo;
	
	@Autowired
	CompanyAddressRepository compaddressrepo;
	
	@Autowired
	ActsRepository actrepo;
	
	@Autowired
	ActivitiesRepository activityrepo;
	
	@Autowired
	CompanyActivityRepository cmpactivityrepo;
	
	
	
	@GetMapping("/task")
	public List<?> gettask(@RequestParam (value="cmpId") int cmpId,
			              @RequestParam (value="locId") String locId,
			              @RequestParam (value="fromDate") String fromDate,
			              @RequestParam (value="toDate") String toDate,
			              @RequestParam( value="actId") String actId,
			              @RequestParam(value="status") String status)
	{
		List<CompanyActivity> cmpactivity = cmpactivityrepo.tasklist(cmpId);
		List <CompanyActivity> tasklist=new ArrayList<CompanyActivity>();
		List<CompanyActivity> finaltask = new ArrayList<>();
		ArrayList<HashMap<String, String>> finalnamelist = new ArrayList<HashMap<String, String>>();
		if(status.equals("Pending")) {
			  tasklist = cmpactivity .stream()
	                                 .filter(s -> s.getApproval_status()==0)
	                                 .collect(Collectors.toList());
			
		}
		else if(status.equals("Completed")) {
			tasklist = cmpactivity .stream()
                                   .filter(s -> s.getApproval_status()==1)
                                   .collect(Collectors.toList());
			
		}
		else {
			tasklist = cmpactivity;
		}
		if(locId.equals("Viewall")) 
		{
			tasklist=tasklist;	   
		}
		else {
				int i=Integer.parseInt(locId); 
				tasklist = tasklist.stream()
                        .filter(s -> s.getCompanies_address_id()==i)
                        .collect(Collectors.toList());
			}

		if((actId.equals("Viewall"))) {
			tasklist=tasklist;
		}
		else {
			int i=Integer.parseInt(actId); 
			tasklist = tasklist.stream()
                               .filter(s -> s.getAct_id()==i)
                               .collect(Collectors.toList());
		}
		
		//date filter
		String from = fromDate;
		String to = toDate;
		for(int i=0;i<tasklist.size();i++) {
			CompanyActivity task=cmpactivity.get(i);
			String duedate=task.getDue_date();
			String result=null;
			LocalDate start = LocalDate.parse(fromDate);
			LocalDate end = LocalDate.parse(toDate);
			LocalDate due=LocalDate.parse(duedate);
			List<LocalDate> totalDates = new ArrayList<>();
		    boolean x = start.compareTo(due) * due.compareTo(end) >= 0;
		    if(x) {
		    	finaltask.add(task);
		    }
		}
		for(int i=0;i<finaltask.size();i++) {
			HashMap<String,String> namelist=new HashMap<String,String>();
			CompanyActivity x = finaltask.get(i);
			Long comId=(long) x.getCom_id();
			Long addId=(long) x.getCompanies_address_id();
			int actsId = x.getAct_id();
			int activityId = x.getActivity_id();
			String file=null;
			if(x.getUndo_status()==0){
                if(!(x.getSave_file_name()==null)) {
                	file=x.getSave_file_name();
                	
                }
                else {
                	file="-";
                }
            }
            else if(x.getUndo_status()==1){
            	if(!(x.getUndo_file_name()==null)) {
                	file=x.getUndo_file_name();
                	
                }
                else {
                	file="-";
                }
               
            }
			
			String Companyname=comprepo.findByCompanyId(comId).getCompany_name();
			String LocationName=compaddressrepo.findLocationByAddressId(addId);
			String ActName=actrepo.findActNameById((long) actsId);
			String ActivityName=activityrepo.findActivityNameById((long) activityId);
			namelist.put("Companyname", Companyname);
			namelist.put("Locationname", LocationName);
			namelist.put("Actname", ActName);
			namelist.put("Activityname", ActivityName);
			namelist.put("DueDate", x.getDue_date());
			namelist.put("ActualDate", x.getActual_date());
			namelist.put("Remark", x.getDelay_remarks());
			namelist.put("FileUpload", file);
			finalnamelist.add(namelist);
			
			
			
		}
		return finalnamelist;
	}

	@GetMapping("/companyname/{id}")
		public String companyname(@PathVariable(value="id") Long id) {
		 String CompanyName=comprepo.findByCompanyId(id).getCompany_name();
			return CompanyName;
		}
	@GetMapping("/locationname/{id}")
	public String locationname(@PathVariable(value="id") Long id) {
	 String LocationName=compaddressrepo.findLocationByAddressId(id);;
	 return LocationName;
	}
	@GetMapping("/actname/{id}")
	public String actname(@PathVariable(value="id") Long id) {
	 String ActName=actrepo.findActNameById(id);
	 return ActName;
	}
	
	@GetMapping("/activityname/{id}")
	public String activityname(@PathVariable(value="id") Long id) {
	 String ActivityName=activityrepo.findActivityNameById(id);
	 return ActivityName;
	}
	
	
	

}
