package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	private static Logger log = Logger.getRootLogger();

	public List<Employee> getEmployees() {
		List<Employee> employeeList = new ArrayList<>();
		String sql = "SELECT * FROM P1EMPLOYEE";

		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)) {

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

		} catch (IOException | SQLException e) {
			log.error(e.getMessage());
		}
		return employeeList;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee e = new Employee();
		String sql = "SELECT * FROM P1EMPLOYEE WHERE EMP_ID = ?";
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {

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
		return e;
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		Employee e = new Employee();
		String sql = "SELECT * FROM P1EMPLOYEE WHERE USERNAME = ?";
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, username);

			rs = ps.executeQuery();

			while (rs.next()) {

				int employeeId = rs.getInt("EMP_ID");
				e.setId(employeeId);

				String fname = rs.getString("F_NAME");
				e.setFname(fname);
				
				String lname = rs.getString("L_NAME");
				e.setLname(lname);
				
				String usename = rs.getString("USERNAME");
				e.setUsername(usename);

				String pwd = rs.getString("PWD");
				e.setPwd(pwd);

				int managerId = rs.getInt("MANAGER_ID");
				e.setManagerId(managerId);
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
		return e;
	}

	public int createEmployee(Employee employee) {
		int empCreated = 0;

		String sql = "{call INSERT_NEW_EMP(?, ?, ?, ?, ?)}";

		try (Connection con = ConnectionUtil.getConnection(); CallableStatement cs = con.prepareCall(sql);) {

			cs.setString(1, employee.getFname());
			cs.setString(2, employee.getLname());
			cs.setString(3, employee.getUsername());
			cs.setString(4, employee.getPwd());
			cs.setInt(5, employee.getManagerId());
			cs.execute();

		} catch (SQLException | IOException e) {
			log.error(e.getMessage());
		}
		return empCreated;
	}

	public int updateEmployee(Employee employee) {
		int empUpdated = 0;

		String sql = "UPDATE P1EMPLOYEE" 
				+ " SET F_NAME = ?,"
				+ " L_NAME = ?,"
				+ " USERNAME = ?," 
				+ " PWD = ?" 
				+ " WHERE EMP_ID = ?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, employee.getFname());
			ps.setString(2, employee.getLname());
			ps.setString(3, employee.getUsername());
			ps.setString(4, employee.getPwd());
			ps.setInt(5, employee.getId());

			empUpdated = ps.executeUpdate();

		} catch (SQLException | IOException e) {
			log.error(e.getMessage());
		}

		return empUpdated;
	}

	public int deleteEmployeeById(int id) {

		return 0;
	}

	@Override
	public boolean isMatchUsername(String inputUsername) {
		boolean match = false;
		ResultSet rs = null;

		String sql = "SELECT * FROM P1EMPLOYEE WHERE USERNAME = ?";

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

		String sql = "SELECT * FROM P1EMPLOYEE WHERE PWD = ?" + " AND USERNAME = ?";

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
