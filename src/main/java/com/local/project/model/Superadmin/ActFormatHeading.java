package com.local.project.model.Superadmin;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(	name = "act_format_heading")
public class ActFormatHeading {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long act_format_heading_id;
	
	private Long act_format_id;
	private Long act_id;
	
	private String act_format_col_type;
	private String act_format_seq_no;
	private String act_format_content;
	private String act_format_input_type;
	private String act_format_input_type_col_name;
	private String act_format_content_align;
//	private int act_format_content_width;
	private int parent_id;
	private int column_level;
	private boolean has_sub_column;
	
	private Long created_by;	 
	private Long updated_by;	 
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
	
	public ActFormatHeading(Long act_format_id, Long act_id, String act_format_col_type, String act_format_seq_no,
			String act_format_content, String act_format_input_type, String act_format_input_type_col_name,
			String act_format_content_align,  int parent_id, int column_level,
			boolean has_sub_column, Long created_by, Long updated_by, LocalDateTime created_at,
			LocalDateTime updated_at) {
		super();
		this.act_format_id = act_format_id;
		this.act_id = act_id;
		this.act_format_col_type = act_format_col_type;
		this.act_format_seq_no = act_format_seq_no;
		this.act_format_content = act_format_content;
		this.act_format_input_type = act_format_input_type;
		this.act_format_input_type_col_name = act_format_input_type_col_name;
		this.act_format_content_align = act_format_content_align;
//		this.act_format_content_width = act_format_content_width;
		this.parent_id = parent_id;
		this.column_level = column_level;
		this.has_sub_column = has_sub_column;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	
	public ActFormatHeading() {
		super();
	}

	public Long getAct_format_heading_id() {
		return act_format_heading_id;
	}
	public void setAct_format_heading_id(Long act_format_heading_id) {
		this.act_format_heading_id = act_format_heading_id;
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
	public String getAct_format_col_type() {
		return act_format_col_type;
	}
	public void setAct_format_col_type(String act_format_col_type) {
		this.act_format_col_type = act_format_col_type;
	}
	public String getAct_format_seq_no() {
		return act_format_seq_no;
	}
	public void setAct_format_seq_no(String act_format_seq_no) {
		this.act_format_seq_no = act_format_seq_no;
	}
	public String getAct_format_content() {
		return act_format_content;
	}
	public void setAct_format_content(String act_format_content) {
		this.act_format_content = act_format_content;
	}
	public String getAct_format_input_type() {
		return act_format_input_type;
	}
	public void setAct_format_input_type(String act_format_input_type) {
		this.act_format_input_type = act_format_input_type;
	}
	public String getAct_format_input_type_col_name() {
		return act_format_input_type_col_name;
	}
	public void setAct_format_input_type_col_name(String act_format_input_type_col_name) {
		this.act_format_input_type_col_name = act_format_input_type_col_name;
	}
	public String getAct_format_content_align() {
		return act_format_content_align;
	}
	public void setAct_format_content_align(String act_format_content_align) {
		this.act_format_content_align = act_format_content_align;
	}
//	public int getAct_format_content_width() {
//		return act_format_content_width;
//	}
//	public void setAct_format_content_width(int act_format_content_width) {
//		this.act_format_content_width = act_format_content_width;
//	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public int getColumn_level() {
		return column_level;
	}
	public void setColumn_level(int column_level) {
		this.column_level = column_level;
	}
	public boolean isHas_sub_column() {
		return has_sub_column;
	}
	public void setHas_sub_column(boolean has_sub_column) {
		this.has_sub_column = has_sub_column;
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

