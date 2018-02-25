package com.spring.myblog.domain;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "foldersecond")
public class FolderSecond {
	
	@EmbeddedId
	private FolderSecondKey foldersecondkey;
	private String folderSecondName;
	//private List<Post> posts; ������,getset�� �߰��ض�
	
	public FolderSecond() {}
	public FolderSecond(FolderSecondKey foldersecondkey, String folderSecondName) {
		super();
		this.foldersecondkey = foldersecondkey;
		this.folderSecondName = folderSecondName;
	}
	public FolderSecondKey getFoldersecondkey() {
		return foldersecondkey;
	}
	public void setFoldersecondkey(FolderSecondKey foldersecondkey) {
		this.foldersecondkey = foldersecondkey;
	}
	public String getFolderSecondName() {
		return folderSecondName;
	}
	public void setFolderSecondName(String folderSecondName) {
		this.folderSecondName = folderSecondName;
	}
	
	
}
