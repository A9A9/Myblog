package com.spring.myblog.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.myblog.domain.User;
//@Repository
public interface UserDao {
	public void add(User user);

	public void delete(User user);

	public void modify();

	public User get(String userId);
	
	public List<User> getAll();
	
	public String pri();

}
