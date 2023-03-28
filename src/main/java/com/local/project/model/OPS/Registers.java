package com.local.project.model.OPS;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "registers")
public class Registers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long registers_id;
	private Long company_id;
	private String company_name;
	private String summary_name;
	private int day;
	private int month;
	private int year;
	private String excel_month_year;
	private String save_file_name;
	private int save_editor_id;
	private boolean is_generated;
	private boolean is_calculated;
	private int approval_status;
	private String appr_rej_datetime;
	private Long created_by;
	private Long updated_by;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	@org.hibernate.annotations.ColumnDefault("0")
	private Boolean is_deleted=false;

		public Registers() {
		super();
		// TODO Auto-generated constructor stub
		}
		
		public Registers(Long company_id, String company_name, String summary_name, int day, int month, int year,
		String excel_month_year, String save_file_name, int save_editor_id, boolean is_generated,
		boolean is_calculated, int approval_status, String appr_rej_datetime, Long created_by, Long updated_by,
		LocalDateTime created_at, LocalDateTime updated_at, Boolean is_deleted) {
		super();
		this.company_id = company_id;
		this.company_name = company_name;
		this.summary_name = summary_name;
		this.day = day;
		this.month = month;
		this.year = year;
		this.excel_month_year = excel_month_year;
		this.save_file_name = save_file_name;
		this.save_editor_id = save_editor_id;
		this.is_generated = is_generated;
		this.is_calculated = is_calculated;
		this.approval_status = approval_status;
		this.appr_rej_datetime = appr_rej_datetime;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.is_deleted = is_deleted;
		}
		
		
		
		public Long getRegisters_id() {
		return registers_id;
		}
		
		public void setRegisters_id(Long registers_id) {
		this.registers_id = registers_id;
		}
		
		public Long getCompany_id() {
		return company_id;
		}
		
		public void setCompany_id(Long company_id) {
		this.company_id = company_id;
		}
		
		public String getCompany_name() {
		return company_name;
		}
		
		public void setCompany_name(String company_name) {
		this.company_name = company_name;
		}
		
		public String getSummary_name() {
		return summary_name;
		}
		
		public void setSummary_name(String summary_name) {
		this.summary_name = summary_name;
		}
		
		public int getDay() {
		return day;
		}
		
		public void setDay(int day) {
		this.day = day;
		}
		
		public int getMonth() {
		return month;
		}
		
		public void setMonth(int month) {
		this.month = month;
		}
		
		public int getYear() {
		return year;
		}
		
		public void setYear(int year) {
		this.year = year;
		}
		
		public String getExcel_month_year() {
		return excel_month_year;
		}
		
		public void setExcel_month_year(String excel_month_year) {
		this.excel_month_year = excel_month_year;
		}
		
		public String getSave_file_name() {
		return save_file_name;
		}
		
		public void setSave_file_name(String save_file_name) {
		this.save_file_name = save_file_name;
		}
		
		public int getSave_editor_id() {
		return save_editor_id;
		}
		
		public void setSave_editor_id(int save_editor_id) {
		this.save_editor_id = save_editor_id;
		}
		
		public boolean isIs_generated() {
		return is_generated;
		}
		
		
		public void setIs_generated(boolean is_generated) {
		this.is_generated = is_generated;
		}
		
		
		public boolean isIs_calculated() {
		return is_calculated;
		}
		
		
		public void setIs_calculated(boolean is_calculated) {
		this.is_calculated = is_calculated;
		}
		
		
		public int getApproval_status() {
		return approval_status;
		}
		
		public void setApproval_status(int approval_status) {
		this.approval_status = approval_status;
		}
		
		public String getAppr_rej_datetime() {
		return appr_rej_datetime;
		}
		
		public void setAppr_rej_datetime(String appr_rej_datetime) {
		this.appr_rej_datetime = appr_rej_datetime;
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
}