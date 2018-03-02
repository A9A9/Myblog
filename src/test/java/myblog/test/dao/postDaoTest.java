package myblog.test.dao;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class postDaoTest {

	@PersistenceContext
	private EntityManager em;
	FolderFirst f1 = new FolderFirst();
	FolderSecond f2 = new FolderSecond();
	Post p = new Post();
	//@Autowired
	//PostDao postDao;
	
	@Before
	public void start() {
		f1.setFolderFirstName("f1_name");
		f2.setFolderSecondName("f2_name");
		f1.getFolderSeconds().add(f2);
		
		p.setPostContent("postContent");
		p.setPostFile("postFile");
		p.setPostTag("tag");
		p.setPostTitle("postTitle");
		p.setPostVisibility("true");
		
		f2.getPosts().add(p);
		
		em.persist(f1);
		em.persist(f2);
		em.persist(p);
		em.flush();
	}
	
	@Test
	@Transactional
	public void postUpdate() {
		p = em.find(Post.class, p.getPostIndex());
		p.setPostTitle("chPostTitle");
		em.flush();
		
		p = em.find(Post.class, p.getPostIndex());
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
		p = em.find(Post.class, p.getPostIndex());
		em.remove(p);
		em.flush();
		
		p = em.find(Post.class, p.getPostIndex());
		assertNull(p);
	}
	
	@Test
	@Transactional
	public void folder2Delete() {
		f2 = em.find(FolderSecond.class, f2.getFolderSecondIndex());
		em.remove(f2);
		em.flush();
		
		f2 = em.find(FolderSecond.class, f2.getFolderSecondIndex());
		assertNull(f2);
		p = em.find(Post.class, p.getPostIndex());
		assertNull(p);
	}
	
	@Test
	@Transactional
	public void folder1Delete() {
		f1 = em.find(FolderFirst.class, f1.getFolderFirstIndex());
		em.remove(f1);
		em.flush();
		
		f1 = em.find(FolderFirst.class, f1.getFolderFirstIndex());
		assertNull(f1);
		f2 = em.find(FolderSecond.class, f2.getFolderSecondIndex());
		assertNull(f2);
		p = em.find(Post.class, p.getPostIndex());
		assertNull(p);
	}
	
	@Test
	@Transactional
	public void postGetAll() {
		Post p2 = new Post();
		p2.setPostContent("postContent2");
		p2.setPostFile("postFile2");
		p2.setPostTag("tag2");
		p2.setPostTitle("postTitle2");
		p2.setPostVisibility("false");
		
		f2.getPosts().add(p2);
		em.persist(p2);
		
		List<Post> p2s = em.createQuery("select p from Post p where p.folderSecondIndex = " + f2.getFolderSecondIndex(), Post.class).getResultList();
		for (Post pp : p2s)
			System.out.println(pp.getFolderSecondIndex() + " / " +  pp.getPostIndex() + " / "
					+ pp.getPostContent() + " / " + pp.getPostFile() + " / " + pp.getPostTag() + " / " + pp.getPostTitle()
					+  " / " + pp.getPostVisibility());
			

		em.flush();
		
	}
	
	@After
	public void after() {
		em.close();
	}
}
