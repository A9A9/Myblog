package com.spring.myblog.domain;

import java.util.List;

public class Folder {
	private String userId;
	private String folderFirstIndex;
	private String folderSecondIndex;
	private String folderFirstName;
	private String folderSecondName;
	private List<Post> posts;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFolderFirstIndex() {
		return folderFirstIndex;
	}
	public void setFolderFirstIndex(String folderFirstIndex) {
		this.folderFirstIndex = folderFirstIndex;
	}
	public String getFolderSecondIndex() {
		return folderSecondIndex;
	}
	public void setFolderSecondIndex(String folderSecondIndex) {
		this.folderSecondIndex = folderSecondIndex;
	}
	public String getFolderFirstName() {
		return folderFirstName;
	}
	public void setFolderFirstName(String folderFirstName) {
		this.folderFirstName = folderFirstName;
	}
	public String getFolderSecondName() {
		return folderSecondName;
	}
	public void setFolderSecondName(String folderSecondName) {
		this.folderSecondName = folderSecondName;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
