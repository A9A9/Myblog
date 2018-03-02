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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myblog.dao.FolderDao;
import com.spring.myblog.domain.FolderFirst;
import com.spring.myblog.domain.FolderFirstKey;
import com.spring.myblog.domain.FolderSecond;
import com.spring.myblog.domain.FolderSecondKey;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class folderDaoTest {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	@Qualifier("folderFirstDao")
	FolderDao<FolderFirst, FolderFirstKey> f1Dao;
	@Autowired
	FolderFirstKey f1k;
	@Autowired
	FolderFirst f1;

	@Before
	public void start() {
		f1k.setFolderFirstIndex("f1index");
		f1k.setUserId("user1");

		// f1.setFolderFirstKey(f1k);
		f1.setFolderFirstName("f1_name");
		f1Dao.add(f1);
		em.flush();
		em.clear();
	}

	@Test
	@Transactional
	public void folder1Update() {

		f1 = f1Dao.get(f1k);
		f1.setFolderFirstName("ch_f1name");
		// f1Dao.modify(f1);
		em.flush();

		f1 = f1Dao.get(f1k);
		assertThat("user1", is(f1.getFolderFirstKey().getUserId()));
		assertThat("f1index", is(f1.getFolderFirstKey().getFolderFirstIndex()));
		assertThat("ch_f1name", is(f1.getFolderFirstName()));
	}

	@Test
	@Transactional
	public void folder1Delete() {
		f1 = f1Dao.get(f1k);
		f1Dao.delete(f1);
		em.flush();

		f1 = f1Dao.get(f1k);
		assertNull(f1);
	}

	@Test
	@Transactional
	public void folder1GetAll() {

		FolderFirstKey f3k = new FolderFirstKey();
		f3k.setFolderFirstIndex("f1index2");
		f3k.setUserId("user2");

		FolderFirst f3 = new FolderFirst();
		f3.setFolderFirstKey(f3k);
		f3.setFolderFirstName("f1_name");
		f1Dao.add(f3);

		List<FolderFirst> f1s = f1Dao.getAll();
		for (FolderFirst ff : f1s)
			System.out.println(ff.getFolderFirstKey().getFolderFirstIndex() + " / " + ff.getFolderFirstKey().getUserId()
					+ " / " + ff.getFolderFirstName());

		em.flush();

	}


	@After
	public void after() {
		em.close();
	}
}
