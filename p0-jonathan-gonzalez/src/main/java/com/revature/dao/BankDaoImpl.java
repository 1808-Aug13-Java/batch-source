package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.Account;
import com.revature.util.connectionUtil;

public class BankDaoImpl implements BankDao {

	@Override
	public Account login(String username, String password) {
		Account a = new Account();
		String sql = "SELECT BALANCE FROM BANK WHERE USERNAME = ? AND " +
				"PASSWORD = ?";
				
				try(Connection con = connectionUtil.getConnection();
						PreparedStatement ps = con.prepareStatement(sql)){
					
					con.setAutoCommit(false);
					ps.setString(1, username);
					ps.setString(2, password);
					ResultSet rs = ps.executeQuery();
					con.commit();
					
					rs.next();
					
					a.setBalance(rs.getDouble("BALANCE"));
					a.setUsername(username);
					a.setPassword(password);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return a;
	}

	@Override
	public void createAccount(String username, String password, double startingBalance) {
		String sql = "INSERT INTO BANK (USERNAME, PASSWORD, BALANCE) VALUES (?,?,?)";
		try(Connection con = connectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
		
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setDouble(3, startingBalance);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateAccount(Account a) {
		String sql = "UPDATE BANK SET BALANCE = ? WHERE USERNAME = ? AND " +
				"PASSWORD = ?";
				
				try(Connection con = connectionUtil.getConnection();
						PreparedStatement ps = con.prepareStatement(sql)){
					
					con.setAutoCommit(false);
					ps.setDouble(1, a.getBalance());
					ps.setString(2, a.getUsername());
					ps.setString(3, a.getPassword());
					ps.executeUpdate();
					con.commit();
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		
	}

}
