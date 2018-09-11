package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Login;
import com.revature.util.ConnectionUtil;

public class LoginDaoImpl implements LoginDao{

	@Override
	public Login getLoginByUser(String user) {
		Login l = new Login();
		
		String sql = "SELECT * FROM P1_LOGIN WHERE USERNAME = ?";
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, user);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int empId = rs.getInt("EMP_ID");
				l.setId(empId);
				
				String username = rs.getString("USERNAME");
				l.setUser(username);
				
				String pwsd = rs.getString("PASSWORD");
				l.setPwsd(pwsd);
			}
		} catch (IOException | SQLException e) {
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
		return l;
	}

	@Override
	public List<Login> getLogins() {
		List<Login> loginList = new ArrayList<Login>();
		
		String sql = "SELECT * FROM P1_LOGIN";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while(rs.next()) {
				Login l = new Login();
				int empId = rs.getInt("EMP_ID");
				l.setId(empId);
				
				String user = rs.getString("USERNAME");
				l.setUser(user);
				
				String pwsd = rs.getString("PASSWORD");
				l.setPwsd(pwsd);
				
				loginList.add(l);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
		return loginList;
	}

}
