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

import com.revature.model.Department;
import com.revature.util.ConnectionUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	private static Logger log = Logger.getRootLogger();
	
	@Override
	public List<Department> getDepartments() {
		List<Department> departments = new ArrayList<>();
		
		String sql = "SELECT * FROM DEPARTMENT";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while (rs.next()) {
				int deptId = rs.getInt("DEPT_ID");
				String name = rs.getString("DEPT_NAME");
				float budget = rs.getFloat("MONTHLY_BUDGET");
				departments.add(new Department(deptId, name, budget));
			}
			
			
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		
		return departments;
	}

	@Override
	public Department getDepartmentById(int id) {
		Department d = null;
		String sql = "SELECT * FROM DEPARTMENT WHERE DEPT_ID = ?";

		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				int deptId = rs.getInt("DEPT_ID");
				String name = rs.getString("DEPT_NAME");
				float budget = rs.getFloat("MONTHLY_BUDGET");
				d = new Department(deptId, name, budget);
			}
		} catch (IOException|SQLException e) {
			log.error(e.getMessage());
		} finally {
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
			}
		}

		return d;
	}

	public Department getDepartmentById(int id, Connection con) {
		Department d = null;
		String sql = "SELECT * FROM DEPARTMENT WHERE DEPT_ID = ?";

		ResultSet rs = null;

		try (PreparedStatement ps = con.prepareStatement(sql);){

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				int deptId = rs.getInt("DEPT_ID");
				String name = rs.getString("DEPT_NAME");
				float budget = rs.getFloat("MONTHLY_BUDGET");
				d = new Department(deptId, name, budget);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
			}
		}

		return d;
	}

	@Override
	public int createDepartment(Department department) {
		int departmentsCreated = 0;
		String sql = "INSERT INTO DEPARTMENT (DEPT_NAME, MONTHLY_BUDGET) VALUES (?,?)";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, department.getName());
			ps.setFloat(2, department.getMonthlyBudget());
			departmentsCreated = ps.executeUpdate();
			
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		return departmentsCreated;
	}

	@Override
	public int updateDepartment(Department department) {
		int departmentsUpdated = 0;
		
		String sql = "UPDATE DEPARTMENT "
				+ "SET DEPT_NAME = ?,"
				+ "MONTHLY_BUDGET = ?"
				+ "WHERE DEPT_ID = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			con.setAutoCommit(false);
			ps.setString(1, department.getName());
			ps.setFloat(2, department.getMonthlyBudget());
			ps.setInt(3, department.getId());
			departmentsUpdated = ps.executeUpdate();
			con.commit();
			
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		
		return departmentsUpdated;
	}

	@Override
	public int deleteDepartmentById(int id) {
		int rowsDeleted = 0;
		
		String sql = "DELETE FROM DEPARTMENT WHERE DEPT_ID = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, id);
			rowsDeleted = ps.executeUpdate();
			
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		}
		
		return rowsDeleted;
	}

	@Override
	public void increaseBudget(int deptId, float increaseAmount) {
		
		String sql = "{call INCREASE_BUDGET(?,?)}";
		
		try( Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){
			
			cs.setInt(1, deptId);
			cs.setFloat(2, increaseAmount);
			cs.execute();
			log.info("raise given");
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
	}

}
