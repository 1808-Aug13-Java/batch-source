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
import com.revature.util.ProjectOneConnection;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public List<Employee> getEmployees() {
	List<Employee> employeeList = new ArrayList<>();
		
		String sql = "SELECT * FROM EMPLOYEE";
		
		try(Connection con = ProjectOneConnection.getConnection(); 
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql)){
		
			while(rs.next())
			{
				Employee e = new Employee();
				
				int employeeId = rs.getInt("EMP_ID");
				e.setId(employeeId);
				
				String name = rs.getString("EMP_NAME");
				e.setName(name);
				
				String isManager = rs.getString("ISMANAGER");
				e.setIsManager(isManager);
				
				String email = rs.getString("EMAIL");
				e.setEmail(email);
				
				String username = rs.getString("USERNAME");
				e.setUsername(username);
				
				String privateinfo = rs.getString("PRIVATEINFO");
				e.setPrivateInfo(privateinfo);
				
				Date startDate = rs.getDate("STARTDATE");
				e.setStartDate(startDate);
				
				float monthlySalary = rs.getFloat("MONTHLY_SALARY");
				e.setMonthlySalary(monthlySalary);
				
				employeeList.add(e);
			}
		}
		catch(IOException | SQLException e){
			e.printStackTrace();;
		};
		
		return employeeList;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee emp = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
		try(Connection con = ProjectOneConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				String name = rs.getString("EMP_NAME");
				String email = rs.getString("EMAIL");
				String username = rs.getString("USERNAME");
				String privateinfo = rs.getString("PRIVATEINFO");
				Date startDate = rs.getDate("STARTDATE");
				float monthlySalary = rs.getFloat("MONTHLY_SALARY");
				String isManager = rs.getString("ISMANAGER");
				emp = new Employee(id, name, email, username, 
						privateinfo, startDate, monthlySalary, isManager);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emp;
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		Employee emp = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM EMPLOYEE WHERE EMAIL = ?";
		try(Connection con = ProjectOneConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				int id = rs.getInt("EMP_ID");
				String name = rs.getString("EMP_NAME");
				String username = rs.getString("USERNAME");
				String privateinfo = rs.getString("PRIVATEINFO");
				Date startDate = rs.getDate("STARTDATE");
				float monthlySalary = rs.getFloat("MONTHLY_SALARY");
				String isManager = rs.getString("ISMANAGER");
				emp = new Employee(id, name, email, username, 
						privateinfo, startDate, monthlySalary, isManager);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emp;
	}

	@Override
	public int createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployeeById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateEmployee(int id, String name, String user, String privateInfo) {
		String sql = "UPDATE EMPLOYEE SET "
				+ "EMP_NAME = ?, USERNAME = ?, PRIVATEINFO = ? "
				+ "WHERE EMP_ID = ?";
		try(Connection con = ProjectOneConnection.getConnection();
				PreparedStatement ps =  con.prepareStatement(sql)){
			
			con.setAutoCommit(false);
			ps.setString(1, name);
			ps.setString(2, user);
			ps.setString(3, privateInfo);
			ps.setInt(4, id);
			ps.executeQuery();
			con.commit();
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return 1;
	}

}
