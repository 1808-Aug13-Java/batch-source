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

import org.apache.log4j.Logger;

import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.model.Location;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao{
	private static Logger log = Logger.getRootLogger();

	@Override
	public Employee getEmployeeById(int id) {
		Employee e = new Employee();
		String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
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
				// call to the DepartmentDao's getDepartmentById method
				DepartmentDao ddi = new DepartmentDaoImpl();
				Department d = ddi.getDepartmentById(departmentId, con);
				e.setDepartment(d);
				
				int locationId = rs.getInt("LOCATION_ID");
				// call to the LocationDao's getLocationById method
				LocationDao ldi = new LocationDaoImpl();
				Location l = ldi.getLocationById(locationId, con);
				e.setLocation(l);
			}
			
		} catch (IOException|SQLException ex) {
			log.error(ex.getMessage());
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
				
				int departmentId = rs.getInt("DEPT_ID");
				DepartmentDao ddi = new DepartmentDaoImpl();
				Department d = ddi.getDepartmentById(departmentId, con);
				e.setDepartment(d);
				
				int locationId=rs.getInt("LOC_ID");
//				e.setLocationId(locationId);
				LocationDao ldi = new LocationDaoImpl();
				Location l = ldi.getLocationById(locationId, con);
				e.setLocation(l);
				
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
		int employeeCreated=0;
		String sql="INSERT INTO EMPLOYEE (EMP_NAME,MONTHLY_SALARY,DEPT_ID,LOC_ID) VALUES (?,?,?,?)";
		try(
				Connection con= ConnectionUtil.getConnection();
				PreparedStatement ps=con.prepareStatement(sql);){
				ps.setString(1, employee.getName());
				ps.setDouble(2, employee.getMonthlySalary());
				ps.setInt(3, employee.getDepartment().getId());
				ps.setInt(4, employee.getLocation().getId());
				employeeCreated=ps.executeUpdate();
				
				
				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return employeeCreated;
		
	}

	@Override
	public int updateEmployee(Employee employee) {
		return 0;
	}

	@Override
	public int deleteEmployeeById(int id) {
		return 0;
	}

}
