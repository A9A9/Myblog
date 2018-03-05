package com.spring.myblog.service;

import java.util.List;

public interface FolderService<T>{
	public void folderAdd(T newFolder);
	public void folderModify(T newFolder);
	public void folderDelete(T folder);
	public T folderGet(Long key);
	public List<T> folderGetAll(Object foreignkey);
	public boolean folderNameDuplicationCheck(Object foreignkey, T newFolder);
}
