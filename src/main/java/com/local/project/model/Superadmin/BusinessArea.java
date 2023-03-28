package com.local.project.model.Superadmin;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@Entity
//@EntityListeners(AuditingEntityListener.class)
@Table(name = "business_area")
public class BusinessArea{
//	public class BusinessArea extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long business_area_id;
    
    @NotBlank
    private String business_area_name;
    
    private Long created_by;
	 
	 private Long updated_by;
	 
	 private LocalDateTime created_at;

	 private LocalDateTime updated_at;

	public BusinessArea(){
        super();
    }
	
	public BusinessArea(String business_area_name,Long created_by,Long updated_by,LocalDateTime created_at,LocalDateTime updated_at) {
        super();
        this.business_area_name = business_area_name;
        this.created_by=created_by;
		this.updated_by=updated_by;
		this.created_at =created_at;
		this.updated_at =updated_at;
    }
	
	public Long getId() {
        return business_area_id;
    }
	
	public void setId(Long business_area_id) {
        this.business_area_id = business_area_id;
    }
	
	public String getBusiness_area_name() {
        return business_area_name;
    }
	
	public void setBusiness_area_name(String business_area_name) {
        this.business_area_name = business_area_name;
    }
	
	public Long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Long created_by) {
		this.created_by = created_by;
	}

	public Long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Long updated_by) {
		this.updated_by = updated_by;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

}
