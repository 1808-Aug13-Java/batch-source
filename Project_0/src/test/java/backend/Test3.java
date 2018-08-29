package backend;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import message.Message;

public class Test3
{
	
	static private Logger loggy = Logger.getRootLogger();
	
	@BeforeClass
	public static void createAccount()
	{
		Message m = AccountLayer.createNewAccount("jljacko", "lamer password", "John", "Jacko", "Empire324", true);
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
	
	@AfterClass
	public static void logOut()
	{
		Message m = AccountLayer.logOff();
		assertEquals(0, m.getCode());
		loggy.info(m.getMessage());
	}
}
