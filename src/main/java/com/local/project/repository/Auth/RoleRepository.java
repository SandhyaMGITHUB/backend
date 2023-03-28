package com.local.project.repository.Auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.local.project.enumeration.ERole;
import com.local.project.model.Auth.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(ERole name);
	
	
	
	
}