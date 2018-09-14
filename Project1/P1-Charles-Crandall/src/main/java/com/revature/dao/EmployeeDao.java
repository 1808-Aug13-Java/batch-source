package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDao implements EmployeeDaoInterface{
	static final private String WHERE_USER = " WHERE USERNAME = '";
	
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> output = null;
		String sql = "SELECT * FROM EMPLOYEE";
		try(Connection conn = ConnectionUtil.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);){
			if (rs.next()) {
				output = new ArrayList<>();
				do{
					Employee tmp = new Employee();
					tmp.setEmpID(rs.getInt("EMP_ID"));
					tmp.setUsername(rs.getString("USERNAME"));
					tmp.setPass(rs.getString("PASS"));
					tmp.setEmail(rs.getString("EMAIL"));
					tmp.setfName(rs.getString("FNAME"));
					tmp.setlName(rs.getString("LNAME"));
					tmp.setPhone(rs.getLong("PHONE"));
					tmp.setSecondary(rs.getLong("SECONDARY"));
					tmp.setStateAbbr(rs.getString("STATE_ABR"));
					tmp.setManager(rs.getInt("MANAGER") == 1);
					output.add(tmp);
				} while(rs.next());
			}
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return output;
	}

	@Override
	public List<Employee> getAllManagers() {
		List<Employee> output = null;
		String sql = "SELECT * FROM EMPLOYEE WHERE MANAGER = 1";
		try(Connection conn = ConnectionUtil.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);){
			if (rs.next()) {
				output = new ArrayList<>();
				do {
					Employee tmp = new Employee();
					tmp.setEmpID(rs.getInt("EMP_ID"));
					tmp.setUsername(rs.getString("USERNAME"));
					tmp.setPass(rs.getString("PASS"));
					tmp.setEmail(rs.getString("EMAIL"));
					tmp.setfName(rs.getString("FNAME"));
					tmp.setlName(rs.getString("LNAME"));
					tmp.setPhone(rs.getLong("PHONE"));
					tmp.setSecondary(rs.getLong("SECONDARY"));
					tmp.setStateAbbr(rs.getString("STATE_ABR"));
					tmp.setManager(rs.getInt("MANAGER") == 1);
					output.add(tmp);
				}while(rs.next());
			}
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return output;
	}

	@Override
	public List<Employee> getNonManagers() {
		List<Employee> output = null;
		String sql = "SELECT * FROM EMPLOYEE WHERE MANAGER = 0";
		try(Connection conn = ConnectionUtil.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);){
			if (rs.next()) {
				output = new ArrayList<>();
				do {
					Employee tmp = new Employee();
					tmp.setEmpID(rs.getInt("EMP_ID"));
					tmp.setUsername(rs.getString("USERNAME"));
					tmp.setPass(rs.getString("PASS"));
					tmp.setEmail(rs.getString("EMAIL"));
					tmp.setfName(rs.getString("FNAME"));
					tmp.setlName(rs.getString("LNAME"));
					tmp.setPhone(rs.getLong("PHONE"));
					tmp.setSecondary(rs.getLong("SECONDARY"));
					tmp.setStateAbbr(rs.getString("STATE_ABR"));
					tmp.setManager(rs.getInt("MANAGER") == 1);
					output.add(tmp);
				} while(rs.next());
			}
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return output;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee output = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);){
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				output = new Employee();
				output.setEmpID(rs.getInt("EMP_ID"));
				output.setUsername(rs.getString("USERNAME"));
				output.setPass(rs.getString("PASS"));
				output.setEmail(rs.getString("EMAIL"));
				output.setfName(rs.getString("FNAME"));
				output.setlName(rs.getString("LNAME"));
				output.setPhone(rs.getLong("PHONE"));
				output.setSecondary(rs.getLong("SECONDARY"));
				output.setStateAbbr(rs.getString("STATE_ABR"));
				output.setManager(rs.getInt("MANAGER") == 1);
			}
			
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return output;
	}
	
	@Override
	public Employee getEmployeeByUsername(String user) {
		Employee output = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = ?";
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);){
			st.setString(1, user);
			rs = st.executeQuery();
			if(rs.next()) {
				output = new Employee();
				output.setEmpID(rs.getInt("EMP_ID"));
				output.setUsername(rs.getString("USERNAME"));
				output.setPass(rs.getString("PASS"));
				output.setEmail(rs.getString("EMAIL"));
				output.setfName(rs.getString("FNAME"));
				output.setlName(rs.getString("LNAME"));
				output.setPhone(rs.getLong("PHONE"));
				output.setSecondary(rs.getLong("SECONDARY"));
				output.setStateAbbr(rs.getString("STATE_ABR"));
				output.setManager(rs.getInt("MANAGER") == 1);
			}
			
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return output;
	}

	@Override
	public boolean matchPassword(String user, String pass) {
		String sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = ? AND PASS = ?";
		ResultSet rs = null;
		boolean out = false;
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);){
			st.setString(1, user);
			st.setString(2, pass);
			rs = st.executeQuery();
			out = rs.next();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return out;
	}

	@Override
	public boolean verifyManager(String user, String pass) {
		String sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = ? AND PASS = ? and MANAGER = 1";
		ResultSet rs = null;
		boolean out = false;
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);){
			st.setString(1, user);
			st.setString(2, pass);
			rs = st.executeQuery();
			out = rs.next();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return out;
	}

	@Override
	public int addEmployee(Employee emp) {
		int rowAdded = 0;
		String user = emp.getUsername();
		String pass = emp.getPass();
		String email = emp.getEmail();
		String fName = emp.getfName();
		String lName = emp.getlName();
		long phone = emp.getPhone();
		long secondary = emp.getSecondary();
		String state = emp.getStateAbbr();
		String sql = "INSERT INTO EMPLOYEE ("
				+ "USERNAME, PASS, EMAIL, "
				+ "FNAME, LNAME, PHONE, "
				+ "SECONDARY, STATE_ABR, "
				+ "MANAGER) VALUES (?,?,?,?,?,?,?,?,0)";
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);){
			st.setString(1, user);
			st.setString(2, pass);
			st.setString(3, email);
			st.setString(4, fName);
			st.setString(5, lName);
			st.setLong(6, phone);
			st.setLong(7, secondary);
			st.setString(8, state);
			
			rowAdded = st.executeUpdate();
			
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return rowAdded;
	}

	@Override
	public int addManager(Employee emp) {
		int rowAdded = 0;
		String user = emp.getUsername();
		String pass = emp.getPass();
		String email = emp.getEmail();
		String fName = emp.getfName();
		String lName = emp.getlName();
		long phone = emp.getPhone();
		long secondary = emp.getSecondary();
		String state = emp.getStateAbbr();
		String sql = "INSERT INTO EMPLOYEE ("
				+ "USERNAME, PASS, EMAIL, "
				+ "FNAME, LNAME, PHONE, "
				+ "SECONDARY, STATE_ABR, "
				+ "MANAGER) VALUES (?,?,?,?,?,?,?,?,1)";
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);){
			st.setString(1, user);
			st.setString(2, pass);
			st.setString(3, email);
			st.setString(4, fName);
			st.setString(5, lName);
			st.setLong(6, phone);
			st.setLong(7, secondary);
			st.setString(8, state);
			
			rowAdded = st.executeUpdate();
			
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return rowAdded;
	}
	
	@Override
	public int updatePass(String user, String pass) {
		return updateInfo("UPDATE EMPLOYEE SET PASS = '" + 
				pass + "'" + WHERE_USER + user + "'");
	}

	@Override
	public int updateEmail(String user, String email) {
		return updateInfo("UPDATE EMPLOYEE SET EMAIL = '" + 
				email + "'" + WHERE_USER + user + "'");
	}
	
	@Override
	public int updatePhone(String user, long phone) {
		return updateInfo("UPDATE EMPLOYEE SET PHONE = " + 
				(phone) + WHERE_USER + user + "'");
	}
	
	@Override
	public int updateSecond(String user, long second) {
		return updateInfo("UPDATE EMPLOYEE SET SECONDARY = " + 
				(second) + WHERE_USER + user + "'");
	}
	
	@Override
	public int updateState(String user, String state) {
		return updateInfo("UPDATE EMPLOYEE SET STATE_ABR = '" + 
				state + "'" + WHERE_USER + user + "'");
	}

	@Override
	public int updateIsManager(String user, boolean man) {
		return updateInfo("UPDATE EMPLOYEE SET MANAGER = " + 
				(man?1:0) + WHERE_USER + user + "'");		
	}

	@Override
	public int updateInfo(String sql) {
		int rowsUpdated = 0;
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);){
			rowsUpdated = st.executeUpdate();
			
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return rowsUpdated;
	}

	@Override
	public int deleteEmployee(Employee emp) {
		return delete("DELETE FROM EMPLOYEE WHERE EMP_ID = " + emp.getEmpID() + " AND USERNAME = '" + emp.getUsername() + "'");
	}

	@Override
	public int deleteEmployee(String user) {
		return delete("DELETE FROM EMPLOYEE WHERE USERNAME = '" + user + "'");
	}

	@Override
	public int deleteEmployee(int id) {
		return delete("DELETE FROM EMPLOYEE WHERE EMP_ID = " + id);
	}

	@Override
	public int delete(String sql) {
		int rowsDeleted = 0;
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);){
			rowsDeleted = st.executeUpdate();
			
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return rowsDeleted;
	}

}
