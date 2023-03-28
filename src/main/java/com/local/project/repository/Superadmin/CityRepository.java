package com.local.project.repository.Superadmin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.local.project.model.Superadmin.Acts;
import com.local.project.model.Superadmin.City;
import com.local.project.model.Superadmin.State;


@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByStateId(Long stateId);
    Optional<City> findByIdAndStateId(Long id, Long stateId);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM City c WHERE c.city_name = :cityName")
    boolean existsByCityName(@Param("cityName") String cityName);
    
    @Query("SELECT s FROM City s WHERE s.city_name = :Name")
	City  findByCityName(@Param("Name") String Name);
}
