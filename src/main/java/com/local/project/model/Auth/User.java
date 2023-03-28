package com.local.project.model.Auth;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users", 
		uniqueConstraints = {
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;
	

	@NotBlank
	@Size(max = 20)
	private Boolean isenabled;
		
	private String token;
	
	private long company_id;

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"),
				
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	
	private Set<Role> roles = new HashSet<>();
	
	public List<Integer> getCompany() {
		return company;
	}

	public void setCompany(List<Integer> company) {
		this.company = company;
	}




	//company address location
	@ElementCollection
    @CollectionTable(name = "user_company_addres", joinColumns = @JoinColumn(name = "id"))
    private List<Integer> companyaddress;
	
	@ElementCollection
    @CollectionTable(name = "user_company_addres", joinColumns = @JoinColumn(name = "id"))
    private List<Integer> company;


	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime tokenCreationDate;
	
	public List<Integer> getCompanyaddress() {
		return companyaddress;
	}

	public void setCompanyaddress(List<Integer> companyaddress) {
		this.companyaddress = companyaddress;
	}




	private String designation;
	
	private boolean isHOD;
	private boolean isteamLead;
	


	private Long created_by;
	 
	 private Long updated_by;
	 
	 private LocalDateTime created_at;

	 private LocalDateTime updated_at;

	
	public User() {
	}

	public User(String username, String email, String password,Boolean isenabled) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.isenabled=isenabled;
		
	}
	

	
	public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password, @NotBlank @Size(max = 20) Boolean isenabled,
			String designation,Long created_by,Long updated_by,LocalDateTime created_at,LocalDateTime updated_at) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.isenabled = isenabled;
		this.designation = designation;
		this.created_by=created_by;
		this.updated_by=updated_by;
		this.created_at =created_at;
		this.updated_at =updated_at;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Boolean getIsenabled() {
		return isenabled;
	}

	public void setIsenabled(Boolean isenabled) {
		this.isenabled =isenabled;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public Long getCompany_id() {
		return company_id;
	}

	public LocalDateTime getTokenCreationDate() {
		return tokenCreationDate;
	}

	public void setTokenCreationDate(LocalDateTime tokenCreationDate) {
		this.tokenCreationDate = tokenCreationDate;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setCompany_id(long company_id) {
		this.company_id = company_id;
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
	
	
	
	public boolean isHOD() {
		return isHOD;
	}

	public void setHOD(boolean isHOD) {
		this.isHOD = isHOD;
	}

	public boolean isIsteamLead() {
		return isteamLead;
	}

	public void setIsteamLead(boolean isteamLead) {
		this.isteamLead = isteamLead;
	}
	public User(Long id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password, @NotBlank @Size(max = 20) Boolean isenabled, String token,
			long company_id, Set<Role> roles, List<Integer> companyaddress, LocalDateTime tokenCreationDate,
			String designation, boolean isHOD, boolean isteamLead, Long created_by, Long updated_by,
			LocalDateTime created_at, LocalDateTime updated_at) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.isenabled = isenabled;
		this.token = token;
		this.company_id = company_id;
		this.roles = roles;
		this.companyaddress = companyaddress;
		this.tokenCreationDate = tokenCreationDate;
		this.designation = designation;
		this.isHOD = isHOD;
		this.isteamLead = isteamLead;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public User(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password,
			List<Integer> companyaddress, Long created_by, LocalDateTime created_at) {
		super();
		this.email = email;
		this.password = password;
		this.companyaddress = companyaddress;
		this.created_by = created_by;
		this.created_at = created_at;
	}

	

	

	

	
	
	
	
	
	
	
}

