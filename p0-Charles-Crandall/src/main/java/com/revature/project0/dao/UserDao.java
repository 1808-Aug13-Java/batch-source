package com.revature.project0.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.project0.abstraction.CurrentUser;
import com.revature.project0.drivers.ConnectionUtil;

public class UserDao implements UserDaoInterface{

	@Override
	public CurrentUser getCustomer(int id) {
		CurrentUser cu = null;
		String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
		try(Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();) {
			ps.setInt(1, id);
			
			if (rs.next()) {
				String user = rs.getString("USERNAME");
				String addy = rs.getString("ADDRESS");
				
				cu = new CurrentUser(id, user, addy, new ArrayList<Integer>());
			}
				
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return cu;
	}

	@Override
	public CurrentUser getCustomer(String userName) {
		CurrentUser cu = null;
		String sql = "SELECT * FROM CUSTOMER WHERE USERNAME = ?";
		ResultSet rs = null;
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, userName);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("CUST_ID");
				String addy = rs.getString("ADDRESS");
				
				cu = new CurrentUser(id, userName, addy, new ArrayList<Integer>());
			}
				
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs!=null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return cu;
	}
	
	@Override
	public boolean checkPassword(String user, String pass) {
		String sql = "SELECT * FROM CUSTOMER WHERE USERNAME = ? AND PASSWORD = ?";
		boolean out = false;
		ResultSet rs = null;
		try{
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			out = rs.next();
			ps.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if (rs!=null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return out;
	}

	@Override
	public boolean addCustomer(String user, String pass, String address) {
		String sql = "INSERT INTO CUSTOMER (USERNAME, PASS, ADDRESS) VALUES (?,?,?)";
		boolean output = false;
		PreparedStatement ps = null;
		try{
			Connection conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, pass);
			ps.setString(3, address);
			output = ps.execute();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if (ps!=null)
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return output;
	}

	@Override
	public boolean modifyAddress(int id, String newAddress) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCustomer(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
