package com.revature.dao;

import com.revature.model.Account;

public interface BankDao {
	
	public Account login(String username, String password);
	public void createAccount(String username, String password, double startingBalance);
	//public void logout();
	//public void viewBalance();
	public void updateAccount(Account a);

}
