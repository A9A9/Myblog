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

import com.spring.myblog.domain.FolderFirst;
import com.spring.myblog.domain.User;
import com.spring.myblog.service.FolderService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class FolderServiceTest {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	@Qualifier("folderFirstService")
	FolderService<FolderFirst> f1Service;
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
		f1Service.folderAdd(f1);
		System.out.println("----------start");
		em.flush();
		em.clear();
	}
	@Test
	@Transactional	
	public void folderAddNameDuplication() {
		FolderFirst f1_t = new FolderFirst();
		f1_t.setFolderFirstName("f1_name");
		f1_t.setFolderFirstVisibility(true);
		boolean chk = f1Service.folderNameDuplicationCheck(user1.getUserId(),f1_t);
		assertThat(chk,is(false));
		System.out.println("--------------add1");
	}
	@Test
	@Transactional
	public void folderAddNameNoDuplication() {
		FolderFirst f1_t = new FolderFirst();
		f1_t.setFolderFirstName("f1_name1");
		f1_t.setFolderFirstVisibility(true);
		if(f1Service.folderNameDuplicationCheck(user1.getUserId(),f1_t)) {
			user1.getFolders().add(f1_t);
			f1Service.folderAdd(f1_t);
			em.flush();
		}		
		FolderFirst f1_t2 = f1Service.folderGet(f1_t.getFolderFirstIndex());
		assertThat(f1_t2,is(notNullValue()));
		assertThat(f1_t2.getFolderFirstName(),is("f1_name1"));
		System.out.println("--------------add2");
	}
	@Test
	@Transactional
	public void folderDelete() {
		FolderFirst f1_t = f1Service.folderGet(f1.getFolderFirstIndex());
		f1Service.folderDelete(f1_t);
		em.flush();
		f1_t = f1Service.folderGet(f1_t.getFolderFirstIndex());
		assertNull(f1_t);
		System.out.println("--------------delete");
	}
	@Test
	@Transactional
	public void folderNameModifyDuplication() {
		FolderFirst f1_t = f1Service.folderGet(f1.getFolderFirstIndex());
		f1_t.setFolderFirstName("f1_name");
		boolean chk = f1Service.folderNameDuplicationCheck(user1.getUserId(), f1_t);
		assertThat(chk,is(false));
	}
	
	@Test
	@Transactional
	public void folderNameModifyNoDuplication() {
		FolderFirst f1_t = f1Service.folderGet(f1.getFolderFirstIndex());
		f1_t.setFolderFirstName("newf1_name");
		if(f1Service.folderNameDuplicationCheck(user1.getUserId(), f1_t)) {
			f1Service.folderModify(f1_t);
		}
		FolderFirst f1_t2 = f1Service.folderGet(f1_t.getFolderFirstIndex());
		assertThat(f1_t2.getFolderFirstName(),is("newf1_name"));
	}
	@Test
	@Transactional
	public void folderGetAll() {
		List<FolderFirst> f1s = f1Service.folderGetAll(user1.getUserId());
		for(FolderFirst f : f1s) {
			System.out.println(f.getUserId() + " / " + f.getFolderFirstIndex() + " / " + f.getFolderFirstName());
		}
	}
	@After
	public void after() {
		em.close();
	}
}