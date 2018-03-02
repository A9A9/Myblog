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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myblog.dao.FolderDao;
import com.spring.myblog.domain.FolderFirst;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class folderDaoTest {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	@Qualifier("folderFirstDao")
	FolderDao<FolderFirst> f1Dao;

	FolderFirst f1 = new FolderFirst();

	@Before
	public void start() {
		f1.setFolderFirstName("f1_name");
		f1Dao.add(f1);
		em.flush();
		em.clear();
	}

	@Test
	@Transactional
	public void folder1Update() {
		f1 = f1Dao.get(f1.getFolderFirstIndex());
		f1.setFolderFirstName("ch_f1name");
		f1Dao.modify(f1);
		em.flush();
		f1 = f1Dao.get(f1.getFolderFirstIndex());
		//assertThat("user1", is(f1.getUserId()));
		assertThat("ch_f1name", is(f1.getFolderFirstName()));
	}

	@Test
	@Transactional
	public void folder1Delete() {
		f1 = f1Dao.get(f1.getFolderFirstIndex());
		f1Dao.delete(f1);
		em.flush();
		f1 = f1Dao.get(f1.getFolderFirstIndex());
		assertNull(f1);
	}

//	@Test
//	@Transactional
//	public void folder1GetAll() {
//		FolderFirst f3 = new FolderFirst();
//		f3.setFolderFirstName("f1_name");
//		f1Dao.add(f3);
//
//		List<FolderFirst> f1s = f1Dao.getAll();
//		for (FolderFirst ff : f1s)
//			System.out.println(ff.getFolderFirstIndex() + " / "
//			+ " / " + ff.getFolderFirstName());
//
//		em.flush();
//
//	}


	@After
	public void after() {
		em.close();
	}
}
