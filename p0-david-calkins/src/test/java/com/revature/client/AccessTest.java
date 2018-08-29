package com.revature.client;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.client.data.BankUserData;

public class AccessTest {
	
	@BeforeClass
	public static void testCreateUser() throws IOException, SQLException { 
		BankServerBridge bridge = new BankServerBridge();
		
		String testUser = "Test01";
		bridge.addNewUser(new BankUserData(0, testUser, testUser, testUser, new BigDecimal(0)));
		
		assertEquals(0, bridge.getUserByEmail(testUser));
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
