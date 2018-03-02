package com.spring.myblog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.spring.myblog.domain.Post;

@Repository
public class PostDaoImpl implements PostDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void add(Post post) {
		em.persist(post);
	}
	
	@Override
	public void delete(Post post) {
		em.remove(post);
	}
	
	@Override
	public void modify(Post post) {
		em.merge(post);
	}	

	@Override
	public Post get(Long postIndex) {
		return em.find(Post.class, postIndex);
	}

	@Override
	public List<Post> getList(Object foreignkey) {
		List<Post> posts = em.createQuery("select p from Post p where p.folderSecondIndex = " + foreignkey, Post.class).getResultList();
		return posts;
	}


}
