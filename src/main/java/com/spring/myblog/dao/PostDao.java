package com.spring.myblog.dao;

import java.util.List;

import com.spring.myblog.domain.Post;


public interface PostDao {
	public void add(Post post);

	public void delete();

	public void modify();

	public Post get(Long postIndex);
	
	public List<Post> getList(Object... columns);
	
}
