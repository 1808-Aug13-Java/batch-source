package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.main.BankCore;
import com.revature.model.BankUsers;
import com.revature.util.ConnectionUtil;

public class BankUsersDaoImpl implements BankUsersDao {
	static final Logger logger = Logger.getLogger(BankCore.class);
	
	public boolean checkIfAccountExists(String name) {
		boolean accountExists = false;
		BankUsers user = getUserByName(name);
		ResultSet rs = null;
		String sql = "SELECT * FROM BANK WHERE USER_ID = ?";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			
			ps.setInt(1, user.getId());
			rs = ps.executeQuery();
			
			if (rs.isBeforeFirst()) {
				accountExists = true;
			}
			
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}
		
		return accountExists;
		
	}
	
	public int getUserIdByUsername(String username) {
		int id = 0;
		ResultSet rs = null;
		String sql = "SELECT * FROM BANK_USERS WHERE USERNAME = ?";
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, username);
			rs = ps.executeQuery();

			while(rs.next()) {
				id = rs.getInt("USER_ID");
			}
			
			
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}
		
		return id;
		
		
	}
	

	@Override
	public BankUsers getUserById(int id) {
		return null;
	}

	@Override
	public BankUsers getUserByName(String name) {

		ResultSet rs = null;
		BankUsers user = null;
		String sql = "SELECT * FROM BANK_USERS WHERE USERNAME = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			int id = 0;
			String username;
			String emailAddress;
			String password;
			
			while (rs.next()) {
				username = rs.getString("USERNAME");
				emailAddress = rs.getString("EMAIL");
				password = rs.getString("USER_PASSWORD");
				id = rs.getInt("USER_ID");
				user = new BankUsers(password, id, username, emailAddress);
				
			}
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}
		
		return user;
	}

	@Override
	public int createUser(BankUsers user) {
		int createdUsers = 0;
		String sql = "INSERT INTO BANK_USERS (USERNAME, EMAIL, USER_PASSWORD) VALUES (? ,? ,?)";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1,  user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3,  user.getUserPassword());
			
			createdUsers = ps.executeUpdate();
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		}
		
		if (createdUsers > 0) {
			logger.info("Thanks for creating an account with us! Welcome to Revature Bank!\n");
		} else {
			logger.info("Your attempt to create an account was unsuccessful. Please try again.\n");
		}
		return createdUsers;
	}

	@Override
	public int updateUser(BankUsers user) {
		return 0;
	}

	@Override
	public int hideUserById(BankUsers user) {
		return 0;
	}

	@Override
	public boolean isUsernameUnique(BankUsers user) {
		boolean isUnique = false;
		String username = user.getUsername();
		String sql = "SELECT * FROM BANK_USERS WHERE USERNAME = ?";
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if (!rs.isBeforeFirst()) {
				isUnique = true;
			}
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return isUnique;
	}

	@Override
	public boolean isUserEmailUnique(BankUsers user) {
		boolean isUnique = false;
		String emailAddress = user.getEmail();
		String sql = "SELECT * FROM BANK_USERS WHERE EMAIL = ?";
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, emailAddress);
			rs = ps.executeQuery();
			
			if (!rs.isBeforeFirst()) {
				isUnique = true;
			}
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return isUnique;
	}
	


}
