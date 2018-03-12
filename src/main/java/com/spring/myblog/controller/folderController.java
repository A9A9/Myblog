package com.spring.myblog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.myblog.domain.Folder;
import com.spring.myblog.service.FolderService;


@Controller
public class folderController {

//	@Autowired
//	UserService uService;
	@Autowired
	FolderService fService;
	
	@GetMapping("/folders/{userId}")
	@ResponseBody
	public List<Folder> folderList(@PathVariable("userId") String userId) {
		return fService.folderGetAll(userId);
	}
	
	@PostMapping("/folder/{userId}")
	@ResponseBody
	public List<Folder> folderAdd(@RequestBody Map<String,String> newFolderName, @PathVariable("userId") String userId) {
		String newName = newFolderName.get("folderName");
		if(newName.trim().length() == 0 || !fService.folderNameDuplicationCheck(fService.folderGetAll(userId), newName)) {
			return null;
		}
		Folder newFolder = new Folder();
		newFolder.setFolderName(newName);
		fService.folderAdd(newFolder,userId);
		return fService.folderGetAll(userId);
	}
	
	@PutMapping("/folder/{userId}/{folderIndex}")
	@ResponseBody
	public List<Folder> folderModify(@PathVariable("userId") String userId, @RequestBody Map<String,String> newFolderName, @PathVariable("folderIndex") Long folderIndex) {
		String newName = newFolderName.get("folderName");
		System.out.println(newName);
		if(newName.trim().length() == 0 || !fService.folderNameDuplicationCheck(fService.folderGetAll(userId), newName)) {
			return null;
		}
		fService.folderModify(folderIndex,newName);
		return fService.folderGetAll(userId);
	}
	
	@DeleteMapping("/folder/{userId}/{folderIndex}")
	@ResponseBody
	public List<Folder> folderAdd(@PathVariable("userId") String userId, @PathVariable("folderIndex") Long folderIndex) {
		fService.folderDelete(folderIndex, userId);
		return fService.folderGetAll(userId);
	}
	//메인 컨트롤러로 넣어야함 
	@GetMapping("/blog")
	public String blogStart() {
		return "blog";
	}
	
	
}
