package com.reavture.p0_jonathan_novoa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.app.dao.AccountDaoImpl;

public class AccountDaoImplTest {
	
	//fix rounding errors
	@Test
	public void emptyUsername(){ 
		AccountDaoImpl run= new AccountDaoImpl();
		int sum = run.usernameValid("");
			assertEquals(sum,1);
	}
	
	@Test
	public void usernameTooLong() {
		AccountDaoImpl run= new AccountDaoImpl();
		int sum = run.usernameValid("asdjklfhlakdsblfbadhsbvflkbadsjfhjplakdsh");
		assertEquals(sum,2);
	}
	@Test(expected=NullPointerException.class)
	public void nullUsername() {
		AccountDaoImpl run= new AccountDaoImpl();
		run.usernameValid(null);
	}
	
	@Test
	public void negativeBalance() {
		AccountDaoImpl run= new AccountDaoImpl();
		int test=run.balanceValid(-23);
		assertEquals(test,1);
	}
	
	@Test
	public void badInput() {
		AccountDaoImpl run= new AccountDaoImpl();
		int test =run.inputValid("23");
		assertEquals(test,1);
	}
	
	@Test
	public void stringInput() {
		AccountDaoImpl run= new AccountDaoImpl();
		int test =run.inputValid("f");
		assertEquals(test,2);
	}
	
	

}
