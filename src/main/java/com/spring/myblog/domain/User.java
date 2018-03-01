package com.spring.myblog.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "user")
public class User implements Serializable {
	@Id
	private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	private String nickName;
	
	private String profilePhoto;
	private String profileIntro;
	private String blogName;
	private String blogVisibility;
	
	//private List<FolderFirst> folders;
	
	/*public List<FolderFirst> getFolders() {
		return folders;
	}
	public void setFolders(List<FolderFirst> folders) {
		this.folders = folders;
	}*/
	
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
	public String getProfilePhoto() {
		return profilePhoto;
	}
	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getProfileIntro() {
		return profileIntro;
	}
	public void setProfileIntro(String profileIntro) {
		this.profileIntro = profileIntro;
	}
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public String getBlogVisibility() {
		return blogVisibility;
	}
	public void setBlogVisibility(String blogVisibility) {
		this.blogVisibility = blogVisibility;
	}
}