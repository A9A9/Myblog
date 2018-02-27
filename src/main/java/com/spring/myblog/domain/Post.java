package com.spring.myblog.domain;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "post")
@Component
public class Post {

	@EmbeddedId
	@Autowired
	private PostKey postKey;
	
	@MapsId("folderSecond")
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="userId", referencedColumnName="userId"),
		@JoinColumn(name="folderFirstIndex", referencedColumnName="folderFirstIndex"),
		@JoinColumn(name="folderSeocndIndex", referencedColumnName="folderSecondIndex")
	})
	@Autowired
	private FolderSecond folderSecond;
	private String postTitle;
	private String postFile;
	private String postContent;
	private String postVisibility;
	private String postTag;
	
	public PostKey getPostKey() {
		return postKey;
	}
	public void setPostKey(PostKey postKey) {
		this.postKey = postKey;
	}
	public FolderSecond getFolderSecond() {
		return folderSecond;
	}
	public void setFolderSecond(FolderSecond folderSecond) {
		this.folderSecond = folderSecond;
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
}
