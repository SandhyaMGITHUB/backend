package com.local.project.repository.Superadmin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.local.project.model.Superadmin.Activities;

@Repository
public interface ActivitiesRepository extends JpaRepository<Activities,Long>{


	@Query("SELECT c.activity_name FROM Activities c WHERE c.activities_id = :activitiesId")
	String findActivityNameById(Long activitiesId);

}
