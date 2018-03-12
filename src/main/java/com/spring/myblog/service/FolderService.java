package com.spring.myblog.service;

import java.util.List;

import com.spring.myblog.domain.Folder;

public interface FolderService{
	public void folderAdd(Folder newFolder, String userId);
	public void folderModify(Long folderIndex, String newFolderName);
	public void folderDelete(Long folderIndex, String userId);
	public Folder folderGet(Long key);
	public boolean folderNameDuplicationCheck(List<Folder> folders, String newFolderName);
	public List<Folder> folderGetAll(String userId);
}
