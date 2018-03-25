package com.spring.myblog.service;

import java.util.List;

import com.spring.myblog.domain.User;

public interface UserService {
	public boolean userIdDuplicationCheck(String userId);
	public boolean nickNameDuplicationCheck(String userNickName);
	public void join(User user);
	public User login(String userId, String userPw);
	public boolean setNickName(User user, String userNickName);
	public void blogInit(User user);
	public User get(String userId);
	public void modify(User user);
	public void addProfilePhoto(String fullName, String userId);
	public List<User> blogSearch(String blogName);
}