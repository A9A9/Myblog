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
	public void insert(Post post) {
		em.persist(post);
	}
	
	@Override
	public void delete(Post post) {
		em.remove(post);
	}	

	@Override
	public Post getById(Long postIndex) {
		return em.find(Post.class, postIndex);
	}

	@Override
	public List<Post> getList(int startPosition, int maxResult, Object foreignkey) {
		List<Post> posts = em.createQuery("select p from Post p where p.folderSecondIndex = :folderSecondIndex order by p.postIndex desc" , Post.class)
				.setParameter("folderSecondIndex", foreignkey)
				.setFirstResult(startPosition).setMaxResults(maxResult)
				.getResultList();
		return posts;
	}
	public Long getAllCount(Object foreignkey) {
		Long postCnt = em.createQuery("select count(p) from Post p where p.folderSecondIndex = :folderSecondIndex",Long.class)
				.setParameter("folderSecondIndex", foreignkey).getSingleResult();
		return postCnt;
	}
}
