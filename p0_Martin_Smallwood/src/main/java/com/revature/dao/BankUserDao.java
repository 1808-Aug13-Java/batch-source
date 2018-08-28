package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.model.BankUser;

public interface BankUserDao {
	public List<BankUser> getBankUsers();
	public BankUser getBankUserByLogin(String username, String password, Connection con);
	public int createBankUser(BankUser newUser, Connection con);
	public int updateBankUser(BankUser user, Connection con);
	public int deleteBankUserByLogin(String username, String password, Connection con);
	public void modifyBalance(String username, String password, float changeAmount, Connection con);
}
