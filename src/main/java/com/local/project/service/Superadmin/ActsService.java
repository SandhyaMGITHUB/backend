package com.local.project.service.Superadmin;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.project.model.Superadmin.Acts;
import com.local.project.repository.Superadmin.ActsRepository;

@Service
public class ActsService {

	@Autowired
	ActsRepository actsRepository;
	
	
	public Acts get(Long id) {
		return actsRepository.findById(id).get();
	}
	
	
	@Transactional
	public void save(Acts area) {
		
		actsRepository.save(area);
	}

}
