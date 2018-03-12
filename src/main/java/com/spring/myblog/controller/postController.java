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

import com.spring.myblog.domain.Post;
import com.spring.myblog.service.PostService;

@Controller
public class postController {
	@Autowired
	PostService pService;
	
	@GetMapping("/posts/{folderIndex}/{pageNum}")
	@ResponseBody
	public List<Post> firstPage(@PathVariable("folderIndex") Long folderIndex, @PathVariable("pageNum") int pageNum) {
		List<Post> p = pService.postGetOnePage(pageNum * 5 - 5, 5, folderIndex);
		return p;
	}
	
	@GetMapping("/pages/{folderIndex}")
	@ResponseBody
	public Long pageCount(@PathVariable("folderIndex") Long folderIndex) {
		return pService.postAllCount(folderIndex);
	}
	
	@GetMapping("/post/{postIndex}")
	@ResponseBody
	public Post postSelect(@PathVariable("postIndex") Long postIndex) {
		return pService.postGetById(postIndex);
	}
	
	@PostMapping("/post/{folderIndex}")
	@ResponseBody
	public Post postAdd(@RequestBody Post newPost, @PathVariable("folderIndex") Long folderIndex) {
		pService.postAdd(newPost,folderIndex);
		System.out.println("postAddFile:" + newPost.getPostFile());
		return newPost;
	}
	
	@DeleteMapping("/post/{postIndex}")
	@ResponseBody
	public Long postDelete(@PathVariable("postIndex") Long postIndex) {
		Long folderIndex = pService.postGetById(postIndex).getFolderIndex();
		pService.postDelete(postIndex,folderIndex);
		return folderIndex;
	}
	
	@PutMapping("/post/{postIndex}")
	@ResponseBody
	public Post postModify(@RequestBody Map<String,Object> newPost, @PathVariable("postIndex") Long postIndex) {
		return pService.postModify(postIndex,newPost);
	}
	
	@GetMapping("/post/search/{userId}/{postSearch}")
	@ResponseBody
	public List<Post> postSearch(@PathVariable("userId") String userId, @PathVariable("postSearch") String postSearch) {
		return pService.postSearch(userId, postSearch);
	}
}
