package myblog.test.dao;

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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myblog.dao.PostDao;
import com.spring.myblog.domain.FolderFirst;
import com.spring.myblog.domain.FolderSecond;
import com.spring.myblog.domain.Post;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class postDaoTest {

	@PersistenceContext
	private EntityManager em;
	FolderFirst f1 = new FolderFirst();
	FolderSecond f2 = new FolderSecond();
	Post p = new Post();
	@Autowired
	PostDao postDao;
	
	@Before
	public void start() {
		f1.setFolderFirstName("f1_name");
		em.persist(f1);
		
		f2.setFolderSecondName("f2_name");
		f1.getFolderSeconds().add(f2);
		em.persist(f2);
		
		p.setPostContent("postContent");
		p.setPostFile("postFile");
		p.setPostTag("tag");
		p.setPostTitle("postTitle");
		p.setPostVisibility("true");
		f2.getPosts().add(p);
		postDao.add(p);
		em.flush();
		em.clear();
	}
	
	@Test
	@Transactional
	public void postUpdate() {
		p = postDao.get(p.getPostIndex());
		p.setPostTitle("chPostTitle");
		postDao.modify(p);
		em.flush();
		
		p = postDao.get(p.getPostIndex());
		//		assertThat("user1",is(p.getPostKey().getFolderSecondKey().getFolderFirstKey().getUserId()));
//		assertThat("f1index",is(p.getPostKey().getFolderSecondKey().getFolderFirstKey().getFolderFirstIndex()));
//		assertThat("f2index", is(p.getPostKey().getFolderSecondKey().getFolderSecondIndex()));
		//assertThat("postid",is(p.getPostKey().getPostId()));
		assertThat("postContent",is(p.getPostContent()));
		assertThat("postFile",is(p.getPostFile()));
		assertThat("tag",is(p.getPostTag()));
		assertThat("true",is(p.getPostVisibility()));
		assertThat("chPostTitle",is(p.getPostTitle()));
	}
	
	@Test
	@Transactional
	public void postDelete() {
		p = postDao.get(p.getPostIndex());
		postDao.delete(p);
		em.flush();
		
		p = postDao.get(p.getPostIndex());
		assertNull(p);
	}
	
	@Test
	@Transactional
	public void folder2Delete() {
		System.out.println("-------------f2");
		f2 = em.find(FolderSecond.class, f2.getFolderSecondIndex());
		em.remove(f2);
		em.flush();
		
		f2 = em.find(FolderSecond.class, f2.getFolderSecondIndex());
		assertNull(f2);
		p = postDao.get(p.getPostIndex());
		assertNull(p);
	}
	
	@Test
	@Transactional
	public void folder1Delete() {
		System.out.println("-------------f1");
		f1 = em.find(FolderFirst.class, f1.getFolderFirstIndex());
		em.remove(f1);
		em.flush();
		
		f1 = em.find(FolderFirst.class, f1.getFolderFirstIndex());
		assertNull(f1);
		f2 = em.find(FolderSecond.class, f2.getFolderSecondIndex());
		assertNull(f2);
		p = postDao.get(p.getPostIndex());
		assertNull(p);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void postGetAll() {
		f2 = em.find(FolderSecond.class, f2.getFolderSecondIndex());
		Post p2 = new Post();
		p2.setPostContent("postContent2");
		p2.setPostFile("postFile2");
		p2.setPostTag("tag2");
		p2.setPostTitle("postTitle2");
		p2.setPostVisibility("false");
		
		f2.getPosts().add(p2);
		postDao.add(p2);
		em.flush();
		em.clear();
		List<Post> p2s = postDao.getList(f2.getFolderSecondIndex());
		assertThat(p2s.get(0).getFolderSecondIndex(),is(notNullValue()));
		assertThat(p2s.get(0).getPostIndex(),is(notNullValue()));
		assertThat(p2s.get(0).getPostTitle(),is("postTitle"));
		assertThat(p2s.get(0).getPostContent(),is("postContent"));
		assertThat(p2s.get(0).getPostFile(),is("postFile"));
		assertThat(p2s.get(0).getPostTag(),is("tag"));
		assertThat(p2s.get(0).getPostVisibility(),is("true"));
		
		assertThat(p2s.get(1).getFolderSecondIndex(),is(notNullValue()));
		assertThat(p2s.get(1).getPostIndex(),is(notNullValue()));
		assertThat(p2s.get(1).getPostTitle(),is("postTitle2"));
		assertThat(p2s.get(1).getPostContent(),is("postContent2"));
		assertThat(p2s.get(1).getPostFile(),is("postFile2"));
		assertThat(p2s.get(1).getPostTag(),is("tag2"));
		assertThat(p2s.get(1).getPostVisibility(),is("false"));
		em.flush();
	}
	
	@After
	public void after() {
		em.close();
	}
}
