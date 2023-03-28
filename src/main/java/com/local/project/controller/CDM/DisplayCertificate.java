package com.local.project.controller.CDM;

import java.util.ArrayList;
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

import com.local.project.model.CDM.CompanyAddress;
import com.local.project.model.OPS.CompaniesRegistrationCertificate;
import com.local.project.model.OPS.UserPassword;
import com.local.project.model.Superadmin.Acts;
import com.local.project.model.Superadmin.ActsStates;
import com.local.project.model.Superadmin.Company;
import com.local.project.repository.CDM.CompanyActivityRepository;
import com.local.project.repository.CDM.CompanyAddressRepository;
import com.local.project.repository.OPS.CompaniesRegistrationCertificateRepository;
import com.local.project.repository.Superadmin.ActivitiesRepository;
import com.local.project.repository.Superadmin.ActsRepository;
import com.local.project.repository.Superadmin.CompanyRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DisplayCertificate {
	
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
	CompaniesRegistrationCertificateRepository cmpcertificaterepo;
	
	//act dropdown 
		@GetMapping("/displayregacts/{com_id}")
		public List<Acts> actdropdown(@PathVariable Long com_id) {
			List<Acts> act=comprepo.findByCompanyId(com_id).getActs();
			List<Acts> finalacts=new ArrayList<>();
			for(int i=0;i< act.size(); i++) {
			  List<ActsStates> actstate =act.get(i).getActList();
			  for(int j=0; j< actstate.size(); j++) {
				   ActsStates actstates = actstate.get(i);
				   if(actstates.getIs_registration_require()) {
					   finalacts.add(act.get(i));
				   }
			  }
				
			}
			
			List<Acts> actdropdown = finalacts.stream()
                                              .distinct()               // it will remove duplicate object, It will check duplicate using equals method
                                              .collect(Collectors.toList());
			
			return actdropdown;
		}
		
	
		//location dropdown
		@GetMapping("/reglocation/{actId}/{companyId}")
		public List<CompanyAddress> reglocationdropdown(@PathVariable Long actId,@PathVariable Long companyId) {
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
	//certificate display table
		@GetMapping("/displaycertificate")
		public ArrayList<HashMap<String, String>> displaypassword(@RequestParam (value="cmpId") int cmpId,
	                                  @RequestParam (value="locId") int locId,
	                                  @RequestParam (value="actId") int actId) {
			
			List<CompaniesRegistrationCertificate> certificate = cmpcertificaterepo.DisplayCertifiate(cmpId, actId, locId);
			ArrayList<HashMap<String, String>> finallist = new ArrayList<HashMap<String, String>>();
			for(int i=0;i<certificate.size();i++) {
				HashMap<String,String> finalreg=new HashMap<String,String>();
				CompaniesRegistrationCertificate reg=certificate.get(i);
				Long comId=(long) reg.getCompanies_id();
				int actsId = reg.getAct_id();
				Long addId=(long) reg.getCompanies_address_id();
				
				
				String State=compaddressrepo.findById(addId).orElseThrow().getState().getName();
				String LocationName=compaddressrepo.findLocationByAddressId(addId);
				String ActName=actrepo.findActNameById((long) actsId);
				String RegNo=reg.getRegistration_number();
				String ValidFrom=reg.getReg_valid_from();
				String ValidTO=reg.getReg_valid_to();
				String UploadCerticate=reg.getUpload_certificate();
				
				if(ValidTO == null) {
					ValidTO= "--";
				}
				
				finalreg.put("State", State);
				finalreg.put("Locationname", LocationName);
				finalreg.put("Actname", ActName);
				finalreg.put("RegNO", RegNo);
				finalreg.put("ValidFrom", ValidFrom);
				finalreg.put("ValidTo", ValidTO);
				finalreg.put("Certificate", UploadCerticate);
				
				finallist.add(finalreg);
				
			}
			
			
			return finallist;
	}
		
	

}
