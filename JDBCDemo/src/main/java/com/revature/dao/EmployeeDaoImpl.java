package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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

public class EmployeeDaoImpl implements EmployeeDao {

	private static Logger log = Logger.getRootLogger();
	
	@Override
	public List<Employee> getEmployees() {
		List<Employee> employeeList = new ArrayList<>();
		
		String sql = "SELECT * FROM EMPLOYEE";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
		
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
				// call to the DepartmentDao's getDepartmentById method
				DepartmentDao ddi = new DepartmentDaoImpl();
				Department d = ddi.getDepartmentById(departmentId, con);
				e.setDepartment(d);
				
				int locationId = rs.getInt("LOCATION_ID");
				// call to the LocationDao's getLocationById method
				LocationDao ldi = new LocationDaoImpl();
				Location l = ldi.getLocationById(locationId, con);
				e.setLocation(l);
				
				employeeList.add(e);
			}
			
		} catch (IOException|SQLException e) {
			log.error(e.getMessage());
		} 
		
		return employeeList;
	}

	@Override
	public Employee getEmployeeById(int id) {
		return null;
	}

	@Override
	public int createEmployee(Employee employee) {
		return 0;
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
