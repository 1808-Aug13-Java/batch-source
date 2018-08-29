package com.revature.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.dao.BankDaoImpl;
import com.revature.exceptions.FractionalCentException;
import com.revature.exceptions.NegativeNumberException;
import com.revature.model.Account;

public class JUnitTests {
	
	@Test
	public void simpleWithdrawal() throws NegativeNumberException, FractionalCentException {
		
		Account a = new Account("user1","password",100.00);
		assertEquals(50,a.makeWithdrawal("50.00"), 0.001);

	}
	
	@Test
	public void ensureRowCreation() {
		
		BankDaoImpl bdi = new BankDaoImpl();
		
		String username = Integer.toString((int) (Math.random()*10000000));
		String token = "token";
		String startingBalance = "100.00";
		int numRows = bdi.createAccount("user" + username, token, startingBalance);
		assertEquals(1, numRows, 0.001);
	}
	
	@Test(expected = FractionalCentException.class)
	public void fractionalCentWithdrawal() throws NegativeNumberException, FractionalCentException {
		
		Account a = new Account("user1","password",100.00);
		a.makeWithdrawal("0.005");

	}
	
	
	@Test(expected = NegativeNumberException.class)
	public void negativeWithdrawal() throws NegativeNumberException, FractionalCentException {
		
		Account a = new Account("user1","password",100.00);
		a.makeWithdrawal("-0.005");

	}
	@Test
	public void incorrectLogin() {
		BankDaoImpl bdi = new BankDaoImpl();
		Account a = new Account();
		assertEquals(bdi.login("hjjhh", "jkjkjkj"),a);
	}

	@Test
	public void blankLogin() {
		BankDaoImpl bdi = new BankDaoImpl();
		Account a = new Account();
		assertEquals(bdi.login("", ""),a);
	} 
}



















//@Test(expected = FractionalCentException.class)
//public void largeFractionalCentWithdrawal() throws NegativeNumberException, FractionalCentException {
//	
//	Account a = new Account("user1","password",100.00);
//	a.makeWithdrawal("9.973");
//
//}
