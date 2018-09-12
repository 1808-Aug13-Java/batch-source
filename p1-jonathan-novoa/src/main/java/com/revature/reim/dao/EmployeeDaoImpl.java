package com.revature.reim.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.reim.model.Employee;
import com.revature.reim.model.Reimbursement;
import com.revature.reim.util.ConnectionUtil;;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public int logIn(String username,String password) {
		int result=0;
		Map<String,String> credentials= new HashMap<>();
		Map<String,Integer> isManMap=new HashMap<>();
		
		String sql = "SELECT * FROM EMPLOYEES";
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			rs = ps.executeQuery();
			while (rs.next()) {
				String email = rs.getString("EMAIL");
				String e_key = rs.getString("E_KEY");
				int is=rs.getInt("IS_MANAGER");
				
				credentials.put(email, e_key);
				isManMap.put(email, is);
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
			if(!credentials.isEmpty() && credentials.containsKey(username) ) {
				String current= credentials.remove(username);
				result =(current.equals(password))? 1:0;
				int man=isManMap.remove(username);
				if(result==1 && man==1 ) {
					result=2;
				}
				System.out.println("credentials check ran, username exists");
			}
		return result;
	}

	@Override
	public int logOut() {
		return 0;
	}

	@Override
	public int submitReimbursement(int id, double amount) {
		String sql = "INSERT INTO REIM (EMP_ID,AMOUNT) VALUES (?,?) ";
		int reimSubmitted = 0;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			ps.setDouble(2,amount);
			reimSubmitted = ps.executeUpdate();
			{
			}
		} catch (IOException | SQLException p) {
			p.printStackTrace();
		}
		return reimSubmitted;
	}

	@Override
	public List<Reimbursement> viewReimbursments(int empId, int choice) {
		String pending = "SELECT * FROM REIM WHERE EMP_ID=? AND RESOLUTION='PENDING'";
		String resolved= "SELECT * FROM REIM WHERE EMP_ID=? AND RESOLUTION!='PENDING'";
		String all= "SELECT * FROM REIM WHERE EMP_ID=?";
//		String sql=(choice==1)? pending:resolved;
		String sql=null;
		switch(choice) {
		case 1:{
			sql=pending;
			break;
		}
		case 2:{
			sql=resolved;
			break;
		}
		default:{
			sql=all;
		}
		}
		System.out.println(sql);
		List<Reimbursement> pendingReim=new ArrayList<>();
		ResultSet rs=null;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, empId);
			rs=ps.executeQuery();
			{
				while(rs.next()) {
					Reimbursement current= new Reimbursement();
					int new_id= rs.getInt("EMP_ID");
					current.setEmpId(new_id);
					String reso= rs.getString("RESOLUTION");
					current.setResolution(reso);
					String status=rs.getString("STATUS");
					current.setStatus(status);
					double amt=rs.getDouble("AMOUNT");
					current.setAmount(amt);
					int reimId=rs.getInt("REIM_ID");
					current.setReimId(reimId);
					pendingReim.add(current);
				}
			}
		} catch (IOException | SQLException p) {
			p.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return pendingReim;
		
	}

	public void viewAllReimbursements (int empId) {
		
	}
	@Override
	public Employee viewProfile(int id) {
		String sql = "SELECT * FROM EMPLOYEES WHERE EMP_ID=?";
		Employee e = new Employee();
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				int employeeId = rs.getInt("EMP_ID");
				e.setEmployeeID(employeeId);
				String firstName = rs.getString("FIRST_NAME");
				e.setFirstName(firstName);
				String lastName = rs.getString("LAST_NAME");
				e.setLastName(lastName);
				String email = rs.getString("EMAIL");
				e.setEmail(email);
				String e_key = rs.getString("E_KEY");
				e.setE_key(e_key);
				int man=rs.getInt("IS_MANAGER");
				e.setIsMan(man);
			}
			
		} catch (IOException | SQLException p) {
			p.printStackTrace();
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
	public int changeProfile(int id,String key, String firstName, String lastname, String username) {
		int result=0;
		String sql="UPDATE EMPLOYEES"+
				" SET E_KEY=?,"+
				" FIRST_NAME=?,"+
				"LAST_NAME=?,"+
				"EMAIL=?"+
				"WHERE EMP_ID=?";
//		String sql = "UPDATE DEPARTMENT"+
//				" SET DEPT_NAME = ?"+
//				" MONTHLY_BUDGET = ?"+
//				" WHERE DEPT_ID = ?";
		
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			con.setAutoCommit(false);
			ps.setString(1,key);
		ps.setString(2, firstName);
		ps.setString(3, lastname);
		ps.setString(4, username);
		ps.setInt(5, id);
		result = ps.executeUpdate();
		con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int getEmpId(String username) {
		int empId=0;
		String sql = "SELECT * FROM EMPLOYEES WHERE EMAIL=?";
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				empId=rs.getInt("EMP_ID");
			}
		}catch (IOException | SQLException p) {
			p.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return empId;
	}

}
