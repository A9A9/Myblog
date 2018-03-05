package com.spring.myblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myblog.dao.FolderDao;
import com.spring.myblog.domain.FolderFirst;
import com.spring.myblog.domain.FolderSecond;

@Service
@Qualifier("folderSecondService")
public class FolderServiceSecondImpl implements FolderService<FolderSecond> {

	@Autowired
	@Qualifier("folderSecondDao")
	FolderDao<FolderSecond> folder2_dao;
	
	@Transactional
	@Override
	public void folderAdd(FolderSecond newFolder) {
		//parent.getFolderSeconds().add(newFolder);
		folder2_dao.insert(newFolder);
	}

	@Transactional
	@Override
	public void folderModify(FolderSecond newFolder) {}

	@Transactional
	@Override
	public void folderDelete(FolderSecond folder) {
		//FolderSecond f2 = folderGet(folder.getFolderSecondIndex());
		folder2_dao.delete(folder);
	}

	@Transactional
	@Override
	public FolderSecond folderGet(Long key) {
		return folder2_dao.getById(key);
	}

	@Transactional
	@Override
	public List<FolderSecond> folderGetAll(Object foreignkey) {
		return folder2_dao.getAll(foreignkey);
	}

	@Override
	public boolean folderNameDuplicationCheck(Object foreignkey, FolderSecond newFolder) {
		List<FolderSecond> folderSeconds = folderGetAll(foreignkey);
		for(FolderSecond f : folderSeconds) {
			if(f.getFolderSecondName().equals(newFolder.getFolderSecondName())) {
				return false;
			}
		}
		return true;
	}

}
