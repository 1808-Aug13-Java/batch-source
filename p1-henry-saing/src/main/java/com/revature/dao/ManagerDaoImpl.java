package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.util.ConnectionUtil;

public class ManagerDaoImpl implements ManagerDao {

	boolean st = false;
	@Override
	public boolean validateUser(String username, String password) {
		String sql = "SELECT * FROM ERS_MANAGER WHERE ERS_MAN_USERNAME = ? AND ERS_MAN_PASS = ?";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			Boolean res = false;
			
			res = rs.next();
			return res;

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
