package com.spring.myblog.dao;

import java.util.List;

public interface Dao {
	public void add();
	public void delete();
	public void modify();
	public Object get(String id);
}
