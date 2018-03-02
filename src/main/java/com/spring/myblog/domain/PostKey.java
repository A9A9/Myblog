package com.spring.myblog.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Embeddable
@Component
public class PostKey implements Serializable{
	@Autowired
	private FolderSecondKey folderSecondKey;
	private String postId;
	public FolderSecondKey getFolderSecondKey() {
		return folderSecondKey;
	}
	public void setFolderSecondKey(FolderSecondKey folderSecondKey) {
		this.folderSecondKey = folderSecondKey;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
}
