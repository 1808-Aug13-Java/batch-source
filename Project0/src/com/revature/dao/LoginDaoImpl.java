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

import com.revature.model.Login;
import com.revature.util.ConnectionUtil;

public class LoginDaoImpl implements LoginDao{
	private static Logger log = Logger.getRootLogger();
	static final String NAMESTRING = "USERNAME";
	static final String PASSTRING = "PASSWORD";
	
	@Override
	public List<Login> getLogins() {
		List<Login> loginList = new ArrayList<>();
		Login l = null;
		
		String sql = "SELECT * FROM LOGIN";
		ResultSet rs = null;
		try(Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement()){
			rs = s.executeQuery(sql);
			
			while (rs.next()) {
				String username = rs.getString(NAMESTRING);
				String password = rs.getString(PASSTRING);
				l = new Login(username, password);
				loginList.add(l);
			}
		} catch (SQLException|IOException e) {
			log.error(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error(e);
			}
		}
		
		return loginList;
	}

	@Override
	public Login getLoginById(String id) {
		Login l = null;
		String sql = "SELECT * FROM LOGIN WHERE USERNAME = ?";
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String username = rs.getString(NAMESTRING);
				String password = rs.getString(PASSTRING);
				l = new Login(username, password);
			}
		
		} catch (IOException|SQLException e) {
			log.error(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error(e);
			}
		}
		return l;
	}

	@Override
	public Login getLoginById(String id, Connection con) {
		Login l = null;
		String sql = "SELECT * FROM LOGIN WHERE USERNAME = ?";
		ResultSet rs = null;
		
		try (PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String username = rs.getString(NAMESTRING);
				String password = rs.getString(PASSTRING);
				l = new Login(username, password);
			}
		
		} catch (SQLException e) {
			log.error(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error(e);
			}
		}
		return l;
	}

	@Override
	public int createLogin(Login login) {
		int loginsCreated = 0;
		String sql = "INSERT INTO LOGIN (USERNAME, PASSWORD) VALUES (?, ?)";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, login.getUsername());
			ps.setString(2, login.getPassword());
			loginsCreated = ps.executeUpdate();
		} catch (IOException | SQLException e) {
			log.error(e);
		}
		return loginsCreated;
	}

	@Override
	public int updateLogin(Login login) {
		int loginsUpdated = 0;
		String sql = "UPDATE LOGIN "
				+ "SET PASSWORD = ? "
				+ "WHERE USERNAME = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, login.getPassword());
			ps.setString(2, login.getUsername());
			loginsUpdated = ps.executeUpdate();
			
		} catch (IOException | SQLException e) {
			log.error(e);
		}
		return loginsUpdated;
	}

	@Override
	public int deleteLoginById(String id) {
		int rowsDeleted = 0;
		String sql = "DELETE FROM LOGIN WHERE USERNAME = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, id);
			rowsDeleted = ps.executeUpdate();
		} catch (IOException | SQLException e) {
			log.error(e);
		}
		return rowsDeleted;
	}

}
