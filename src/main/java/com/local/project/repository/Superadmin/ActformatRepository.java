package com.local.project.repository.Superadmin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.local.project.model.Superadmin.ActFormat;
import com.local.project.model.Superadmin.ActFormatHeading;

@Repository
public interface ActformatRepository extends JpaRepository<ActFormat, Long>{

	@Query("SELECT a.act_form_name FROM ActFormat a WHERE a.act_id =:id")
	List<String> findFormByActId(@Param("id") Long id);

	@Query("SELECT a FROM ActFormat a WHERE a.act_id =:id and a.act_form_name=:act_form_name")
	List<ActFormat> findFormByActIds(@Param("id")Long id,@Param("act_form_name") String act_form_name);

	@Query("SELECT a FROM ActFormatHeading a WHERE a.act_id =:id and a.act_format_id=:act_format_id")
	List<ActFormatHeading> findHeadingsByActIds(Long id, Long act_format_id);

//	@Query(value = "SELECT * FROM act_format_input_column WHERE input_type = ?1", nativeQuery = true)
//	List<?> findSummary(String summaryname);
//	
	
	@Query(value = "SELECT * FROM act_format_input_column", nativeQuery = true)
	List<?> findSummary();

//	@Query("delete from ActFormatHeading a where a.act_format_id =: id")
//	@Query(value="DELETE af,afh from ActFormat af inner join ActFormatHeading afh on af.act_format_id = afh.act_format_id where af.act_format_id=:id ",nativeQuery = true)
//	void deleteByIds(@Param("id")Long id);

}
