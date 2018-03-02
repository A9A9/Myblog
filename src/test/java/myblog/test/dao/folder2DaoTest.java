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
		f1Dao.add(f1);
		em.flush();
		
		f2.setFolderSecondName("f2_name");
		f1.getFolderSeconds().add(f2);
		f2Dao.add(f2);
		em.flush();
		System.out.println("天天天天天天");
		em.clear();
	}

	@Test
	@Transactional
	public void folder2Update() {
		f2 = f2Dao.get(f2.getFolderSecondIndex());
		f2.setFolderSecondName("chf2name");
		f2Dao.modify(f2);
//		assertThat("user1", is(f2.getFolderSecondKey().getFolderFirstKey().getUserId()));
		assertThat("chf2name", is(f2.getFolderSecondName()));
	}

	@Test
	@Transactional
	public void folder2Delete() {
		f2 = f2Dao.get(f2.getFolderSecondIndex());
		f2Dao.delete(f2);
		em.flush(); 
		f2 = f2Dao.get(f2.getFolderSecondIndex());
		em.flush();
		assertNull(f2);
	}
	@Test
	@Transactional
	public void folder1Delete() {
		f1 = f1Dao.get(f1.getFolderFirstIndex());
		f1Dao.delete(f1);
		em.flush();

		f1 = f1Dao.get(f1.getFolderFirstIndex());
		assertNull(f1);
		f2 = f2Dao.get(f2.getFolderSecondIndex());
		assertNull(f2);
	}
	@Test
	@Transactional
	public void folder2GetAll() {
		f1 = f1Dao.get(f1.getFolderFirstIndex());
		FolderSecond f3 = new FolderSecond();
		f3.setFolderSecondName("f2_name2");
		f1.getFolderSeconds().add(f3);
		f2Dao.add(f3);
		em.flush();
		em.clear();
		List<FolderSecond> f2s = f2Dao.getAll(f1.getFolderFirstIndex());
		assertThat(f2s.get(0).getFolderFirstIndex(),is(notNullValue()));
		assertThat(f2s.get(1).getFolderFirstIndex(),is(notNullValue()));
		assertThat(f2s.get(0).getFolderSecondIndex(),is(notNullValue()));
		assertThat(f2s.get(1).getFolderSecondIndex(),is(notNullValue()));
		assertThat(f2s.get(0).getFolderSecondName(),is("f2_name"));
		assertThat(f2s.get(1).getFolderSecondName(),is("f2_name2"));
		em.flush();
	}

	@After
	public void after() {
		em.close();
	}
}
