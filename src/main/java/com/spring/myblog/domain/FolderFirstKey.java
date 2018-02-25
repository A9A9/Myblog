package com.spring.myblog.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

@Embeddable
@Component
public class FolderFirstKey implements Serializable{
	private String userId;
	private String folderFirstIndex;
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
	
}
