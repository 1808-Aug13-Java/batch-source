package com.revature.dao;

import java.util.List;

import com.revature.model.Transaction;

public interface TransactionDao {
	public List<Transaction> getTransactionsByUserId(int id);
}
