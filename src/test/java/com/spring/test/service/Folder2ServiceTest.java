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
import com.spring.myblog.domain.FolderSecond;
import com.spring.myblog.domain.User;
import com.spring.myblog.service.FolderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class Folder2ServiceTest {
	@PersistenceContext
	private EntityManager em;
	@Autowired
	@Qualifier("folderSecondService")
	FolderService<FolderSecond> f2Service;
	
	FolderSecond f2 = new FolderSecond();
	FolderFirst f1 = new FolderFirst();
	User user1 = new User();
	@Before
	public void start() {
		user1.setUserId("user1_id");
		em.persist(user1);
		
		f1.setFolderFirstName("f1_name");
		f1.setFolderFirstVisibility(true);
		user1.getFolders().add(f1);
		em.persist(f1);
		
		f2.setFolderSecondName("f2_name");
		f2.setFolderSecondVisibility(true);
		f1.getFolderSeconds().add(f2);
		f2Service.folderAdd(f2);
		System.out.println("----------start");
		em.flush();
		em.clear();
	}
	@Test
	@Transactional	
	public void folderAddNameDuplication() {
		FolderSecond f2_t = new FolderSecond();
		f2_t.setFolderSecondName("f2_name");
		f2_t.setFolderSecondVisibility(true);
		boolean chk = f2Service.folderNameDuplicationCheck(f1.getFolderFirstIndex(),f2_t);
		assertThat(chk,is(false));
		System.out.println("--------------1");
	}
	@Test
	@Transactional
	public void folderAddNameNoDuplication() {
		FolderSecond f2_t = new FolderSecond();
		f2_t.setFolderSecondName("f2_name1");
		f2_t.setFolderSecondVisibility(true);
		if(f2Service.folderNameDuplicationCheck(f1.getFolderFirstIndex(),f2_t)) {
			f1.getFolderSeconds().add(f2_t);
			f2Service.folderAdd(f2_t);
			em.flush();
		}		
		FolderSecond f2_t2 = f2Service.folderGet(f2_t.getFolderSecondIndex());
		assertThat(f2_t2,is(notNullValue()));
		assertThat(f2_t2.getFolderSecondName(),is("f2_name1"));
		System.out.println("--------------2");
	}
	@Test
	@Transactional
	public void folder2Delete() {
		f2 = f2Service.folderGet(f2.getFolderSecondIndex());
		f2Service.folderDelete(f2);
		em.flush();
		f2 = f2Service.folderGet(f2.getFolderSecondIndex());
		assertNull(f2);
		System.out.println("--------------delete2");
		
	}
	@Test
	@Transactional
	public void folder1Delete() {
		em.remove(em.find(FolderFirst.class, f1.getFolderFirstIndex()));
		em.flush();
		f2 = f2Service.folderGet(f2.getFolderSecondIndex());
		assertNull(f2);
		System.out.println("--------------delete1");
	}

	@Test
	@Transactional
	public void folderNameModifyDuplication() {
		FolderSecond f2_t = f2Service.folderGet(f2.getFolderSecondIndex());
		f2_t.setFolderSecondName("f2_name");
		boolean chk = f2Service.folderNameDuplicationCheck(f1.getFolderFirstIndex(),f2_t);
		assertThat(chk,is(false));
	}
	
	@Test
	@Transactional
	public void folderNameModifyNoDuplication() {
		FolderSecond f2_t = f2Service.folderGet(f2.getFolderSecondIndex());
		f2_t.setFolderSecondName("newf2_name");
		if(f2Service.folderNameDuplicationCheck(f1.getFolderFirstIndex(),f2_t)) {
			f2Service.folderModify(f2_t);
		}
		FolderSecond f2_t2 = f2Service.folderGet(f2_t.getFolderSecondIndex());
		assertThat(f2_t2.getFolderSecondName(),is("newf2_name"));
	}
	@Test
	@Transactional
	public void folderGetAll() {
		List<FolderSecond> f2s = f2Service.folderGetAll(f1.getFolderFirstIndex());
		for(FolderSecond f : f2s) {
			System.out.println(f.getFolderFirstIndex() + " / " + f.getFolderSecondIndex() + " / " + f.getFolderSecondName());
		}
	}
	@After
	public void after() {
		em.close();
	}
}
