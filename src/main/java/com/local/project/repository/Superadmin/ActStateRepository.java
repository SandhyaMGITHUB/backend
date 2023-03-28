package com.local.project.repository.Superadmin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.local.project.model.Superadmin.Acts;
import com.local.project.model.Superadmin.ActsStates;

@Repository
public interface ActStateRepository extends JpaRepository<ActsStates,Long>{

	@Modifying
	@Transactional
	@Query(value="SELECT act_id FROM acts_states WHERE state_id=?1",nativeQuery=true)
	List<Integer> findbystateid(Long stateId);
	
	
	
	


}
