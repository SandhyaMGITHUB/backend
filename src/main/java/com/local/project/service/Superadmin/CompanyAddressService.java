package com.local.project.service.Superadmin;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.local.project.model.CDM.CompanyAddress;
import com.local.project.model.Superadmin.Acts;
import com.local.project.repository.Superadmin.ActsRepository;

public class CompanyAddressService {
	
	@Autowired 
	private ActsRepository actsrepo;
	
	
	@Autowired
	private ActsService actservice;


	

}
