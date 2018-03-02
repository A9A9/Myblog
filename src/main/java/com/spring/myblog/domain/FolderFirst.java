package com.spring.myblog.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "folderfirst")
@Component
public class FolderFirst{
	@EmbeddedId
	@Autowired
	private FolderFirstKey folderFirstKey;
	
//	@MapsId("userId")
//	@ManyToOne
//	@JoinColumn(name = "userId")
//	private User user;
	private String folderFirstName;
	//@OneToMany(mappedBy = "folderFirst", cascade=CascadeType.ALL)
	//@Transient
	//@Autowired
	//private List<FolderSecond> folderSeconds;

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

//	public List<FolderSecond> getFolderSeconds() {
//		if(folderSeconds == null) {
//			folderSeconds = new ArrayList<FolderSecond>();
//		}
//		return folderSeconds;
//	}
//
//	public void setFolderSeconds(List<FolderSecond> folderSeconds) {
//		this.folderSeconds = folderSeconds;
//	}
	
}
