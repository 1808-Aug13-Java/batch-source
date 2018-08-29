package com.revature.banktest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoImpl;
import com.revature.main.Driver;
import com.revature.dao.*;

public class project0Tests {

	
	@Test
	public void testGetAccId()  {
		
		AccountDao accDao = new AccountDaoImpl();
		assertEquals(1, accDao.getAccId("hsaing"));
	}
	
	@Test 
	public void increaseAmount() {
		AccountDao accDao = new AccountDaoImpl();
		assertEquals(130, accDao.increaseAmount(9,7));
	}
	
	@Test 
	public void testCreateUser() {
		UserDao usDao = new UserDaoImpl();
		assertEquals(1, usDao.createUser("name", "password"));
	}
	
//	@Test
//	public void testBalanceCheck() {
//		Driver d = new Driver();
//		assertEquals(0, d.balanceCheck());
//	}
	
	
	
	
	
}
