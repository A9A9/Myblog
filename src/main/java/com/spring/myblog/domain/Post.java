package com.spring.myblog.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long postIndex;
	private Long folderSecondIndex;
	private String postTitle;
	private String postFile;
	private String postContent;
	private String postVisibility;
	private String postTag;
	
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
	public Long getPostIndex() {
		return postIndex;
	}
	public void setPostIndex(Long postIndex) {
		this.postIndex = postIndex;
	}

	public Long getFolderSecondIndex() {
		return folderSecondIndex;
	}
	public void setFolderSecondIndex(Long folderSecondIndex) {
		this.folderSecondIndex = folderSecondIndex;
	}
	
}
