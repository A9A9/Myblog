package com.spring.myblog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myblog.dao.FolderDao;
import com.spring.myblog.dao.PostDao;
import com.spring.myblog.dao.UserDao;
import com.spring.myblog.domain.Folder;
import com.spring.myblog.domain.Post;
import com.spring.myblog.domain.User;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	PostDao postDao;
	@Autowired
	FolderDao fDao;
	
	@Autowired
	UserDao uDao;
	
	@Transactional
	@Override
	public void postAdd(Post newPost, Long folderIndex) {
		Folder f = fDao.getById(folderIndex);
		f.getPosts().add(newPost);
		postDao.insert(newPost);
	}
	@Transactional
	@Override
	public Post postModify(Long postIndex,Map<String,Object> newPost) {
		Post p = postGetById(postIndex);
		p.setPostTitle(newPost.get("postTitle").toString());
		p.setPostFile(newPost.get("postFile").toString());
		p.setPostContent(newPost.get("postContent").toString());
		return p;
	}

	@Transactional
	@Override
	public void postDelete(Long postIndex, Long folderIndex) {
		Folder f = fDao.getById(folderIndex);
		Post p = postGetById(postIndex);
		f.getPosts().remove(p);
		postDao.delete(p);
	}

	@Transactional
	@Override
	public Post postGetById(Long key) {
		return postDao.getById(key);
	}

	@Transactional
	@Override
	public List<Post> postSearch(String userId, String search) {
		User user = uDao.get(userId);
		List<Post> p1 = new ArrayList<Post>();
		for(Folder f1 : user.getFolders()) {
				for(Post post : f1.getPosts()) {
					if (post.getPostTitle().contains(search) || post.getPostContent().contains(search)) {
						p1.add(post);
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
	public List<Post> postGetOnePage(int startPosition, int maxResult, Long folderIndex) {
		return postDao.getList(startPosition, maxResult, folderIndex);
	}

	@Transactional
	@Override
	public Long postAllCount(Long folderIndex) {
		return postDao.getAllCount(folderIndex);
	}


}
