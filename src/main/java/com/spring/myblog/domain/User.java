package com.spring.myblog.domain;

import java.util.List;

public class User {
	private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	private Profile userProfile;
	private List<Folder> folders;
	
	public List<Folder> getFolders() {
		return folders;
	}
	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Profile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(Profile userProfile) {
		this.userProfile = userProfile;
	}
}
