package com.spring.myblog.service;

import org.springframework.stereotype.Repository;

import com.spring.myblog.domain.User;
//@Repository
public interface UserService {
	public boolean userIdDuplicationCheck(String userId);
	public boolean nickNameDuplicationCheck(String userNickName);
	public void join(User user);
	public boolean login(String userId, String userPw);
	public boolean setNickName(User user, String userNickName);
	public void blogInit(User user);
}