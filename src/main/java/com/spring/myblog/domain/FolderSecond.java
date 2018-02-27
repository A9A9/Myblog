package com.spring.myblog.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private FolderFirst folderfirst;
	private String folderSecondName;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Post> posts;

	public FolderSecondKey getFolderSecondKey() {
		return folderSecondKey;
	}

	public void setFolderSecondKey(FolderSecondKey folderSecondKey) {
		this.folderSecondKey = folderSecondKey;
	}

	public FolderFirst getFolderfirst() {
		return folderfirst;
	}

	public void setFolderfirst(FolderFirst folderfirst) {
		this.folderfirst = folderfirst;
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

	public void addPost(Post post) {
		if(posts == null) {
			posts = new ArrayList<Post>();
		}
		posts.add(post);
	}
	
}
