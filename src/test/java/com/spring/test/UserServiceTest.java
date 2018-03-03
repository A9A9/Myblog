package com.spring.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myblog.dao.UserDao;
import com.spring.myblog.domain.User;
import com.spring.myblog.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserServiceTest {
	@PersistenceContext EntityManager em;
	@Autowired UserDao userDao;
	@Autowired UserService userService;
	
	private User user1;
	private User user2;
	
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
		
		//userDao.add(user1);
	}
	
	@Test
	@Transactional
	public void userIdDuplicationTest()
	{
		userDao.add(user1);
		user2.setUserId("user1_id");
		
		assertThat(userService.userIdDuplicationCheck(user2.getUserId()), is(false));
		user2.setUserId("user2_id");
		assertThat(userService.userIdDuplicationCheck(user2.getUserId()), is(true));
	}
	
	@Test
	@Transactional
	public void nickNameDuplicationAndSetTest()
	{
		userDao.add(user1);
		userDao.add(user2);
		assertThat(userService.setNickName(user2, user1.getNickName()), is(false));
		assertThat(userService.setNickName(user2, "change"), is(true));
		
		User tmpuser = userDao.get(user2.getUserId());
		assertThat(tmpuser.getNickName(), is("change"));
	}
	
	@Test
	@Transactional
	public void loginTest()
	{
		userDao.add(user1);
		User user = userDao.get(user1.getUserId());
		assertThat(userService.login("user1_id", "user1_pw"), is(true));
		assertThat(userService.login("user1_id", "user2_pw"), is(false));
		assertThat(userService.login("user2_id", "user1_pw"), is(false));
	}
	
	@Test
	@Transactional
	public void BlogInitTest()
	{
		User addUser = new User();
		addUser.setUserId("addUserId");
		addUser.setUserPw("addUserPw");
		addUser.setUserName("addUserName");
		addUser.setUserEmail("addUserEmail");
		if(userService.setNickName(addUser, "addUserNickName"))
			userService.blogInit(addUser);
		assertThat(addUser.getNickName(), is("addUserNickName"));
		assertThat(addUser.getBlogName(), is(addUser.getNickName() + " 의 블로그"));
	}
}