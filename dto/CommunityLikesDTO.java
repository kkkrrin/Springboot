package com.spring.boot.dto;

public class CommunityLikesDTO {

	private int lno, cno;
	private String userId, userNick;
	
	public int getLno() {		
		return lno;
	}
	public void setLno(int lno) {
		this.lno = lno;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}	
	
	
}
