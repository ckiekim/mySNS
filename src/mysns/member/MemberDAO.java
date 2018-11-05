package mysns.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mysns.util.*;
/**
 * File : MemberDAO.java
 * Desc : SNS 회원 등록 및 로그인 처리 클래스
 * @author 황희정(dinfree@dinfree.com)
 *
 */
public class MemberDAO {
	private static final Logger LOG = LoggerFactory.getLogger(MemberDAO.class);
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	/**
	 * 신규 회원 등록
	 * @param member
	 * @return
	 */
	public boolean addMember(Member member) {
		LOG.trace("addMember()");
		LOG.debug(member.toString());
		BCrypt bc = new BCrypt();
		conn = DBManager.getConnection();
		String sql = "insert into s_member(uid, name, passwd, email, date) values(?,?,?,?,now())";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUid());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, bc.hashpw(member.getPasswd(), bc.gensalt(10)));
			pstmt.setString(4, member.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.info("Error Code : {}",e.getErrorCode());
			return false;
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	 * 회원 로그인
	 * @param uid
	 * @param passwd
	 * @return
	 */
	public boolean login(String uid, String passwd) {
		conn = DBManager.getConnection();
		String sql = "select passwd from s_member where uid = ?";
		boolean result = false;
		BCrypt bc = new BCrypt();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			rs = pstmt.executeQuery();
			rs.next();
			String hashedPwd = rs.getString(1);
			if(bc.checkpw(passwd, hashedPwd))
				result=true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 메인화면 우측 신규회원 목록
	 * @return
	 */
	public ArrayList<Member> getNewMembers() {
		ArrayList<Member> memberList = new ArrayList<Member>();
		conn = DBManager.getConnection();
		// 회원 목록은 7개 까지만 가져옴
		String sql = "select * from s_member order by date desc limit 0,7";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member m = new Member();
				m.setUid(rs.getString(1));
				m.setName(rs.getString(2));
				m.setPasswd(rs.getString(3));
				m.setEmail(rs.getString(4));
				m.setDate(rs.getDate(5));
				memberList.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.info("getNewMembers(): Error Code : {}", e.getErrorCode());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberList;
	}
	
	public boolean changePassword(String uid, String password) {
		conn = DBManager.getConnection();
		LOG.debug("changePassword(): uid=" + uid + ", password=" + password);
		String sql = "update s_member set passwd=? where uid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, uid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.info("changePassword(): Error Code : {}", e.getErrorCode());
			return false;
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public Member getOneMember(String uid) {
		Member member = new Member();
		conn = DBManager.getConnection();
		String sql = "select * from s_member where uid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				member.setUid(rs.getString(1));
				member.setName(rs.getString(2));
				member.setPasswd(rs.getString(3));
				member.setEmail(rs.getString(4));
				member.setDate(rs.getDate(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.info("getOneMember(): Error Code : {}", e.getErrorCode());
			return null;
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}
}