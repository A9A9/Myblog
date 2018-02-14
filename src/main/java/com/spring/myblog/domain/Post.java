package com.spring.myblog.domain;

public class Post {
	private int postId;
	private String postTitle;
	private String postFile;
	private String postContent;
	private String postVisibility;
	private String postTag;
	private String userId;
	private int forderFirstIndex;
	private int forderSecondIndex;
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
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
	public int getForderFirstIndex() {
		return forderFirstIndex;
	}
	public void setForderFirstIndex(int forderFirstIndex) {
		this.forderFirstIndex = forderFirstIndex;
	}
	public int getForderSecondIndex() {
		return forderSecondIndex;
	}
	public void setForderSecondIndex(int forderSecondIndex) {
		this.forderSecondIndex = forderSecondIndex;
	}
	
	

}
