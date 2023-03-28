package com.local.project.service.Superadmin;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.project.model.Superadmin.RegistrationCompany;
import com.local.project.repository.Superadmin.RegistrationCompanyRepository;


@Service
public class RegistrationCompanyService {

	@Autowired
	private RegistrationCompanyRepository repo;
	
	
	public RegistrationCompany get(Long id) {
		return repo.findById(id).get();
	}
	
	@Transactional
	public void save(RegistrationCompany area) {
		
		repo.save(area);
	}
}
