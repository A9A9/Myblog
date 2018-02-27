package com.spring.myblog.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Embeddable
@Component
public class FolderSecondKey implements Serializable{
	@Autowired
	private FolderFirstKey folderFirstKey;
	private String folderSecondIndex;
	
	public FolderFirstKey getFolderFirstKey() {
		return folderFirstKey;
	}
	public void setFolderFirstKey(FolderFirstKey folderFirstKey) {
		this.folderFirstKey = folderFirstKey;
	}
	public String getFolderSecondIndex() {
		return folderSecondIndex;
	}
	public void setFolderSecondIndex(String folderSecondIndex) {
		this.folderSecondIndex = folderSecondIndex;
	}
	
	
}