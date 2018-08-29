package backend;

import static org.junit.Assert.assertEquals;



import org.apache.log4j.Logger;
import org.junit.AfterClass;
//import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import backend.AccountLayer;
import message.Message;


public class Test1
{
	static String userName = "trooper";
	//static String createSuccess = "Account Created! You are now logged on as <" + userName + ">";
	static String depositSuccess1 = "Funds $23.05 Deposited! Balance is now $23.05!";
	
	static private Logger loggy = Logger.getRootLogger();
	
	@BeforeClass
	public static void createAccount()
	{
		String createSuccess = "Account Created! You are now logged on as <" + userName + ">";

		Message m = AccountLayer.createNewAccount(userName, "lame password", "Lord", "Trooper", "Marow Money", true);
		assertEquals(0,m.getCode());
		
		loggy.info("Test 1 (result): " + m.getMessage());
		loggy.info("Test 1 (target): " + createSuccess +"\n");


	}
	
	@Test
	public void depositMoney1()
	{
		Message m = AccountLayer.depositMoney(23.05f);
		assertEquals(0,m.getCode());
		
		loggy.info("Test 2 (result): " + m.getMessage());
		loggy.info("Test 2 (target): " + depositSuccess1 +"\n");
	}
	
	@Test
	public void createNewBankAccount()
	{
		Message m = AccountLayer.createNewBankingAccount("secondBank", false);
		assertEquals(0, m.getCode());
		loggy.info("Test 3: " + m.getMessage());
	}
	
	@Test
	public void viewAccount()
	{
		Message m = AccountLayer.getAccountInfo();
		assertEquals(0, m.getCode());
		loggy.info("Test 4: " + m.getMessage());
	}
	
	@AfterClass
	public static void logOut()
	{
		Message m = AccountLayer.logOff();
		assertEquals(0, m.getCode());
		loggy.info("Test 5: " + m.getMessage());
	}
	
}
