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

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employeeList = new ArrayList<>();
		
		String sql = "SELECT * FROM EMPLOYEE";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			while (rs.next()) {
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
				e.setHiredate(hireDate);
				
				String position = rs.getString("POSITION");
				e.setPosition(position);
				
				int managerId = rs.getInt("MANAGER_ID");
				e.setManagerId(managerId);
				
				int departmentId = rs.getInt("DEPT_ID");
				// call to the DepartmentDao's getDepartmentById method
				DepartmentDao ddi = new DepartmentDaoImpl();
				Department d = ddi.getDepartmentById(con, departmentId);
				e.setDepartment(d);
				
				int locationId = rs.getInt("LOCATION_ID");
				// call to the LocationDao's getLocationById method
				LocationDao ldi = new LocationDaoImpl();
				Location l = ldi.getLocationById(locationId, con);
				e.setLocation(l);
				employeeList.add(e);
			}
		} catch (IOException e) {
			System.out.println("The file or something doesn't exist!!!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL Exceptionnnnn");
			e.printStackTrace();
		}
		
		return employeeList;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee e = null;
		
		String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
		ResultSet rs = null;
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int empId = rs.getInt("EMP_ID");
				String name = rs.getString("EMP_NAME");
				Date birthdate = rs.getDate("BIRTHDAY");
				float monthlySalary = rs.getFloat("MONTHLY_SALARY");
				Date hiredate = rs.getDate("HIRE_DATE");
				String position = rs.getString("POSITION");
				int managerId = rs.getInt("MANAGER_ID");
				int departmentId = rs.getInt("DEPT_ID");
				int locationId = rs.getInt("LOCATION_ID");
				
				DepartmentDao dd = new DepartmentDaoImpl();
				Department d = dd.getDepartmentById(departmentId);
				
				LocationDao ld = new LocationDaoImpl();
				Location l = ld.getLocationById(locationId);
				
				e = new Employee(empId, name, birthdate, monthlySalary, hiredate, position, managerId, d, l);
			}
			
		} catch (SQLException e2) {
			System.out.println("getEmployeeById SQL Exception blehhh");
			e2.printStackTrace();
		} catch (IOException e2) {
			System.out.println("getEmployeeById IO Exception");
			e2.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		return e;
	}
	
	@Override
	public Employee getEmployeeById(int id, Connection con) {
		Employee e = null;
		
		String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
		ResultSet rs = null;
		
		try(PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int empId = rs.getInt("EMP_ID");
				String name = rs.getString("EMP_NAME");
				Date birthdate = rs.getDate("BIRTHDAY");
				float monthlySalary = rs.getFloat("MONTHLY_SALARY");
				Date hiredate = rs.getDate("HIRE_DATE");
				String position = rs.getString("POSITION");
				int managerId = rs.getInt("MANAGER_ID");
				int departmentId = rs.getInt("DEPT_ID");
				int locationId = rs.getInt("LOCATION_ID");
				
				DepartmentDao dd = new DepartmentDaoImpl();
				Department d = dd.getDepartmentById(departmentId);
				
				LocationDao ld = new LocationDaoImpl();
				Location l = ld.getLocationById(locationId);
				e = new Employee(empId, name, birthdate, monthlySalary, hiredate, position, managerId, d, l);
			}
			
		} catch (SQLException e2) {
			System.out.println("gross, con getEmployeeById SQL exception");
			e2.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		return e;
	}

	@Override
	public int createEmployee(Employee employee) {
		int employeesCreated = 0;
		String sql = "INSERT INTO EMPLOYEE (EMP_NAME, MONTHLY_SALARY, DEPT_ID, LOCATION_ID) VALUES (?,?,?,?)";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, employee.getName());
			ps.setFloat(2,  employee.getMonthlySalary());
			ps.setInt(3, employee.getDepartment().getId());
			ps.setInt(4, employee.getLocation().getId());
			employeesCreated = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeesCreated;
	}

	@Override
	public int updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return 0;
	}
}
