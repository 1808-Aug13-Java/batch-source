package com.revature.dao;

import com.revature.model.Account;

public interface BankDao {
	
	public Account login(String username, String password);
	public int createAccount(String username,String password, String startingBalance);
	public void updateAccount(Account a);
	public void updatePassword(String username, String newpassword);
	public void quickView(String username, String password);

}
