package com.revature.app.dao;


import com.revature.app.model.Account;



public interface AccountDao {
	//account manipulation
	public int withdraw(float amount, Account currAccount);//prepared statement
	public int deposit(float amount, Account currAcount);//prepared statement
	public void viewBalance(Account currAccount);
	
	//user input validation
	public int usernameValid(String user);
	public int balanceValid(float bal);
	public int inputValid(String in);
	public int nonFloatBalance(String in); 
	
	//user interface methods
	public void screen();
	public void loggedInScreen(boolean status, Account workingAccount);
	public void getAccounts();//reg statement
	public int createAccount(Account newAccount);//callable statement
}
