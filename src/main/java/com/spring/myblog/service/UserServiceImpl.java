package com.spring.myblog.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.myblog.dao.UserDao;
import com.spring.myblog.domain.User;

//@Service
@Repository
public class UserServiceImpl implements UserService {
	@Autowired UserDao userDao;
	@PersistenceContext EntityManager em;
	
	@Override
	public boolean userIdDuplicationCheck(String userId) {
		// TODO Auto-generated method stub
		User joinUser = em.find(User.class, userId);
		if(joinUser == null)
			return true;
		else
			return false;
	}

	@Override
	public boolean nickNameDuplicationCheck(String userNickName) {
		// TODO Auto-generated method stub
		List<User> users = userDao.getAll();
		for(User user : users)
		{
			if(user.getNickName() == userNickName)
				return false;
		}
		return true;
	}

	@Override
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
	public void join(User user) {
		// TODO Auto-generated method stub
		if(userIdDuplicationCheck(user.getUserId()))
			userDao.add(user);
	}

	@Override
	public boolean login(String userId, String userPw) {
		// TODO Auto-generated method stub
		User user = userDao.get(userId);
		if(user == null)
			return false;
		if(user.getUserPw() == userPw)
			return true;
		else
			return false;
	}

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		user.setBlogName(user.getNickName() + " 의 블로그");
		userDao.add(user);
	}
}
