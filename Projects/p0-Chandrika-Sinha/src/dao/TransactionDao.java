package dao;

import java.sql.Connection;
import java.util.List;

import model.Transaction;

public interface TransactionDao {
	public List<Transaction> getTransactions();
	public Transaction getTransactionById(int id);
	public Transaction getTransactionById(Connection con, int id);
	public int createTransaction(Transaction transaction);
	public int updateTransaction(Transaction transaction);
	public int deleteTransaction(Transaction transaction);
	int deleteTransactionById(int id);
}
