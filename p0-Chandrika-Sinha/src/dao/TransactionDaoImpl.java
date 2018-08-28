package dao;

import java.sql.Connection;
import java.util.List;

import model.Transaction;

public class TransactionDaoImpl implements TransactionDao {

	@Override
	public List<Transaction> getTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction getTransactionById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction getTransactionById(Connection con, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteTransactionById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
