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
import com.spring.myblog.domain.FolderFirstKey;
import com.spring.myblog.domain.FolderSecond;
import com.spring.myblog.domain.FolderSecondKey;
import com.spring.myblog.domain.Post;
import com.spring.myblog.domain.PostKey;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class postDaoTest {

	@PersistenceContext
	private EntityManager em;
	FolderFirstKey f1k = new FolderFirstKey();
	FolderFirst f1 = new FolderFirst();
	FolderSecondKey f2k = new FolderSecondKey();
	FolderSecond f2 = new FolderSecond();
	PostKey pk = new PostKey();
	Post p = new Post();
	//@Autowired
	//PostDao postDao;
	
	@Before
	public void start() {
		f1k.setUserId("user1");
		f1k.setFolderFirstIndex("f1index");
		
		f1.setFolderFirstKey(f1k);
		f1.setFolderFirstName("f1name");
		
		f2k.setFolderFirstKey(f1k);
		f2k.setFolderSecondIndex("f2index");
		
	// f2.setFolderfirst(f1);
		f2.setFolderSecondKey(f2k);
		f2.setFolderSecondName("f2name");
		
		f1.getFolderSeconds().add(f2);
		
		pk.setFolderSecondKey(f2k);
		pk.setPostId("postid");
		
		p.setPostContent("postContent");
		p.setPostFile("postFile");
		p.setPostTag("tag");
		p.setPostTitle("postTitle");
		p.setPostVisibility("true");
		p.setPostKey(pk);
	//	p.setFolderSecond(f2);
		
		f2.getPosts().add(p);
		
		em.persist(f1);
		em.persist(f2);
		em.persist(p);
		em.flush();
	}
	
	@Test
	@Transactional
	public void postUpdate() {
		p = em.find(Post.class, pk);
		p.setPostTitle("chPostTitle");
		em.flush();
		
		p = em.find(Post.class,pk);
		assertThat("user1",is(p.getPostKey().getFolderSecondKey().getFolderFirstKey().getUserId()));
		assertThat("f1index",is(p.getPostKey().getFolderSecondKey().getFolderFirstKey().getFolderFirstIndex()));
		assertThat("f2index", is(p.getPostKey().getFolderSecondKey().getFolderSecondIndex()));
		assertThat("postid",is(p.getPostKey().getPostId()));
		assertThat("postContent",is(p.getPostContent()));
		assertThat("postFile",is(p.getPostFile()));
		assertThat("tag",is(p.getPostTag()));
		assertThat("true",is(p.getPostVisibility()));
		assertThat("chPostTitle",is(p.getPostTitle()));
	}
	
	@Test
	@Transactional
	public void postDelete() {
		p = em.find(Post.class, pk);
		em.remove(p);
		em.flush();
		
		p = em.find(Post.class, pk);
		assertNull(p);
	}
	
	@Test
	@Transactional
	public void folder2Delete() {
		f2 = em.find(FolderSecond.class, f2k);
		em.remove(f2);
		em.flush();
		
		f2 = em.find(FolderSecond.class, f2k);
		assertNull(f2);
	}
	
	@Test
	@Transactional
	public void folder1Delete() {
		f1 = em.find(FolderFirst.class, f1k);
		em.remove(f1);
		em.flush();
		
		f1 = em.find(FolderFirst.class, f1k);
		assertNull(f1);
	}
	
	@Test
	@Transactional
	public void postGetAll() {
		PostKey pk2 = new PostKey();
		pk2.setFolderSecondKey(f2k);
		pk2.setPostId("postid2");
		
		Post p2 = new Post();
		p2.setPostContent("postContent2");
		p2.setPostFile("postFile2");
		p2.setPostTag("tag2");
		p2.setPostTitle("postTitle2");
		p2.setPostVisibility("false");
		p2.setPostKey(pk2);
		//p2.setFolderSecond(f2);
		em.persist(p2);
		
		List<Post> p2s = em.createQuery("select p from Post p", Post.class).getResultList();
		for (Post pp : p2s)
			System.out.println(pp.getPostKey().getFolderSecondKey().getFolderFirstKey().getUserId() + " / "
					+ pp.getPostKey().getFolderSecondKey().getFolderFirstKey().getFolderFirstIndex() + " / "
					+ pp.getPostKey().getFolderSecondKey().getFolderSecondIndex() + " / " +  pp.getPostKey().getPostId()
					+ pp.getPostContent() + " / " + pp.getPostFile() + " / " + pp.getPostTag() + " / " + pp.getPostTitle()
					+  " / " + pp.getPostVisibility());

		em.flush();
		
	}
	
	@After
	public void after() {
		em.close();
	}
}
