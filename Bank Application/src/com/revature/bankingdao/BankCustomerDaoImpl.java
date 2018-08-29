package com.revature.bankingdao;

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

import com.revature.bankingmodel.BankCustomer;
import com.revature.bankingutil.BankingConnection;

public class BankCustomerDaoImpl implements BankingCustomerDao {
	
	private static Logger log = Logger.getRootLogger();

	@Override
	public List<BankCustomer> getBankCustomers() {
		ResultSet rs = null;
		List<BankCustomer> bankCustomerList = new ArrayList<>();
		String sql = "SELECT * FROM BankCustomer";
		BankCustomer c;
		
		try(Connection con = BankingConnection.getConnection();
				Statement s = con.createStatement()){
			
			rs = s.executeQuery(sql);
			while(rs.next())
			{
				int id = rs.getInt("CUSTOMER_ID");
				String name = rs.getString("NAME");
				String email = rs.getString("EMAIL");
				String username = rs.getString("USERNAME");
				String privateInfo = rs.getString("PASSWORD");
				float balance = rs.getFloat("BALANCE");
				c = new BankCustomer(id, name, email, username, privateInfo, balance);
				bankCustomerList.add(c);
			}
			 
		} catch (SQLException | IOException e) {
			log.error(e.getMessage());
		} 
		finally {
			try {
				if(rs != null)
				{
					rs.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return bankCustomerList;
	}

	@Override
	public BankCustomer getBankCustomerById(int id) {
		ResultSet rs = null;
		BankCustomer c = null;
		String sql = "SELECT * FROM BankCustomer WHERE CUSTOMER_ID = ?";
		
		try {
			Connection con = BankingConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				String name = rs.getString("NAME");
				String email = rs.getString("EMAIL");
				String username = rs.getString("USERNAME");
				String privateInfo = rs.getString("PASSWORD");
				float balance = rs.getFloat("BALANCE");
				c = new BankCustomer(id, name, email, username, privateInfo, balance);
			}
			
		} catch (IOException | SQLException e) {
			log.info(e.getMessage());
		} finally {
			if(rs != null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					log.info(e.getMessage());			}
			}
		}
		
		return c;
	}

	@Override
	public BankCustomer getBankCustomerById(int id, Connection c) {
		BankCustomer newCustomer = null;
		String sql = "SELECT * FROM BankCustomer WHERE CUSTOMER_ID = ?";
		
		ResultSet rs = null;
		
		try (PreparedStatement ps = c.prepareStatement(sql);){
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				String name = rs.getString("NAME");
				String email = rs.getString("EMAIL");
				String username = rs.getString("USERNAME");
				String privateInfo = rs.getString("PASSWORD");
				float balance = rs.getFloat("BALANCE");
				newCustomer = new BankCustomer(id, name, email, username, privateInfo, balance);
			}
			
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if(rs != null)
				{
					rs.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return newCustomer;
	}

	@Override
	public int createBankCustomer(BankCustomer bc) {
		String sql = "INSERT INTO BankCustomer (CUSTOMER_ID, NAME, EMAIL, USERNAME, PASSWORD, BALANCE) "
				+ "values (?, ?, ?, ?, ?, ?)";
		ResultSet customersCreated = null;
		
		try(Connection con = BankingConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, bc.getId());
			ps.setString(2, bc.getName());
			ps.setString(3, bc.getEmail());
			ps.setString(4, bc.getUsername());
			ps.setString(5, bc.getPassword());
			ps.setFloat(6, bc.getBalance());
			customersCreated = ps.executeQuery();
			
		} catch (SQLException | IOException e) {
			log.error(e.getMessage());
		} finally {
			if(customersCreated != null)
			{
				try {
					customersCreated.close();
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
			}
		}
		return 1;
	}

	@Override
	public int updateBankCustomer(BankCustomer BankCustomer) {
		String sql = "UPDATE BankCustomer " +
				"SET  NAME = ?, EMAIL = ?, USERNAME = ?, PASSWORD = ?, BALANCE = ? " +
				"WHERE CUSTOMER_ID = ?";
				
				try(Connection con = BankingConnection.getConnection();
						PreparedStatement ps =  con.prepareStatement(sql)){
					
					con.setAutoCommit(false);
					ps.setString(1, BankCustomer.getName());
					ps.setString(2, BankCustomer.getEmail());
					ps.setString(3, BankCustomer.getUsername());
					ps.setString(4, BankCustomer.getPassword());
					ps.setFloat(5, BankCustomer.getBalance());
					ps.setInt(6, BankCustomer.getId());
					ps.executeQuery();
					con.commit();
					
				} catch (SQLException | IOException e) {
					log.error(e.getMessage());
				}
				
		return 1;
	}

	@Override
	public int deleteBankCustomerById(int id) {
		String sql = "DELETE FROM BANKCUSTOMER WHERE CUSTOMER_ID = ?";
		ResultSet rs = null;
		
		try(Connection con = BankingConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			
			con.setAutoCommit(false);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			con.commit();
			
		} catch (SQLException |IOException e) {
			log.error(e.getMessage());
		} 
		return 1;
	}

	@Override
	public void updateBalance(int id, float moneyAmount) {
		String sql = "{call UPDATE_BALANCE(?,?)}";
		
		try (Connection con = BankingConnection.getConnection();
			CallableStatement cs = con.prepareCall(sql)){
			
			con.setAutoCommit(false);
			cs.setInt(1, id);
			cs.setFloat(2, moneyAmount);
			cs.executeQuery();
			con.commit();
		} catch (SQLException | IOException e) {
			log.error(e.getMessage());
		}
		
	}

	@Override
	public BankCustomer getBankCustomerByEmail(String email) {
		BankCustomer newCustomer = null;
		String sql = "SELECT * FROM BankCustomer WHERE EMAIL = ?";
		
		ResultSet rs = null;
		
		try (Connection con = BankingConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);){
			
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				int id = rs.getInt("CUSTOMER_ID");
				String name = rs.getString("NAME");
				String username = rs.getString("USERNAME");
				String privateInfo = rs.getString("PASSWORD");
				float balance = rs.getFloat("BALANCE");
				newCustomer = new BankCustomer(id, name, email, username, privateInfo, balance);
			}
			
		} catch (SQLException | IOException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if(rs != null)
				{
					rs.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return newCustomer;
	}
	

}
