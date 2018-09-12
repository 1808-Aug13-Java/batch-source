package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao{

	@Override
	public boolean validateUser(String username, String password) {
		String sql = "SELECT * FROM ERS_EMPLOYEE WHERE ERS_EMP_USERNAME = ? AND ERS_EMP_PASS = ?";
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

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM ERS_EMPLOYEE";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			List<Employee> results = new ArrayList<>();
			while(rs.next()) {
				int userID = rs.getInt("ERS_EMP_ID");
				String eName = rs.getString("ERS_EMP_NAME");
				String eUname = rs.getString("ERS_EMP_USERNAME");
				String ePass = rs.getString("ERS_EMP_PASS");
				String email = rs.getString("ERS_EMP_EMAIL");
				Employee employee = new Employee(userID,eName, eUname, ePass, email);
				results.add(employee);
			}
		return results;
	} catch (SQLException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
		
	}

	@Override
	public boolean updateEmployee(int empId, String name, String username, String password, String email) {
		// TODO Auto-generated method stub
		String sql = "UPDATE ERS_EMPLOYEE SET ERS_EMP_NAME = ?, ERS_EMP_USERNAME = ?, ERS_EMP_PASS = ?,ERS_EMP_EMAIL = ? WHERE ERS_EMP_ID = ? ";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, name);
			ps.setString(2, username);
			ps.setString(3, password);
			ps.setString(4, email);
			ps.setInt(5, empId);
			
			ResultSet rs = ps.executeQuery();
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

}
