package com.spring.myblog.service;

import java.util.List;

import com.spring.myblog.domain.Post;
import com.spring.myblog.domain.User;

public interface PostService{
	public void postAdd(Post newPost);
	public void postModify(Post newPost);
	public void postDelete(Post post);
	public Post postGetById(Long key);
	public List<Post> postSearch(User user,String search);
	public List<Post> postGetOnePage(int startPosition, int maxResult, Object foreignkey);
	public Long postAllCount(Object foreignkey);
}
