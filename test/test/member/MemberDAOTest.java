package test.member;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mysns.member.MemberDAO;

public class MemberDAOTest {
	private static final Logger LOG = LoggerFactory.getLogger(MemberDAOTest.class);
/*	MemberDAO memDao;
	@Before
	void beforeTest() {
		memDao = new MemberDAO();
		LOG.debug("beforeTest()");
	}*/
	
	@Test
	public void loginTest() {
		MemberDAO memDao = new MemberDAO();
		String uid = "tkkim";
		String passwd = "qwerty";
		assertTrue(memDao.login(uid, passwd));
	}

/*	@After
	void afterTest() {
		LOG.debug("afterTest()");
	}*/
}
