package com.spring.test.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myblog.dao.PostDao;
import com.spring.myblog.domain.Folder;
import com.spring.myblog.domain.Post;
import com.spring.myblog.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class PostDaoTest {

	@PersistenceContext
	private EntityManager em;
	User user1 = new User();
	Folder f1 = new Folder();
	Post p = new Post();
	@Autowired
	PostDao postDao;
	
	@Before
	public void start() {
		user1.setUserId("user1_id");
		em.persist(user1);
		
		f1.setFolderName("f1_name");
		em.persist(f1);
		user1.getFolders().add(f1);
		
		p.setPostContent("postContent");
		p.setPostFile("postFile");
		p.setPostTitle("postTitle");
		p.setPostDate(Calendar.getInstance().getTime());
		p.setPostVisibility(true);
		f1.getPosts().add(p);
		postDao.insert(p);
		
		em.flush();
		em.clear();
	}
	
	@Test
	@Transactional
	public void postUpdate() {
		p = postDao.getById(p.getPostIndex());
		p.setPostTitle("chPostTitle");
		em.flush();
		
		p = postDao.getById(p.getPostIndex());
		assertThat("postContent",is(p.getPostContent()));
		assertThat("postFile",is(p.getPostFile()));
		
		assertThat(true,is(p.isPostVisibility()));
		assertThat("chPostTitle",is(p.getPostTitle()));
	}
	
	@Test
	@Transactional
	public void postDelete() {
		p = postDao.getById(p.getPostIndex());
		postDao.delete(p);
		em.flush();
		
		p = postDao.getById(p.getPostIndex());
		assertNull(p);
	}
	
	@Test
	@Transactional
	public void folder1Delete() {
		System.out.println("-------------f1");
		f1 = em.find(Folder.class, f1.getFolderIndex());
		em.remove(f1);
		em.flush();
		
		f1 = em.find(Folder.class, f1.getFolderIndex());
		assertNull(f1);
		p = postDao.getById(p.getPostIndex());
		assertNull(p);
	}
	
	@Test
	@Transactional
	public void postGetAll() {
		f1 = em.find(Folder.class, f1.getFolderIndex());
		Post p2 = new Post();
		p2.setPostContent("postContent2");
		p2.setPostFile("postFile2");
		p2.setPostTitle("postTitle2");
		p2.setPostVisibility(false);
		
		f1.getPosts().add(p2);
		postDao.insert(p2);
		em.flush();
		em.clear();
		List<Post> p2s = postDao.getList(0,2,f1.getFolderIndex());
		
		assertThat(p2s.size(),is(2));
		assertThat(p2s.get(0).getFolderIndex(),is(notNullValue()));
		assertThat(p2s.get(0).getPostIndex(),is(notNullValue()));
		assertThat(p2s.get(0).getPostTitle(),is("postTitle2"));
		assertThat(p2s.get(0).getPostContent(),is("postContent2"));
		assertThat(p2s.get(0).getPostFile(),is("postFile2"));
		assertThat(p2s.get(0).isPostVisibility(),is(false));
		
		assertThat(p2s.get(1).getFolderIndex(),is(notNullValue()));
		assertThat(p2s.get(1).getPostIndex(),is(notNullValue()));
		assertThat(p2s.get(1).getPostTitle(),is("postTitle"));
		assertThat(p2s.get(1).getPostContent(),is("postContent"));
		assertThat(p2s.get(1).getPostFile(),is("postFile"));
		assertThat(p2s.get(1).isPostVisibility(),is(true));
		
		em.flush();
	}
	
	@Test
	@Transactional
	public void postCount() {
		f1 = em.find(Folder.class, f1.getFolderIndex());
		Post p2 = new Post();
		p2.setPostContent("postContent2");
		p2.setPostFile("postFile2");
		p2.setPostTitle("postTitle2");
		p2.setPostVisibility(false);
		
		f1.getPosts().add(p2);
		postDao.insert(p2);
		em.flush();
		em.clear();
		Long count = postDao.getAllCount(f1.getFolderIndex());
		assertThat(count,is(2L));
	}
	
	@After
	public void after() {
		em.close();
	}
}
