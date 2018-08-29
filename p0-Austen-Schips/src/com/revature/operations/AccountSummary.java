package com.revature.operations;

import com.revature.accounts.Account;

public class AccountSummary implements AccountOperations{

	public void performOperation(Account account) {
		// TODO Auto-generated method stub
		 System.out.println("Account current balance: " + account.getBalance());
		
	}
	
}
