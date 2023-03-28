package com.local.project.model.Superadmin;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "registration_companies")
public class RegistrationCompany {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reg_comp_id;
	
	 @NotBlank
	 private String reg_comp_name;
	 
	 private Long created_by;
	 
	 private Long updated_by;
	 
	 private LocalDateTime created_at;

	 private LocalDateTime updated_at;

	 
	public RegistrationCompany() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegistrationCompany(@NotBlank String reg_comp_name, Long created_by, Long updated_by,
			LocalDateTime created_at, LocalDateTime updated_at) {
		super();
		this.reg_comp_name = reg_comp_name;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public Long getReg_comp_id() {
		return reg_comp_id;
	}

	public void setReg_comp_id(Long reg_comp_id) {
		this.reg_comp_id = reg_comp_id;
	}

	public String getReg_comp_name() {
		return reg_comp_name;
	}

	public void setReg_comp_name(String reg_comp_name) {
		this.reg_comp_name = reg_comp_name;
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

