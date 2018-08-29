package com.revature.bank;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BankTest {
	
	@Test
	public void invalidmoneyDecimal (){
		//public boolean validMoneyFormat(String s)
		Bank bank = new Bank();
		boolean test = bank.validMoneyFormat("4523.234");
		assertEquals(false, test);
		
	}
	@Test
	public void invalidMoneyNegative (){
		Bank bank = new Bank();
		boolean test = bank.validMoneyFormat("-4523.23");
		assertEquals(false, test);
	}
	
	@Test
	public void wrongMainMenuInput() {
		boolean test = Driver.checkInput("Be");
		assertEquals(false, test);
	}
	
//	@Test
//	public void withdrawMoreThanBalance() {
//		//withdraw()
//		//this part isn't part of only 1 method :( if the withdraw amount is more, then
//		//itll be in while loop and scan until it's valid number. How do i test then?
//		//ACTUALLY :D the dao.updateUser() returns an int lol...so u can test if its above 0
//		
//	}
	
	//test if it is found with both user or email
}
