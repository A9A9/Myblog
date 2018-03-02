package com.spring.myblog.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.spring.myblog.domain.Post;


public interface PostDao {
	public void add(Post post);

	public void delete();

	public void modify();

	public Post get();
	
	public List<Post> getList();
	
}
