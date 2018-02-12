package com.spring.myblog.domain;

public class Profile {
	private String profileId;
	private String profilePhoto;
	private String nickName;
	private String profileIntro;
	private String blogName;
	private String blogVisibility;
	
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
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
