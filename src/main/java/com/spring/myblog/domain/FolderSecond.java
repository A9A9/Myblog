package com.spring.myblog.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "foldersecond")
@Component
public class FolderSecond implements Serializable{
	
	@EmbeddedId
	@Autowired
	private FolderSecondKey folderSecondKey;
	
	@MapsId("folderFirstKey")
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="userId", referencedColumnName="userId"),
		@JoinColumn(name="folderFirstIndex", referencedColumnName="folderFirstIndex")
	})
	@Autowired
	private FolderFirst folderFirst;
	private String folderSecondName;
	
	//@OneToMany(mappedBy="folderSecond", cascade=CascadeType.ALL)
	//@Transient
	//@Autowired
	//private List<Post> posts;

	public FolderSecondKey getFolderSecondKey() {
		return folderSecondKey;
	}

	public void setFolderSecondKey(FolderSecondKey folderSecondKey) {
		this.folderSecondKey = folderSecondKey;
	}

	public FolderFirst getFolderFirst() {
		return folderFirst;
	}

	public void setFolderFirst(FolderFirst folderFirst) {
		this.folderFirst = folderFirst;
	}

	public String getFolderSecondName() {
		return folderSecondName;
	}

	public void setFolderSecondName(String folderSecondName) {
		this.folderSecondName = folderSecondName;
	}
	
//	public List<Post> getPosts() {
//		if(posts == null) {
//			posts = new ArrayList<Post>();
//		}
//		return posts;
//	}
//
//	public void setPosts(List<Post> posts) {
//		this.posts = posts;
//	}
	
}
