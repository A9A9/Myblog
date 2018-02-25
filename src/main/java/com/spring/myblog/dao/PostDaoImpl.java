package com.spring.myblog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spring.myblog.domain.FolderSecondKey;
import com.spring.myblog.domain.Post;

public class PostDaoImpl implements PostDao{
	
	@PersistenceContext
	private EntityManager em;
	@Override
	public void add(Post post) {
		//em.persist(post);
	}

	@Override
	public void delete() {
		
	}

	@Override
	public void modify() {
		
	}

	@Override
	public Post get() {
		return null;
	}

	@Override
	public List<Post> getAll() {
		return null;
	}

}
