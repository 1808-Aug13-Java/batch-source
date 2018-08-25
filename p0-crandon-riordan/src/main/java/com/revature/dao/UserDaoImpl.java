package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.engine.Engine;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserDaoImpl implements UserDao {
	
	static final Logger logger = Logger.getLogger(Engine.class);
	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean userHasBank(String name) {
		boolean hasBank = false;
		User user = getUserByName(name);
		String sql = "SELECT * FROM BANK WHERE USER_ID = ?";
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getUserIdByName(String name) {
		ResultSet rs = null;
		int id = 0;
		String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, name);
			rs = ps.executeQuery();

			while(rs.next()) {
				id = rs.getInt("USER_ID");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		
		return id;
	}

	@Override
	public User getUserByName(String name) {
		ResultSet rs = null;
		User user = null;
		String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, name);
			rs = ps.executeQuery();
			int id = 0;
			String username;
			String userEmail;
			String userPassword;
			while(rs.next()) {
				username = rs.getString("USERNAME");
				userEmail = rs.getString("USER_EMAIL");
				userPassword = rs.getString("USER_PASSWORD");
				id = rs.getInt("USER_ID");
				System.out.println("user id : " + id);
				user = new User(id, username, userEmail, userPassword);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		
		return user;
	}

	@Override
	public int createUser(User user) {
		int createdUsers = 0;
		String sql = "INSERT INTO USERS (USERNAME, USER_EMAIL, USER_PASSWORD) VALUES (?,?,?)";
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getUserEmail());
			ps.setString(3, user.getUserPassword());
			createdUsers = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(createdUsers > 0) {
			logger.info("Account creation successful");
			logger.info("");
		} else {
			logger.info("Account creation unsuccessful");
			logger.info("");
		}
		
		return createdUsers;
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hideUserById(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUsernameUnique(User user) {
		boolean isUnique = false;
		String username = user.getUsername();
		String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
		ResultSet rs = null;
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if(!rs.isBeforeFirst()) {
				isUnique = true;
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
		
		
		return isUnique;
	}

	@Override
	public boolean isUserEmailUnique(User user) {
		boolean isUnique = false;
		String email = user.getUserEmail();
		String sql = "SELECT * FROM USERS WHERE USER_EMAIL = ?";
		ResultSet rs = null;
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if(!rs.isBeforeFirst()) {
				isUnique = true;
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
		
		
		return isUnique;
	}
	
	

	

}
