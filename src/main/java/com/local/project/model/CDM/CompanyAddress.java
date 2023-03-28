package com.local.project.model.CDM;

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
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.local.project.model.Superadmin.Acts;
import com.local.project.model.Superadmin.City;
import com.local.project.model.Superadmin.Company;
import com.local.project.model.Superadmin.State;


@Entity
@Table(name = "company_address")
public class CompanyAddress {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long company_address_id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Company company;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "state_id")
	@JsonIgnore
	private State state;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "City_id")
	private City city;
	
	
	@Column(name="company_loc_startdate")
    private String company_loc_startdate;
	
	@Column(name="company_loc_enddate")
    private String company_loc_enddate;
	
	@Column(name="address")
    private String address;
   
	@Column(name="mail_id")
	@Email
    private String mailId;
   
   @Column(name="company_staff_male")
   private Integer company_staff_male;
   
   @Column(name="company_staff_female")
   private Integer company_staff_female;
   
   @Column(name="company_staff_apprentices")
   private Integer company_staff_apprentices;
   
   @Column(name="company_staff_contract")
   private Integer company_staff_contract;
   
   @Column(name="country_office")
   private int country_office;
   
   @Column(name="state_office")
   private int state_office;
   
   @Column(name="city_office")
   private int city_office;
   
   @NotNull
   @Size(min=0,max=10)
   @Column(name="company_mobile")
   private String company_mobile;
   
   @Column(name="company_location")
   private String company_location;
   
   @ElementCollection
   @CollectionTable(name = "company_address_location_types", joinColumns = @JoinColumn(name = "company_address_id"))
   private Set<Integer> location_types;
   
   @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "company_address_acts", 
				joinColumns = @JoinColumn(name = "company_address_id"), 
				inverseJoinColumns = @JoinColumn(name = "act_id"))
   private List<Acts> acts;
	
    private Long created_by;
	 
	private Long updated_by;
	 
	private LocalDateTime created_at;

	private LocalDateTime updated_at;


   public CompanyAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
//getter and setter
   
	

	public Long getCompany_address_id() {
		return company_address_id;
	}
	
	public List<Acts> getActs() {
		return acts;
	}



	public void setActs(List<Acts> acts) {
		this.acts = acts;
	}



	public void setCompany_address_id(Long company_address_id) {
		this.company_address_id = company_address_id;
	}
	
	public String getCompany_location() {
		return company_location;
	}

	public void setCompany_location(String company_location) {
		this.company_location = company_location;
	}
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}
	
	public String getCompany_loc_startdate() {
		return company_loc_startdate;
	}
	
	public void setCompany_loc_startdate(String company_loc_startdate) {
		this.company_loc_startdate = company_loc_startdate;
	}
	
	public String getCompany_loc_enddate() {
		return company_loc_enddate;
	}
	
	public void setCompany_loc_enddate(String company_loc_enddate) {
		this.company_loc_enddate = company_loc_enddate;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getMailId() {
		return mailId;
	}
	
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	
	public Integer getCompany_staff_male() {
		return company_staff_male;
	}
	
	public void setCompany_staff_male(Integer company_staff_male) {
		this.company_staff_male = company_staff_male;
	}
	
	public Integer getCompany_staff_female() {
		return company_staff_female;
	}
	
	public void setCompany_staff_female(Integer company_staff_female) {
		this.company_staff_female = company_staff_female;
	}
	
	public Integer getCompany_staff_apprentices() {
		return company_staff_apprentices;
	}
	
	public void setCompany_staff_apprentices(Integer company_staff_apprentices) {
		this.company_staff_apprentices = company_staff_apprentices;
	}
	
	public Integer getCompany_staff_contract() {
		return company_staff_contract;
	}
	
	public void setCompany_staff_contract(Integer company_staff_contract) {
		this.company_staff_contract = company_staff_contract;
	}
	
	
	public int getCountry_office() {
		return country_office;
	}



	public void setCountry_office(int country_office) {
		this.country_office = country_office;
	}



	public int getState_office() {
		return state_office;
	}



	public void setState_office(int state_office) {
		this.state_office = state_office;
	}



	public int getCity_office() {
		return city_office;
	}



	public void setCity_office(int city_office) {
		this.city_office = city_office;
	}



	public String getCompany_mobile() {
		return company_mobile;
	}
	
	public void setCompany_mobile(String company_mobile) {
		this.company_mobile = company_mobile;
	}
	
	
	public Set<Integer> getLocation_types()
	{
		return location_types;
	}
	
	public void setLocation_types(Set<Integer> location_types) 
	{
		this.location_types = location_types;
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
	public CompanyAddress(Long company_address_id, Company company, State state, City city,
			String company_loc_startdate, String company_loc_enddate, String address, @Email String mailId,
			Integer company_staff_male, Integer company_staff_female, Integer company_staff_apprentices,
			Integer company_staff_contract, int country_office, int state_office, int city_office,
			@NotNull @Size(min = 0, max = 10) String company_mobile, String company_location,
			Set<Integer> location_types, List<Acts> acts, Long created_by, Long updated_by, LocalDateTime created_at,
			LocalDateTime updated_at) {
		super();
		this.company_address_id = company_address_id;
		this.company = company;
		this.state = state;
		this.city = city;
		this.company_loc_startdate = company_loc_startdate;
		this.company_loc_enddate = company_loc_enddate;
		this.address = address;
		this.mailId = mailId;
		this.company_staff_male = company_staff_male;
		this.company_staff_female = company_staff_female;
		this.company_staff_apprentices = company_staff_apprentices;
		this.company_staff_contract = company_staff_contract;
		this.country_office = country_office;
		this.state_office = state_office;
		this.city_office = city_office;
		this.company_mobile = company_mobile;
		this.company_location = company_location;
		this.location_types = location_types;
		this.acts = acts;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	   
	   

}
