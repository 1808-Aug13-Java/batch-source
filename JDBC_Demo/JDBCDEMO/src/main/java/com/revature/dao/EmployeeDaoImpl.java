package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Location;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao{

	@Override
	public Employee getEmployeeById(int id) {
		// TODO Auto-generated method stub
	
Employee e = null;
		
		String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
		ResultSet rs = null;
		
		try(Connection con = ConnectionUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {	
				 String name= rs.getString("EMP_NAME");
				 Date birthday= rs.getDate("BIRTHDAY");
				 float monthlySalary= rs.getInt("MONTHLY_SALARY");
				 Date hireDate= rs.getDate("HIRE_DATE");
				 String position= rs.getString("POSITION");
				 int managerId= rs.getInt("MANAGER_ID");
				 int deptId= rs.getInt("DEPT_ID");
				 int locationId= rs.getInt("LOC_ID");
				
				e = new Employee(id, name, birthday,monthlySalary,hireDate,
						position,managerId,deptId,locationId);
			}
			
		} catch (SQLException a) {
			// TODO Auto-generated catch block
			a.printStackTrace();
		} catch (IOException a) {
			// TODO Auto-generated catch block
			a.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException a) {
					// TODO Auto-generated catch block
					a.printStackTrace();
				}
			}
		}
		
		return e;
	}
	
	
	

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub 
		List<Employee> employeelist = new ArrayList<>();
		String sql="SELECT * FROM EMPLOYEE";
		
		
		try (
			Connection con =ConnectionUtil.getConnection();
			Statement s=con.createStatement(); 
			ResultSet rs =s.executeQuery(sql) ;		
			)
			{
				
				while( rs.next() )
				{
			
				Employee e = new Employee();
				int employeeId= rs.getInt("EMP_ID");
				e.setId(employeeId);
				
				String name=rs.getString("EMP_NAME");
				e.setName(name);
				
				Date birthday=rs.getDate("BIRTHDAY");
				e.setBirthday(birthday);
				
				float monthlySalary=rs.getFloat("MONTHLY_SALARY");
				e.setMonthlySalary(monthlySalary);
				
				Date hireDate=rs.getDate("HIRE_DATE");
				e.setHireDate(hireDate);
				
				String position=rs.getString("POSITION");
				e.setPosition(position);
				
				int managerId=rs.getInt("MANAGER_ID");
				e.setManagerId(managerId);
				
				int departmentId=rs.getInt("DEPT_ID");
				e.setDeptId(departmentId);
				
				int locationId=rs.getInt("LOC_ID");
				e.setLocationId(locationId);
				
				employeelist.add(e);
			}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employeelist;
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

}
