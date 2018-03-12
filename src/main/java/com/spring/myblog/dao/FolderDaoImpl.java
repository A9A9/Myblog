package com.spring.myblog.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.spring.myblog.domain.Folder;

@Repository
public class FolderDaoImpl implements FolderDao{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void insert(Folder folder) {
		em.persist(folder);
	}
	@Override
	public void delete(Folder folder) {
		em.remove(folder);
	}
	@Override
	public Folder getById(Long folderIndex) {
		return em.find(Folder.class, folderIndex);
	}
	
	@Override
 	public List<Folder> getAll(String userId) {
		List<Folder> folders = em.createQuery("select f from Folder f where f.userId = :userId order by f.folderIndex desc", Folder.class).setParameter("userId", userId).getResultList();
		//List<Folder> folders = em.createQuery("select f.folderIndex, f.folderName from Folder f where f.userId = :userId order by f.folderIndex desc", Folder.class).setParameter("userId", userId).getResultList();
		return folders;
	}

}
