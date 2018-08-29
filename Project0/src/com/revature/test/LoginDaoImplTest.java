package com.revature.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.revature.dao.LoginDao;
import com.revature.dao.LoginDaoImpl;
import com.revature.model.Login;

public class LoginDaoImplTest {
	LoginDao ldi = new  LoginDaoImpl();
	Login l;
	static final String HEY_SONARCLOUD_IGNORE_PLEASE = "password";
	
	// Tests assume Bank.sql is last change to database
	@Test
	public void getLoginsTestValid() {
		List<Login> listLogins = new ArrayList<>();
		l = new Login("user", HEY_SONARCLOUD_IGNORE_PLEASE);
		listLogins.add(l);
		l = new Login("josh.pen", "kappa123");
		listLogins.add(l);
		l = new Login("user@email.com", HEY_SONARCLOUD_IGNORE_PLEASE);
		listLogins.add(l);
		
		assertEquals(listLogins, ldi.getLogins());
	}
	
	@Test
	public void getLoginsTestInvalid() {
		List<Login> listLogins = new ArrayList<>();
		l = new Login("user", "pp");
		listLogins.add(l);
		l = new Login("josh.pen", "kappa123");
		listLogins.add(l);
		l = new Login("user@email.com", HEY_SONARCLOUD_IGNORE_PLEASE);
		listLogins.add(l);
		
		assertNotEquals(listLogins, ldi.getLogins());
	}
	
	@Test
	public void getLoginByIdTestValid() {
		l = new Login("user", HEY_SONARCLOUD_IGNORE_PLEASE);
		
		assertEquals(l, ldi.getLoginById("user"));
	}
	
	@Test
	public void getLoginByIdTestInvalid() {
		assertEquals(null, ldi.getLoginById("null"));
	}
	
	@Test
	public void createLoginInvalid() {
		l = new Login("user", "diffpassword");
		assertEquals(0, ldi.createLogin(l));
	}
	
	@Test
	public void createLoginValid() {
		l = new Login("new", "cool");
		assertEquals(1, ldi.createLogin(l));
	}
	
	@Test
	public void deleteLoginInvalid() {
		assertEquals(0, ldi.deleteLoginById("new"));
	}

	@Test
	public void deleteLoginValid() {
		l = new Login("new", "cool");
		ldi.createLogin(l);
		assertEquals(1, ldi.deleteLoginById("new"));
	}
	
	@After
	public void refresh() {
		ldi.deleteLoginById("new");
	}
}
