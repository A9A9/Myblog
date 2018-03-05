package com.spring.myblog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.spring.myblog.domain.FolderSecond;

@Repository
@Qualifier("folderSecondDao")
public class FolderDaoSecondImpl implements FolderDao<FolderSecond>{
	
	@PersistenceContext 
	private EntityManager em;
	@Override
	public void insert(FolderSecond folder) {
		em.persist(folder);
	}

	@Override
	public void delete(FolderSecond folder) {
		em.remove(folder);
	}

	@Override
	public FolderSecond getById(Long folderIndex) {
		return em.find(FolderSecond.class, folderIndex);
	}

	@Override
	public List<FolderSecond> getAll(Object foreignkey) {
		List<FolderSecond> folderSeconds = em.createQuery("select f from FolderSecond f where f.folderFirstIndex = :folderFirstIndex order by f.folderSecondIndex desc", FolderSecond.class).setParameter("folderFirstIndex", foreignkey).getResultList();
		return folderSeconds;
	}


}
