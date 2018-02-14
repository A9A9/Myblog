package com.spring.myblog.domain;

public class Post {
	private String postId;
	private String postTitle;
	private String postFile;
	private String postContent;
	private String postVisibility;
	private String postTag;
	private String userId;
	private String forderFirstIndex;
	private String forderSecondIndex;
	
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostFile() {
		return postFile;
	}
	public void setPostFile(String postFile) {
		this.postFile = postFile;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public String getPostVisibility() {
		return postVisibility;
	}
	public void setPostVisibility(String postVisibility) {
		this.postVisibility = postVisibility;
	}
	public String getPostTag() {
		return postTag;
	}
	public void setPostTag(String postTag) {
		this.postTag = postTag;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getForderFirstIndex() {
		return forderFirstIndex;
	}
	public void setForderFirstIndex(String forderFirstIndex) {
		this.forderFirstIndex = forderFirstIndex;
	}
	public String getForderSecondIndex() {
		return forderSecondIndex;
	}
	public void setForderSecondIndex(String forderSecondIndex) {
		this.forderSecondIndex = forderSecondIndex;
	}

}
