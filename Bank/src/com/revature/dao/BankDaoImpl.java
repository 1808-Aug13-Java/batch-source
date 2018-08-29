package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.main.BankCore;
import com.revature.model.Bank;
import com.revature.util.ConnectionUtil;
import com.revature.util.UserInputValidation;

public class BankDaoImpl implements BankDao {
	
	Scanner sc = new Scanner(System.in);
	private static final Logger logger = Logger.getLogger(BankCore.class);

	@Override
	public List<Bank> getAllBanks() {
	List<Bank> bankList = new ArrayList<>();
		
		String sql = "SELECT * FROM BANK";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
		
			while(rs.next()) {
				Bank b = new Bank();
				
				int bankId = rs.getInt("ACCOUNT_NO");
				b.setId(bankId);
				
				bankList.add(b);
			}
			
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return Collections.emptyList();
	}

	@Override
	public Bank getBankById(int id) {
		return null;
	}

	@Override
	public int createBank(Bank bank) {
		int createdBanks = 0;
		
		String sql = "INSERT INTO BANK (USER_ID) VALUES (?)";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, bank.getUserId());
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		}
		
		if (createdBanks > 0) {
			logger.info("Congratulations, your account has been successfully created!");
		}
		
		return createdBanks;
	}
	
	public int createBank(int userId) {
		int createdBanks = 0;
		String sql = "INSERT INTO BANK (USER_ID) VALUES (?)";
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, userId);
			createdBanks = ps.executeUpdate();
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		}
		
		if (createdBanks > 0) {
			logger.info("Congratulations, your account has been successfully created!");
		}
		
		return createdBanks;
	}
	
	@Override
	public Bank getBankByUserId(int id) {
		Bank bank = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BANK WHERE ACCOUNT_NO = ?";
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int bankAccountNumber = rs.getInt("ACCOUNT_NO");
				float accountBalance = rs.getFloat("BALANCE");
				int userId = rs.getInt("USER_ID");
				
				bank = new Bank(bankAccountNumber, accountBalance, userId);
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
		
		return bank;
		
	}

	@Override
	public int updateBank(Bank bank) {
		return 0;
	}

	@Override
	public boolean deposit(int id, float balance) {
		return false;
	}

	@Override
	public void withdraw(int id) {
		float currentBalance = viewBalanceByBankId(id);
		float balance = getPositiveDecimalNumber();
		
		if(currentBalance - balance <= 0) {
			logger.info("You're trying to overdraw your account. Operation denied.");
			logger.info("");
			return;
		}
		
		String sql = "{call WITHDRAW(?, ?)}"; //implement WITHDRAW procedure
		try(Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)) {
			cs.setInt(1, id);
			cs.setFloat(2, balance);
			cs.execute();
			logger.info("You withdrew $" + UserInputValidation.floatConfig(balance));
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		}
		
	}
	
	public float viewBalanceByUserId(int id) {
		String sql = "SELECT BALANCE FROM BANK WHERE USER_ID = ?";
		ResultSet rs = null;
		float balance = 0;
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				balance = rs.getInt("BALANCE");
			}
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}
		
		return balance;
	}
	
	public float viewBalanceByBankId(int id) {
		String sql = "SELECT BALANCE FROM BANK WHERE ACCOUNT_NO = ?";
		ResultSet rs = null;
		float balance = 0;
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				balance = rs.getFloat("BALANCE");
			}
			
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}
		
		return balance;
	}
	
	public float getPositiveDecimalNumber() {
		float balance = 0;
		
		do {
			try {
				logger.info("Enter a valid positive number.");
				String userInput = sc.nextLine();
				balance = Float.parseFloat(userInput);
				
			} catch (Exception e) {
				
			}
		}   while (balance < 10 || !UserInputValidation.determineFloatPrecision(balance));
		
		return balance;
	}

	@Override
	public void deposit(int id) {
		float amount = getPositiveDecimalNumber();
		String sql = "{call DEPOSIT(?,?)}"; // MAKE STORED PROCEDURE FOR THIS!
		try (Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)) {
					cs.setInt(1, id);
					cs.setFloat(2, amount);
					cs.execute();
					
					logger.info("Deposited $" + UserInputValidation.floatConfig(amount));
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		}
	}
}
