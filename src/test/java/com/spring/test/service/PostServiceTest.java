package com.spring.test.service;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myblog.dao.PostDao;
import com.spring.myblog.domain.FolderFirst;
import com.spring.myblog.domain.FolderSecond;
import com.spring.myblog.domain.Post;
import com.spring.myblog.domain.User;
import com.spring.myblog.service.PostService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class PostServiceTest {
	@PersistenceContext
	private EntityManager em;
	@Autowired
	PostDao pDao;
	@Autowired
	PostService pService;
	Post p = new Post();
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
		em.persist(f2);
		
		p.setPostContent("postContent1");
		p.setPostFile("postFile1");
		p.setPostTag("tag1");
		p.setPostTitle("postTitle1");
		p.setPostVisibility(true);
		f2.getPosts().add(p);
		pService.postAdd(p);
		
		Post p2 = new Post();
		p2.setPostContent("postContent2");
		p2.setPostFile("postFile2");
		p2.setPostTag("tag2");
		p2.setPostTitle("postTitle2");
		p2.setPostVisibility(true);
		f2.getPosts().add(p2);
		pService.postAdd(p2);
		
		Post p3 = new Post();
		p3.setPostContent("내용3");
		p3.setPostFile("파일3");
		p3.setPostTag("태그3");
		p3.setPostTitle("제목3");
		p3.setPostVisibility(true);
		f2.getPosts().add(p3);
		pService.postAdd(p3);
		
		Post p4 = new Post();
		p4.setPostContent("내용4");
		p4.setPostFile("파일4");
		p4.setPostTag("태그4");
		p4.setPostTitle("제목4");
		p4.setPostVisibility(true);
		f2.getPosts().add(p4);
		pService.postAdd(p4);
		
		Post p5 = new Post();
		p5.setPostContent("내용5");
		p5.setPostFile("파일5");
		p5.setPostTag("태그5");
		p5.setPostTitle("제목5");
		p5.setPostVisibility(true);
		f2.getPosts().add(p5);
		pService.postAdd(p5);
		
		Post p6 = new Post();
		p6.setPostContent("내용6");
		p6.setPostFile("파일6");
		p6.setPostTag("태그6");
		p6.setPostTitle("제목6");
		p6.setPostVisibility(true);
		f2.getPosts().add(p6);
		pService.postAdd(p6);
		
		System.out.println("----------start");
		em.flush();
		em.clear();
	}
	
	@Test
	@Transactional
	public void getByPostSearch() {
//		List<Post> p1 = new ArrayList<Post>();
//		for(FolderFirst f1t : user1.getFolders()) {
//			for(FolderSecond f2t : f1t.getFolderSeconds()) {
//				for(Post post : f2t.getPosts()) {
//					if (post.getPostTitle().contains("postTitle") || post.getPostContent().contains("내용3")) {
//						p1.add(post);
//					}
//				}
//			}
//		}
		List<Post> p1 = pService.postSearch(user1, "postTitle");
		em.flush();
		for(Post pt : p1) {
			System.out.println(pt.getPostIndex() + " / "
					+ pt.getPostTitle() + " / " 
					+ pt.getPostFile() + " / "
					+ pt.getPostContent() + " / "
					+ pt.getPostTag() + " / "
					+ pt.isPostVisibility());
		}
		
	}
	
	@Test
	@Transactional
	public void noGetByPostSearch() {
//		List<Post> p1 = new ArrayList<Post>();
//		for(FolderFirst f1t : user1.getFolders()) {
//			for(FolderSecond f2t : f1t.getFolderSeconds()) {
//				for(Post post : f2t.getPosts()) {
//					if (post.getPostTitle().contains("존재하지않음") || post.getPostContent().contains("con")) {
//						p1.add(post);
//					}
//				}
//			}
//		}
//		assertThat(p1.isEmpty(),is(true));
		List<Post> p1 = pService.postSearch(user1, "존재하지않음");
		em.flush();
		assertNull(p1);
	}
	@Test
	@Transactional
	public void postDelete() {
//		Post pt = pDao.getById(p.getPostIndex());
//		pDao.delete(pt);
//		pt = pDao.getById(p.getPostIndex());
		Post pt = pService.postGetById(p.getPostIndex());
		pService.postDelete(pt);
		em.flush();
		pt = pService.postGetById(p.getPostIndex());
		assertNull(pt);
	}

	@Test
	@Transactional
	public void postModify() {
		Post pt = pService.postGetById(p.getPostIndex());
		pt.setPostTitle("제목수정");
		pService.postModify(pt);
		em.flush();
		pt = pService.postGetById(p.getPostIndex());
		assertThat(pt.getPostTitle(),is("제목수정"));
	}
	
	@Test
	@Transactional
	public void postGetOnePage() {
		List<Post> posts = pService.postGetOnePage(0,3,f2.getFolderSecondIndex());
		for(Post pt : posts) {
			System.out.println(pt.getPostIndex() + " / "
					+ pt.getPostTitle() + " / " 
					+ pt.getPostFile() + " / "
					+ pt.getPostContent() + " / "
					+ pt.getPostTag() + " / "
					+ pt.isPostVisibility());
		}
	}
	
	@Test
	@Transactional
	public void postGetAllCount() {
		Long count = pService.postAllCount(f2.getFolderSecondIndex());
		assertThat(count,is(6L));
	}
	@After
	public void after() {
		em.close();
	}
}
