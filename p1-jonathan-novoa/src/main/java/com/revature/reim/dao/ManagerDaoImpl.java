package com.revature.reim.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.reim.model.Employee;
import com.revature.reim.model.Reimbursement;
import com.revature.reim.util.ConnectionUtil;

public class ManagerDaoImpl implements ManagerDao{

	@Override
	public List<Employee> viewAllEmployees(int choice) {
		List<Employee> allEmployees = new ArrayList<>();
		String sql1 = "SELECT * FROM EMPLOYEES WHERE IS_MANAGER=0"; 
		String sql2= "SELECT * FROM EMPLOYEES"; 
		String sql= (choice==3)? sql1:sql2;
		
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				int employeeId = rs.getInt("EMP_ID");
				emp.setEmployeeID(employeeId);
				String firstName = rs.getString("FIRST_NAME");
				emp.setFirstName(firstName);
				String lastName = rs.getString("LAST_NAME");
				emp.setLastName(lastName);
				String email = rs.getString("EMAIL");
				emp.setEmail(email);
				String e_key = rs.getString("E_KEY");
				emp.setE_key(e_key);
				int is=rs.getInt("IS_MANAGER");
				emp.setIsMan(is);
				allEmployees.add(emp);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return allEmployees;
	}

	@Override
	public List<Reimbursement> viewAllRequest(int i) {
		List<Reimbursement> pendReims = new ArrayList<>();
		String pending = "SELECT * FROM REIM WHERE RESOLUTION='PENDING'"; 
		String resolved= "SELECT * FROM REIM WHERE RESOLUTION!='PENDING'";
		String sql= (i==1)? pending:resolved;
		System.out.println(sql);
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			rs = ps.executeQuery();
			while (rs.next()) {
				Reimbursement reim=new Reimbursement();
				int employeeId = rs.getInt("EMP_ID");
				reim.setEmpId(employeeId);
				String status = rs.getString("STATUS");
				reim.setStatus(status);
				String reso= rs.getString("RESOLUTION");
				reim.setResolution(reso);
				int manid= rs.getInt("MANAGER_ID");
				reim.setManId(manid);
				double amount=rs.getDouble("AMOUNT");
				reim.setAmount(amount);
				int reimId=rs.getInt("REIM_ID");
				reim.setReimId(reimId);
				pendReims.add(reim);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		return pendReims;
	}


	@Override
	public int resolveRequest(int reimId, int manId,int action) {
		String sql = "UPDATE REIM"+
				" SET RESOLUTION = ?"+
				", MANAGER_ID= ? "+
				" WHERE REIM_ID = ?";
		String method= (action==1)? "APPROVED":"DENIED";
		int requestResult=0;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, method);
			ps.setInt(2, manId);
			ps.setInt(3, reimId);
			System.out.println(ps);
			requestResult = ps.executeUpdate();
			{
			}
		} catch (IOException | SQLException p) {
			p.printStackTrace();
		}
		return requestResult;
	}


	@Override
	public List<Reimbursement> getReimbursementsByEmp(int empId) {
		List<Reimbursement> pendReims = new ArrayList<>();

		String sql = "SELECT * FROM REIM WHERE EMP_ID=?"; 
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Reimbursement reim=new Reimbursement();
				int employeeId = rs.getInt("EMP_ID");
				reim.setEmpId(employeeId);
				String status = rs.getString("STATUS");
				reim.setStatus(status);
				String reso= rs.getString("RESOLUTION");
				reim.setResolution(reso);
				int manid= rs.getInt("MANAGER_ID");
				reim.setManId(manid);
				double amount=rs.getDouble("AMOUNT");
				reim.setAmount(amount);
				int reimId=rs.getInt("REIM_ID");
				reim.setReimId(reimId);
				pendReims.add(reim);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return pendReims;
	}

	@Override
	public int createEmployee(String fname, String lname, String emial, String pass, int isMan) {
		String sql = "INSERT INTO EMPLOYEES (FIRST_NAME,LAST_NAME,EMAIL,E_KEY,IS_MANAGER) VALUES (?,?,?,?,?) ";
		int empSubmitted = 0;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1,fname);
			ps.setString(2, lname);
			ps.setString(3, emial);
			ps.setString(4,pass);
			ps.setInt(5, isMan);
			empSubmitted = ps.executeUpdate();
			
		} catch (IOException | SQLException p) {
			p.printStackTrace();
		}
		return empSubmitted;
	}
	}


