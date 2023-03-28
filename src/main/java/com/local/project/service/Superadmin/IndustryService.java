package com.local.project.service.Superadmin;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.project.model.Superadmin.Industry;
import com.local.project.repository.Superadmin.IndustryRepository;


@Service
public class IndustryService {

	@Autowired
	private 
	IndustryRepository repo;
	
	
	public Industry get(Long id) {
		return repo.findById(id).get();
	}
	
	@Transactional
	public void save(Industry area) {
		
		repo.save(area);
	}
}
