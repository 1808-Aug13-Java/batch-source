package backend;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import message.Message;

public class Test2 
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
	public void setDeposit()
	{
		Message m = AccountLayer.depositMoney(46.09f);
		assertEquals(0, m.getCode());
		loggy.info(m.getMessage() + "\n");
	}
	
	@Test
	public void viewBankAccounts()
	{
		Message m = AccountLayer.getAlternateBankAccounts();
		assertEquals(0, m.getCode());
		loggy.info(m.getMessage() + "\n");
	}
	
	@Test
	public void withdrawTooMuch()
	{
		Message m = AccountLayer.withdrawMoney(5000.09f);
		assertEquals(8, m.getCode());
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
