package com.spring.myblog.dao;

import com.spring.myblog.domain.User;

public interface userDao {

	public void add(User user);

	public void delete();

	public void modify();

	public User get(String userId);

}
