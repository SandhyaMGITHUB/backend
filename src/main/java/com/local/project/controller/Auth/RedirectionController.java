package com.local.project.controller.Auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.local.project.model.Auth.User;
import com.local.project.service.Auth.UserDetailsServiceImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/redirect")
public class RedirectionController {
	
	@Autowired
	UserDetailsServiceImpl userimpl;

	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	@GetMapping("/subadmin")
	@PreAuthorize("hasRole('SUBADMIN')")
	public String subadminAccess() {
		return "SubAdmin Board.";
	}
	
	@GetMapping("/department")
	@PreAuthorize("hasRole('DEPARTMENT_HOD') or hasRole('DEPARTMENT_REPORTEE')")
	public String DepartmentAccess() {
		return "Ci  Content.";
	}
	
	
	@GetMapping("/ops")
	@PreAuthorize("hasRole('OPS_HEAD') or hasRole('PC') or hasRole('LC')or hasRole('FC')")
	public String OPSAccess() {
		return "OPS Content.";
	}

	@GetMapping("/cdm")
	@PreAuthorize("hasRole('ROLE_RELATION_HEAD') or hasRole('ROLE_RELATION_TEAM') or hasRole('ROLE_LIASION_HEAD') or hasRole('ROLE_LIASION_TEAM_N') or hasRole('ROLE_LIASION_TEAM_S')")
	public String CDMAccess() {
		return "CDM Content.";
	}
	
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('CLIENT_USER')")
	public String userAccess() {
		return "User Content.";}


	
	/*Fetch users with ROLE_USER*/
	@GetMapping("/all-users")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> allusersAccess() {
		return userimpl.listAllUsers();
	}
	
}
