package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Employees;
import com.revature.util.ConnectionUtil;

public class EmployeesDaoImpl implements EmployeesDao {

	@Override
	public Employees getEmployeesById(int id) {
		
		Employees e = new Employees();
		String sql = "SELECT * FROM EMPLOYEES WHERE EMP_ID = ?";
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				e.setId(rs.getInt("EMP_ID"));
				e.setName(rs.getString("EMP_NAME"));
				e.setManagerId(rs.getInt("MANAGER_ID"));
				e.setPosition(rs.getString("POSITION"));
				e.setUsername(rs.getString("EMP_USERNAME"));
				e.setPassword(rs.getString("EMP_PASSWORD"));
				e.setIsManager(rs.getInt("IS_MANAGER"));
				
			}
			
		} catch (SQLException|IOException e1) {
			e1.printStackTrace();
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
	public Employees getEmployeesByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employees> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createEmployee(Employees employee) {
		int employeesCreated = 0;
		String sql = "INSERT INTO EMPLOYEES (EMP_NAME, MANAGER_ID, POSITION, EMP_USERNAME, EMP_PASSWORD, IS_MANAGER)  VALUES (?,?,?,?,?,?)";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, employee.getName());
			ps.setInt(2, employee.getManagerId());
			ps.setString(3, employee.getPosition());
			ps.setString(4, employee.getUsername());
			ps.setString(5, employee.getPassword());
			ps.setInt(6, employee.getIsManager());

			employeesCreated = ps.executeUpdate();

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employeesCreated;
	}

	@Override
	public Employees getEmployeeByUsername(String username) {
		Employees e = new Employees();
		String sql = "SELECT * FROM EMPLOYEES WHERE EMP_USERNAME = ?";
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				e.setId(rs.getInt("EMP_ID"));
				e.setName(rs.getString("EMP_NAME"));
				e.setManagerId(rs.getInt("MANAGER_ID"));
				e.setPosition(rs.getString("POSITION"));
				e.setUsername(rs.getString("EMP_USERNAME"));
				e.setPassword(rs.getString("EMP_PASSWORD"));
				e.setIsManager(rs.getInt("IS_MANAGER"));
			}
			
		} catch (SQLException|IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return e;
	}

}
