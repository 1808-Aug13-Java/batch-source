package com.revature;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.model.User;

public class BankTest {
	
	@Test
	public void successfulWithdraw() {
		UserDao udi = new UserDaoImpl();
		User user = udi.getUserById("karla");
		//user.(100.00F);
	}
	
	@Test
	public void successfulUpdateBalance() {
		UserDao udi = new UserDaoImpl();
		User user = udi.getUserById("karla");
		user.setBalance(100.00F);
		float result = user.getBalance();
		//assert(result, 100.00F);
	}
	
	/** Test deleting that user
	 * 
	 */
	@Test
	public void successfulDeleteReturnsOneOrMore() {
		UserDao udi = new UserDaoImpl();
		int result = udi.deleteUserById("blanki");
		assertEquals(result, 0);
	}
	
	/** Test deleting the same user again
	 * 
	 */
	@Test
	public void successfulDelete2ReturnsOneOrMore() {
		UserDao udi = new UserDaoImpl();
		int result = udi.deleteUserById("Zuko");
		assertEquals(result, 0);
	}

}
