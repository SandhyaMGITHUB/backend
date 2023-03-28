package com.local.project.repository.Superadmin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.local.project.model.Superadmin.CompanyType;

@Repository
public interface CompanyTypeRepository extends JpaRepository<CompanyType,Long>{

}
