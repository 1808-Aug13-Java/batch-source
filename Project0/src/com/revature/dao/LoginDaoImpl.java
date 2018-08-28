package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Login;
import com.revature.util.ConnectionUtil;

public class LoginDaoImpl implements LoginDao{

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
				String username = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				l = new Login(username, password);
				loginList.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return loginList;
	}

	@Override
	public Login getLoginById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login getLoginById(String id, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createLogin(Login login) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateLogin(Login login) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteLoginById(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
