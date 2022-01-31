package com.spring.boot.dto;

import java.io.File;

public class CommunityDTO {
	private String title, category, userName, userNick, content, link, created , ip, saveName ,savePicture , checklike ,checkzzim , profile, sumNail, checkMyZzim;	
	private int likes, num, hitCount, countComment;
	
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public String getSavePicture() {
		return savePicture;
	}
	public void setSavePicture(String savePicture) {
		this.savePicture = savePicture;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getHitCount() {
		return hitCount;
	}
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getChecklike() {
		return checklike;
	}
	public void setChecklike(String checklike) {
		this.checklike = checklike;
	}
	public int getCountComment() {
		return countComment;
	}
	public void setCountComment(int countComment) {
		this.countComment = countComment;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getCheckzzim() {
		return checkzzim;
	}
	public void setCheckzzim(String checkzzim) {
		this.checkzzim = checkzzim;
	}
	public String getSumNail() {
		return sumNail;
	}
	public void setSumNail(String sumNail) {
		this.sumNail = sumNail;
	}
	public String getCheckMyZzim() {
		return checkMyZzim;
	}
	public void setCheckMyZzim(String checkMyZzim) {
		this.checkMyZzim = checkMyZzim;
	}
	
	
}
