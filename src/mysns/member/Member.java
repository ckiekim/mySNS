package mysns.member;

import java.util.Date;

/**
 * File : Member.java
 * Desc : SNS ȸ�� Data Object Ŭ����
 * @author Ȳ����(dinfree@dinfree.com)
 *
 */
public class Member {	
	private String uid;
	private String name;
	private String passwd;
	private String email;
	private Date date;

	public String getUid() {
		return uid;
	}
	public void setUid(String id) {
		this.uid = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Member [uid=" + uid + ", name=" + name + ", passwd=" + passwd + ", email=" + email + ", date=" + date
				+ "]";
	}
}
