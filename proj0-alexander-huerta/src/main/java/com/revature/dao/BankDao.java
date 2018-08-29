package com.revature.dao;

import com.revature.transactions.*;

public interface BankDao {
	
	//account transactions deposit, withdraw and current balance
	public int withdraw(float sum, AccountTransactions currentAccount);
	public int deposit(float sum, AccountTransactions currentAccount);
	public void currentBalance(AccountTransactions currentAccount);
	
	//user interface
	public void menu();
	public int createAccount(AccountTransactions createAccount);
	public void oldAccounts();
	public void loginMenu(boolean state, AccountTransactions currentAccount);
}
