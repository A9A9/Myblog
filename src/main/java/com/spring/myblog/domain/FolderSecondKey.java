package com.spring.myblog.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class FolderSecondKey implements Serializable{
	private String userId;
	private String folderFirstIndex;
	private String folderSecondIndex;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFolderFirstIndex() {
		return folderFirstIndex;
	}
	public void setFolderFirstIndex(String folderFirstIndex) {
		this.folderFirstIndex = folderFirstIndex;
	}
	public String getFolderSecondIndex() {
		return folderSecondIndex;
	}
	public void setFolderSecondIndex(String folderSecondIndex) {
		this.folderSecondIndex = folderSecondIndex;
	}
	
	
}