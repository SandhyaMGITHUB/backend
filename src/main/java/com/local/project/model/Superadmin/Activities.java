package com.local.project.model.Superadmin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Activities {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long activities_id;
	
	private String activity_name;
	private String activity_category;

	private int month;
	private String date;
	private String importance_level;
	private String rule_information;

	private int act_format_id;
	


	public Activities(String activity_name, String activity_category, int month, String date,
			String importance_level, String rule_information,int act_format_id) {
		super();
		this.activity_name = activity_name;
		this.activity_category = activity_category;
		this.month = month;
		this.date = date;
		this.importance_level = importance_level;
		this.rule_information = rule_information;
		this.act_format_id = act_format_id;

	}

	public Activities() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getActivities_id() {
		return activities_id;
	}

	public void setActivities_id(long activities_id) {
		this.activities_id = activities_id;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public String getActivity_category() {
		return activity_category;
	}

	public void setActivity_category(String activity_category) {
		this.activity_category = activity_category;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	

	public String getImportance_level() {
		return importance_level;
	}

	public void setImportance_level(String importance_level) {
		this.importance_level = importance_level;
	}

	public String getRule_information() {
		return rule_information;
	}

	public void setRule_information(String rule_information) {
		this.rule_information = rule_information;
	}

	public int getAct_format_id() {
		return act_format_id;
	}

	public void setAct_format_id(int act_format_id) {
		this.act_format_id = act_format_id;
	}


	
}

