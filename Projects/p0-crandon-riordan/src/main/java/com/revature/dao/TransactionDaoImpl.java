package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.engine.Engine;
import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class TransactionDaoImpl implements TransactionDao {
	static final Logger logger = Logger.getLogger(Engine.class);
	
	public List<Transaction> getAllTransactions() {
		List<Transaction> transactions = new ArrayList<>();
		ResultSet rs = null;
		String sql = "SELECT * FROM TRANSACTIONS";
		
		try(Connection con = ConnectionUtil.getConnection();
				Statement s = con.prepareStatement(sql);
				) {
			rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("user_id");
				int transactionId = rs.getInt("transaction_id");
				float amount = rs.getFloat("amount");
				String transactionType = rs.getString("transaction_type");
				Date transactionDate = rs.getDate("transaction_date");
				int fromUserId = rs.getInt("FROM_USER_ID");
				transactions.add(new Transaction(id, transactionId, transactionType, 
						amount, transactionDate, fromUserId));
			}
		} catch (SQLException | IOException e) {
			logger.info(e.getMessage());
		} finally {
			if( rs!= null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		}
		
		return transactions;
	}
	
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
				int fromUserId = rs.getInt("FROM_USER_ID");
				transactions.add(new Transaction(id, transactionId, transactionType, 
						amount, transactionDate, fromUserId));
			}
		} catch (SQLException | IOException e) {
			logger.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		}
		
		return transactions;
	}

	public void logHistory(List<Transaction> transactions) {
		for(Transaction transaction : transactions) {
			UserDaoImpl udi = new UserDaoImpl();
			User fromUser = udi.getUserById(transaction.getfromUserId());
			String username = "";
			if(fromUser != null) {
				username = fromUser.getUsername();
			}
			
			logger.info("");
			logger.info(transaction.gettransactionDate() + " : "
					 + transaction.gettransactionType() + " : "
					+ transaction.getAmount() + " : " + username);
		}
	}
	

}
