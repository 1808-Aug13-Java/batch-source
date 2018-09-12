package backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import service.*;

public class AccountCreators
{
	@Test
	public void CreateEmployee1()
	{
		int result = AccountManager.createAccount("John", "Jacko", "jljacko", "johnPass", false);
		assertTrue(result> 0);
	}
	
	@Test
	public void CreateEmployee2()
	{
		int result = AccountManager.createAccount("Kylo", "Ren", "kren", "kyloPass", false);
		assertTrue(result> 0);
	}
	
	@Test
	public void CreateEmployee3()
	{
		int result = AccountManager.createAccount("Rey", "Vos", "rvos", "rayPass", false);
		assertTrue(result> 0);
	}
	
	@Test
	public void CreateEmployee4()
	{
		int result = AccountManager.createAccount("Thor", "Odinson", "todinson", "thorPass", false);
		assertTrue(result> 0);
	}
	
	@Test
	public void CreateManager1()
	{
		int result = AccountManager.createAccount("Odin", "Borson", "oborson", "odinPass", true);
		assertTrue(result> 0);
	}
	
	@Test
	public void CreateManager2()
	{
		int result = AccountManager.createAccount("Lord", "Trooper", "ltrooper", "lordPass", true);
		assertTrue(result> 0);
	}

}
