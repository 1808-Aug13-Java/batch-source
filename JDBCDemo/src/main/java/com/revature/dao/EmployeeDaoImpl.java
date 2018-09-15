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
import com.revature.util.connectionUtil;

public class EmployeeDaoImpl implements EmployeeDao{

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employeeList = new ArrayList();
		
		String sql = "SELECT * FROM EMPLOYEE";
		
		try (
			Connection con = connectionUtil.getConnection();
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
				
				float monthlySalary = rs.getShort("MONTHLY_SALARY");
				e.setMonthlySalary(monthlySalary);
				
				Date hireDate = rs.getDate("HIRE_DATE");
				e.setHireDate(hireDate);

				String position = rs.getString("POSITION");
				e.setPosition(position);
				
				int managerId = rs.getInt("MANAGER_ID");
				e.setManagerId(managerId);
				
				int departmentId = rs.getInt("DEPT_ID");
				e.setDepartmentId(departmentId);
				
				int locationId = rs.getInt("LOCATION_ID");
				e.setLocationId(locationId);
				
				employeeList.add(e);
			}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employeeList;
	}

	@Override
	public Employee getEmployeebyId(int id) {	
		Employee e = null;

		

		String sql = "SELECT * FROM LOCATIONS WHERE LOCATION_ID = ?";

		ResultSet rs = null;

		

		try(Connection con = connectionUtil.getConnection();

				PreparedStatement ps = con.prepareStatement(sql)){

			ps.setInt(1, id);

			rs = ps.executeQuery();

			

			while(rs.next()) {
				
				
				int employeeId = rs.getInt("EMP_ID");
				
				String name = rs.getString("EMP_NAME");
				
				Date birthday = rs.getDate("BIRTHDAY");
				
				float monthlySalary = rs.getShort("MONTHLY_SALARY");
				
				Date hireDate = rs.getDate("HIRE_DATE");

				String position = rs.getString("POSITION");
				
				int managerId = rs.getInt("MANAGER_ID");
				
				int departmentId = rs.getInt("DEPT_ID");
				
				int locationId = rs.getInt("LOCATION_ID");

				e = new Employee(id, name, birthday, monthlySalary, hireDate, position,
						managerId, departmentId, locationId);

			}

			

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} finally {

			if (rs != null) {

				try {

					rs.close();

				} catch (SQLException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}

			}

		}

		

		return e;
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
	public int deleteEmployeeById(Employee employee) {
		// TODO Auto-generated method stub
		return 0;
	}

}
