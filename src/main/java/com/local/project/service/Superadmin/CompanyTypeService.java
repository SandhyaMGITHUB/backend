package com.local.project.service.Superadmin;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.project.model.Superadmin.*;
import com.local.project.repository.Superadmin.*;
@Service
public class CompanyTypeService {

	@Autowired
	private CompanyTypeRepository repo;
	
	
	public CompanyType get(Long id) {
		return repo.findById(id).get();
	}
	
	@Transactional
	public void save(CompanyType area) {
		
		repo.save(area);
	}
	
	
}

