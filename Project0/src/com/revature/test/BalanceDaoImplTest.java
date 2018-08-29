package com.revature.test;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.revature.dao.BalanceDao;
import com.revature.dao.BalanceDaoImpl;
import com.revature.dao.LoginDao;
import com.revature.dao.LoginDaoImpl;
import com.revature.model.Balance;
import com.revature.model.Login;

public class BalanceDaoImplTest {
	BalanceDao bdi = new BalanceDaoImpl();
	Balance b;
	LoginDao ldi = new LoginDaoImpl();
	Login l;
	static final String NEWUSER = "newuser";
	
	// Tests assume Bank.sql is last change to database
	@Test
	public void getBalancesTestValid() {
		List<Balance> listBalances = new ArrayList<>();
		b = new Balance("user", new BigDecimal("200.03"));
		listBalances.add(b);
		b = new Balance("josh.pen", new BigDecimal("15.3"));
		listBalances.add(b);
		b = new Balance("user@email.com", new BigDecimal("6000.15"));
		listBalances.add(b);
		
		assertEquals(listBalances, bdi.getBalances());
	}
	
	public void getBalancesTestInvalid() {
		List<Balance> listBalances = new ArrayList<>();
		b = new Balance("user", new BigDecimal("2.03"));
		listBalances.add(b);
		b = new Balance("josh.pen", new BigDecimal("15.3"));
		listBalances.add(b);
		b = new Balance("user@email.com", new BigDecimal("6000.15"));
		listBalances.add(b);
		
		assertNotEquals(listBalances, bdi.getBalances());
	}
	
	@Test
	public void getBalanceByIdTestValid() {
		b = new Balance("user", new BigDecimal("200.03"));
		
		assertEquals(b, bdi.getBalanceById("user"));
	}
	
	@Test
	public void getBalanceIdNotValid() {
		assertEquals(null, bdi.getBalanceById("null"));
	}
	
	@Test
	public void createBalanceInvalid() {
		b = new Balance(NEWUSER, new BigDecimal("0"));
		assertEquals(0, bdi.createBalance(b));
	}
	
	@Test
	public void createBalanceValid() {
		l = new Login (NEWUSER, "pswrd");
		ldi.createLogin(l);
		b = new Balance(NEWUSER, new BigDecimal("0"));
		assertEquals(1, bdi.createBalance(b));
	}
	
	@Test
	public void deleteBalanceInvalid() {
		bdi.deleteBalanceById(NEWUSER);
		assertEquals(0, bdi.deleteBalanceById(NEWUSER));
	}
	
	@Test
	public void deleteBalanceValid() {
		l = new Login (NEWUSER, "pswrd");
		ldi.createLogin(l);
		b = new Balance(NEWUSER, new BigDecimal("0"));
		assertEquals(0, bdi.deleteBalanceById(NEWUSER));
	}
	
	@After
	public void refresh() {
		ldi.deleteLoginById(NEWUSER);
		bdi.deleteBalanceById(NEWUSER);
	}

}
