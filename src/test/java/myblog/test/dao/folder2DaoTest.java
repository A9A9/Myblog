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
import com.spring.myblog.domain.FolderSecond;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class folder2DaoTest {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	@Qualifier("folderFirstDao")
	FolderDao<FolderFirst> f1Dao;

	@Autowired
	@Qualifier("folderSecondDao")
	FolderDao<FolderSecond> f2Dao;

	
	FolderFirst f1 = new FolderFirst();
	FolderSecond f2 = new FolderSecond();
	@Before
	public void start() {
		System.out.println("Start");

		f1.setFolderFirstName("f1_name");

		f2.setFolderSecondName("f2_name");
		
		f1.getFolderSeconds().add(f2);
		f1Dao.add(f1);
		f2Dao.add(f2);
		em.flush();
		System.out.println("天天天天天天");
	}

//	@Test
//	@Transactional
//	public void folder2Update() {
//		f2 = f2Dao.get(f2.getFolderSecondIndex());
//		f2.setFolderSecondName("chf2name");
//		f2Dao.modify(f2);
////		assertThat("user1", is(f2.getFolderSecondKey().getFolderFirstKey().getUserId()));
//		assertThat("chf2name", is(f2.getFolderSecondName()));
//	}
//
	@Test
	@Transactional
	//@Rollback(false)
	public void folder2Delete() {
		System.out.println("------");
		System.out.println(f2.getFolderSecondName());
		FolderSecond f3 = f2Dao.get(f2.getFolderSecondIndex());
		em.flush();
		
		//em.remove(f2Dao.get(f2.getFolderSecondIndex()));
		f2Dao.delete(f3);
		em.flush();
		FolderSecond tmpf2 = f2Dao.get(f2.getFolderSecondIndex());
		em.flush();
		System.out.println(tmpf2.getFolderSecondName());
		assertNull(tmpf2);
	}
//	@Test
//	@Transactional
//	public void folder1Delete() {
//		f1 = f1Dao.get(f1.getFolderFirstIndex());
//		f1Dao.delete(f1);
//		em.flush();
//
//		f1 = f1Dao.get(f1.getFolderFirstIndex());
//		assertNull(f1);
//		f2 = f2Dao.get(f2.getFolderSecondIndex());
//		assertNull(f2);
//	}
//	@Test
//	@Transactional
//	public void folder2GetAll() {
//
//		FolderSecond f3 = new FolderSecond();
//		f3.setFolderSecondName("f2name2");
//		f1.getFolderSeconds().add(f3);
//		f2Dao.add(f3);
//		List<FolderSecond> f2s = f2Dao.getAll(f1.getFolderFirstIndex());
//		System.out.println(f2.getFolderFirstIndex());
//		System.out.println(f2s.size());
//		for (FolderSecond ff : f2s)
//			System.out.println(ff.getFolderFirstIndex() + " / " + ff.getFolderSecondIndex() + " / " + ff.getFolderSecondName());
//		em.flush();
//	}

	@After
	public void after() {
		em.close();
	}
}
