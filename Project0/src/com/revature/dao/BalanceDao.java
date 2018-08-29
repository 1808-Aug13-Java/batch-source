package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.model.Balance;

public interface BalanceDao {
	public List<Balance> getBalances();
	public Balance getBalanceById(String id);
	public Balance getBalanceById(String id, Connection con);
	public int createBalance(Balance balance);
	public void updateBalance(Balance balance);
	public int deleteBalanceById(String id);
}
