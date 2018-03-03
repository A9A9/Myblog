package com.spring.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myblog.dao.FolderDao;
import com.spring.myblog.dao.PostDao;
import com.spring.myblog.dao.UserDao;
import com.spring.myblog.domain.FolderFirst;
import com.spring.myblog.domain.FolderSecond;
import com.spring.myblog.domain.Post;
import com.spring.myblog.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDaoTest {
	@PersistenceContext EntityManager em;
	@Autowired UserDao userDao;
	@Autowired @Qualifier("folderFirstDao")
	FolderDao<FolderFirst> f1Dao;
	@Autowired @Qualifier("folderSecondDao")
	FolderDao<FolderSecond> f2Dao;
	@Autowired PostDao postDao;
	
	private User user1;
	private User user2;
	private FolderFirst f1;
	private FolderFirst f1_1;
	private FolderSecond f2;
	private Post p;
	
	@Before
	public void before()
	{
		user1 = new User();
		user1.setUserId("user1_id");
		user1.setUserPw("user1_pw");
		user1.setUserName("user1_name");
		user1.setUserEmail("user1_email");
		user1.setBlogName("user1_blogname");
		user1.setBlogVisibility("user1_visibility");
		user1.setNickName("user1_nickname");
		user1.setProfileIntro("user1_intro");
		user1.setProfilePhoto("user1_photo");
		
		user2 = new User();
		user2.setUserId("user2_id");
		user2.setUserPw("user2_pw");
		user2.setUserName("user2_name");
		user2.setUserEmail("user2_email");
		user2.setBlogName("user2_blogname");
		user2.setBlogVisibility("user2_visibility");
		user2.setNickName("user2_nickname");
		user2.setProfileIntro("user2_intro");
		user2.setProfilePhoto("user2_photo");
		
		f1 = new FolderFirst();
		f1_1 = new FolderFirst();
		f1.setFolderFirstName("folder1");
		f1_1.setFolderFirstName("folder1_1");
		user1.getFolders().add(f1);
		user1.getFolders().add(f1_1);
		
		f2 = new FolderSecond();
		f2.setFolderSecondName("folder2");
		
		p = new Post();
		p.setPostTitle("postTitle");
		p.setPostTag("postTag");
		p.setPostContent("postContent");
		p.setPostFile("postFile");
		p.setPostVisibility("postVisibility");
		
		f1.getFolderSeconds().add(f2);
		f2.getPosts().add(p);
	}
	
	@Test
	@Transactional
	//@Rollback(false)
	public void userAddTest() {
		//assertThat(userDao, is(nullValue()));
		userDao.add(user1);
		userDao.add(user2);
		
		User tmpuser = userDao.get(user1.getUserId());
		assertThat(tmpuser.getUserName(), is(user1.getUserName()));
		
		em.flush();
		em.clear();
		em.close();
	}
	
	@Test
	@Transactional
//	@Rollback(false)
	public void userModifyTest()
	{
		userDao.add(user1);
		userDao.add(user2);
		
		User tmpuser = userDao.get(user1.getUserId());
		tmpuser.setUserName("modify_name");
		
		em.flush();
		em.clear();
		em.close();
	}
	
	@Test
	@Transactional
	//@Rollback(false)
	public void userDeleteTest()
	{
		userDao.add(user1);
		userDao.add(user2);
		
		User tmpuser = userDao.get(user1.getUserId());
		userDao.delete(tmpuser);
		
		em.flush();
		em.clear();
		em.close();
	}
	
	@Test
	@Transactional
	public void userGetTest()
	{
		userDao.add(user1);
		User tmpuser = userDao.get(user1.getUserId());
		assertThat(tmpuser.getUserId(), is(user1.getUserId()));
		User tmpUser2 = userDao.get("tmp");
		assertNull(tmpUser2);
	}
	
	@Test
	@Transactional
	//@Rollback(false)
	public void userGetAllTest()
	{
		userDao.add(user1);
		userDao.add(user2);
		
		List<User> users = userDao.getAll();
		
		assertThat(users.get(0).getUserName(), is("user1_name"));
		assertThat(users.get(1).getUserName(), is("user2_name"));
	}
	
	@Test
	@Transactional
	//@Rollback(false)
	public void folderAndPosTDeleteCheck()
	{
		System.out.println("folder start");
		userDao.add(user1);
		f1Dao.add(f1);
		f1Dao.add(f1_1);
		f2Dao.add(f2);
		postDao.add(p);
		Long f1_index1 = f1.getFolderFirstIndex();
		Long f1_index2 = f1.getFolderFirstIndex();
		Long f2_index = f2.getFolderSecondIndex();
		Long p_index = p.getPostIndex();
		em.flush();
		em.clear();

		User tmpUser = userDao.get(user1.getUserId());
		assertThat(tmpUser.getUserId(), is("user1_id"));
		userDao.delete(tmpUser);
		User checkUser = userDao.get("user1_id");
		FolderFirst checkF1 = f1Dao.get(f1_index1);
		FolderFirst checkF1_1 = f1Dao.get(f1_index2);
		FolderSecond checkF2 = f2Dao.get(f2_index);
		Post checkP = postDao.get(p_index);
		
		assertNull(checkUser);
		assertNull(checkF1);
		assertNull(checkF1_1);
		assertNull(checkF2);
		assertNull(checkP);
		
		em.flush();
		
		System.out.println("folder end");
	}
}