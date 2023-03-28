package com.local.project.repository.CDM;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.local.project.model.CDM.CompanyActivity;
import com.local.project.model.CDM.CompanyAddress;

@Repository
public interface CompanyActivityRepository extends JpaRepository <CompanyActivity,Long>{

@Query("SELECT g FROM CompanyActivity g where g.companies_address_id = :company_address_id")
   List<CompanyActivity> getExistingActivity(int company_address_id);

@Query("SELECT g FROM CompanyActivity g where g.com_id = :company_id")
List<CompanyActivity> tasklist(int company_id);


}