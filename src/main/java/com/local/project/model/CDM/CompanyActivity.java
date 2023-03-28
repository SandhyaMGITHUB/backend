package com.local.project.model.CDM;



import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "companies_activity")
public class CompanyActivity {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long companies_activity_id;
private int activity_id;
private int act_id;
private int businessarea_id;
private int com_id;
private int isDisplay;
private boolean RegistrationRequire;
private int companies_address_id;
private String due_date;
private String actual_date;
private String undo_actual_date;
private String save_file_name;
private String undo_file_name;
private String delay_remarks;
private String undo_reason;
private int undo_status;
private int approval_status;
private String undo_datetime;
private String appr_rej_datetime;
private int na;
private int is_generated_by_corn_job;
private int IsDeleted;
private Long created_by;
private Long updated_by;
private LocalDateTime created_at;
private LocalDateTime updated_at;
public CompanyActivity() {
super();
// TODO Auto-generated constructor stub
}

public CompanyActivity(int activity_id, int act_id, int businessarea_id, int com_id,
boolean registrationRequire, int companies_address_id, String due_date, String actual_date,
String undo_actual_date, String save_file_name, String undo_file_name, String delay_remarks,
String undo_reason, int undo_status, int approval_status, String undo_datetime, String appr_rej_datetime,
int na, int is_generated_by_corn_job, int isDeleted, Long created_by, Long updated_by,
LocalDateTime created_at, LocalDateTime updated_at) {
super();
this.activity_id = activity_id;
this.act_id = act_id;
this.businessarea_id = businessarea_id;
this.com_id = com_id;
this.RegistrationRequire = registrationRequire;
this.companies_address_id = companies_address_id;
this.due_date = due_date;
this.actual_date = actual_date;
this.undo_actual_date = undo_actual_date;
this.save_file_name = save_file_name;
this.undo_file_name = undo_file_name;
this.delay_remarks = delay_remarks;
this.undo_reason = undo_reason;
this.undo_status = undo_status;
this.approval_status = approval_status;
this.undo_datetime = undo_datetime;
this.appr_rej_datetime = appr_rej_datetime;
this.na = na;
this.is_generated_by_corn_job = is_generated_by_corn_job;
IsDeleted = isDeleted;
this.created_by = created_by;
this.updated_by = updated_by;
this.created_at = created_at;
this.updated_at = updated_at;
}

public Long getCompanies_activity_id() {
return companies_activity_id;
}

public void setCompanies_activity_id(Long companies_activity_id) {
this.companies_activity_id = companies_activity_id;
}


public int getActivity_id() {
return activity_id;
}

public void setActivity_id(int activity_id) {
this.activity_id = activity_id;
}

public int getAct_id() {
return act_id;
}

public void setAct_id(int act_id) {
this.act_id = act_id;
}

public int getBusinessarea_id() {
return businessarea_id;
}

public void setBusinessarea_id(int businessarea_id) {
this.businessarea_id = businessarea_id;
}

public int getCom_id() {
return com_id;
}

public void setCom_id(int com_id) {
this.com_id = com_id;
}

public int getIsDisplay() {
return isDisplay;
}

public void setIsDisplay(int isDisplay) {
this.isDisplay = isDisplay;
}


public boolean isRegistrationRequire() {
return RegistrationRequire;
}

public void setRegistrationRequire(boolean registrationRequire) {
RegistrationRequire = registrationRequire;
}

public int getCompanies_address_id() {
return companies_address_id;
}

public void setCompanies_address_id(int companies_address_id) {
this.companies_address_id = companies_address_id;
}

public String getDue_date() {
return due_date;
}

public void setDue_date(String due_date) {
this.due_date = due_date;
}

public String getActual_date() {
return actual_date;
}

public void setActual_date(String actual_date) {
this.actual_date = actual_date;
}

public String getUndo_actual_date() {
return undo_actual_date;
}

public void setUndo_actual_date(String undo_actual_date) {
this.undo_actual_date = undo_actual_date;
}

public String getSave_file_name() {
return save_file_name;
}

public void setSave_file_name(String save_file_name) {
this.save_file_name = save_file_name;
}

public String getUndo_file_name() {
return undo_file_name;
}

public void setUndo_file_name(String undo_file_name) {
this.undo_file_name = undo_file_name;
}

public String getDelay_remarks() {
return delay_remarks;
}

public void setDelay_remarks(String delay_remarks) {
this.delay_remarks = delay_remarks;
}

public String getUndo_reason() {
return undo_reason;
}

public void setUndo_reason(String undo_reason) {
this.undo_reason = undo_reason;
}

public int getUndo_status() {
return undo_status;
}

public void setUndo_status(int undo_status) {
this.undo_status = undo_status;
}

public int getApproval_status() {
return approval_status;
}

public void setApproval_status(int approval_status) {
this.approval_status = approval_status;
}

public String getUndo_datetime() {
return undo_datetime;
}

public void setUndo_datetime(String undo_datetime) {
this.undo_datetime = undo_datetime;
}

public String getAppr_rej_datetime() {
return appr_rej_datetime;
}

public void setAppr_rej_datetime(String appr_rej_datetime) {
this.appr_rej_datetime = appr_rej_datetime;
}

public int getNa() {
return na;
}

public void setNa(int na) {
this.na = na;
}

public int getIs_generated_by_corn_job() {
return is_generated_by_corn_job;
}

public void setIs_generated_by_corn_job(int is_generated_by_corn_job) {
this.is_generated_by_corn_job = is_generated_by_corn_job;
}

public int getIsDeleted() {
return IsDeleted;
}

public void setIsDeleted(int isDeleted) {
IsDeleted = isDeleted;
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
}