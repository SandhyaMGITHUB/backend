package com.local.project.model.Superadmin;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(	name = "act_format")
public class ActFormat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long act_format_id;
	
	private Long act_id;
	private String act_form_name;
	private String act_form_desc;
	private boolean is_calculate;
	
	private long copy_format_id;
	private long copy_act_id;
	
	private Long created_by;	 
	private Long updated_by;	 
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
	@JoinColumn(name = "act_format_id")
	private List<ActFormatHeading> actList;
	
	
	public ActFormat(Long act_id, String act_form_name, String act_form_desc, boolean is_calculate, long copy_format_id,
			long copy_act_id, Long created_by, Long updated_by, LocalDateTime created_at, LocalDateTime updated_at) {
		super();
		this.act_id = act_id;
		this.act_form_name = act_form_name;
		this.act_form_desc = act_form_desc;
		this.is_calculate = is_calculate;
		this.copy_format_id = copy_format_id;
		this.copy_act_id = copy_act_id;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public ActFormat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public List<ActFormatHeading> getActList() {
		return actList;
	}
	public void setActList(List<ActFormatHeading> actList) {
		
		if(this.actList == null) {			
			this.actList = actList;			
		  } 
		else{		    
			  this.actList.retainAll(actList);
			  this.actList.addAll(actList);			  
		  }
	}
	
	
	public Long getAct_format_id() {
		return act_format_id;
	}
	public void setAct_format_id(Long act_format_id) {
		this.act_format_id = act_format_id;
	}
	public Long getAct_id() {
		return act_id;
	}
	public void setAct_id(Long act_id) {
		this.act_id = act_id;
	}
	public String getAct_form_name() {
		return act_form_name;
	}
	public void setAct_form_name(String act_form_name) {
		this.act_form_name = act_form_name;
	}
	public String getAct_form_desc() {
		return act_form_desc;
	}
	public void setAct_form_desc(String act_form_desc) {
		this.act_form_desc = act_form_desc;
	}
	
	public long getCopy_format_id() {
		return copy_format_id;
	}
	public void setCopy_format_id(long copy_format_id) {
		this.copy_format_id = copy_format_id;
	}
	public long getCopy_act_id() {
		return copy_act_id;
	}
	public void setCopy_act_id(long copy_act_id) {
		this.copy_act_id = copy_act_id;
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
	public boolean isIs_calculate() {
		return is_calculate;
	}
	public void setIs_calculate(boolean is_calculate) {
		this.is_calculate = is_calculate;
	}	
	
	
	
}


