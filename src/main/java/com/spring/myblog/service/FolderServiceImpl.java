package com.spring.myblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myblog.dao.FolderDao;
import com.spring.myblog.domain.FolderFirst;
import com.spring.myblog.domain.User;

@Service
@Qualifier("folderFirstService")
public class FolderServiceFirstImpl implements FolderService<FolderFirst>{
	@Autowired 
	@Qualifier("folderFirstDao")
	FolderDao<FolderFirst> folder1_dao;

	@Transactional
	@Override
	public void folderAdd(FolderFirst newFolder) {
		//parent.getFolders().add(newFolder);
		folder1_dao.insert(newFolder);
	}

	@Transactional
	@Override
	public void folderModify(FolderFirst newFolder) {}

	@Transactional
	@Override
	public void folderDelete(FolderFirst folder) {
		//FolderFirst f1 = folderGet(folder.getFolderFirstIndex());
		folder1_dao.delete(folder);
	}
	
	@Transactional
	@Override
	public FolderFirst folderGet(Long key) {
		return folder1_dao.getById(key);
	}
	@Transactional
	@Override
	public List<FolderFirst> folderGetAll(Object foreignkey) {
		return folder1_dao.getAll(foreignkey);
	}

	@Override
	public boolean folderNameDuplicationCheck(Object foreignkey, FolderFirst newFolder) {
		List<FolderFirst> folderFirsts = folderGetAll(foreignkey);
		for(FolderFirst f : folderFirsts) {
			if (f.getFolderFirstName().equals(newFolder.getFolderFirstName())) {
				return false;
			}
		}
		return true;
	}

}
