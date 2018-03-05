package com.spring.myblog.dao;

import java.util.List;

public interface FolderDao<T>{
	public void insert(T folder);
	public void delete(T folder);
	public T getById(Long folderIndex);
	public List<T> getAll(Object foreignkey); 
}
