package com.local.project.repository.CDM;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.local.project.model.CDM.CompanyAddress;
import com.local.project.model.Superadmin.Acts;
import com.local.project.model.Superadmin.Company;


@Repository
public interface CompanyAddressRepository extends JpaRepository <CompanyAddress,Long> {
	
//	@Query("SELECT c FROM CompanyAddress c WHERE c.company = :imageName")
//	Optional<Company> findByImgName(@Param("imageName") String imageName);
	
//	@Query("SELECT * FROM company_address_acts WHERE company_address_id=?1")
//	List<Acts> findAddressByCompanyId(Long companyId);
	
	
	@Query("SELECT c.company_location FROM CompanyAddress c WHERE c.company_address_id = :addressId")
	String findLocationByAddressId(Long addressId);

	
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CompanyAddress c WHERE c.company_location = :z")
	boolean existByLocationName(String z);

	
	
	

}
