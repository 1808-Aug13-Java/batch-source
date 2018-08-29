package backend;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import backend.AccountLayer;

public class BeginTest
{
	String notLoggedOn = "You're not currently logged on!";
	
	@Test
	public void logFailedLogOff()
	{
		assertEquals(notLoggedOn, AccountLayer.logOff());
	}
	
	@Test
	public void logFailedLogOn()
	{
		assertEquals("Error logging on! Either Username or passord are incorrect!", 
				AccountLayer.logOnAccount("trooper", "password"));
	}
	
	@Test
	public void logFailedViewBankAccounts()
	{
		assertEquals(notLoggedOn, AccountLayer.getAlternateBankAccounts());
	}
	
	@Test
	public void logFailedViewAccount()
	{
		assertEquals(notLoggedOn, AccountLayer.logOff());
	}
	
	@Test
	public void logFailedSwitchBankAccount()
	{
		assertEquals(notLoggedOn, AccountLayer.switchBankAccounts("theBanks"));
	}
	
	@Test
	public void logFailedCreateBankAccount()
	{
		assertEquals(notLoggedOn, AccountLayer.createNewBankingAccount("banks", true));
	}
	
	@Test
	public void logFailedShareAccount()
	{
		assertEquals(notLoggedOn, AccountLayer.shareBankingAccount("password", "otherUser"));
	}
	
	@Test
	public void logFailedDeposit()
	{
		assertEquals(notLoggedOn, AccountLayer.depositMoney(23.23f));
	}
	
	@Test
	public void logFailedWithdraw()
	{
		assertEquals(notLoggedOn, AccountLayer.withdrawMoney(23.43f));
	}
}
