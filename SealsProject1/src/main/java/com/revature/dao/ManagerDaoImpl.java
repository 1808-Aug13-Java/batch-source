package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.util.ConnectionUtil;

public class ManagerDaoImpl implements ManagerDao {
	
	private static Logger log = Logger.getRootLogger(); 

	@Override
	public Manager getManagerById(int id) {
		Manager m = new Manager();
		String sql = "SELECT * FROM MANAGER WHERE MANAGER_ID = ?";
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {

				int managerId = rs.getInt("MANAGER_ID");
				m.setId(managerId);

				String fname = rs.getString("F_NAME");
				m.setFname(fname);
				
				String lname = rs.getString("L_NAME");
				m.setLname(lname);
			
				String username = rs.getString("USERNAME");
				m.setUsername(username);

				String pwd = rs.getString("PWD");
				m.setPwd(pwd);
			}

		} catch (IOException | SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return m;
	}
	
	
	@Override
	public Manager getManagerByUsername(String username) {
		Manager m = new Manager();
		String sql = "SELECT * FROM MANAGER WHERE USERNAME = ?";
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, username);

			rs = ps.executeQuery();

			while (rs.next()) {

				int managerId = rs.getInt("MANAGER_ID");
				m.setId(managerId);

				String fname = rs.getString("F_NAME");
				m.setFname(fname);
				
				String lname = rs.getString("L_NAME");
				m.setLname(lname);
			
				String usernme = rs.getString("USERNAME");
				m.setUsername(usernme);

				String pwd = rs.getString("PWD");
				m.setPwd(pwd);
			}

		} catch (IOException | SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return m;
	}


	@Override
	public List<Employee> getEmployeesByManagerId(int id) {
		List<Employee> employeeList = new ArrayList<>();
		String sql = "SELECT * FROM P1EMPLOYEE WHERE MANAGER_ID = ?";
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
				
				ps.setInt(1, id);
				
				rs = ps.executeQuery(); 

			while (rs.next()) {
				Employee e = new Employee();
				int employeeId = rs.getInt("EMP_ID");
				e.setId(employeeId);

				String fname = rs.getString("F_NAME");
				e.setFname(fname);
				
				String lname = rs.getString("L_NAME");
				e.setLname(lname);
			
				String username = rs.getString("USERNAME");
				e.setUsername(username);

				String pwd = rs.getString("PWD");
				e.setPwd(pwd);

				int managerId = rs.getInt("MANAGER_ID");
				e.setManagerId(managerId);

				employeeList.add(e);
			}

		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		return employeeList;
	}
	@Override
	public boolean isMatchUsername(String inputUsername) {
		boolean match = false;
		ResultSet rs = null;

		String sql = "SELECT * FROM MANAGER WHERE USERNAME = ?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, inputUsername);

			rs = ps.executeQuery();

			if (rs.next()) {
				match = true;

			} else {
				match = false;
			}

		} catch (SQLException | IOException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}

		return match;
	}

	@Override
	public boolean isMatchPassword(String inputUsername, String inputPwd) {
		boolean match = false;
		ResultSet rs = null;

		String sql = "SELECT * FROM MANAGER WHERE PWD = ?" + " AND USERNAME = ?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, inputPwd);
			ps.setString(2, inputUsername);

			rs = ps.executeQuery();

			while (rs.next()) {
				if ((inputPwd.equals(rs.getString("PWD")) && inputUsername.equals(rs.getString("USERNAME")))) {
					match = true;
				} else {
					match = false;
				}
			}
		} catch (SQLException | IOException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}

		return match;
	}

}


