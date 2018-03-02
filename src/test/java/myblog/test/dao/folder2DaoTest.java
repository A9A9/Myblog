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
import com.spring.myblog.domain.FolderFirstKey;
import com.spring.myblog.domain.FolderSecond;
import com.spring.myblog.domain.FolderSecondKey;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class folder2DaoTest {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	@Qualifier("folderFirstDao")
	FolderDao<FolderFirst, FolderFirstKey> f1Dao;
	@Autowired
	FolderFirstKey f1k;
	@Autowired
	FolderFirst f1;

	@Autowired
	FolderSecondKey f2k;
	@Autowired
	FolderSecond f2;
	@Autowired
	@Qualifier("folderSecondDao")
	FolderDao<FolderSecond, FolderSecondKey> f2Dao;

	@Before
	public void start() {
		f1k.setFolderFirstIndex("f1index");
		f1k.setUserId("user1");

		// f1.setFolderFirstKey(f1k);
		f1.setFolderFirstName("f1_name");

		// f2k.setFolderfirstkey(f1k);
		f2k.setFolderSecondIndex("f2index");

		// f2.setFolderSecondKey(f2k);
		f2.setFolderSecondName("f2_name");
		f1Dao.add(f1);
		f2Dao.add(f2);
		em.flush();
	}

	@Test
	@Transactional
	public void folder2Update() {

		f2 = f2Dao.get(f2k);
		f2.setFolderSecondName("chf2name");
		f2Dao.modify(f2);
		assertThat("user1", is(f2.getFolderSecondKey().getFolderFirstKey().getUserId()));
		assertThat("f1index", is(f2.getFolderSecondKey().getFolderFirstKey().getFolderFirstIndex()));
		assertThat("f2index", is(f2.getFolderSecondKey().getFolderSecondIndex()));
		assertThat("chf2name", is(f2.getFolderSecondName()));
	}

	@Test
	@Transactional
	public void folder2Delete() {
		f2 = f2Dao.get(f2k);
		f2Dao.delete(f2);
		em.flush();
		f2 = f2Dao.get(f2k);
		assertNull(f2);
	}
	@Test
	@Transactional
	public void folder1Delete() {
		f1 = f1Dao.get(f1k);
		f1Dao.delete(f1);
		//em.flush();

		f1 = f1Dao.get(f1k);
		assertNull(f1);
		f2 = f2Dao.get(f2k);
		assertNull(f2);
	}
	@Test
	@Transactional
	public void folder2GetAll() {

		FolderSecondKey f3k = new FolderSecondKey();
		f3k.setFolderFirstKey(f1k);
		f3k.setFolderSecondIndex("f2index2");

		FolderSecond f3 = new FolderSecond();
		f3.setFolderSecondKey(f3k);
		f3.setFolderSecondName("f2name2");
		f3.setFolderFirst(f1);
		f2Dao.add(f3);

		List<FolderSecond> f2s = f2Dao.getAll();
		for (FolderSecond ff : f2s)
			System.out.println(ff.getFolderSecondKey().getFolderFirstKey().getUserId() + " / "
					+ ff.getFolderSecondKey().getFolderFirstKey().getFolderFirstIndex() + " / "
					+ ff.getFolderSecondKey().getFolderSecondIndex() + " / " + ff.getFolderSecondName());

		em.flush();
	}

	@After
	public void after() {
		em.close();
	}
}
