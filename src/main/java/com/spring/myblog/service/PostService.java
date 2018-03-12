package com.spring.myblog.service;

import java.util.List;

import com.spring.myblog.domain.Post;
import com.spring.myblog.domain.User;

public interface PostService{
	public void postAdd(Post newPost, Long folderIndex);
	public void postModify(Post newPost);
	public void postDelete(Long postIndex, Long folderIndex);
	public Post postGetById(Long key);
	public List<Post> postSearch(String userId,String search);
	public List<Post> postGetOnePage(int startPosition, int maxResult, Long folderIndex);
	public Long postAllCount(Long folderIndex);
}
