package com.local.project.repository.Superadmin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.local.project.model.Superadmin.Company;
import com.local.project.model.Superadmin.State;


@Repository
public interface StateRepository extends JpaRepository<State, Long> {

	boolean existsByName(String name);

    //State findOne(String stringCellValue);
	
	@Query("SELECT s FROM State s WHERE s.name = :Name")
	State  findByName(@Param("Name") String Name);
	
	

	//State findByName(String stringCellValue);

}