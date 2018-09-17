package dao;

import java.sql.Connection;
import java.util.List;

import model.Account;
import model.User;

public interface AccountDao {
	public List<Account> getAccounts();
	public Account getAccountById(int id);
	public Account getAccountById(Connection con, int id);
	public int createAccount(Account account, User u);
	public int updateAccount(Account account);
	public int deleteAccount(Account account);
	int deleteAccountById(int id);
	int getNextAccountId();
}
