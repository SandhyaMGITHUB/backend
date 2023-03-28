package com.local.project.service.CDM;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.project.model.Superadmin.ActsStates;
import com.local.project.repository.CDM.CompanyAddressRepository;
import com.local.project.repository.Superadmin.ActStateRepository;
import com.local.project.repository.Superadmin.ActsRepository;

@Service
public class CompanyAddressSerivces {
	
	@Autowired
	CompanyAddressRepository cmprepo;
	
	@Autowired
	ActStateRepository actstaterepo;
	@Autowired
	ActsRepository actsrepo;

	@Autowired
	ActStateRepository apprepo;
	
	@Transactional
	public List<?>getactsapp(Long stateId){
		List<?> alldata =actstaterepo.findbystateid(stateId);
		
		 return alldata;
		
	}

}
