package com.spring.boot.dto;

public class TermsDTO {
/*
create table pet_terms(
t_num number not null,
t_title varchar2(100),
t_content varchar2(4000),
t_cat varchar2(30),
t_startdate date
);
*/
	
	private int t_num;
	private String t_title, t_content, t_cat, t_startdate;
	
	public int getT_num() {
		return t_num;
	}
	public void setT_num(int t_num) {
		this.t_num = t_num;
	}
	public String getT_title() {
		return t_title;
	}
	public void setT_title(String t_title) {
		this.t_title = t_title;
	}
	public String getT_content() {
		return t_content;
	}
	public void setT_content(String t_content) {
		this.t_content = t_content;
	}
	public String getT_cat() {
		return t_cat;
	}
	public void setT_cat(String t_cat) {
		this.t_cat = t_cat;
	}
	public String getT_startdate() {
		return t_startdate;
	}
	public void setT_startdate(String t_startdate) {
		this.t_startdate = t_startdate;
	}
	
	
	
}
