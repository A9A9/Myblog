package com.spring.myblog.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {
	@Id private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	
	private String nickName;
	private String blogName;
	private String profileIntro;
	private String profilePhoto;
	private boolean blogVisibility;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumns({
		@JoinColumn(name="userId")
	})
	@OrderBy("folderIndex DESC")
	@JsonIgnore
	private List<Folder> folders;
	
	public List<Folder> getFolders() {
		if(folders == null) {
			folders = new ArrayList<Folder>();
		}
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
	public boolean isBlogVisibility() {
		return blogVisibility;
	}
	public void setBlogVisibility(boolean blogVisibility) {
		this.blogVisibility = blogVisibility;
	}
}