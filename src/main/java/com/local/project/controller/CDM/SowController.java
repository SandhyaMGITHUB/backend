package com.local.project.controller.CDM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.local.project.model.Auth.Role;
import com.local.project.model.Auth.User;
import com.local.project.model.CDM.CompanyAddress;
import com.local.project.model.Superadmin.Acts;
import com.local.project.model.Superadmin.Company;
import com.local.project.repository.Auth.UserRepository;
import com.local.project.repository.CDM.CompanyAddressRepository;
import com.local.project.repository.Superadmin.CompanyRepository;
import com.local.project.service.Superadmin.companyservice;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/sow")


public class SowController {
	
	@Autowired
	companyservice companyservice;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyRepository comprepo;
	
	@Autowired
	CompanyAddressRepository compaddressrepo;
	
	/*sow*/	
	@GetMapping("/get-all")
    public List<Company> getAllNotes() {
		List<Company> companylist = companyservice.getIfNotDeleted();
        return companylist;
    }
	
	
	@GetMapping("/cdm/{comId}")
	public List<User> getcdm(@PathVariable Long comId) {
		List<User> relationhead = userRepository.findAllUsers2(10);
		List<User> relationteam = userRepository.findAllUsers2(12);
		List<User> user = Stream.of(relationhead,relationteam)
                                  .flatMap(x -> x.stream())
                                  .collect(Collectors.toList());
		for(int i=0;i<user.size();i++) {
			User u = user.get(i);
		}
		
		
		return user;
	}
	
	
	@GetMapping("/cdm/finalsow")
	public List<Company> getfinalsow()
	{
		List<Company> alllist = comprepo.findAll();
		for(int i=0;i<alllist.size();i++) {
			HashMap<String,String> finalsow=new HashMap<String,String>();
			Company loc = alllist.get(i);
			Long CompanyId= loc.getId();
			String CompanyName=loc.getCompany_name();
			String ContractDate=loc.getCompany_contract_date();
			String GroupName=loc.getCompany_group_name();
			Boolean pf=loc.getPf();
			Boolean Pt=loc.getPt();
			Boolean esi=loc.getEsi();
			Boolean clr=loc.getClra();
			Boolean fc=loc.getLc();
			Boolean lwf =loc.getLwf();
			Boolean lc=loc.getLc();
			Boolean ic=loc.getIc();
			
			
			
		}
		return alllist;
	}
	
	@GetMapping("/getcdm/{comId}")
	public List<User> getcdmname(@PathVariable Long comId) {
		List<User> relationhead= userRepository.findAllUsers2(10);
		List<User> relationteam= userRepository.findAllUsers2(12);
		
		List<User> list=new ArrayList<>();
		List<Long> UserId = userRepository.findUserByComId(comId);
		List<User> finaluserlist= new ArrayList<>();
		
		for(int i=0;i<UserId.size();i++) {
			Long id = UserId.get(i);
			User userdetail = userRepository.findById(id).orElseThrow();
			list.add(userdetail);
		}
		List<User> userlist = Stream.of(relationhead,relationteam)
                                    .flatMap(x -> x.stream())
                                    .collect(Collectors.toList());
		list.retainAll(userlist);
		
		 finaluserlist = list.stream()
                .distinct()
                 .collect(Collectors.toList());
		return finaluserlist;
	}

}
