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
import com.revature.model.Department;
import com.revature.util.ConnectionUtil;

public class DepartmentDaoImpl implements DepartmentDao{

	@Override
	public List<Department> getDepartments() {
		// TODO Auto-generated method stub
		List<Department> departmentList = new ArrayList<>();
		String sql="SELECT * FROM DEPARTMENT";
		

		try (
				Connection con =ConnectionUtil.getConnection();
				Statement s=con.createStatement(); 
				//statement here is okay because there is not user input
				ResultSet rs=s.executeQuery(sql) ;	
				//get our result set from the query
				)
				{					
					while( rs.next() )
					{
					Department d = new Department();
					int deptId= rs.getInt("DEPT_ID");
					d.setId(deptId);
					
					String deptName=rs.getString("DEPT_NAME");
					d.setName(deptName);
			
					int monthlyBudget=rs.getInt("MONTHLY_BUDGET");
					d.setMonthlyBudget(monthlyBudget);
						
					departmentList.add(d);
				}
			} catch (IOException | SQLException d) {
				// TODO Auto-generated catch block
				d.printStackTrace();
			}

		
		return departmentList;
	}

	@Override
	public Department getDepartmentById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createDepartment(Department department) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO DEPARTMENTS (DEPT_NAME, MONTHLY_BUDGET) VALUES (? ,?)";
		int departmentsCreated=0;
		
		try (Connection con=ConnectionUtil.getConnection();
				PreparedStatement ps=con.prepareStatement(sql);
				
				){
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

		// TODO Auto-generated method stub=
		
		int departmentsUpdated=0;
		
		
		String sql = "UPDATE DEPARTMENT"+
				" SET DEPT_NAME = ?"+
				" MONTHLY_BUDGET"+
				" WHERE DEPT_ID = ?";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql) ){
			
			con.setAutoCommit(false);
			ps.setString(1, department.getName());
			ps.setFloat(2, department.getMonthlyBudget());
			ps.setFloat(3, department.getId());
			departmentsUpdated=1;
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		return 0;
	}

	@Override
	public int deleteDepartmentById(int id) {
		// TODO Auto-generated method stub
		
		String sql="DELETE FROM DEPARTMENT WHERE DEPT_ID = ? ";
		int rowsDeleted=0;
		
		try(Connection con= ConnectionUtil.getConnection();
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setInt(1, id))
		{
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	public void increaseBudget(int deptId, int incearseAmount)
	{
		String sql="{call INCREASE_BUDGET(?,?)}";
		
		try ( Connection con = ConnectionUtil.getConnection();
				CallableStatement cs=con.prepareCall(sql)){
				
				cs.setInt(1, deptId);
				
				
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
				
				
	
	}
	
}
