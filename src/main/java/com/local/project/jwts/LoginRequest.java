package com.local.project.jwts;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	private String email;

	@NotBlank
	private String password;
	
	private Boolean isenabled;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getisEnabled() {
        return isenabled;
    }
 
    public Boolean setisEnabled(Boolean isenabled) {
    	return this.isenabled = isenabled;
    }
}