package com.local.project.model.Superadmin;

import java.time.LocalDateTime;
import java.util.HashSet;
//import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import com.local.project.model.CDM.CompanyAddress;

import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
//@EntityListeners(AuditingEntityListener.class)
@Table(	name = "company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String company_name;
	private String company_group_name;
	private String company_short_name;
	private String company_type_id;
	private String industry_id;
	private String company_trade_union;
	private String company_turnover;
	private String domain_name;
	
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private String company_incorporation_date;
	
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private String company_contract_date;	
	
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")

	private String company_contractend_date;        
	
	private String importance_level;
	
	@org.hibernate.annotations.ColumnDefault("0")
	private Boolean is_deleted=false;
	
	private String is_selfservice;
	
	private Long created_by;
	 
	private Long updated_by;
	 
	private LocalDateTime created_at;

	private LocalDateTime updated_at;
	
	private String file_name;	
	private String document_type; 
	private String upload_dir;
	
	@Lob
	private byte[] data;
	
	private Boolean pf;
	private Boolean esi;
	private Boolean pt;
	private Boolean lwf;
	private Boolean fc;
	private Boolean ic;
	private Boolean clra;
	private Boolean lc;
	
	
	@OneToMany(mappedBy = "company", cascade = {CascadeType.ALL})
	private List <CompanyAddress> companyaddress;
		
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "company_acts_applicable", 
				joinColumns = @JoinColumn(name = "company_id"), 
				inverseJoinColumns = @JoinColumn(name = "act_id"))
	private List<Acts> acts;
	
	
	public List<Acts> getActs() {
		return acts;
	}



	public void setActs(List<Acts> acts) {
		this.acts = acts;
	}



	public List<CompanyAddress> getCompanyaddress() {
		return companyaddress;
	}



	public void setCompanyaddress(List<CompanyAddress> companyaddress) {
		this.companyaddress = companyaddress;
	}

    public Company() {
		
	}

	public Company(String file_name, String document_type, byte[] data) {
		super();
		this.file_name = file_name;
		this.document_type = document_type;
		this.data = data;
	}



	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getData() {
	    return data;
	  }

	  public void setData(byte[] data) {
	    this.data = data;
	  }
	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getDomain_name() {
		return domain_name;
	}

	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

	public String getCompany_group_name() {
		return company_group_name;
	}

	public void setCompany_group_name(String company_group_name) {
		this.company_group_name = company_group_name;
	}

	public String getCompany_short_name() {
		return company_short_name;
	}

	public void setCompany_short_name(String company_short_name) {
		this.company_short_name = company_short_name;
	}

	public String getCompany_type_id() {
		return company_type_id;
	}

	public void setCompany_type_id(String company_type_id) {
		this.company_type_id = company_type_id;
	}

	public String getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(String industry_id) {
		this.industry_id = industry_id;
	}

	public String getCompany_trade_union() {
		return company_trade_union;
	}

	public void setCompany_trade_union(String company_trade_union) {
		this.company_trade_union = company_trade_union;
	}

	public String getCompany_turnover() {
		return company_turnover;
	}

	public void setCompany_turnover(String company_turnover) {
		this.company_turnover = company_turnover;
	}

	public String getCompany_incorporation_date() {
		return company_incorporation_date;
	}

	public void setCompany_incorporation_date(String company_incorporation_date) {
		this.company_incorporation_date = company_incorporation_date;
	}

	public String getCompany_contract_date() {
		return company_contract_date;
	}

	public void setCompany_contract_date(String company_contract_date) {
		this.company_contract_date = company_contract_date;
	}

	public String getCompany_contractend_date() {
		return company_contractend_date;
	}

	public void setCompany_contractend_date(String company_contractend_date) {
		this.company_contractend_date = company_contractend_date;
	}
	

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getIs_selfservice() {
		return is_selfservice;
	}

	public void setIs_selfservice(String is_selfservice) {
		this.is_selfservice = is_selfservice;
	}

	public String getImportance_level() {
		return importance_level;
	}

	public void setImportance_level(String importance_level) {
		this.importance_level = importance_level;
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

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getDocument_type() {
		return document_type;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}

	public String getUpload_dir() {
		return upload_dir;
	}

	public void setUpload_dir(String upload_dir) {
		this.upload_dir = upload_dir;
	}


	public Boolean getPf() {
		return pf;
	}

	public void setPf(Boolean pf) {
		this.pf = pf;
	}

	public Boolean getEsi() {
		return esi;
	}

	public void setEsi(Boolean esi) {
		this.esi = esi;
	}

	public Boolean getPt() {
		return pt;
	}

	public void setPt(Boolean pt) {
		this.pt = pt;
	}

	public Boolean getLwf() {
		return lwf;
	}

	public void setLwf(Boolean lwf) {
		this.lwf = lwf;
	}

	public Boolean getClra() {
		return clra;
	}

	public void setClra(Boolean clra) {
		this.clra = clra;
	}



	public Boolean getFc() {
		return fc;
	}



	public void setFc(Boolean fc) {
		this.fc = fc;
	}
	
   public Boolean getIc() {
		return ic;
	}
   
   public void setIc(Boolean ic) {
		this.ic = ic;
	}

   public Boolean getLc() {
		return lc;
	}
   
   public void setLc(Boolean lc) {
		this.lc = lc;
	}

	
   public Company(Long id, String company_name, String company_group_name, String company_short_name,
		String company_type_id, String industry_id, String company_trade_union, String company_turnover,
		String domain_name, String company_incorporation_date, String company_contract_date,
		String company_contractend_date, String importance_level, Boolean is_deleted, String is_selfservice,
		Long created_by, Long updated_by, LocalDateTime created_at, LocalDateTime updated_at, String file_name,
		String document_type, String upload_dir, byte[] data, Boolean pf, Boolean esi, Boolean pt, Boolean lwf,
		Boolean fc, Boolean ic, Boolean clra, Boolean lc, List<CompanyAddress> companyaddress, List<Acts> acts) {
	super();
	this.id = id;
	this.company_name = company_name;
	this.company_group_name = company_group_name;
	this.company_short_name = company_short_name;
	this.company_type_id = company_type_id;
	this.industry_id = industry_id;
	this.company_trade_union = company_trade_union;
	this.company_turnover = company_turnover;
	this.domain_name = domain_name;
	this.company_incorporation_date = company_incorporation_date;
	this.company_contract_date = company_contract_date;
	this.company_contractend_date = company_contractend_date;
	this.importance_level = importance_level;
	this.is_deleted = is_deleted;
	this.is_selfservice = is_selfservice;
	this.created_by = created_by;
	this.updated_by = updated_by;
	this.created_at = created_at;
	this.updated_at = updated_at;
	this.file_name = file_name;
	this.document_type = document_type;
	this.upload_dir = upload_dir;
	this.data = data;
	this.pf = pf;
	this.esi = esi;
	this.pt = pt;
	this.lwf = lwf;
	this.fc = fc;
	this.ic = ic;
	this.clra = clra;
	this.lc = lc;
	this.companyaddress = companyaddress;
	this.acts = acts;
}
	
	
}
