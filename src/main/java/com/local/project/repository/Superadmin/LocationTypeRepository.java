package com.local.project.repository.Superadmin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.local.project.model.Superadmin.City;
import com.local.project.model.Superadmin.LocationType;

public interface LocationTypeRepository extends JpaRepository<LocationType,Long>{
	
	
	 @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM LocationType l WHERE l.location_name = :Name")
	    boolean existsByLocationName(@Param("Name") String Name);
	    
	    @Query("SELECT l FROM LocationType l WHERE l.location_name = :Name")
		LocationType  findByLocationName(@Param("Name") String Name);

}
