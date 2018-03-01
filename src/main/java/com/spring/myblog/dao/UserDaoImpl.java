package com.spring.myblog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.spring.myblog.domain.User;

@Repository
public class UserDaoImpl implements UserDao {
	@PersistenceContext private EntityManager em;
	
	@Override
	public void add(User user) {
		em.persist(user);
	}

	@Override
	public void delete(User user) {
		em.remove(user);
	}

	@Override
	public void modify() {
		
	}

	@Override
	public User get(String userId) {
		return em.find(User.class, userId);	
	}
	
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		List<User> users = em.createQuery("select u from User u", User.class).getResultList();
		return users;
	}

	@Override
	public String pri()
	{
		System.out.println("user print");
		//String ret = "user print";
		return "user print";
	}

}
