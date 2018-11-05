package mysns.member;

import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Password {
	private static final Logger LOG = LoggerFactory.getLogger(Password.class);
	BCrypt bc = new BCrypt();
	
	public String generateHashedPassword(String pass) {
		String hashedPassword = bc.hashpw(pass, bc.gensalt(10));
		return hashedPassword;
	}
	
	public Boolean checkPassword(String plainPass, String hashedPass) {
		return bc.checkpw(plainPass, hashedPass);
	}
	
/*	public void passwordInitialization() {
		MemberDAO mDao = new MemberDAO();
		ArrayList<Member> mList = mDao.getNewMembers();
		for (Member m : mList) {
			LOG.debug(m.toString());
			String hashedPwd = generateHashedPassword(m.getPasswd());
			if (!mDao.changePassword(m.getUid(), hashedPwd))
				break;
		}
	}*/
}
