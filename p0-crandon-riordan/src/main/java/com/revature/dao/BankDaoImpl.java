package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.model.Bank;
import com.revature.util.ConnectionUtil;
import com.revature.validation.Validator;

public class BankDaoImpl implements BankDao {
	static final Scanner sc = new Scanner(System.in);
	static final Logger logger = Logger.getLogger(BankDaoImpl.class);
	@Override
	public List<Bank> getAllBanks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bank getBankById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createBank(Bank bank) {
		int banksCreated = 0;
		
		String sql = "INSERT INTO BANK (USER_ID) VALUES (?)";
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				) {
			ps.setInt(1, bank.getUserId());
			banksCreated = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		if(banksCreated > 0) {
			logger.info("Successfully created an account.");
		}
		return banksCreated;
	}
	
	public int createBank(int userId) {
		int banksCreated = 0;
		
		String sql = "INSERT INTO BANK (USER_ID) VALUES (?)";
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				) {
			ps.setInt(1, userId);
			banksCreated = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		if(banksCreated > 0) {
			logger.info("Successfully created an account.");
		}
		return banksCreated;
	}

	@Override
	public int updateBank(Bank bank) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hideBankById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deposit(int id) {
		float amount = getPositiveFloat();
		String sql = "{call DEPOSIT_MONEY(?, ?)}";
		try(Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)) {
			cs.setInt(1, id);
			cs.setFloat(2, amount);
			cs.execute();
			logger.info("Successfully deposited $" + Validator.formatDecimals(amount));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void withdraw(int id) {
		float amountInBank = viewAmountByBankId(id);
		float amount = getPositiveFloat();
		
		if(amountInBank - amount <= 0) {
			logger.info("Attempted to withdraw more than in bank");
			logger.info("I'd recommend Dave Ramsey's books or podcast.");
			logger.info("");
			return;
		}
		
		String sql = "{call WITHDRAW_MONEY(?, ?)}";
		try(Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)) {
			cs.setInt(1, id);
			cs.setFloat(2, amount);
			cs.execute();
			logger.info("Successfully withdrawn $" + Validator.formatDecimals(amount));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public float viewAmountByUserId(int id) {
		String sql = "SELECT AMOUNT FROM BANK WHERE USER_ID = ?";
		ResultSet rs = null;
		float amount = 0;
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				amount = rs.getInt("AMOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return amount;
	}
	
	public float getPositiveFloat() {
		float amount = 0;
		do {
			try {
				logger.info("Enter a valid number");
				String input = sc.nextLine();
				amount = Float.parseFloat(input);
				//amount = (float) (Math.round(amount*100.0)/100.0);
			} catch (Exception e) {

			}
		} while ( amount < 10 || !Validator.isCorrectScale(amount));
		
		
		return amount;
	}
	
	
	public float viewAmountByBankId(int id) {
		String sql = "SELECT AMOUNT FROM BANK WHERE BANK_ID = ?";
		ResultSet rs = null;
		float amount = 0;
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				amount = rs.getFloat("AMOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return amount;
	}
	
	

	@Override
	public Bank getBankByUserId(int id) {
		Bank bank = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BANK WHERE BANK_ID = ?";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int bankId = rs.getInt("BANK_ID");
				float amount = rs.getFloat("AMOUNT");
				int userId = rs.getInt("USER_ID");
				
				bank = new Bank(bankId, amount, userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
		
		return bank;
	}

	

}
