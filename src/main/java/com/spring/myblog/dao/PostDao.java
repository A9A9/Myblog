package com.spring.myblog.dao;

import java.util.List;

import com.spring.myblog.domain.Post;


public interface PostDao {
	public void insert(Post post);
	public void delete(Post post);
	public Post getById(Long postIndex);
	public List<Post> getList(int startPosition, int maxResult, Long folderIndex);
	public Long getAllCount(Long folderIndex);
}
