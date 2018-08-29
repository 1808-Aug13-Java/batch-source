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

public class UserDaoImpl implements UserDao{

	private static Logger log = Logger.getRootLogger();

	@Override
	public List<User> getUser() {
		// we want this to return a list
		List<User> userList = new ArrayList<>();

		String sql = "SELECT * FROM BANK_USERS";

		// try with resources, these need to be closed with finally block
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql);){

			// move pointer to next row each time
			// return false when no longer next line to move to
			while(rs.next()) {
				// create a new user that we're going to add this info to

				//each time we will get a new user
				User u = new User();
				String userId = rs.getString("USER_NAME");
				u.setUserId(userId);

				String password = rs.getString("PASS_WORD");
				u.setPassword(password);

				float balance = rs.getFloat("BALANCE");
				u.setBalance(balance);

				userList.add(u);		
			}

		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		return userList;
	}

	@Override
	public User getUserById(String id) {
		
		User u = null;
		String sql = "SELECT * FROM BANK_USERS WHERE USER_NAME = ?";

		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){

			ps.setString(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				String userId = rs.getString("USER_NAME");
				String password = rs.getString("PASS_WORD");
				float balance = rs.getFloat("BALANCE");

				u = new User(userId, password, balance);
			}
		} catch (IOException o) {
			log.error(o.getMessage());
		} catch (SQLException o) {
			log.error(o.getMessage());
		} finally {
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException o) {
					log.error(o.getMessage());
				}
			}
		}

		return u;
	}

	@Override
	public User getUserById(String id, Connection con) {

		User u = null;
		String sql = "SELECT * FROM BANK_USERS WHERE USER_NAME = ?";

		ResultSet rs = null;

		try (PreparedStatement ps = con.prepareStatement(sql);){

			ps.setString(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				String userId = rs.getString("USER_NAME");
				String password = rs.getString("PASS_WORD");
				float balance = rs.getFloat("BALANCE");

				u = new User(userId, password, balance);
			}
		} catch (SQLException e) {
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

	@Override
	public int createUser(User user) {

		int usersCreated = 0;
		String sql = "INSERT INTO BANK_USERS (USER_NAME, PASS_WORD, BALANCE) VALUES (?,?,?)";

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){

			ps.setString(1, user.getUserId());
			ps.setString(2, user.getPassword());
			ps.setFloat(3, user.getBalance());
			usersCreated = ps.executeUpdate();

		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		return usersCreated;
	}

	@Override
	public int updateUser(User user) {
		
		int usersUpdated = 0;

		String sql = "UPDATE BANK_USERS "
				+ "SET PASS_WORD = ?,"
				+ "SET BALANCE = ? "
				+ "WHERE USER_NAME = ?";

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){

			con.setAutoCommit(false);
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getPassword());
			ps.setFloat(3, user.getBalance());
			usersUpdated = ps.executeUpdate();
			con.commit();

		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 

		return usersUpdated;
	}

	@Override
	public int deleteUserById(String id) {
		int rowsDeleted = 0;

		String sql = "DELETE FROM BANK_USERS WHERE USER_NAME = ?";

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){

			ps.setString(1, id);
			rowsDeleted = ps.executeUpdate();

		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		}
		return rowsDeleted;
	}

	public void makeDeposit(String userName, float amount) {
		String sql = "{call DEPOSIT_AMOUNT(?,?)}";

		try( Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){

			cs.setString(1, userName);
			cs.setFloat(2, amount);
			cs.execute();
			log.info("deposit added");
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
	}

	public void makeWithdrawal(String userName, float amount) {
		String sql = "{call WITHDRAW_AMOUNT(?,?)}";

		try( Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){

			cs.setString(1, userName);
			cs.setFloat(2, amount);
			cs.execute();
			log.info("amount withdrawn");
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
	}

}