package com.local.project.repository.Superadmin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.local.project.model.Superadmin.City;
import com.local.project.model.Superadmin.Company;

public interface CompanyRepository extends JpaRepository<Company,Long>{

	@Query("SELECT c FROM Company c WHERE c.is_deleted = 0")
	List<Company> getIfNotDeleted();

	@Query("SELECT c FROM Company c WHERE c.file_name = :imageName")
	Optional<Company> findByImgName(@Param("imageName") String imageName);
	
	@Query("SELECT c FROM Company c WHERE c.company_name = :companyName")
	Company  findByCompanyName(@Param("companyName") String companyName);
	
	 @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Company c WHERE c.company_name = :companyName")
	 boolean existsByCompanyName(@Param("companyName") String companyName);
	 
	 @Query("SELECT c FROM Company c WHERE c.id = :companyId")
	 Company  findByCompanyId(@Param("companyId") Long companyId);
	 
	boolean existsById(Long id);
	
//	@Query("SELECT c FROM Company c WHERE c.company_name=:companyName")
//	Optional CompanyAddress void findByCompanyName(@Param("companyName") String companyName);

}
