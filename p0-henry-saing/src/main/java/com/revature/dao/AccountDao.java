package com.revature.dao;

import java.sql.Connection;

import com.revature.model.Account;

public interface AccountDao {
	public int createAccount(double balance);
	public double getAmount(int id);
	public int getAccId(String username);
	public void increaseAmount(int accId, double increaseAmount);
	public void decreaseAmount(int accId, double decreaseAmount);
}
