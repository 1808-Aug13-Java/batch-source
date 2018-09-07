package com.revature.doa;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Manager;
import com.revature.util.ConnectionUtil;

public class ManagerDAOImpl implements ManagerDAO{

	
	@Override
	public List<Manager> getManagers() {
		List<Manager> manList = new ArrayList<>();
		String sql = "SELECT * FROM MANAGER";

		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)) {
			while (rs.next()) {
				Manager m = new Manager();
				m.setManId(rs.getInt("MAN_ID"));
				m.setfName(rs.getString("FNAME"));
				m.setlName(rs.getString("LNAME"));
				manList.add(m);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			// REPLACE WITH LOG4J
		}
		return manList;
	}
	
	@Override
	public Manager getManagerById(int id) {
		
		String sql = "SELECT * FROM MANAGER WHERE MAN_ID = ?";
		Manager m = new Manager();
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				m.setManId(rs.getInt("MAN_ID"));
				m.setfName(rs.getString("FNAME"));
				m.setlName(rs.getString("LNAME"));
			}
		} catch (SQLException | IOException e1) {
			// TODO log4j
			e1.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					if (rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException e1) {
					// TODO log4j
					e1.printStackTrace();
				}
			}
		}
		return m;
	}

	@Override
	public int updateManager(Manager manager) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateManager(Manager manager, Connection con) {
		// TODO Auto-generated method stub
		return 0;
	}
}
