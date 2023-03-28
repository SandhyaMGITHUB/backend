package com.local.project.model.Superadmin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;


@Entity
@Table(	name = "acts")
//@SecondaryTable(name="acts_company_types")
public class Acts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long act_id;	
	
	private String act_name;
	private String act_group_name;
	private int central_or_state;
	private int act_applicable_state;
	private int business_area_id;
	private boolean all_industries;
	private boolean all_company_types;
	private String act_start_date;
	private String act_end_date;
	private boolean has_abstract;
	private boolean has_password;
	private String abstract_path;
	private String abstract_file;
	
	private Long created_by;	 
	private Long updated_by;	 
	private LocalDateTime created_at;
	private LocalDateTime updated_at;	
	
	@org.hibernate.annotations.ColumnDefault("0")
	private Boolean is_deleted=false;	
	
	@Lob
	private byte[] data;
	private String document_type;
	
		
	@ElementCollection
    @CollectionTable(name = "acts_company_types", joinColumns = @JoinColumn(name = "act_id"))
    private Set<Integer> company_type_id;
	
	@ElementCollection
    @CollectionTable(name = "acts_industries", joinColumns = @JoinColumn(name = "act_id"))
    private Set<Integer> industry_id;
	
	@ElementCollection
    @CollectionTable(name = "acts_location_types", joinColumns = @JoinColumn(name = "act_id"))
    private Set<Integer> location_types;


	
//	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true)
//	@JoinColumn(name = "act_id")
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
	@JoinColumn(name = "act_id")
	private List<ActsStates> actList;
		
	
	public Acts(String act_name, String act_group_name, int central_or_state, int act_applicable_state,
			int business_area_id, boolean all_industries, boolean all_company_types, String act_start_date,
			String act_end_date, boolean has_abstract, boolean has_password,String abstract_path, String abstract_file, 
			Long created_by,String document_type,byte[] data,
			Set<Integer> company_type_id,Set<Integer> industry_id,Set<Integer> location_types) {
		super();
		this.act_name = act_name;
		this.act_group_name = act_group_name;
		this.central_or_state = central_or_state;
		this.act_applicable_state = act_applicable_state;
		this.business_area_id = business_area_id;
		this.all_industries = all_industries;
		this.all_company_types = all_company_types;
		this.act_start_date = act_start_date;
		this.act_end_date = act_end_date;
		this.has_abstract = has_abstract;
		this.has_password = has_password;
		this.abstract_path = abstract_path;
		this.abstract_file = abstract_file;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.is_deleted = is_deleted;
		this.company_type_id=company_type_id;
		this.industry_id=industry_id;
		this.location_types=location_types;
		this.data = data;
		this.document_type = document_type;
	}

	public Acts() {
		
	}
	

	public Acts(String abstract_file, String document_type, byte[] data) {
		super();
		this.abstract_file = abstract_file;
		this.document_type = document_type;
		this.data = data;
	}
	
	public List<ActsStates> getActList() {
		return actList;
	}

	public void setActList(List<ActsStates> actList) {
		
		if(this.actList == null) {
			
			this.actList = actList;
			
		  } 
		else{
		    
			  this.actList.retainAll(actList);
			  this.actList.addAll(actList);
			  
		  }
	}

	public Long getAct_id() {
		return act_id;
	}

	public void setAct_id(Long act_id) {
		this.act_id = act_id;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public String getAct_group_name() {
		return act_group_name;
	}

	public void setAct_group_name(String act_group_name) {
		this.act_group_name = act_group_name;
	}

	public int getCentral_or_state() {
		return central_or_state;
	}

	public void setCentral_or_state(int central_or_state) {
		this.central_or_state = central_or_state;
	}

	public int getAct_applicable_state() {
		return act_applicable_state;
	}

	public void setAct_applicable_state(int act_applicable_state) {
		this.act_applicable_state = act_applicable_state;
	}

	public int getBusiness_area_id() {
		return business_area_id;
	}

	public void setBusiness_area_id(int business_area_id) {
		this.business_area_id = business_area_id;
	}

	public boolean isAll_industries() {
		return all_industries;
	}

	public void setAll_industries(boolean all_industries) {
		this.all_industries = all_industries;
	}

	public boolean isAll_company_types() {
		return all_company_types;
	}

	public void setAll_company_types(boolean all_company_types) {
		this.all_company_types = all_company_types;
	}

	public String getAct_start_date() {
		return act_start_date;
	}

	public void setAct_start_date(String act_start_date) {
		this.act_start_date = act_start_date;
	}

	public String getAct_end_date() {
		return act_end_date;
	}

	public void setAct_end_date(String act_end_date) {
		this.act_end_date = act_end_date;
	}

	public boolean isHas_abstract() {
		return has_abstract;
	}

	public void setHas_abstract(boolean has_abstract) {
		this.has_abstract = has_abstract;
	}

	public boolean isHas_password() {
		return has_password;
	}

	public void setHas_password(boolean has_password) {
		this.has_password = has_password;
	}
	
	

	public String getAbstract_path() {
		return abstract_path;
	}

	public void setAbstract_path(String abstract_path) {
		this.abstract_path = abstract_path;
	}

	public String getAbstract_file() {
		return abstract_file;
	}

	public void setAbstract_file(String abstract_file) {
		this.abstract_file = abstract_file;
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

	public Set<Integer> getCompany_type_id() {
		return company_type_id;
	}

	public void setCompany_type_id(Set<Integer> company_type_id) {
		this.company_type_id = company_type_id;
	}

	public Set<Integer> getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(Set<Integer> industry_id) {
		this.industry_id = industry_id;
	}

	public Set<Integer> getLocation_types() {
		return location_types;
	}

	public void setLocation_types(Set<Integer> location_types) {
		this.location_types = location_types;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getDocument_type() {
		return document_type;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}
	
	
	

}
