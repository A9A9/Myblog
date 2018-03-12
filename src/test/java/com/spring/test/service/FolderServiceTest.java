package com.spring.test.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myblog.domain.Folder;
import com.spring.myblog.domain.User;
import com.spring.myblog.service.FolderService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class FolderServiceTest {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	FolderService f1Service;
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
		f1Service.folderAdd(f1,user1.getUserId());
		System.out.println("----------start");
		em.flush();
		em.clear();
	}
	@Test
	@Transactional	
	public void folderAddNameDuplication() {
		boolean chk = f1Service.folderNameDuplicationCheck(user1.getFolders(),"f1_name");
		assertThat(chk,is(false));
		System.out.println("--------------add1");
	}
	@Test
	@Transactional
	public void folderAddNameNoDuplication() {
		Folder f1_t = new Folder();
		if(f1Service.folderNameDuplicationCheck(user1.getFolders(),"f1_name1")) {
			f1_t.setFolderName("f1_name1");
			f1_t.setFolderVisibility(true);
			user1.getFolders().add(f1_t);
			f1Service.folderAdd(f1_t,user1.getUserId());
			em.flush();
		}		
		Folder f1_t2 = f1Service.folderGet(f1_t.getFolderIndex());
		assertThat(f1_t2,is(notNullValue()));
		assertThat(f1_t2.getFolderName(),is("f1_name1"));
		System.out.println("--------------add2");
	}
	@Test
	@Transactional
	public void folderDelete() {
		Folder f1_t = f1Service.folderGet(f1.getFolderIndex());
		f1Service.folderDelete(f1_t.getFolderIndex(),user1.getUserId());
		em.flush();
		f1_t = f1Service.folderGet(f1_t.getFolderIndex());
		assertNull(f1_t);
		System.out.println("--------------delete");
	}
	@Test
	@Transactional
	public void folderNameModifyNoDuplication() {
		Folder f1_t = f1Service.folderGet(f1.getFolderIndex());
		if(f1Service.folderNameDuplicationCheck(user1.getFolders(), "newf1_name")) {
			f1Service.folderModify(f1_t.getFolderIndex(),"newf1_name");
		}
		Folder f1_t2 = f1Service.folderGet(f1_t.getFolderIndex());
		assertThat(f1_t2.getFolderName(),is("newf1_name"));
	}
	@Test
	@Transactional
	public void folderGetAll() {
		User u = em.find(User.class, user1.getUserId());
		List<Folder> f1s = u.getFolders();
		for(Folder f : f1s) {
			System.out.println(f.getUserId() + " / " + f.getFolderIndex() + " / " + f.getFolderName());
		}
	}
	@After
	public void after() {
		em.close();
	}
}