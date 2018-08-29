package dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Transaction;
import model.User;
import util.ConnectionUtil;

public class TransactionDaoImpl implements TransactionDao {

	@Override
	public List<Transaction> getTransactions() {
		List<Transaction> transactionList = new ArrayList<>();
		String sql = "SELECT * FROM BANKTRANSACTION";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql); ) {
			while (rs.next()) {
				Transaction t = new Transaction();
				int id = rs.getInt("TRANSACTION_ID");
				Timestamp time = rs.getTimestamp("TRANSACTION_TIME");
				int accountId = rs.getInt("BANKACCOUNT_ID");
				String username = rs.getString("BANK_USERNAME");
				String type = rs.getString("TRANSACTION_TYPE");
				BigDecimal beforeBalance = rs.getBigDecimal("BEFORE_TRANSACTION_BALANCE");
				BigDecimal afterBalance = rs.getBigDecimal("AFTER_TRANSACTION_BALANCE");
				
				AccountDaoImpl ad = new AccountDaoImpl();
				Account account = ad.getAccountById(accountId);
				
				UserDaoImpl ud = new UserDaoImpl();
				User user = ud.getUserById(username);
				
				t.setId(id);
				t.setTime(time);
				t.setAccount(account);
				t.setUser(user);
				t.setType(type);
				t.setBalanceBefore(beforeBalance);
				t.setBalanceAfter(afterBalance);
				
				transactionList.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return transactionList;
	}

	@Override
	public Transaction getTransactionById(int id) {
		Transaction t = null;
		String sql = "SELECT * FROM BANKTRANSACTION WHERE TRANSACTION_ID = ?";
		
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int transactionId = rs.getInt("TRANSACTION_ID");
				Timestamp time = rs.getTimestamp("TRANSACTION_TIME");
				AccountDaoImpl ad = new AccountDaoImpl();
				Account account = ad.getAccountById(rs.getInt("BANKACCOUNT_ID"));
				
				UserDaoImpl ud = new UserDaoImpl();
				User user = ud.getUserById(rs.getString("BANK_USERNAME"));
				
				String type = rs.getString("TRANSACTION_TYPE");
				BigDecimal balanceBefore = rs.getBigDecimal("BEFORE_TRANSACTION_BALANCE");
				BigDecimal balanceAfter = rs.getBigDecimal("AFTER_TRANSACTION_BALANCE");
				
				t = new Transaction(transactionId, time, account, user, type, balanceBefore, balanceAfter);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return t;
	}

	@Override
	public Transaction getTransactionById(Connection con, int id) {
		Transaction t = null;
		String sql = "SELECT * FROM BANKTRANSACTION WHERE TRANSACTION_ID = ?";
		
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int transactionId = rs.getInt("TRANSACTION_ID");
				Timestamp time = rs.getTimestamp("TRANSACTION_TIME");
				AccountDaoImpl ad = new AccountDaoImpl();
				Account account = ad.getAccountById(rs.getInt("BANKACCOUNT_ID"));
				
				UserDaoImpl ud = new UserDaoImpl();
				User user = ud.getUserById(rs.getString("BANK_USERNAME"));
				
				String type = rs.getString("TRANSACTION_TYPE");
				BigDecimal balanceBefore = rs.getBigDecimal("BEFORE_TRANSACTION_BALANCE");
				BigDecimal balanceAfter = rs.getBigDecimal("AFTER_TRANSACTION_BALANCE");
				
				t = new Transaction(transactionId, time, account, user, type, balanceBefore, balanceAfter);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return t;
	}

	@Override
	public int createTransaction(Transaction transaction) {
		int transactionsCreated = 0;
		String sql = "INSERT INTO BANKTRANSACTION (TRANSACTION_ID, TRANSACTION_TIME, BANKACCOUNT_ID, BANK_USERNAME, TRANSACTION_TYPE, BEFORE_TRANSACTION_BALANCE, AFTER_TRANSACTION_BALANCE) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, transaction.getId());
			ps.setTimestamp(2, transaction.getTime());
			ps.setInt(3, transaction.getAccount().getId());
			ps.setString(4, transaction.getUser().getUsername());
			ps.setString(5, transaction.getType());
			ps.setBigDecimal(6, transaction.getBalanceBefore());
			ps.setBigDecimal(7, transaction.getBalanceAfter());
			transactionsCreated = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactionsCreated;
	}

	@Override
	public int updateTransaction(Transaction transaction) {
		int transactionsUpdated = 0;
		String sql = "UPDATE BANKTRANSACTION "
				+ "SET TRANSACTION_ID = ?, "
				+ "TRANSACTION_TIME = ?, "
				+ "BANKACCOUNT_ID = ?, "
				+ "BANK_USERNAME = ?, "
				+ "TRANSACTION_TYPE = ?, "
				+ "BEFORE_TRANSACTION_BALANCE = ?, "
				+ "AFTER_TRANSACTION_BALANCE = ? ";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			con.setAutoCommit(false);
			ps.setInt(1, transaction.getId());
			ps.setTimestamp(2, transaction.getTime());
			ps.setInt(3, transaction.getAccount().getId());
			ps.setString(4, transaction.getUser().getUsername());
			ps.setString(5, transaction.getType());
			ps.setBigDecimal(6, transaction.getBalanceBefore());
			ps.setBigDecimal(7, transaction.getBalanceAfter());

			transactionsUpdated = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactionsUpdated;
	}

	@Override
	public int deleteTransaction(Transaction transaction) {
		int rowsDeleted = 0;
		String sql = "DELETE FROM BANKTRANSACTION WHERE TRANSACTION_ID = ?";
		try (Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql); ) {
			ps.setInt(1, transaction.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsDeleted;
	}

	@Override
	public int deleteTransactionById(int id) {
		int rowsDeleted = 0;
		String sql = "DELETE FROM BANKTRANSACTION WHERE TRANSACTION_ID = ?";
		try (Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql); ) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsDeleted;
	}
}
