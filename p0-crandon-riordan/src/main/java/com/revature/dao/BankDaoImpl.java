package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.engine.Driver;
import com.revature.model.Bank;
import com.revature.util.ConnectionUtil;

public class BankDaoImpl implements BankDao {
	
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
	public boolean deposit(int id, float amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean withdraw(int id, float amount) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
