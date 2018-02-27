package com.spring.myblog.dao;

import java.util.List;

import com.spring.myblog.domain.FolderFirst;


public interface FolderDao<T,S>{
	public void add(T folder);
	public void modify(T folder);
	public void delete(T folder);
	public T get(S folderKey); 
	public List<T> getAll(); 

}
