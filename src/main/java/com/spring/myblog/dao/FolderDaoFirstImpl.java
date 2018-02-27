package com.spring.myblog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.spring.myblog.domain.FolderFirst;
import com.spring.myblog.domain.FolderFirstKey;

@Repository
@Qualifier("folderFirstDao")
public class FolderDaoFirstImpl implements FolderDao<FolderFirst,FolderFirstKey>{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void add(FolderFirst folder) {
		em.persist(folder);
	}
	@Override
	public void modify(FolderFirst folder) {
		em.merge(folder);
	}
	@Override
	public void delete(FolderFirst folder) {
		em.remove(folder);
	}
	@Override
	public FolderFirst get(FolderFirstKey folderFirstKey) {
		return em.find(FolderFirst.class, folderFirstKey);
	}
	@Override
	public List<FolderFirst> getAll() {
		List<FolderFirst> folderFirsts = em.createQuery("select f from FolderFirst f", FolderFirst.class).getResultList();
		return folderFirsts;
	}
}
