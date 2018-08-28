package com.revature.app.dao;


import java.util.Map;

import com.revature.app.model.Account;



public interface AccountDao {
	//account manipulation
	public void withdraw(float amount, Account currAccount);
	public void deposit(float amount, Account currAcount);
	public void viewBalance(Account currAccount);
	
	//user interface methods

	public void startUp();
	public void screen();
	public void loggedInScreen(boolean status, Account workingAccount);//loggedScreen
	public void saveData(Map<String, Account> accountsList);//Account.logOut
	public Map<String,Account> getAccounts();
}
