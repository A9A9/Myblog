package com.spring.myblog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.spring.myblog.domain.FolderSecond;
import com.spring.myblog.domain.FolderSecondKey;

@Repository
@Qualifier("folderSecondDao")
public class FolderDaoSecondImpl implements FolderDao<FolderSecond,FolderSecondKey>{
	
	@PersistenceContext 
	EntityManager em;
	@Override
	public void add(FolderSecond folder) {
		em.persist(folder);
	}

	@Override
	public void modify(FolderSecond folder) {
		em.merge(folder);
	}

	@Override
	public void delete(FolderSecond folder) {
		em.remove(folder);
	}

	@Override
	public FolderSecond get(FolderSecondKey folderKey) {
		return em.find(FolderSecond.class, folderKey);
	}

	@Override
	public List<FolderSecond> getAll() {
		List<FolderSecond> folderSeconds = em.createQuery("select f from FolderSecond f", FolderSecond.class).getResultList();
		return folderSeconds;
	}

}
