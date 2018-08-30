package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.model.Department;
import com.revature.util.ConnectionUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public List<Department> getDepartments() {
		// TODO Auto-generated method stub
		return null;
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs!=null) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs!=null) {
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

	@Override
	public int createDepartment(Department department) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDepartment(Department department) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDepartmentById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
