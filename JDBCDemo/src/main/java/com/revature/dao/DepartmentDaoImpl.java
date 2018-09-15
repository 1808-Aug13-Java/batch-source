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

import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.model.Location;
import com.revature.util.connectionUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public List<Department> getDepartments() {
	List<Department> departmentList = new ArrayList<Department>();
		
		String sql = "SELECT * FROM DEPARTMENT";
		
		try (
			Connection con = connectionUtil.getConnection();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql)){
			while(rs.next()) {
				String name = rs.getString("DEPT_NAME");

				int id = rs.getInt("DEPT_ID");
				
				int budget = rs.getInt("MONTHLY_BUDGET");

				Department d = new Department(id, name, budget);
				
				departmentList.add(d);
			}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return departmentList;
	}

	@Override
	public Employee getEmployeeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createDepartment(Department department) {
		
		String sql = "INSERT INTO DEPARTMENT (DEPT_NAME, MONTHLY_BUDGET) VALUES (?,?)";
		int departmentsCreated = 0;
		try(Connection con = connectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
		
			ps.setString(1, department.getName());
			ps.setFloat(2, department.getMonthlyBudget());
			departmentsCreated = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return departmentsCreated;
	}

	@Override
	public int updateDepartment(Department department) {
		int departmentsUpdated = 0;
		
		String sql = "UPDATE DEPARTMENT " +
		"SET DEPT_NAME = ?," + "MONTHLY_BUDGET = ?"
	 + "WHERE DEPT_ID = ?";
		
		try(Connection con = connectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			con.setAutoCommit(false);
			ps.setString(1, department.getName());
			ps.setFloat(2, department.getMonthlyBudget());
			ps.setInt(3, department.getId());
			departmentsUpdated = ps.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return departmentsUpdated;
	}

	@Override
	public int deletebDepartmentById(int id) {
		int rowsDeleted = 0;
		
		String sql = "DELETE FROM DEPARTMENT WHERE DEPT_ID = ?";
		
		try(Connection con = connectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			rowsDeleted = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowsDeleted;
	}

	@Override
	public Department getDepartmentById(int id) {
		Department d = null;
		String sql = "SELECT * FROM DEPARTMENT WHERE DEPT_ID = ?";
		
		try {
			Connection con = connectionUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int deptId = rs.getInt("DEPT_ID");
				String name = rs.getString("DEPT_NAME");
				float budget = rs.getFloat("MONTHLY_BUDGET");
				d = new Department(deptId, name, budget);
			}
			
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return d;
	}

	@Override
	public void increaseBudget(int deptId, int increaseAmount) {
		String sql = "{call INCREASE_BUDGET(?,?)}";
		
		try(Connection con = connectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){

		cs.setInt(1, deptId);
		cs.setFloat(2, increaseAmount);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
