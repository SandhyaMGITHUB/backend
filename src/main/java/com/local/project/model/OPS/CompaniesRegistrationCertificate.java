package com.local.project.model.OPS;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Null;

@Entity
@Table(name = "companies_registration_certificate")

public class CompaniesRegistrationCertificate {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int company_registration_certificate_id;
	
	private int companies_id;
	private int act_id;
	private int business_area_id;
	private int state_id;
	private boolean is_assigned;
	private int companies_address_id;
	private int act_state_id;
	private String registration_number;
	private String reg_valid_from;
	private String reg_valid_to;
	private String upload_certificate;
	private int na;
	private String remarks;
	private Long created_by;
	
	private Long updated_by;	 
	private LocalDateTime created_at;
	private LocalDateTime updated_at;	
	
	@org.hibernate.annotations.ColumnDefault("0")
	private Boolean is_deleted=false;
	
	private int IsExpired;
	
	
	public CompaniesRegistrationCertificate(int company_registration_certificate_id, int companies_id, int act_id,
			int business_area_id, int state_id, boolean is_assigned, int companies_address_id, int act_state_id,
			String registration_number, String reg_valid_from, String reg_valid_to, String upload_certificate, int na,
			String remarks, Long created_by, Long updated_by, LocalDateTime created_at, LocalDateTime updated_at,
			Boolean is_deleted, int isExpired) {
		super();
		this.company_registration_certificate_id = company_registration_certificate_id;
		this.companies_id = companies_id;
		this.act_id = act_id;
		this.business_area_id = business_area_id;
		this.state_id = state_id;
		this.is_assigned = is_assigned;
		this.companies_address_id = companies_address_id;
		this.act_state_id = act_state_id;
		this.registration_number = registration_number;
		this.reg_valid_from = reg_valid_from;
		this.reg_valid_to = reg_valid_to;
		this.upload_certificate = upload_certificate;
		this.na = na;
		this.remarks = remarks;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.is_deleted = is_deleted;
		IsExpired = isExpired;
	}

	public CompaniesRegistrationCertificate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCompany_registration_certificate_id() {
		return company_registration_certificate_id;
	}

	public void setCompany_registration_certificate_id(int company_registration_certificate_id) {
		this.company_registration_certificate_id = company_registration_certificate_id;
	}

	public int getCompanies_id() {
		return companies_id;
	}

	public void setCompanies_id(int companies_id) {
		this.companies_id = companies_id;
	}

	public int getAct_id() {
		return act_id;
	}

	public void setAct_id(int act_id) {
		this.act_id = act_id;
	}

	public int getBusiness_area_id() {
		return business_area_id;
	}

	public void setBusiness_area_id(int business_area_id) {
		this.business_area_id = business_area_id;
	}

	public int getState_id() {
		return state_id;
	}

	public void setState_id(int state_id) {
		this.state_id = state_id;
	}

	public boolean isIs_assigned() {
		return is_assigned;
	}

	public void setIs_assigned(boolean is_assigned) {
		this.is_assigned = is_assigned;
	}

	public int getCompanies_address_id() {
		return companies_address_id;
	}

	public void setCompanies_address_id(int companies_address_id) {
		this.companies_address_id = companies_address_id;
	}

	public int getAct_state_id() {
		return act_state_id;
	}

	public void setAct_state_id(int act_state_id) {
		this.act_state_id = act_state_id;
	}

	public String getRegistration_number() {
		return registration_number;
	}

	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}

	public String getReg_valid_from() {
		return reg_valid_from;
	}

	public void setReg_valid_from(String reg_valid_from) {
		this.reg_valid_from = reg_valid_from;
	}

	public String getReg_valid_to() {
		return reg_valid_to;
	}

	public void setReg_valid_to(String reg_valid_to) {
		this.reg_valid_to = reg_valid_to;
	}

	public String getUpload_certificate() {
		return upload_certificate;
	}

	public void setUpload_certificate(String upload_certificate) {
		this.upload_certificate = upload_certificate;
	}

	public int getNa() {
		return na;
	}

	public void setNa(int na) {
		this.na = na;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public int getIsExpired() {
		return IsExpired;
	}

	public void setIsExpired(int isExpired) {
		IsExpired = isExpired;
	}

	
	
	
	
	
}
