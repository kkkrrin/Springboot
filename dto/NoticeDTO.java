package com.spring.boot.dto;

public class NoticeDTO {

/*
CREATE TABLE pet_notice(
n_num number not null,
n_title varchar2(100),
n_content varchar2(3000),
n_created date default sysdate
);
*/
	private int n_num;
	private String n_title, n_content, n_created;
	
	public int getN_num() {
		return n_num;
	}
	public void setN_num(int n_num) {
		this.n_num = n_num;
	}
	public String getN_title() {
		return n_title;
	}
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	public String getN_content() {
		return n_content;
	}
	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
	public String getN_created() {
		return n_created;
	}
	public void setN_created(String n_created) {
		this.n_created = n_created;
	}
	
	
	
	
}
