package com.spring.myblog.service;

import java.util.List;
import java.util.Map;

import com.spring.myblog.domain.Post;

public interface PostService{
	public void postAdd(Post newPost, Long folderIndex);
	public Post postModify(Long postIndex,Map<String,Object> newPost);
	public void postDelete(Long postIndex, Long folderIndex);
	public Post postGetById(Long key);
	public List<Post> postSearch(String userId,String search);
	public List<Post> postGetOnePage(int startPosition, int maxResult, Long folderIndex);
	public Long postAllCount(Long folderIndex);
}
