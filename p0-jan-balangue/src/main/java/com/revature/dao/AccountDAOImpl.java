package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.bank.Account;
import com.revature.util.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {

	private static Logger log = Logger.getRootLogger();
	
	public List<Account> getAccounts() {
		
		List<Account> accountList = new ArrayList<>();
		
		String sql = "SELECT * FROM ACCOUNT";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			while(rs.next()) {
				Account a = null;
				String accountType = rs.getString("ACCOUNTTYPE");
				double balance = rs.getDouble("BALANCE");
				int customerId = rs.getInt("CUSTOMERID");
				a = new Account(accountType, balance, customerId);
				accountList.add(a);
			}
		} catch (IOException|SQLException e) {
			log.error(e);
		}
		return accountList;
	}

	@Override
	public Account getAccountById(int accountId) {
		Account a = null;
		String sql = "SELECT * FROM ACCOUNT WHERE ACCOUNTID = ?";
		
		Connection con;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try { 
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, accountId);
			 
			rs = ps.executeQuery();
			
			if (rs.next()) {
				a = new Account();
				a.setAccountType(rs.getString("ACCOUNTTYPE"));
				a.setBalance(rs.getDouble("BALANCE"));
			}
		} catch (IOException | SQLException e) {
			log.error(e);
		} finally {
			try {if (rs!=null) rs.close();} catch(SQLException e) {}
			try {if (ps!=null) ps.close();} catch(SQLException e) {}
		}
		
		return a;
	}

	@Override
	public Account getAccountByCustomerId(int customerId) {
		Account a = null;
		String sql = "SELECT * FROM ACCOUNT WHERE CUSTOMERID = ?";
		
		Connection con;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try { 
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, customerId);
			 
			rs = ps.executeQuery();
			
			if (rs.next()) {
				a = new Account();
				a.setAccountType(rs.getString("ACCOUNTTYPE"));
				a.setBalance(rs.getDouble("BALANCE"));
			}
		} catch (IOException | SQLException e) {
			log.error(e);
		} finally {
			try {if (rs!=null) rs.close();} catch(SQLException e) {}
			try {if (ps!=null) ps.close();} catch(SQLException e) {}
		}
		
		return a;
	}
	@Override
	public int createAccount(Account account) {
		String sql = "INSERT INTO ACCOUNT (ACCOUNTTYPE, BALANCE, CUSTOMERID) values (?, ?, ?)";
		
		Connection con = null;
		
		PreparedStatement ps = null;
		
		int accountsUpdated = 0;
		
		try {
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, account.getAccountType());
			ps.setDouble(2, account.getBalance(account.getAccountId()));
			ps.setInt(3, account.getCustomerId());
			
			// Execute the statement, and give the rows affected. 
			accountsUpdated = ps.executeUpdate();
		} catch (IOException | SQLException e) {
			log.error(e);
		} finally {
			try {if (ps != null) ps.close();} catch(SQLException e) {}
		}
		return accountsUpdated;
	}

	@Override
	public int updateAccount(Account account) {
		return 0;
	}

	@Override
	public int deleteAccountById(int id) {
		int rowsDeleted = 0;
		
		String sql = "DELETE FROM ACCOUNT WHERE ACCOUNTID = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			rowsDeleted = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			log.error(e);
		} finally {
			try {if (ps != null) ps.close();} catch(SQLException e) {}
		}
		
		return rowsDeleted;
	}
	@Override
	public void deposit(int accountId, double amount) {
		String sql = "{call DEPOSIT(?,?)}";
		
		Connection con = null;
		
		CallableStatement cs = null;
				
		try {
			con = ConnectionUtil.getConnection();
			cs = con.prepareCall(sql);
			cs.setInt(1, accountId);
			cs.setDouble(2, amount);
			cs.execute();
		} catch(IOException | SQLException e) {
			log.error(e.getMessage());
		} finally {
			try {if (cs != null) cs.close();} catch(SQLException e) {}
		}
	}
	@Override
	public void withdraw(int accountId, double amount) {
		String sql = "{call WITHDRAW(?,?)}";
		
		Connection con = null;
		
		CallableStatement cs = null;
				
		try {
			con = ConnectionUtil.getConnection();
			cs = con.prepareCall(sql);
			cs.setInt(1, accountId);
			cs.setDouble(2, amount);
			cs.execute();
		} catch(IOException | SQLException e) {
			log.error(e.getMessage());
		} finally {
			try {if (cs != null) cs.close();} catch(SQLException e) {}
		}
	}
}
