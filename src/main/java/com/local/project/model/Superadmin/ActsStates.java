package com.local.project.model.Superadmin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
public class ActsStates implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long act_state_id;
	
	private int state_id;
	private Boolean is_registration_require;
	private String is_sublocation_require;
	

	@ManyToOne
    @JoinColumn(name="act_id")
    private Acts acts;
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch= FetchType.LAZY)
	@JoinColumn(name = "act_state_id", nullable = false)
	private List<Activities> activityList = new ArrayList<>();
	
//	
//	@OneToMany(cascade=CascadeType.ALL,fetch= FetchType.LAZY)
//	@JoinColumns({
//        @JoinColumn(name = "act_state_id", referencedColumnName = "act_state_id", nullable = false),
//        @JoinColumn(name = "act_id", referencedColumnName = "act_id",nullable = false)
//    })
//	private List<Activities> activityList;
	
		
	public ActsStates(int state_id, Boolean is_registration_require,
			String is_sublocation_require) {
		super();
		this.state_id = state_id;
		this.is_registration_require = is_registration_require;
		this.is_sublocation_require = is_sublocation_require;
	}

	public ActsStates() {
		super();
		// TODO Auto-generated constructor stub
	}	
	


	public List<Activities> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<Activities> activityList) {
		this.activityList = activityList;
	}


	public Long getAct_state_id() {
		return act_state_id;
	}

	public void setAct_state_id(Long act_state_id) {
		this.act_state_id = act_state_id;
	}

	public int getState_id() {
		return state_id;
	}

	public void setState_id(int state_id) {
		this.state_id = state_id;
	}

	

	public Boolean getIs_registration_require() {
		return is_registration_require;
	}

	public void setIs_registration_require(Boolean is_registration_require) {
		this.is_registration_require = is_registration_require;
	}

	public String getIs_sublocation_require() {
		return is_sublocation_require;
	}

	public void setIs_sublocation_require(String is_sublocation_require) {
		this.is_sublocation_require = is_sublocation_require;
	}
	
	
	
	
	
}

