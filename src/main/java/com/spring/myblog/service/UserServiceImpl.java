package com.spring.myblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myblog.dao.UserDao;
import com.spring.myblog.domain.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired UserDao userDao;
	
	@Override
	@Transactional
	public boolean userIdDuplicationCheck(String userId) {
		// TODO Auto-generated method stub
		User joinUser = userDao.get(userId);
		if(joinUser == null)
			return true;
		else
			return false;
	}

	@Override
	@Transactional
	public boolean nickNameDuplicationCheck(String userNickName) {
		// TODO Auto-generated method stub
		List<User> users = userDao.getAll();
		for(User user : users)
		{
			if(user.getNickName().equals(userNickName))
				return false;
		}
		return true;
	}

	@Override
	@Transactional
	public boolean setNickName(User user, String userNickName) {
		// TODO Auto-generated method stub
		if(nickNameDuplicationCheck(userNickName))
		{
			user.setNickName(userNickName);
			return true;
		}
		else
			return false;
	}

	@Override
	@Transactional
	public void add(User user) {
		// TODO Auto-generated method stub
		if(userIdDuplicationCheck(user.getUserId()))
			userDao.add(user); 
	}

	@Override
	@Transactional
	public User login(String userId, String userPw) {
		// TODO Auto-generated method stub
		User user = userDao.get(userId);
		if(user == null)
			return null;
		if(user.getUserPw().equals(userPw)) 
			return user;
		else
			return null;
	}
	
	@Override
	@Transactional
	public User get(String userId) {
		// TODO Auto-generated method stub
		return userDao.get(userId);
	}
	
	@Override
	@Transactional
	public void modify(User user)
	{
		userDao.modify(user);
	}

	@Override
	@Transactional
	public void addProfilePhoto(String fullName, String userId) { // Ȯ��
		// TODO Auto-generated method stub
		User updateUser = userDao.get(userId);
		updateUser.setProfilePhoto(fullName);
	}

	@Override
	public List<User> blogSearch(String blogName) {
		// TODO Auto-generated method stub
		return userDao.searchBlog(blogName);
	}
}
