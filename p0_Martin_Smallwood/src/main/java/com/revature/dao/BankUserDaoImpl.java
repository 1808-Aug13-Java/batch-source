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


import com.revature.model.BankUser;
import com.revature.util.ConnectionUtil;

public class BankUserDaoImpl implements BankUserDao {

	private static String username = "USERNAME";
	private static String passcol = "PASSWORD";
	private static String balance = "BALANCE";
	private Logger log = Logger.getRootLogger();
	@Override
	public List<BankUser> getBankUsers() {
		List<BankUser> bankUserList = new ArrayList<>();
		
		String sql = "SELECT * FROM BANK_USER";
		//ye olde try with resources
		//resources will automatically close
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql);){
			
			//while there are more rows
			while(rs.next()) {
				BankUser bu = new BankUser();
				
				bu.setUsername(rs.getString(username));
				bu.setPassword(rs.getString(passcol));
				bu.setBalance(rs.getFloat(balance));			
				
				bankUserList.add(bu);
			}
			
		} catch (SQLException | IOException e1) {
			log.error(e1.getMessage());
		}
		return bankUserList;
	}

	@Override
	public BankUser getBankUserByLogin(String user, String pass, Connection con) {
		
		String sql = "SELECT * FROM BANK_USER WHERE USERNAME=? AND PASSWORD=?";
		ResultSet rs = null;
		BankUser bu = new BankUser();
		try (	PreparedStatement ps = con.prepareStatement(sql);){
			
			log.info("in try block");
			ps.setString(1, user);
			log.info("after first setstring");
			ps.setString(2, pass);
			log.info(ps.getMetaData().toString());
			rs = ps.executeQuery();
			log.info("printing rs: "+rs.toString());
			while(rs.next()) {
				bu.setUsername(rs.getString(username));
				bu.setPassword(rs.getString(passcol));
				bu.setBalance(rs.getFloat(balance));	
			}
			log.info("Showing bu in getBankUserByLogin: " + bu.toString());
			
		} catch (SQLException e1) {
			log.error(e1.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException | NullPointerException e) {
					log.error(e);
				}
			}
		}
		return bu;
	}
	public BankUser getBankUserByUsername(String user, Connection con) {
		
		String sql = "SELECT * FROM BANK_USER WHERE USERNAME=?";
		ResultSet rs = null;
		BankUser bu = new BankUser();
		try (	PreparedStatement ps = con.prepareStatement(sql);){
			
			log.info("in try block");
			ps.setString(1, user);
			log.info("after first setstring");
			log.info(ps.getMetaData().toString());
			rs = ps.executeQuery();
			log.info("printing rs: "+rs.toString());
			while(rs.next()) {
				bu.setUsername(rs.getString(username));
			}
			
		} catch (SQLException e1) {
			log.error(e1.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException | NullPointerException e) {
					log.error(e);
				}
			}
		}
		return bu;
	}

	@Override
	public int createBankUser(BankUser newUser, Connection con) {
		String sql = "{call NEW_BANK_USER(?,?)}";
		int userCreated = -10;
		try(CallableStatement ps = con.prepareCall(sql);){
			ps.setString(1, newUser.getUsername());
			ps.setString(2, newUser.getPassword());
			userCreated = ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return userCreated;
	}

	@Override
	public int updateBankUser(BankUser user, Connection con) {
		return 0;
	}

	@Override
	public int deleteBankUserByLogin(String username, String password, Connection con) {
		return 0;
	}

	@Override
	public void modifyBalance(String username, String password, float changeAmount, Connection con) {
		String sql = "UPDATE BANK_USER SET BALANCE = BALANCE + ? WHERE USERNAME = ? AND PASSWORD = ?";
		try(PreparedStatement ps = con.prepareStatement(sql);){
			ps.setFloat(1, changeAmount);
			ps.setString(2, username);
			ps.setString(3, password);
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

}
