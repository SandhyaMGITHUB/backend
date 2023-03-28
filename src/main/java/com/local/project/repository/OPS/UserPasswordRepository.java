package com.local.project.repository.OPS;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.local.project.model.CDM.CompanyActivity;
import com.local.project.model.OPS.UserPassword;

@Repository
public interface UserPasswordRepository extends JpaRepository <UserPassword,Integer>{
	
	@Query("SELECT g FROM UserPassword g where g.companies_id = :company_id and g.act_id =:actId and g.location_id =:locId")
	List<UserPassword> Passwordlist(int company_id,int actId,int locId);

	
}
