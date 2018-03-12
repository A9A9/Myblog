package com.spring.myblog.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "folder")
public class Folder{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long folderIndex;
	private String userId;
	@Column(nullable = false)
	private String folderName;
	@Column(nullable = false)
	private boolean folderVisibility;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "folderIndex")
	@OrderBy("folderIndex desc")
	@JsonIgnore 
	private List<Post> posts ;

	
	public List<Post> getPosts() {
		if(posts == null) {
			posts = new ArrayList<Post>();
		}
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Long getFolderIndex() {
		return folderIndex;
	}

	public void setFolderIndex(Long folderIndex) {
		this.folderIndex = folderIndex;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public boolean isFolderVisibility() {
		return folderVisibility;
	}

	public void setFolderVisibility(boolean folderVisibility) {
		this.folderVisibility = folderVisibility;
	}


	
}
