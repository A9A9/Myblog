package com.spring.myblog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spring.myblog.domain.Post;

public class PostDaoImpl implements PostDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void add(Post post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Post get(Long postIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getList(Object... columns) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
