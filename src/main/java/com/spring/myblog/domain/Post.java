package com.spring.myblog.domain;



import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "post")
public class Post{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long postIndex;
	private Long folderIndex;
	@Column(nullable = false)
	private String postTitle;
	private String postFile;
	private String postContent;
	@Temporal(TemporalType.TIMESTAMP)
	private Date postDate = Calendar.getInstance().getTime();
	@Column(nullable = false)
	private boolean postVisibility;
	
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
	
	public Long getPostIndex() {
		return postIndex;
	}
	public void setPostIndex(Long postIndex) {
		this.postIndex = postIndex;
	}

	public Long getFolderIndex() {
		return folderIndex;
	}
	public void setFolderIndex(Long folderIndex) {
		this.folderIndex = folderIndex;
	}
	public boolean isPostVisibility() {
		return postVisibility;
	}
	public void setPostVisibility(boolean postVisibility) {
		this.postVisibility = postVisibility;
	}
	public String getPostDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		return format.format(postDate);
	}
	
}
