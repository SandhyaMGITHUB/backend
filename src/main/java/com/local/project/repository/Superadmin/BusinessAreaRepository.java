package com.local.project.repository.Superadmin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.local.project.model.Superadmin.BusinessArea;
import com.local.project.model.Superadmin.Company;

@Repository
public interface BusinessAreaRepository extends JpaRepository<BusinessArea,Long>{

	@Query("SELECT b FROM BusinessArea b WHERE b.business_area_name = :Name")
	BusinessArea findByBusinessArea(String Name);

}