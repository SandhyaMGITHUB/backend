package com.local.project.model.Superadmin;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "industries")
public class Industry {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long industry_id;
	
	 @NotBlank
	 private String industry_name;
	 
	 private Long created_by;
	 
	 private Long updated_by;
	 
	 private LocalDateTime created_at;

	 private LocalDateTime updated_at;

	public Industry(@NotBlank String industry_name, Long created_by, Long updated_by, LocalDateTime created_at,
			LocalDateTime updated_at) {
		super();
		this.industry_name = industry_name;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public Industry() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Long getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(Long industry_id) {
		this.industry_id = industry_id;
	}

	public String getIndustry_name() {
		return industry_name;
	}

	public void setIndustry_name(String industry_name) {
		this.industry_name = industry_name;
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
