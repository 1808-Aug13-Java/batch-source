package com.revature.doa;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDoaImp implements EmployeeDOA {
//TODO remove reimb id from employee table. the reimb table points to employee
	
	@Override
	public List<Employee> getEmployees() {

		List<Employee> employeeList = new ArrayList<>();
		String sql = "SELECT * FROM EMPLOYEE";

		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)) {
			while (rs.next()) {
				Employee e = new Employee();
				e.setEmpId(rs.getInt("EMP_ID"));
				e.setfName(rs.getString("FNAME"));
				e.setlName(rs.getString("LNAME"));
				e.setManId(rs.getInt("MAN_ID"));
				e.setPswrd(rs.getString("PSWRD"));
				employeeList.add(e);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			// REPLACE WITH LOG4J
		}
		return employeeList;
	}

	@Override
	public Employee getEmployeeById(int id) {
		String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
		Employee e = new Employee();
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				e.setEmpId(rs.getInt("EMP_ID"));
				e.setfName(rs.getString("FNAME"));
				e.setlName(rs.getString("LNAME"));
				e.setManId(rs.getInt("MAN_ID"));
				e.setPswrd(rs.getString("PSWRD"));
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
		return e;
	}

	@Override
	public List<Employee> getEmployeesByManId(int manId) {
		
		List<Employee> employeeList = new ArrayList<>();
		String sql = "SELECT * FROM EMPLOYEE WHERE MAN_ID = ?";
		Employee e = new Employee();
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, manId);
			rs = ps.executeQuery();

			while (rs.next()) {
				e.setEmpId(rs.getInt("EMP_ID"));
				e.setfName(rs.getString("FNAME"));
				e.setlName(rs.getString("LNAME"));
				e.setManId(rs.getInt("MAN_ID"));
				e.setPswrd(rs.getString("PSWRD"));
				employeeList.add(e);
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
		return employeeList;
	}
	
	@Override
	public int createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployeeById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int createEmployee(Employee employee, Connection con) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateEmployee(Employee employee, Connection con) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployeeById(int id, Connection con) {
		// TODO Auto-generated method stub
		return 0;
	}

}
