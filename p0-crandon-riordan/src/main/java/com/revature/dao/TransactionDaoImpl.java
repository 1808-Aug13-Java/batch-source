package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.engine.Engine;
import com.revature.model.Transaction;
import com.revature.util.ConnectionUtil;

public class TransactionDaoImpl implements TransactionDao {
	static final Logger logger = Logger.getLogger(Engine.class);
	public List<Transaction> getTransactionsByUserId(int id) {
		List<Transaction> transactions = new ArrayList<>();
		ResultSet rs = null;
		String sql = "SELECT * FROM TRANSACTIONS WHERE USER_ID = ?";
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				int transactionId = rs.getInt("transaction_id");
				float amount = rs.getFloat("amount");
				String transactionType = rs.getString("transaction_type");
				Date transactionDate = rs.getDate("transaction_date");
				
				transactions.add(new Transaction(id, transactionId, transactionType, amount, transactionDate));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return transactions;
	}

	public void logHistory(List<Transaction> transactions) {
		for(Transaction transaction : transactions) {
			logger.info("");
			logger.info(transaction.gettransactionDate() + " : "
					 + transaction.gettransactionType() + " : "
					+ transaction.getAmount());
		}
	}
	

}
