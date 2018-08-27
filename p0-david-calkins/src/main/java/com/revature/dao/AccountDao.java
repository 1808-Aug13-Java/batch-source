package com.revature.dao;

import java.util.List;

/**
 * A Data Access Object interface for use in accessing bank accounts. 
 */
public interface AccountDao {
	
	public Account getAccountById(long id);
	
	public List<Account> getAccounts();
	
	public int createAccount(Account account);
	public int updateAccount(Account account);
	public int deleteAccount(long id);
	public int deleteAccount(Account account);
}
