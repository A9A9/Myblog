package com.spring.myblog.dao;

import java.util.List;

import com.spring.myblog.domain.Folder;

public interface FolderDao{
	public void insert(Folder folder);
	public void delete(Folder folder);
	public Folder getById(Long folderIndex);
	public List<Folder> getAll(String userId);
}
