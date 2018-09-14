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
import com.revature.models.Request;
import com.revature.util.ConnectionUtil;

public class RequestDao implements RequestDaoInterface{
	private static Logger log = Logger.getRootLogger();
	@Override
	public Request getRequestByID(int id) {
		Request output = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM REQUEST WHERE REQUEST_ID = ?";
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);){
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				output = new Request();
				output.setRequestID(rs.getInt("REQUEST_ID"));
				output.setEmpID(rs.getInt("EMP_ID"));
				output.setAmount(rs.getFloat("AMOUNT"));
				output.setStatus(rs.getShort("STATUS"));
				output.setManagerID(rs.getInt("MAN_REVIEWED"));
				output.setDescription(rs.getString("DESCRIPTION"));
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
	public List<Request> getPendingRequestsByEmpID(int id) {
		List<Request> output = new ArrayList<>();
		ResultSet rs = null;
		String sql = "SELECT r.REQUEST_ID, r.AMOUNT, r.DESCRIPTION " + 
				"FROM REQUEST r WHERE r.STATUS = 0 AND r.EMP_ID = ?";
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);){
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				do{
					Request tmp = new Request();
					tmp.setRequestID(rs.getInt("REQUEST_ID"));
					tmp.setAmount(rs.getFloat("AMOUNT"));
					tmp.setStatus((short) 0);
					tmp.setDescription(rs.getString("DESCRIPTION"));
					
					output.add(tmp);
				}while(rs.next());
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
	public List<Object[]> getRespondedRequestsByEmpID(int id) {
		List<Object[]> output = new ArrayList<>();
		ResultSet rs = null;
		String sql = "SELECT r.REQUEST_ID, r.EMP_ID, r.AMOUNT, r.STATUS, r.MAN_REVIEWED, r.DESCRIPTION, CONCAT(e.FNAME,CONCAT(' ', e.LNAME)) AS MAN_NAME " + 
				"FROM REQUEST r, EMPLOYEE e WHERE r.MAN_REVIEWED = e.EMP_ID AND r.EMP_ID = ?";
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);){
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				do{
					Request tmp = new Request();
					tmp.setRequestID(rs.getInt("REQUEST_ID"));
					tmp.setEmpID(rs.getInt("EMP_ID"));
					tmp.setAmount(rs.getFloat("AMOUNT"));
					tmp.setStatus(rs.getShort("STATUS"));
					tmp.setManagerID(rs.getInt("MAN_REVIEWED"));
					tmp.setDescription(rs.getString("DESCRIPTION"));
					String manName = rs.getString("MAN_NAME");
					Object[] tuple = {tmp,manName};
					output.add(tuple);
				}while(rs.next());
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
	public List<Object[]> getAllPendingRequests() {
		List<Object[]>output = new ArrayList<>();
		ResultSet rs = null;
		String sql = "SELECT r.REQUEST_ID, r.EMP_ID, r.AMOUNT, r.DESCRIPTION, CONCAT(e.FNAME, CONCAT(' ',e.LNAME)) as EMP_NAME " + 
				"FROM REQUEST r LEFT JOIN EMPLOYEE e ON r.EMP_ID=e.EMP_ID WHERE r.STATUS=0";
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);){
			rs = st.executeQuery();
			if(rs.next()) {
				do{
					Request tmp = new Request();
					tmp.setRequestID(rs.getInt("REQUEST_ID"));
					tmp.setEmpID(rs.getInt("EMP_ID"));
					tmp.setAmount(rs.getFloat("AMOUNT"));
					tmp.setStatus((short)0);
					tmp.setDescription(rs.getString("DESCRIPTION"));
					String empName = rs.getString("EMP_NAME");
					Object[] tuple = {tmp, empName};
					output.add(tuple);
				}while(rs.next());
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
	public List<Object[]> getAllRespondedRequests() {
		List<Object[]>output = new ArrayList<>();
		ResultSet rs = null;
		String sql = "SELECT r.REQUEST_ID, r.EMP_ID, r.AMOUNT, r.STATUS, r.DESCRIPTION, concat(e.FNAME, concat(' ',e.LNAME)) as EMP_NAME, "+ 
				"c.EMP_ID, concat(c.FNAME, concat(' ',c.LNAME)) as MAN_NAME "+
				"FROM REQUEST r LEFT JOIN EMPLOYEE e ON r.EMP_ID=e.EMP_ID LEFT JOIN EMPLOYEE c ON r.MAN_REVIEWED=c.EMP_ID  WHERE r.STATUS <> 0";
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);){
			rs = st.executeQuery();
			if(rs.next()) {
				do{
					Request tmp = new Request();
					tmp.setRequestID(rs.getInt("REQUEST_ID"));
					tmp.setEmpID(rs.getInt("EMP_ID"));
					tmp.setAmount(rs.getFloat("AMOUNT"));
					tmp.setStatus(rs.getShort("STATUS"));
					tmp.setDescription(rs.getString("DESCRIPTION"));
					String empName = rs.getString("EMP_NAME");
					String manName = rs.getString("MAN_NAME");
					Object[] tuple = {tmp, empName, manName};
					output.add(tuple);
				}while(rs.next());
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
	public int addRequest(int emp, float amount, String description) {
		int output = 0;
		String sql = "INSERT INTO REQUEST (EMP_ID, AMOUNT, STATUS, DESCRIPTION) VALUES (?,?,0,?)";
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);){
			st.setInt(1, emp);
			st.setFloat(2, amount);
			st.setString(3, description);
			
			output = st.executeUpdate();

		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return output;
	}

	@Override
	public int approveRequest(int id, int man) {
		int output = 0;
		String sql = "UPDATE REQUEST SET STATUS = 1, MAN_REVIEWED = ? WHERE REQUEST_ID = ? AND STATUS = 0";
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);){
			st.setInt(1, man);
			st.setInt(2, id);
			output = st.executeUpdate();

		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	@Override
	public int denyRequest(int id, int man) {
		int output = 0;
		String sql = "UPDATE REQUEST SET STATUS = -1, MAN_REVIEWED = ? WHERE REQUEST_ID = ? AND STATUS = 0";
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);){
			st.setInt(1, man);
			st.setInt(2, id);
			output = st.executeUpdate();

		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return output;
	}
}
