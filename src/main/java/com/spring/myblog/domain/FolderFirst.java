package com.spring.myblog.domain;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "folderfirst")
public class FolderFirst {
	
	@EmbeddedId
	private FolderFirstKey folderFirstKey;
	private String folderFirstName;
	@OneToMany(mappedBy = "folderfirst", cascade=CascadeType.ALL)
	@JoinColumns({
		@JoinColumn(name="userId"),
		@JoinColumn(name="folderFirstIndex")
	})
	private List<FolderSecond> folderSeconds;
	
	public FolderFirst() {}

	public FolderFirst(FolderFirstKey folderFirstKey, String folderFirstName, List<FolderSecond> folderSeconds) {
		super();
		this.folderFirstKey = folderFirstKey;
		this.folderFirstName = folderFirstName;
		this.folderSeconds = folderSeconds;
	}

	public FolderFirstKey getFolderFirstKey() {
		return folderFirstKey;
	}

	public void setFolderFirstKey(FolderFirstKey folderFirstKey) {
		this.folderFirstKey = folderFirstKey;
	}

	public String getFolderFirstName() {
		return folderFirstName;
	}

	public void setFolderFirstName(String folderFirstName) {
		this.folderFirstName = folderFirstName;
	}

	public List<FolderSecond> getFolderSeconds() {
		return folderSeconds;
	}

	public void setFolderSeconds(List<FolderSecond> folderSeconds) {
		this.folderSeconds = folderSeconds;
	}
}
