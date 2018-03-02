package com.spring.myblog.dao;

import java.util.List;

public interface FolderDao<T>{
	public void add(T folder);
	public void modify(T folder);
	public void delete(T folder);
	public T get(Long folderIndex);
	public List<T> getAll(Object foreignkey); 
}
