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
import com.spring.myblog.domain.FolderFirst;
import com.spring.myblog.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class FolderDaoTest {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	@Qualifier("folderFirstDao")
	FolderDao<FolderFirst> f1Dao;
//	@Autowired
//	FolderService<FolderFirst> f1Service;
	FolderFirst f1 = new FolderFirst();
	User user1 = new User();
	@Before
	public void start() {
		user1.setUserId("user1_id");
		em.persist(user1);
		em.flush();
		
		f1.setFolderFirstName("f1_name");
		f1.setFolderFirstVisibility(true);
		
		user1.getFolders().add(f1);
		f1Dao.insert(f1);
		em.flush();
		em.clear();
	}


	@Test
	@Transactional
	public void folder1Update() {
		f1 = f1Dao.getById(f1.getFolderFirstIndex());
		f1.setFolderFirstName("ch_f1name");
		em.flush();
		f1 = f1Dao.getById(f1.getFolderFirstIndex());
		//assertThat("user1", is(f1.getUserId()));
		assertThat("ch_f1name", is(f1.getFolderFirstName()));
		assertThat(true, is(f1.isFolderFirstVisibility()));
	}

	@Test
	@Transactional
	public void folder1Delete() {
		f1 = f1Dao.getById(f1.getFolderFirstIndex());
		f1Dao.delete(f1);
		em.flush();
		f1 = f1Dao.getById(f1.getFolderFirstIndex());
		assertNull(f1);
	}

//	@Test
//	@Transactional
//	public void folder1GetAll() {
//		FolderFirst f3 = new FolderFirst();
//		f3.setFolderFirstName("f1_name");
//		f3.setFolderFirstVisibility(true);
//		f1Dao.insert(f3);
//
//		List<FolderFirst> f1s = f1Dao.getAll();
//		for (FolderFirst ff : f1s)
//			System.out.println(ff.getFolderFirstIndex() + " / "
//			+ " / " + ff.getFolderFirstName() + " / " +ff.isFolderFirstVisibility());
//		em.flush();
//
//	}


	@After
	public void after() {
		em.close();
	}
}
