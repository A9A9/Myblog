package com.spring.test.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myblog.dao.FolderDao;
import com.spring.myblog.domain.Folder;
import com.spring.myblog.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class FolderDaoTest {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	FolderDao f1Dao;
	Folder f1 = new Folder();
	User user1 = new User();
	@Before
	public void start() {
		user1.setUserId("user1_id");
		em.persist(user1);
		em.flush();
		
		f1.setFolderName("f1_name");
		f1.setFolderVisibility(true);
		
		user1.getFolders().add(f1);
		f1Dao.insert(f1);
		em.flush();
		em.clear();
	}


	@Test
	@Transactional
	public void folder1Update() {
		f1 = f1Dao.getById(f1.getFolderIndex());
		f1.setFolderName("ch_f1name");
		em.flush();
		f1 = f1Dao.getById(f1.getFolderIndex());
		//assertThat("user1", is(f1.getUserId()));
		assertThat("ch_f1name", is(f1.getFolderName()));
		assertThat(true, is(f1.isFolderVisibility()));
	}

	@Test
	@Transactional
	public void folder1Delete() {
		f1 = f1Dao.getById(f1.getFolderIndex());
		f1Dao.delete(f1);
		em.flush();
		f1 = f1Dao.getById(f1.getFolderIndex());
		assertNull(f1);
	}

	@Test
	@Transactional
	public void folder1GetAll() {
		Folder f3 = new Folder();
		f3.setFolderName("f1_name");
		f3.setFolderVisibility(true);
		f1Dao.insert(f3);
		em.flush();
		em.clear();
		User u = em.find(User.class, user1.getUserId());
		List<Folder> f1s = u.getFolders();
		for (Folder ff : f1s)
			System.out.println(ff.getFolderIndex() + " / "
			+ " / " + ff.getFolderName() + " / " +ff.isFolderVisibility());
		em.flush();

	}


	@After
	public void after() {
		em.close();
	}
}
