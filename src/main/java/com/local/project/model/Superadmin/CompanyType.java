package com.local.project.model.Superadmin;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "company_type")
public class CompanyType {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long company_type_id;
	
	 @NotBlank
	 private String company_type_name;
	 
	 private Long created_by;
	 
	 private Long updated_by;
	 
	 private LocalDateTime created_at;

	 private LocalDateTime updated_at;

	public CompanyType(@NotBlank String company_type_name,Long created_by,Long updated_by,LocalDateTime created_at,LocalDateTime updated_at) {
		super();
		this.company_type_name = company_type_name;
		this.created_by=created_by;
		this.updated_by=updated_by;
		this.created_at =created_at;
		this.updated_at =updated_at;
	}

	public CompanyType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getCompany_type_id() {
		return company_type_id;
	}

	public void setCompany_type_id(Long company_type_id) {
		this.company_type_id = company_type_id;
	}

	public String getCompany_type_name() {
		return company_type_name;
	}

	public void setCompany_type_name(String company_type_name) {
		this.company_type_name = company_type_name;
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