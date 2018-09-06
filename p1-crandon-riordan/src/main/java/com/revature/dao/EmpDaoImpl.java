package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class EmpDaoImpl implements EmployeeDao {
	Logger log = Logger.getLogger(EmpDaoImpl.class);
	
	public EmpDaoImpl() {
		super();
	}

	@Override
	public int createEmployee(Employee employee) {
		int createdEmp = 0;
		String sql = "INSERT INTO EMPLOYEE (NAME, USERNAME, EMAIL, "
				+ "PASSWORD, MANAGER_ID, IS_MANAGER) VALUES (?,?,?,?,?,?)";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getUsername());
			ps.setString(3, employee.getEmail());
			ps.setString(4, employee.getPassword());
			ps.setInt(5, employee.getManagerId());
			ps.setInt(6, employee.getIsManager());
			
			createdEmp = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			log.info(e.getMessage());
		}
		
		return createdEmp;
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		Employee emp = new Employee();
		String sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = ?";
		ResultSet rs = null;
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, username);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			String name = rsmd.getColumnName(1);
			System.out.println(name);
			System.out.println(rsmd.getColumnName(2));
			while(rs.next()) {
				emp.setId(rs.getInt("EMPLOYEE_ID"));
				emp.setEmail(rs.getString("EMAIL"));
				emp.setUsername(rs.getString("USERNAME"));
				emp.setName(rs.getString("NAME"));
				emp.setPassword(rs.getString("PASSWORD"));
				emp.setManagerId(rs.getInt("MANAGER_ID"));
				emp.setIsManager(rs.getInt("IS_MANAGER"));
			}
			
			return emp;
		} catch (SQLException | IOException e) {
			log.info(e.getMessage());
		} finally {
			if( rs != null ) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.info(e.getMessage());
				}
			}
		}
		
		return emp;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee emp = new Employee();
		String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
		ResultSet rs = null;
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				emp.setId(rs.getInt("EMPLOYEE_ID"));
				emp.setEmail(rs.getString("EMAIL"));
				emp.setName(rs.getString("NAME"));
				emp.setUsername(rs.getString("USERNAME"));
				emp.setPassword(rs.getString("PASSWORD"));
				emp.setManagerId(rs.getInt("MANAGER_ID"));
				emp.setIsManager(rs.getInt("IS_MANAGER"));
			}
		} catch (SQLException | IOException e) {
			log.info(e.getMessage());
		} finally {
			if( rs != null ) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.info(e.getMessage());
				}
			}
		}
		
		return emp;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		String sql = "SELECT * FROM EMPLOYEE";
		ResultSet rs = null;
		try(Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement()) {
			rs = s.executeQuery(sql);
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("EMPLOYEE_ID"));
				emp.setEmail(rs.getString("EMAIL"));
				emp.setName(rs.getString("NAME"));
				emp.setUsername(rs.getString("USERNAME"));
				emp.setPassword(rs.getString("PASSWORD"));
				emp.setManagerId(rs.getInt("MANAGER_ID"));
				emp.setIsManager(rs.getInt("IS_MANAGER"));
				employees.add(emp);
			}
		} catch (SQLException | IOException e) {
			log.info(e.getMessage());
		} finally {
			if( rs != null ) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.info(e.getMessage());
				}
			}
		}
		
		return employees;
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		Employee emp = new Employee();
		String sql = "SELECT * FROM EMPLOYEE WHERE EMAIL = ?";
		ResultSet rs = null;
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, email);
			rs = ps.executeQuery();
			while(rs.next()) {
				emp.setId(rs.getInt("EMPLOYEE_ID"));
				emp.setEmail(rs.getString("EMAIL"));
				emp.setName(rs.getString("NAME"));
				emp.setUsername(rs.getString("USERNAME"));
				emp.setPassword(rs.getString("PASSWORD"));
				emp.setManagerId(rs.getInt("MANAGER_ID"));
				emp.setIsManager(rs.getInt("IS_MANAGER"));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			log.info(e.getMessage());
		} finally {
			if( rs != null ) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.info(e.getMessage());
				}
			}
		}
		
		return emp;
	}

}
