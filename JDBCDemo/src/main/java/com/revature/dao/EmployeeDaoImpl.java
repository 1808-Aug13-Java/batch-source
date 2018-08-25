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

import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.model.Location;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	public EmployeeDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	
	public List<Employee> getEmployees() {
		List<Employee> employeeList = new ArrayList<Employee>();
		ResultSet rs = null;
		String sql = "SELECT * FROM EMPLOYEE";
		
		try (
				Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				
				){
			rs = s.executeQuery(sql);
			while(rs.next()) {
				Employee e = new Employee();
				
				int employeeId = rs.getInt("EMP_ID");
				e.setId(employeeId);
				
				String name = rs.getString("EMP_NAME");
				e.setName(name);
				
				Date birthday = rs.getDate("BIRTHDAY");
				e.setBirthday(birthday);
				
				float monthlySalary = rs.getFloat("MONTHLY_SALARY");
				e.setMonthlySalary(monthlySalary);
				
				Date hireDate = rs.getDate("HIRE_DATE");
				e.setHireDate(hireDate);
				
				String position = rs.getString("POSITION");
				e.setPosition(position);
				
				int managerId = rs.getInt("MANAGER_ID");
				e.setManagerId(managerId);
				
				int departmentId = rs.getInt("DEPT_ID");
				// call to the dept dao getdepartmentbyid method
				DepartmentDao ddi = new DepartmentDaoImpl();
				Department d = ddi.getDepartmentById(departmentId, con);
				e.setDepartment(d);
				//e.setDepartment(department);
				
				
				// call to the location dao getlocationbyid
				int locationId = rs.getInt("LOCATION_ID");
//				e.setlocation(locationId);
				LocationDao ldi = new LocationDaoImpl();
				Location l = ldi.getLocationById(locationId, con);
				e.setlocation(l);
				employeeList.add(e);
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return employeeList;
	}

	
	public Employee getEmployeeById(int id) {
		Employee e = null;
		String sql = "SELECT * FROM EMPLOYEE where ?";
		ResultSet rs = null;
		try(Connection con = ConnectionUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
				) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			int employeeId;
			String name;
			Date birthday;
			float monthlySalary;
			Date hireDate;
			String position;
			int managerId;
			Department department;
			Location location;
			while(rs.next()) {
				employeeId = rs.getInt("EMP_ID");
				
				name = rs.getString("EMP_NAME");
				birthday = rs.getDate("BIRTHDAY");
				monthlySalary = rs.getFloat("MONTHLY_SALARY");
				hireDate = rs.getDate("HIRE_DATE");
				position = rs.getString("POSITION");
				managerId = rs.getInt("MANAGER_ID");
				int departmentId = rs.getInt("DEPT_ID");
				DepartmentDao ddi = new DepartmentDaoImpl();
				department = ddi.getDepartmentById(departmentId, con);
				int locationId = rs.getInt("LOCATION_ID");
				LocationDao ldi = new LocationDaoImpl();
				location = ldi.getLocationById(locationId, con);
				e = new Employee(employeeId, name, birthday, monthlySalary, hireDate, position, managerId, department, location);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		return e;		
	}

	
	public int createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int deleteEmployeeById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
