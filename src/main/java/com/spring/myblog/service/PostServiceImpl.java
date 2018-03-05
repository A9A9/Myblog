package com.spring.myblog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myblog.dao.PostDao;
import com.spring.myblog.domain.FolderFirst;
import com.spring.myblog.domain.FolderSecond;
import com.spring.myblog.domain.Post;
import com.spring.myblog.domain.User;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	PostDao postDao;
	
	@Transactional
	@Override
	public void postAdd(Post newPost) {
		postDao.insert(newPost);
	}

	@Transactional
	@Override
	public void postModify(Post newPost) {}

	@Transactional
	@Override
	public void postDelete(Post post) {
		postDao.delete(post);
	}

	@Transactional
	@Override
	public Post postGetById(Long key) {
		return postDao.getById(key);
	}

	@Transactional
	@Override
	public List<Post> postSearch(User user, String search) {
		List<Post> p1 = new ArrayList<Post>();
		for(FolderFirst f1 : user.getFolders()) {
			for(FolderSecond f2 : f1.getFolderSeconds()) {
				for(Post post : f2.getPosts()) {
					if (post.getPostTitle().contains(search) || post.getPostContent().contains(search)) {
						p1.add(post);
					}
				}
			}
		}
		if(p1.isEmpty()) {
			return null;
		}
		return p1;
	}

	@Transactional
	@Override
	public List<Post> postGetOnePage(int startPosition, int maxResult, Object foreignkey) {
		return postDao.getList(startPosition, maxResult, foreignkey);
	}

	@Transactional
	@Override
	public Long postAllCount(Object foreignkey) {
		return postDao.getAllCount(foreignkey);
	}
}
