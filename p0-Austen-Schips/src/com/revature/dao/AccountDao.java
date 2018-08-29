package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.model.Accounts;

public interface AccountDao {
	public Accounts getAccountsById(String userName);
	//public Accounts getAccountsByUserPassword(String userPassword, Connection con);
	public int createAccounts(Accounts account);
	public void showBalance(String table, String accno);
	public void increaseBalance(int accountId, float increaseAmount);
	public void decreaseBalance(int accountId, float decreaseAmount);
}
