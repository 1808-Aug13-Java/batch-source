package backend;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import message.Message;

public class Test4 
{
	
	static private Logger loggy = Logger.getRootLogger();
	
	
	@BeforeClass
	public static void logOn()
	{
		Message m = AccountLayer.logOnAccount("trooper", "lame password");
		assertEquals(0, m.getCode());
		loggy.info(m.getMessage()+"\n");
		m = AccountLayer.switchBankAccounts("Marow Money");
		assertEquals(0,m.getCode());
		loggy.info(m.getMessage() + "\n");
	}
	
	@Test
	public void donateAccount()
	{
		Message m = AccountLayer.shareBankingAccount("lame password", "jljacko");
		assertEquals(0, m.getCode());
		loggy.info(m.getMessage() + "\n");
	}
	
	@Test
	public void donateToWrongPersonAccount()
	{
		Message m = AccountLayer.shareBankingAccount("lame password", "palps");
		assertEquals(6, m.getCode());
		loggy.info(m.getMessage() + "\n");
	}
	
	@Test
	public void donateToRightPersonWithWrongPassword()
	{
		Message m = AccountLayer.shareBankingAccount("wrong password", "jljacko");
		assertEquals(7, m.getCode());
		loggy.info(m.getMessage() + "\n");
	}
	
	@Test
	public void withdrawMoney()
	{
		Message m = AccountLayer.withdrawMoney(6.09f);
		assertEquals(0, m.getCode());
		loggy.info(m.getMessage() + "\n");
		
	}
	
	@AfterClass
	public static void logOut()
	{
		Message m = AccountLayer.logOff();
		assertEquals(0, m.getCode());
		loggy.info( m.getMessage());
	}

}
