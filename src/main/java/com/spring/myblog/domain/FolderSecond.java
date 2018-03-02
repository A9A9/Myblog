package com.spring.myblog.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "foldersecond")
public class FolderSecond implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long folderSecondIndex;
	private Long folderFirstIndex;
	private String folderSecondName;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumns({ 
			@JoinColumn(name = "folderFirstIndex", referencedColumnName = "folderFirstIndex"),
			@JoinColumn(name = "folderSeocndIndex", referencedColumnName = "folderSecondIndex") })
	private List<Post> posts;

	public String getFolderSecondName() {
		return folderSecondName;
	}

	public void setFolderSecondName(String folderSecondName) {
		this.folderSecondName = folderSecondName;
	}
	
	public List<Post> getPosts() {
		if(posts == null) {
			posts = new ArrayList<Post>();
		}
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Long getFolderSecondIndex() {
		return folderSecondIndex;
	}

	public void setFolderSecondIndex(Long folderSecondIndex) {
		this.folderSecondIndex = folderSecondIndex;
	}

	public Long getFolderFirstIndex() {
		return folderFirstIndex;
	}

	public void setFolderFirstIndex(Long folderFirstIndex) {
		this.folderFirstIndex = folderFirstIndex;
	}
	
}
