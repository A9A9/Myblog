package com.spring.myblog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.spring.myblog.domain.FolderFirst;

@Repository
@Qualifier("folderFirstDao")
public class FolderDaoFirstImpl implements FolderDao<FolderFirst>{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void insert(FolderFirst folder) {
		em.persist(folder);
	}
	@Override
	public void delete(FolderFirst folder) {
		em.remove(folder);
	}
	@Override
	public FolderFirst getById(Long folderIndex) {
		return em.find(FolderFirst.class, folderIndex);
	}
	@Override
	public List<FolderFirst> getAll(Object foreignkey) {
		List<FolderFirst> folderFirsts = em.createQuery("select f from FolderFirst f where f.userId = :userId order by f.folderFirstIndex desc", FolderFirst.class).setParameter("userId", foreignkey).getResultList();
		return folderFirsts;
	}

}
