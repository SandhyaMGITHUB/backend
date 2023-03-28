package com.local.project.service.Auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.local.project.repository.Auth.UserRepository;

@Service
public class AuthorDataService {

	@Autowired
	UserRepository userrep;
	
	public Long findCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<String> res = Optional.ofNullable(authentication).map(Authentication::getName);
		Long created_id = userrep.findByUsername(res);
		return created_id;
	}
	
	
}
