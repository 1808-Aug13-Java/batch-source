package com.revature.bank.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.bank.util.*;
import com.revature.model.Account;

public class AccountDaoImp implements AccountDAO {

	private static Logger log = Logger.getRootLogger();
	private static final String SQLIOERR = "Connection failed, or SQL error.";
	private static final String USERNAME = "USER_NAME";
	private static final String BALLANCE = "BALLANCE";
	
	@Override
	public List<Account> getAccounts() {

		List<Account> accountList = new ArrayList<>();

		String sql = "SELECT * FROM ACCOUNT";

		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)) {

			while (rs.next()) {
				Account acc = new Account();

				String userName = rs.getString(USERNAME);
				acc.setUser(userName);

				long ballance = rs.getLong(BALLANCE);
				acc.setBallance(ballance);

				accountList.add(acc);
			}

		} catch (IOException | SQLException e) {
			log.error(SQLIOERR, e);
		}

		return accountList;
	}

	@Override
	public Account getAccountByUser(String name) {
		String sql = "SELECT * FROM ACCOUNT WHERE USER_NAME = ?";
		Account acc = null;
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, name);
			rs = ps.executeQuery();

			while (rs.next()) {
				acc = new Account();

				String userName = rs.getString(USERNAME);
				acc.setUser(userName);

				double ballance = rs.getDouble(BALLANCE);
				acc.setBallance(ballance);
			}

		} catch (IOException | SQLException e) {
			log.error(SQLIOERR, e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error("rs failed to close.", e);
			}
		}

		return acc;
	}

	@Override
	public int createAccount(Account account) {

		String sql = "INSERT INTO ACCOUNT (USER_NAME, BALLANCE) VALUES (?, ?) ";
		int accCreated = 0;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, account.getUserName());
			ps.setDouble(2, account.getBallance());
			accCreated = ps.executeUpdate();

		} catch (SQLException | IOException e) {
			log.error(SQLIOERR, e);
		}
		return accCreated;
	}

	@Override
	public int updateAccount(Account account) {

		int accUpdated = 0;

		String sql = "UPDATE ACCOUNT " + "SET USER_NAME = ?," + " BALLANCE = ?" +" WHERE USER_NAME = ?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, account.getUserName());
			ps.setDouble(2, account.getBallance());
			ps.setString(3, account.getUserName());
			accUpdated = ps.executeUpdate();

		} catch (SQLException | IOException e) {
			log.error(SQLIOERR, e);
		}

		return accUpdated;
	}

	@Override
	public Account getAccountByUser(String name, Connection con) {
		String sql = "SELECT * FROM ACCOUNT WHERE USER_NAME = ?";
		Account acc = null;
		ResultSet rs = null;

		try (PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, name);
			rs = ps.executeQuery();

			while (rs.next()) {
				acc = new Account();

				String userName = rs.getString(USERNAME);
				acc.setUser(userName);

				long ballance = rs.getLong(BALLANCE);
				acc.setBallance(ballance);
			}

		} catch (SQLException e) {
			log.error(SQLIOERR + " Be sure your user name is typed correctly.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error("rs failed to close.", e);
			}
		}

		return acc;
	}

	@Override
	public int deleteAccountByUser(String name) {

		int rowsDeleted = 0;

		String sql = "DELETE FROM ACCOUNT WHERE USER_NAME = ?";
		
		try(Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql);){
			ps.setString(1, name);
			rowsDeleted = ps.executeUpdate();
			
		} catch (SQLException | IOException e) {
			log.error(SQLIOERR, e);
		}
		
		return rowsDeleted;
	}

	@Override
	public int createAccount(Account account, Connection con) {
		String sql = "INSERT INTO ACCOUNT (USER_NAME, BALLANCE) VALUES (?, ?) ";
		int accCreated = 0;

		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, account.getUserName());
			ps.setDouble(2, account.getBallance());
			accCreated = ps.executeUpdate();

		} catch (SQLException e) {
			log.error(SQLIOERR, e);
		}
		return accCreated;
	}

	@Override
	public int updateAccount(Account account, Connection con) {
		int accUpdated = 0;

		String sql = "UPDATE ACCOUNT " + "SET USER_NAME = ?," + " BALLANCE = ?" +" WHERE USER_NAME = ?";

		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, account.getUserName());
			ps.setDouble(2, account.getBallance());
			ps.setString(3, account.getUserName());
			accUpdated = ps.executeUpdate();

		} catch (SQLException e) {
			log.error(SQLIOERR, e);
		}

		return accUpdated;
	}

}
