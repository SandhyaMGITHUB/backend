package com.local.project.service.Superadmin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.local.project.enumeration.ERole;
import com.local.project.model.Auth.Role;
import com.local.project.model.Auth.User;
import com.local.project.model.CDM.CompanyAddress;
import com.local.project.model.Superadmin.Acts;
import com.local.project.model.Superadmin.ActsStates;
import com.local.project.model.Superadmin.BusinessArea;
import com.local.project.model.Superadmin.Company;
import com.local.project.repository.Auth.RoleRepository;
import com.local.project.repository.Auth.UserRepository;
import com.local.project.repository.CDM.CompanyAddressRepository;
import com.local.project.repository.Superadmin.ActStateRepository;
import com.local.project.repository.Superadmin.ActsRepository;
import com.local.project.repository.Superadmin.BusinessAreaRepository;
import com.local.project.repository.Superadmin.CityRepository;
import com.local.project.repository.Superadmin.CompanyRepository;
import com.local.project.repository.Superadmin.StateRepository;
import com.local.project.service.Auth.AuthorDataService;

import net.bytebuddy.utility.RandomString;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.time.LocalDateTime;

@Service
public class companyservice {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BusinessAreaRepository barepo;
	
	@Autowired
	private CompanyRepository repo; 
	
	@Autowired 
	private ActsRepository actsrepo;
	 
	@Autowired
	private ActStateRepository actstaterepo;
	
	@Autowired
	private ActsService actservice;
	
	@Autowired
	StateRepository staterepo;
	
	@Autowired
	CityRepository cityrepo;
	
	@Autowired
	CompanyAddressRepository addressrepo;
	
	@Autowired
	companyservice compservice;
	
	@Autowired
	AuthorDataService authorservice;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	/*List all subdomains*/
	public List<Company> listAll(){
		return repo.findAll();
	}	
	
	public Company get(Long id) {
		return repo.findById(id).get();
	}
	
	@Transactional
	public void save(Company comp) {
		
		repo.save(comp);
	}

	public List<Company> getIfNotDeleted() {
		// TODO Auto-generated method stub
		return repo.getIfNotDeleted();
	}

	public List<Acts> getcompanyacts(Company savedcompany) {
		List<Acts> actslist= actsrepo.findAll();
		List<Acts> PFActs= new ArrayList<Acts>();
		List<Acts> ESIAct= new ArrayList<Acts>();
		List<Acts> PTActs= new ArrayList<Acts>();
		List<Acts> CLRAActs= new ArrayList<Acts>();
		List<Acts> FCActs =new ArrayList<Acts>();
		List<Acts> LCActs =new ArrayList<Acts>();
		List<Acts> ICActs=new ArrayList<Acts>();
		List<Acts> Companytypeacts= new ArrayList<Acts>();
		List<Acts> Industrytypeacts= new ArrayList<Acts>();
		//BusinessArea b_id = null;
		if(savedcompany.getPf()) {
		     PFActs = actsrepo.findByPF("PF");
		}
		if(savedcompany.getEsi()) {
			 ESIAct = actsrepo.findByPF("ESI");
		}
		if(savedcompany.getPt()) {
			PTActs  = actsrepo.findByPF("PT");
		}
		if(savedcompany.getFc()) {
			BusinessArea b_id = barepo.findByBusinessArea("Financial Compliance");
			List<Acts>app= actsrepo.findAll();
		    FCActs= app
	                   .stream()
	                   .filter(s->s.getBusiness_area_id()==b_id.getId())
	                   .collect(Collectors.toList());
		}
		if(savedcompany.getLc()) {
			BusinessArea b_id = barepo.findByBusinessArea("Labour Compliance");
			List<Acts>app= actsrepo.findAll();
		    LCActs= app
	                   .stream()
	                   .filter(s->s.getBusiness_area_id()==b_id.getId())
	                   .collect(Collectors.toList());
		}
		if(savedcompany.getIc()) {
			BusinessArea b_id = barepo.findByBusinessArea("Industrial Compliance");
			List<Acts>app= actsrepo.findAll();
		    ICActs= app
	                   .stream()
	                   .filter(s->s.getBusiness_area_id()==b_id.getId())
	                   .collect(Collectors.toList());
		}
		
		//company type id
		String x2 = savedcompany.getCompany_type_id();
		int result2 =Integer.parseInt(x2);
		List<?> actidlist2=actsrepo.getActByCID(result2);
		List<Acts> companyactsid2= new ArrayList<Acts>();
		for(int i=0; i<actidlist2.size(); i++)
		{
			Object y = actidlist2.get(i);
			long l=Long.parseLong(String.valueOf(y));
			Companytypeacts.add(actservice.get(l));
		}
		//industry id
		String x1 = savedcompany.getIndustry_id();
		int result1 =Integer.parseInt(x1);
		List<?> actidlist1=actsrepo.getActByIID(result1);
		List<Acts> companyactsid1= new ArrayList<Acts>();
		for(int i=0; i<actidlist1.size(); i++)
		{
			Object y = actidlist1.get(i);
			long l=Long.parseLong(String.valueOf(y));
			Industrytypeacts.add(actservice.get(l));
		}
		
		List<Acts> finalactlist = Stream.of(PFActs, ESIAct, PTActs,FCActs,ICActs,LCActs,CLRAActs,Industrytypeacts,Companytypeacts)
				                        .flatMap(x -> x.stream())
				                        .collect(Collectors.toList());
	 System.out.println(finalactlist);
				
				
				
				
				
		
	  return finalactlist;
	}

	public List<Acts> getcompanyaddressacts(CompanyAddress savedAddress) {
		Set<Integer> a = savedAddress.getLocation_types();
		Long stateid = savedAddress.getState().getId();
		
		//actsbystateid
		List<?> stateacts=actstaterepo.findbystateid(stateid);
		List<Acts> actsnew= new ArrayList<Acts>();
		for(int i = 0; i<stateacts.size(); i++) {
			
			Object y = stateacts.get(i);
			long l=Long.parseLong(String.valueOf(y));
			actsnew.add(actservice.get(l));
			
		}
        //actsbylocationtype
		List<Integer> targetList = List.copyOf(a);
		List<Integer> actsloc= new ArrayList<>();
		List<Acts> loctypeacts= new ArrayList<Acts>();
		for(int i=0; i<targetList.size();i++) 
		{
			
			Integer id = targetList.get(i);
			List<Integer> actid = actsrepo.findActByLoc(id);
			actsloc.addAll(actid);
		}
		 List<Integer> newList = actsloc.stream()
                                          .distinct()
                                         .collect(Collectors.toList());
        for(int i = 0; i < newList.size(); i++) {
			
			Object y = newList.get(i);
			long l=Long.parseLong(String.valueOf(y));
			loctypeacts.add(actservice.get(l));
			
		}
		
        List<Acts> finalactlist = Stream.of(loctypeacts,actsnew)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
        List<Acts> newList1 = finalactlist.stream()
                                          .distinct()
                                           .collect(Collectors.toList());
       
	return newList1;
	}
	
	public void saveclientuser(String email,Integer addressId,Long companyId) {
		
		User olduser =new User();
		User newuser =new User();
		List<Integer> newaddresslist= new ArrayList<Integer>();
		//check email already exist
		if(userRepository.existsByEmail(email)){
			//get the user
			olduser=userRepository.findByEmail(email).orElseThrow();
			// get user present company address id
			 List<Integer> ca_list=olduser.getCompanyaddress();
			 if(ca_list.contains(addressId)) {
				 ca_list.add(addressId);
				 newaddresslist=ca_list; 
			 }
			 else {
				 ca_list.add(addressId);
				 newaddresslist=ca_list;
			 }
			//saving the value
			    olduser.setCompany_id(companyId);
				olduser.setCompanyaddress(newaddresslist);
				newuser=userRepository.save(olduser);
				
		}
		else {
			//user new list
			newaddresslist.add(addressId);			
			LocalDateTime created_at=LocalDateTime.now();
			//password generated
			String password = RandomString.make(32);
			String passworduser = encoder.encode(password);
			Long createdby = authorservice.findCurrentUser();
			//creating new user
			User user =new User(email,
					            passworduser,
					            newaddresslist,
					            createdby,
					            created_at);
			Set<Role> roles = new HashSet<>();
			//setting client role to user
     		Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT_USER);
			roles.add(userRole);
			user.setRoles(roles);
			user.setIsenabled(true);
			user.setCompany_id(companyId);
			newuser=userRepository.save(user);
		}
			
	}

	

}

