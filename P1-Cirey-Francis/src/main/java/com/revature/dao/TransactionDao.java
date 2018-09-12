package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Transaction;

public interface TransactionDao {
	public List<Transaction> getTransactions();
	public List<Transaction> getPending();
	public List<Transaction> getPendingOfEmployee(int id);
	public List<Transaction> getResolvedOfEmployee(int id);
	public List<Transaction> getResolved();
	public List<Transaction> getTransactionsOfEmployee(int id);
	public Transaction getTransactionById(int id);
	public Transaction getTransactionByEmployee(int id);
	public int createTransaction(Transaction transaction);
	public int deleteTransactionById(int id);
	public int updateTransaction(int id, String newString,  String name);
}
