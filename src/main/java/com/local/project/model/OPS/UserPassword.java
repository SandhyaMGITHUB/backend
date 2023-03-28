package com.local.project.model.OPS;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users_passwordlink")
public class UserPassword {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private int user_id;
	private int companies_id;
	private int location_id;
	private int act_id;
	private String code_name;
	private String login_id;
	private String password;
	
	private Long created_by;	 
	private Long updated_by;	 
	private LocalDateTime created_at;
	private LocalDateTime updated_at;	
	
	@org.hibernate.annotations.ColumnDefault("0")
	private Boolean is_deleted=false;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCompanies_id() {
		return companies_id;
	}

	public void setCompanies_id(int companies_id) {
		this.companies_id = companies_id;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public int getAct_id() {
		return act_id;
	}

	public void setAct_id(int act_id) {
		this.act_id = act_id;
	}

	public String getCode_name() {
		return code_name;
	}

	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public UserPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserPassword(int id, int user_id, int companies_id, int location_id, int act_id, String code_name,
			String login_id, String password, Long created_by, Long updated_by, LocalDateTime created_at,
			LocalDateTime updated_at, Boolean is_deleted) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.companies_id = companies_id;
		this.location_id = location_id;
		this.act_id = act_id;
		this.code_name = code_name;
		this.login_id = login_id;
		this.password = password;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.is_deleted = is_deleted;
	}
	
	
	

}
