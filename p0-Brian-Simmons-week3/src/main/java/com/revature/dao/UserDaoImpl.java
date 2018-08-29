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

import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserDaoImpl implements UserDao {

	
	private static Logger log = Logger.getRootLogger();

	public List<User> getUsers() {
		// TODO Auto-generated method stub
		
		List<User> userList = new ArrayList<>();
		
		String sql = "SELECT * FROM USERS";
		
		try (Connection con = ConnectionUtil.getConnection();
					Statement s = con.createStatement();
					ResultSet rs = s.executeQuery(sql)){
			
			while(rs.next()) {
				String user = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				float balance = rs.getFloat("BALANCE");
				userList.add(new User(user, password, balance));
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		return userList;
	}

	public User getUsersByUsername(String username) {
		// TODO Auto-generated method stub
		User u = null;
		String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
		
		ResultSet rs = null;
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String user = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				float balance = rs.getFloat("BALANCE");
				u = new User(user, password, balance);
			}
		} catch (IOException|SQLException e) {
			log.error(e.getMessage());
		} finally {
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
			}
		}
		return u;
	}

	public int createUser(User user) {
		int userCreated = 0;
		String sql = "INSERT INTO USERS VALUES (?,?,?)";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
				
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setFloat(3, user.getBalance());
				
				userCreated = ps.executeUpdate();
	
		} catch (SQLException |IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return userCreated;
	}

	public int updateUser(User user) {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE USERS "
				+ "SET USERNAME = ?,"
				+ "PASSWORD = ?,"
				+ "BALANCE = ?"
				+ "WHERE USERNAME = ?";
		
		int updateStatus = 0;
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
				
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setFloat(3, user.getBalance());
				ps.setString(4, user.getUsername());
				
				ps.executeUpdate();
	
		} catch (SQLException |IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		return updateStatus;
	}

	public int deleteUsers(String username) {
		int usersDeleted = 0;
		
		String sql = "DELETE FROM USERS WHERE USERNAME = ?";
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
				
			ps.setString(1, username);
			usersDeleted = ps.executeUpdate();
	
		} catch (SQLException |IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		
		return usersDeleted;
	}

	public void makeDeposit(String username, float depositAmount) {
		
		String sql = "{call DEPOSIT_AMOUNT(?,?)}";
		
		try( Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){
			
			cs.setString(1, username);
			cs.setFloat(2, depositAmount);
			cs.execute();
			log.info("deposit complete");
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		
	}

	public void makeWithdrawal(String username, float withdrawalAmount) {
		// TODO Auto-generated method stub
		
String sql = "{call WITHDRAWAL_AMOUNT(?,?)}";
		
		try( Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){
			
			cs.setString(1, username);
			cs.setFloat(2, withdrawalAmount);
			cs.execute();
			log.info("withdrawal complete");
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
			
	}

//	public void getBalance(String username, float balance) {
//		// TODO Auto-generated method stub
//		
//	
//	}
}
		
