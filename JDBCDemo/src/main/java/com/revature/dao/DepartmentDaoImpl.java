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
import com.revature.util.ConnectionUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	
	public List<Department> getDepartments() {
		List<Department> departments = new ArrayList<>();
		String sql = "SELECT * FROM DEPARTMENT";
		
		try(Connection con = ConnectionUtil.getConnection();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
				) {
			Department department = null;
			while(rs.next()) {
				int id = rs.getInt("dept_id");
				String name = rs.getString("dept_name");
				float monthlyBudget = rs.getFloat("monthly_budget");
				
				department = new Department(id, name, monthlyBudget);
				
				departments.add(department);
			}
			
		} catch (SQLException|IOException e) {
			e.printStackTrace();
		} 
		
		
		return departments;
	}

	// possibly overload
	public Department getDepartmentById(int id) {
		Department d = new Department();
		String sql = "SELECT * FROM DEPARTMENT WHERE DEPT_ID = ?";
		ResultSet rs = null;
		try(
				Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int deptId = rs.getInt("DEPT_ID");
				String name = rs.getString("DEPT_NAME");
				float budget = rs.getFloat("monthly_budget");
				
				d.setId(deptId);
				d.setName(name);
				d.setMonthlyBudget(budget);
			}
			
		} catch (SQLException|IOException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		return d;
	}
	
	public Department getDepartmentById(int id, Connection con) {
		Department d = new Department();
		String sql = "SELECT * FROM DEPARTMENT WHERE DEPT_ID = ?";
		ResultSet rs = null;
		
		try(
				PreparedStatement ps = con.prepareStatement(sql);
				) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int deptId = rs.getInt("DEPT_ID");
				String name = rs.getString("DEPT_NAME");
				float budget = rs.getFloat("monthly_budget");
				
				d.setId(deptId);
				d.setName(name);
				d.setMonthlyBudget(budget);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		return d;
	}
	

	
	public int createDepartment(Department department) {
		
		int departmentsCreated = 0;
		String sql = "INSERT INTO DEPARTMENT "
				+ "(DEPT_NAME, MONTHLY_BUDGET) VALUES(?,?)";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
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

	
	public int updateDepartment(Department department) {
		int departmentsUpdated = 0;
		
		String sql = "UPDATE DEPARTMENT"
				+ " SET DEPT_NAME = ?," 
				+ " MONTHlY_BUDGET = ?"
				+ "WHERE DEPT_ID = ?";
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
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

	
	public int deleteDepartmentById(int id) {
		int departmentsDeleted = 0;
		
		String sql = "DELETE FROM DEPARTMENT WHERE DEPT_ID = ?";
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			departmentsDeleted = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return departmentsDeleted;
	}

	@Override
	public void increaseBudget(int deptId, float amount) {
		
		String sql = "{call: INCREASE_BUDGET(?,?)}";
		
		try(Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql);) {
			cs.setInt(1, deptId);
			cs.setFloat(2, amount);
			// cs returns boolean
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				
	}

	

}
