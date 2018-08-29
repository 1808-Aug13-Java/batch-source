package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.revature.exceptions.FractionalCentException;
import com.revature.exceptions.NegativeNumberException;
import com.revature.model.Account;
import com.revature.util.ConnectionUtil;

public class BankDaoImpl implements BankDao {
	
	static final Logger logger = Logger.getLogger(BankDaoImpl.class);
	static final String FAIL = "failed!";

	@Override
	public Account login(String username, String password) {
		Account a = new Account();
		String sql = "SELECT BALANCE FROM BANK WHERE USERNAME = ? AND " +
				"PASSWORD = ?";
				
				try(Connection con = ConnectionUtil.getConnection();
						PreparedStatement ps = con.prepareStatement(sql)){
					
					con.setAutoCommit(false);
					ps.setString(1, username);
					ps.setString(2, password);
					try(ResultSet rs = ps.executeQuery()){
						
						con.commit();
						
						if(rs.next()) {
							a.setBalance(rs.getDouble("BALANCE"));
							a.setUsername(username);
							a.setPassword(password);
						}
					} 
					

					
				} catch (SQLException |IOException e) {

					logger.error( FAIL, e );
				}
					
				
				return a;
	}
	

	@Override
	public int createAccount(String username, String password, String startingBalance) {
		double parsedBalance = 0;
		try {
			parsedBalance = Double.parseDouble(startingBalance);
			double delta = Math.abs(parsedBalance - ((int) parsedBalance));
			
			if(parsedBalance < 0) {
				throw new NegativeNumberException();
			}
			else if(delta < 0.0099999999 & delta > 0) {
				throw new FractionalCentException();
			}
		}
		catch(FractionalCentException e) {
			logger.error("Your starting balance can't have fractional decimals.\n");
		}
		catch(NegativeNumberException e) {
			logger.error("Your starting balance can't be negative.\n");
		}
		catch(IllegalArgumentException e) {
			logger.error("Please enter a number for your starting balance.\n");
		}
		
		String sql = "INSERT INTO BANK (USERNAME, PASSWORD, BALANCE) VALUES (?,?,?)";
		int executeUpdate = 0;
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
		
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setDouble(3, parsedBalance);
			executeUpdate = ps.executeUpdate();
			
			
		}catch(SQLException e) {
			logger.error("Username already taken or password/username combination invalid.");
		}
		catch (IOException e) {
			logger.error( FAIL, e );
		}
		
		return executeUpdate;
	}

	@Override
	public void updateAccount(Account a) {
		String sql = "UPDATE BANK SET BALANCE = ? WHERE USERNAME = ? AND " +
				"PASSWORD = ?";
				
				try(Connection con = ConnectionUtil.getConnection();
						PreparedStatement ps = con.prepareStatement(sql)){
					
					con.setAutoCommit(false);
					ps.setDouble(1, a.getBalance());
					ps.setString(2, a.getUsername());
					ps.setString(3, a.getPassword());
					ps.executeUpdate();
					con.commit();
					
					
				} catch (SQLException | IOException e) {
					logger.error( FAIL, e );
				}
				
		
	}


	@Override
	public void updatePassword(String username, String newpassword) {
		

		String sql = "{call UPDATE_PASSWORD(?,?)}";

		

		try( Connection con = ConnectionUtil.getConnection();

				CallableStatement cs = con.prepareCall(sql)){

			

			cs.setString(1, username);

			cs.setString(2, newpassword);

			cs.execute();

			logger.info("Password updated.");

		} catch (SQLException|IOException e) {

			logger.error(e.getMessage());

		} 
		
	}


	@Override
	public void quickView(String username, String password) {

		String sql = "SELECT BALANCE FROM BANK WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "'";
	
		try (Connection con = ConnectionUtil.getConnection();

				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
				rs.next();

				double balance = rs.getDouble("BALANCE");
				logger.info("You balance is $" + balance);
			}
		 catch (SQLException|IOException e) {

			logger.error(e.getMessage());
		} 
		
	}

}
