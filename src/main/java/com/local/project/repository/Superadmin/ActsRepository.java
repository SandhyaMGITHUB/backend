package com.local.project.repository.Superadmin;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.local.project.model.Superadmin.Activities;
import com.local.project.model.Superadmin.Acts;

@Repository
public interface ActsRepository extends JpaRepository<Acts,Long> {

//	@Query("SELECT a.actList FROM Acts a WHERE a.act_id =:id")
//	List<ActsStates> findByActId(@Param("id") Long actId);
	
//	@Query("SELECT a.activityList FROM ActsStates  a WHERE a.act_state_id =:id")
//	List<Activities> findByActId(@Param("id") Long actId);
	
	//tasklist query
	@Query("SELECT a.act_name FROM Acts a WHERE a.act_id =:id")
    String findActNameById(@Param("id") Long actId);
	
	@Query("SELECT g.activityList FROM ActsStates g where g.acts.act_id = :id")
	List<Activities> findByActId(@Param("id") Long actId);
	
	@Query("SELECT a FROM Acts a WHERE a.act_id=:id")
	List<Acts> RegisterationActs(@Param("id") Long actId);

	@Modifying
	@Transactional
	@Query(value="SELECT act_id FROM acts_location_types WHERE location_types=?1",nativeQuery=true)
    List<Integer> findActByLoc(int i);

	@Modifying
	@Transactional
	@Query(value="SELECT * FROM acts WHERE act_group_name=?1",nativeQuery=true)
	List<Acts> findByPF(String string);

	@Modifying
	@Transactional
	@Query(value="SELECT act_id FROM acts_company_types WHERE company_type_id=?1",nativeQuery=true)
	List<?> getActByCID(int result);
	
	@Modifying
	@Transactional
	@Query(value="SELECT act_id FROM acts_company_types WHERE company_type_id=?1",nativeQuery=true)
	List<?> getActByIID(int result);

	
}