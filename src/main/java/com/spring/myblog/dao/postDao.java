package com.spring.myblog.dao;

import java.util.List;

import com.spring.myblog.domain.Post;

public interface postDao {
	public void add(Post post);

	public void delete();

	public void modify();

	public Post get(int postId, String userId, int folderFirstIndex, int folderSecondIndex);
	
	public List<Post> getAll();
	
}
