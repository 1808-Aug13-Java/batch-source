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
		List<Employee> allEmployees = new ArrayList<>();
		Map<String,String> credentials= new HashMap<>();
		String sql = "SELECT * FROM EMPLOYEES";
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
				credentials.put(email, e_key);
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
			if(!credentials.isEmpty() && credentials.containsKey(username) ) {
				String current= credentials.remove(username);
				result =(current.equals(password))? 1:0;
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
	public List<Reimbursement> viewPendingReimbursments(int id) {
		String sql = "SELECT * FROM REIM WHERE EMP_ID=? AND RESOLUTION='PENDING'";
		List<Reimbursement> pendingReim=new ArrayList<>();
		int viewReim = 0;
		ResultSet rs=null;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
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

	@Override
	public List<Reimbursement> viewResolvedReimbursements(int id) {
		List<Reimbursement> resReim = new ArrayList<>();
		String sql = "SELECT * FROM REIM WHERE EMP_ID=? AND STATUS='RESOLVED'";
		ResultSet rs=null;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs=ps.executeQuery();
			{
				while(rs.next()) {
					Reimbursement current= new Reimbursement();
					int new_id= rs.getInt("EMP_ID");
					current.setEmpId(new_id);
					String reso= rs.getString("RESOLUTION");
					current.setResolution(reso);
					int manid= rs.getInt("MANAGER_ID");
					current.setManID(manid);
					String status=rs.getString("STATUS");
					current.setStatus(status);
					resReim.add(current);
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
		return resReim;
	}

	@Override
	public Employee viewProfile(int id) {
		// TODO Auto-generated method stub
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
	public int changeProfile(int id,String key) {
		int result=0;
		String sql="UPDATE EMPLOYEES SET E_KEY=? WHERE EMP_ID=?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
		ps.setString(1,key);
		ps.setInt(2, id);
		result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
