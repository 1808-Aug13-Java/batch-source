package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.rules.ExpectedException;

import com.revature.dao.BankUserDaoImpl;
import com.revature.model.BankUser;
import com.revature.util.ConnectionUtil;

public class BankUserDaoImplTest {
	
	public BankUserDaoImpl evaluationService = new BankUserDaoImpl();
	BankUser bu = new BankUser("TestUser","Password",0.00f);
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	Logger log = Logger.getRootLogger();
	Connection con=null;{
	try {
		con = ConnectionUtil.getConnection();
	} catch (SQLException | IOException e) {
		log.error(e);}
	}
	
	@Test
	public void CreatingANewUser() {
		assertEquals(1, evaluationService.createBankUser(bu, con));
	}
	
	@Test
	public void testSelectBankUserByUsernameAndPassword() {
		assertTrue(bu.equals(evaluationService.getBankUserByLogin(bu.getUsername(), bu.getPassword(), con)));
	}
	
	@Test
	public void testDepositMoneyIntoUserBalance() {
		evaluationService.modifyBalance(bu.getUsername(), bu.getPassword(), 100, con);
		assertEquals(100f, evaluationService.getBankUserByLogin(bu.getUsername(), bu.getPassword(), con).getBalance(), .5f);
	}
}
