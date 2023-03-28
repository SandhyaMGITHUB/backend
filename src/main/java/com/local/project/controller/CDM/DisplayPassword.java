package com.local.project.controller.CDM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.local.project.model.CDM.CompanyAddress;
import com.local.project.model.OPS.UserPassword;
import com.local.project.model.Superadmin.Acts;
import com.local.project.model.Superadmin.ActsStates;
import com.local.project.model.Superadmin.Company;
import com.local.project.repository.CDM.CompanyActivityRepository;
import com.local.project.repository.CDM.CompanyAddressRepository;
import com.local.project.repository.OPS.UserPasswordRepository;
import com.local.project.repository.Superadmin.ActStateRepository;
import com.local.project.repository.Superadmin.ActivitiesRepository;
import com.local.project.repository.Superadmin.ActsRepository;
import com.local.project.repository.Superadmin.CompanyRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DisplayPassword {
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
	
	@Autowired 
	UserPasswordRepository passrepo;
	
	@Autowired
	ActStateRepository actstaterepo;
	
	
	//location dropdown
	@GetMapping("/displaylocation/{actId}/{companyId}")
	public List<CompanyAddress> locationdropdown(@PathVariable Long actId,@PathVariable Long companyId) {
		Acts acts = actrepo.findById(actId).orElseThrow();
		List<ActsStates> actstate = acts.getActList();
		Company company= comprepo.findByCompanyId(companyId);
		List<CompanyAddress> compaddress = company.getCompanyaddress();
		List<CompanyAddress> finallocationlist=new ArrayList<>();
		if(acts.getCentral_or_state()==1) {
			for(int i=0;i<actstate.size();i++) {
				ActsStates actstates=actstate.get(i);
				String subloc =actstates.getIs_sublocation_require();
				if(subloc.equals("state")) {
					Long state_id = (long)actstates.getState_id();
					finallocationlist = compaddress.stream()
                                                   .filter(s -> s.getState().getId()==state_id && s.getState_office()==1)
                                                   .collect(Collectors.toList());
					
					
				}
				else if(subloc.equals("city")){
					Long state_id = (long)actstates.getState_id();
					finallocationlist = compaddress.stream()
                                                   .filter(s -> s.getState().getId()==state_id && s.getCity_office()==1)
                                                   .collect(Collectors.toList());
					
				}
				else if(subloc.equals("address")){
					Long state_id = (long)actstates.getState_id();
					finallocationlist = compaddress.stream()
                                                   .filter(s -> s.getState().getId()==state_id)
                                                   .collect(Collectors.toList());
					
				}
				else if(subloc.equals("no")) {
					Long state_id = (long)actstates.getState_id();
					finallocationlist = compaddress.stream()
                                                   .filter(s -> s.getState().getId()==state_id && s.getCountry_office()==1)
                                                   .collect(Collectors.toList());
					
				}
			
			}
			
			
		}
		else {
			for(int i=0;i<actstate.size();i++) {
				ActsStates actstates=actstate.get(i);
				String subloc =actstates.getIs_sublocation_require();
			   if(subloc.equals("city")){
				   Long state_id = (long)actstates.getState_id();
				  
					finallocationlist = compaddress.stream()
                                                  .filter(s -> s.getState().getId()==state_id && s.getCity_office()==1)
                                                  .collect(Collectors.toList());
					
				}
				else if(subloc.equals("address")){
					Long state_id = (long)actstates.getState_id();
					finallocationlist = compaddress.stream()
                                                   .filter(s -> s.getState().getId()==state_id)
                                                   .collect(Collectors.toList());
					
				}
				else if(subloc.equals("no")) {
					Long state_id = (long)actstates.getState_id();
					finallocationlist = compaddress.stream()
                                                   .filter(s -> s.getState().getId()==state_id && s.getCountry_office()==1)
                                                   .collect(Collectors.toList());
					
				}
			
			}			
			
			
		}
		return finallocationlist;
	}
	
	//act dropdown 
	@GetMapping("/displayacts/{com_id}")
	public List<Acts> actdropdown(@PathVariable Long com_id) {
		
		List<Acts> act=comprepo.findByCompanyId(com_id).getActs();
		act = act.stream()
                .filter(s -> s.isHas_password()==true)
                .collect(Collectors.toList());
		return act;
	}
	
	//password display table
	@GetMapping("/displaypassword")
	public ArrayList<HashMap<String, String>> displaypassword(@RequestParam (value="cmpId") int cmpId,
                                  @RequestParam (value="locId") int locId,
                                  @RequestParam (value="actId") int actId) {
		
		List<UserPassword> passwordlist = passrepo.Passwordlist(cmpId, actId, locId);
		ArrayList<HashMap<String, String>> finallist = new ArrayList<HashMap<String, String>>();
		for(int i=0;i<passwordlist.size();i++) {
			HashMap<String,String> finalpass=new HashMap<String,String>();
			UserPassword pass=passwordlist.get(i);
			Long comId=(long) pass.getCompanies_id();
			int actsId = pass.getAct_id();
			Long addId=(long) pass.getLocation_id();
			
			
			String Companyname=comprepo.findByCompanyId(comId).getCompany_name();
			String LocationName=compaddressrepo.findLocationByAddressId(addId);
			String ActName=actrepo.findActNameById((long) actsId);
			String CodeName=pass.getCode_name();
			String loginId=pass.getLogin_id();
			String PassWord=pass.getPassword();
			
			finalpass.put("Companyname", Companyname);
			finalpass.put("Locationname", LocationName);
			finalpass.put("Actname", ActName);
			finalpass.put("Codename", CodeName);
			finalpass.put("LoginId", loginId);
			finalpass.put("Password", PassWord);
			
			finallist.add(finalpass);
			
		}
		
		
		return finallist;
	}
	
	

}
