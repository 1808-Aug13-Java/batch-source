package com.chandrika.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.chandrika.model.Employee;
import com.chandrika.model.Manager;
import com.chandrika.model.Request;
import com.chandrika.util.ConnectionUtil;

public class RequestDaoImpl implements RequestDao {
	private static Logger log = Logger.getRootLogger();
	private List<Request> getRequests(String sql) {
		List<Request> requestList = new ArrayList<>();
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();) {
			rs = s.executeQuery(sql);
			while (rs.next()) {
				Request r = populateRequest(rs, con);
				requestList.add(r);
			}
		} catch (SQLException e) {
			log.error("SQL exception encountered", e);
		} catch (IOException e) {
			log.error("IO exception encountered", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error("Closing ResultSet failed", e);
				}
			}
		}
		return requestList;
	}
	
	@Override
	public List<Request> getRequests() {
		return getRequests("SELECT * FROM ERS.REQUEST");
	}
	
	@Override
	public List<Request> getPendingRequests() {
		return getRequests("SELECT * FROM ERS.REQUEST WHERE STATUS = 'pending'");
	}
	
	@Override
	public List<Request> getResolvedRequests() {
		return getRequests("SELECT * FROM ERS.REQUEST WHERE STATUS != 'pending'");
	}
	
	private List<Request> getRequestsByEmployeeId(int employeeId, String sql) {
		List<Request> requests = new ArrayList<>();
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Request req = populateRequest(rs, con);
				requests.add(req);
			}
		} catch (SQLException e) {
			log.error("SQL exception encountered", e);
		} catch (IOException e) {
			log.error("IO exception encountered", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error("Closing ResultSet failed", e);
				}
			}
		}
		return requests;
	}
	
	@Override
	public List<Request> getRequestsByEmployeeId(int employeeId) {
		return getRequestsByEmployeeId(employeeId, "SELECT * FROM ERS.REQUEST WHERE EMPLOYEE_ID = ?");
	}
	
	@Override
	public List<Request> getPendingRequestsByEmployeeId(int employeeId) {
		String sql = "SELECT * FROM ERS.REQUEST WHERE EMPLOYEE_ID = ? AND STATUS = 'pending'";
		return getRequestsByEmployeeId(employeeId, sql);
	}
	
	@Override
	public List<Request> getResolvedRequestsByEmployeeId(int employeeId) {
		String sql = "SELECT * FROM ERS.REQUEST WHERE EMPLOYEE_ID = ? AND STATUS != 'pending'";
		return getRequestsByEmployeeId(employeeId, sql);
	}
	
	@Override
	public Request getRequestById(int id) {
		Request req = null;
		String sql = "SELECT * FROM ERS.REQUEST WHERE REQUEST_ID = ?";
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				req = populateRequest(rs, con);
			}
		} catch (SQLException e) {
			log.error("SQL exception encountered", e);
		} catch (IOException e) {
			log.error("IO exception encountered", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error("Closing ResultSet failed", e);
				}
			}
		}
		return req;
	}
	
	@Override
	public Request getRequestById(int id, Connection con) {
		Request req = null;
		String sql = "SELECT * FROM ERS.REQUEST WHERE REQUEST_ID = ?";
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				String status = rs.getString("STATUS");
				BigDecimal amount = rs.getBigDecimal("AMOUNT");
				int employeeId = rs.getInt("EMPLOYEE_ID");
				int managerId = rs.getInt("MANAGER_ID");
				EmployeeDao ed = new EmployeeDaoImpl();
				Employee e = ed.getEmployeeById(employeeId, con);
				ManagerDao md = new ManagerDaoImpl();
				Manager m = md.getManagerById(managerId, con);
				
				req = new Request();
				req.setId(id);
				req.setStatus(status);
				req.setAmount(amount);
				req.setEmployee(e);
				req.setManager(m);
			}
		} catch (SQLException e) {
			log.error("SQL exception encountered", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error("Closing ResultSet failed", e);
				}
			}
		}
		return req;
	}
	
	private Request populateRequest(ResultSet rs, Connection con) throws SQLException {
		int requestId = rs.getInt("REQUEST_ID");
		String status = rs.getString("STATUS");
		BigDecimal amount = rs.getBigDecimal("AMOUNT");
		int employeeId = rs.getInt("EMPLOYEE_ID");
		int managerId = rs.getInt("MANAGER_ID");
		EmployeeDao ed = new EmployeeDaoImpl();
		Employee e = ed.getEmployeeById(employeeId, con);
		ManagerDao md = new ManagerDaoImpl();
		Manager m = md.getManagerById(managerId, con);
		
		Request r = new Request();
		r.setId(requestId);
		r.setStatus(status);
		r.setAmount(amount);
		r.setEmployee(e);
		r.setManager(m);
		return r;
	}
}
