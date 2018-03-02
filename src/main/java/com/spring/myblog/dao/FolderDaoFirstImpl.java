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
	public FolderFirst get(Long folderIndex) {
		return em.find(FolderFirst.class, folderIndex);
	}
	@Override
	public List<FolderFirst> getAll(Object foreignkey) {
		List<FolderFirst> folderFirsts = em.createQuery("select f from FolderFirst f where f.userId = " + foreignkey, FolderFirst.class).getResultList();
		return folderFirsts;
	}	
	
}
