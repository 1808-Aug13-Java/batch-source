package com.revature.project0.dao;

import java.util.List;

import com.revature.project0.abstraction.Transaction;

public interface HistoryDaoInterface {

	public List<Transaction> getAllTransactions();
	public List<Transaction> getTransactionsByID(int id);
	public List<Transaction> getTransactionByAccount(int id, int accType);
	public List<Transaction> getTransactionsInternal();
	
}
