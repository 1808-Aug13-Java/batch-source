package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Manager;
import com.revature.util.ConnectionUtil;

public class ManagerDaoImpl implements ManagerDao{

	@Override
	public Manager getManagerById(int id) {
		Manager m = new Manager();
		
		String sql = "SELECT * FROM P1_MANAGER WHERE MAN_ID = ?";
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int manId = rs.getInt("MAN_ID");
				m.setId(manId);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
		return m;
	}

	@Override
	public List<Manager> getManagers() {
		List<Manager> manList = new ArrayList<Manager>();
		
		String sql = "SELECT * FROM P1_MANAGER";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while(rs.next()) {
				Manager m = new Manager();
				int manId = rs.getInt("MAN_ID");
				m.setId(manId);
				
				manList.add(m);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return manList;
	}
	
	@Override
	public int createManager(Manager manager) {
		int mansCreated = 0;
		
		String sql = "INSERT INTO  ";
		return mansCreated;
	}
}
