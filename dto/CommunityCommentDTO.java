package com.spring.boot.dto;

public class CommunityCommentDTO {
	
	private int num, communityNum;
	private String userId, userNick, contents, created, profile;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCommunityNum() {
		return communityNum;
	}
	public void setCommunityNum(int communityNum) {
		this.communityNum = communityNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		
		this.profile = profile;
		
	}
	
	
}
