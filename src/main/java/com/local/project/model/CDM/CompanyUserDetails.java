package com.local.project.model.CDM;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CompanyUserDetails {
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private Integer compayaddressId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCompayaddressId() {
		return compayaddressId;
	}

	public void setCompayaddressId(Integer compayaddressId) {
		this.compayaddressId = compayaddressId;
	}

	public CompanyUserDetails(@NotBlank @Size(max = 50) @Email String email, Integer compayaddressId) {
		super();
		this.email = email;
		this.compayaddressId = compayaddressId;
	}

	public CompanyUserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
    

}
