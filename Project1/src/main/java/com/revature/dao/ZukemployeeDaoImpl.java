package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Zukemployee;
import com.revature.util.ConnectionUtil;

public class ZukemployeeDaoImpl implements ZukemployeeDao {
	
	private static Logger log = Logger.getRootLogger();

	@Override
	public List<Zukemployee> getEmployees() {
		List<Zukemployee> employeeList = new ArrayList<>();

		String sql = "SELECT * FROM ZUKEMPLOYEE";

		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){

			while(rs.next()) {
				Zukemployee e = new Zukemployee();

				int employeeId = rs.getInt("EMP_ID");
				e.setId(employeeId);

				String name = rs.getString("EMP_NAME");
				e.setName(name);

				String position = rs.getString("EMP_POSITION");
				e.setPosition(position);

				String username = rs.getString("EMP_USERNAME");
				e.setName(username);
				
				String password = rs.getString("EMP_PASSWORD");
				e.setName(password);

				int managerId = rs.getInt("REPORTS_TO");
				e.setReportsTo(managerId);

				employeeList.add(e);
			}

		} catch (IOException|SQLException e) {
			log.error(e.getMessage());
		} 

		return employeeList;
	}

	@Override
	public Zukemployee getEmployeeById(int id) {
		Zukemployee e = new Zukemployee();
		String sql = "SELECT * FROM ZUKEMPLOYEE WHERE EMP_ID = ?";
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
		
			while(rs.next()) {
				int employeeId = rs.getInt("EMP_ID");
				e.setId(employeeId);

				String name = rs.getString("EMP_NAME");
				e.setName(name);

				String position = rs.getString("EMP_POSITION");
				e.setPosition(position);

				String username = rs.getString("EMP_USERNAME");
				e.setName(username);
				
				String password = rs.getString("EMP_PASSWORD");
				e.setName(password);

				int managerId = rs.getInt("REPORTS_TO");
				e.setReportsTo(managerId);
			}
			
		} catch (IOException|SQLException ex) {
			log.error(ex.getMessage());
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
				}
			}
		}
		return e;
	}

	@Override
	public int updateEmployee(Zukemployee employee) {
		int employeesUpdated = 0;
		
		String sql = "UPDATE ZUKEMPLOYEE "
				+ "SET DEPT_NAME = ?,"
				+ "EMP_USERNAME = ?"
				+ "WHERE EMP_ID = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			con.setAutoCommit(false);
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getUsername());
			ps.setInt(3, employee.getId());
			employeesUpdated = ps.executeUpdate();
			con.commit(); // CRUD operation should have commit
			
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		
		return employeesUpdated;
	}

}
