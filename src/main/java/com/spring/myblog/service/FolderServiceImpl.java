package com.spring.myblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myblog.dao.FolderDao;
import com.spring.myblog.dao.UserDao;
import com.spring.myblog.domain.Folder;
import com.spring.myblog.domain.User;

@Service
public class FolderServiceImpl implements FolderService{
	@Autowired 
	FolderDao folder1_dao;
	@Autowired
	UserDao uDao;
	
	@Transactional
	@Override
	public void folderAdd(Folder newFolder, String userId) {
		User user = uDao.get(userId);
		user.getFolders().add(newFolder);
		folder1_dao.insert(newFolder);
	}

	@Transactional
	@Override
	public void folderModify(Long folderIndex, String newFolderName) {
		Folder f1 = folderGet(folderIndex);
		f1.setFolderName(newFolderName);
	}

	@Transactional
	@Override
	public void folderDelete(Long folderIndex, String userId) {
		User user = uDao.get(userId);
		Folder f1 = folderGet(folderIndex);
		user.getFolders().remove(f1);
		folder1_dao.delete(f1);
	}
	
	@Transactional
	@Override
	public Folder folderGet(Long key) {
		return folder1_dao.getById(key);
	}
	@Transactional
	@Override
	public List<Folder> folderGetAll(String userId) {
		return folder1_dao.getAll(userId);
	}
	@Override
	public boolean folderNameDuplicationCheck(List<Folder> folders, String newFolderName) {
		for(Folder f : folders) {
			if(f.getFolderName().equals(newFolderName)) {
				return false;
			}
		}
		return true;
	}
}
