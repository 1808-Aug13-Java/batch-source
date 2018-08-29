package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.main.Driver;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

import exceptions.UserNameException;

public class UserDaoImpl implements UserDao{
	
	private static Logger log = Logger.getRootLogger();

	@Override
	public int createUser(String username, String password) {
		int usersCreated = 0;
	
		String sql = "INSERT INTO BANK_USERS (USERNAME, PASSWORD) VALUES (?,?)";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
	
			ps.setString(1, username);
			ps.setString(2, password);
			usersCreated = ps.executeUpdate();
			
		} catch (SQLException|IOException e) {
			try {
				throw new UserNameException("Username already taken, please try again.");
			} catch (UserNameException e1) {
				log.error("Exception", e1);
			}
		} 
		return usersCreated;
	}

	@Override
	public List<User> getUsers() {
		List<User> usernames = new ArrayList<>();
		String sql = "SELECT * FROM BANK_USERS";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while (rs.next()) {
				int usId = rs.getInt("USER_ID");
				String un = rs.getString("USERNAME");
				String pw = rs.getString("PASSWORD");
				usernames.add(new User(usId,un,pw));
			}
			
		} catch (SQLException | IOException e) {
			log.error("Exception in getting Users", e);
		}
		
		
		return null;
	}

	@Override
	public boolean validateUser(String username, String password) {
		Driver redo = new Driver();
		String obtPW = "";
		String sql = "SELECT PASSWORD FROM BANK_USERS WHERE USERNAME = ?";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				log.info("This is where the password is stored. it is: ");
				obtPW = rs.getString("PASSWORD");
				if (obtPW.equals(password)) {
					log.info("Login Successful");
				}else {
					log.info("Login Unsuccessful");
					log.info("Wrong username or password");
					log.info("Please try again");
					redo.validateLogin();
				}
				
			} else {
				log.info("Login Unsuccessful");
				log.info("Wrong username or password");
				log.info("Please try again");
				redo.validateLogin();
				
			}
			
		} catch (SQLException | IOException e) {
			log.error("Exception", e);
		}
		return false;
	}
}
