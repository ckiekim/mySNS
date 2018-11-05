package test.member;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import mysns.member.Password;

public class PasswordTest {

	@Test
	public void test() {
		Password pw = new Password();
		String plainPwd = "qwerty";
		String hashedPwd = pw.generateHashedPassword(plainPwd);
		System.out.println("Length of password: " + hashedPwd.length());
		assertTrue(pw.checkPassword(plainPwd, hashedPwd));
	}

}
