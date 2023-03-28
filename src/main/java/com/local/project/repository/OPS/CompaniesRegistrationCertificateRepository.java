package com.local.project.repository.OPS;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.local.project.model.CDM.CompanyActivity;
import com.local.project.model.OPS.CompaniesRegistrationCertificate;
import com.local.project.model.OPS.UserPassword;

@Repository
public interface CompaniesRegistrationCertificateRepository extends JpaRepository <CompaniesRegistrationCertificate,Long>{

	@Query("SELECT g FROM CompaniesRegistrationCertificate g where g.companies_id = :company_id and g.act_id =:actId and g.companies_address_id =:locId")
	List<CompaniesRegistrationCertificate> DisplayCertifiate (int company_id,int actId,int locId);
	
}
