package com.spring.boot.dto;

public class InquiryDTO {
	
	private int num; 
	private String userId;
	private String inquiryCate;
	private String inquiryContent;
	private String saveName;
	private String originalName;
	private String created;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getInquiryCate() {
		return inquiryCate;
	}
	public void setInquiryCate(String inquiryCate) {
		this.inquiryCate = inquiryCate;
	}
	public String getInquiryContent() {
		return inquiryContent;
	}
	public void setInquiryContent(String inquiryContent) {
		this.inquiryContent = inquiryContent;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	
	
	

}
