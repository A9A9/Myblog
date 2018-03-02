package com.spring.myblog.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "folderfirst")
public class FolderFirst{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long folderFirstIndex;
	private String userId;
	private String folderFirstName;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "folderFirstIndex")
	private List<FolderSecond> folderSeconds;

	public String getFolderFirstName() {
		return folderFirstName;
	}

	public void setFolderFirstName(String folderFirstName) {
		this.folderFirstName = folderFirstName;
	}

	public List<FolderSecond> getFolderSeconds() {
		if(folderSeconds == null) {
			folderSeconds = new ArrayList<FolderSecond>();
		}
		return folderSeconds;
	}

	public void setFolderSeconds(List<FolderSecond> folderSeconds) {
		this.folderSeconds = folderSeconds;
	}

	public Long getFolderFirstIndex() {
		return folderFirstIndex;
	}

	public void setFolderFirstIndex(Long folderFirstIndex) {
		this.folderFirstIndex = folderFirstIndex;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
