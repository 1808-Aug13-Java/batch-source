package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.model.Account;
import com.revature.util.ConnectionUtil;

public class AccountDaoImpl implements AccountDao {
	
	private static Logger log = Logger.getRootLogger();

	@Override
	public int createAccount(double balance) {
		int accountsCreated = 0;
		String sql = "INSERT INTO BANK_ACCOUNT (ACCOUNT_BALANCE) VALUES (?)";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setDouble(1, balance);
			accountsCreated = ps.executeUpdate();
			
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		return accountsCreated;
	}

	@Override
	public double getAmount(int id) {
		String sql = "SELECT ACCOUNT_BALANCE FROM BANK_ACCOUNT WHERE ACCOUNT_ID =?";
		double balance = 0;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				balance = rs.getDouble("ACCOUNT_BALANCE");
			}
			
		} catch (SQLException | IOException e) {
			log.error("Exception", e);
		}
		return balance;
	}

	@Override
	public int getAccId(String username) {
		String sql = "SELECT USER_ID FROM BANK_USERS WHERE USERNAME = ?";
		int accId =0;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				accId = rs.getInt("USER_ID");
			}
		} catch (SQLException | IOException e) {
			log.error("Exception", e);
		}
		return accId;
	}

	@Override
	public void increaseAmount(int accId, double increaseAmount) {

		
		String sql = "{call INCREASE_AMOUNT(?,?)}";
		
		try( Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){
			cs.setInt(1, accId);
			cs.setDouble(2, increaseAmount);
			cs.execute();
		} catch (SQLException | IOException e) {
			log.error("Exception", e);
		}
	}

	@Override
	public void decreaseAmount(int accId, double decreaseAmount) {
		String sql = "{call DECREASE_AMOUNT(?,?)}";
		try( Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){
			cs.setInt(1, accId);
			cs.setDouble(2, decreaseAmount);
			cs.execute();
		} catch (SQLException | IOException e) {
			log.error("Exception", e);
		}
	}
	






	
}
