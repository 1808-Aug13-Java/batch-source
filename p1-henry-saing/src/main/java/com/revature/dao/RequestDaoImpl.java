package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Request;
import com.revature.util.ConnectionUtil;

public class RequestDaoImpl implements RequestDao {

	@Override
	public boolean createReimbursement(double amount, int empId) {
		String sql = "INSERT INTO ERS_REQUEST (ERS_STATUS, ERS_AMOUNT, ERS_EMP_ID) VALUES (0,?,?)";

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setDouble(1, amount);
			ps.setInt(2, empId);
			int rowCount = ps.executeUpdate();

			return true;
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Request> getRequestsbyEmployee(int empId) {
		String sql = "SELECT * FROM ERS_REQUEST WHERE ERS_EMP_ID = ?";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, empId);
			ResultSet rs = ps.executeQuery();
			List<Request> results = new ArrayList<>();
			while(rs.next()) {
				int reqID = rs.getInt("ERS_REQ_ID");
				double amount = rs.getDouble("ERS_AMOUNT");
				int status = rs.getInt("ERS_STATUS");
				int empID = rs.getInt("ERS_EMP_ID");
				String manager = rs.getString("ERS_MAN_USERNAME");
				Request request = new Request(reqID, amount, status, empID, manager);
				results.add(request);
			}
			return results;

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Request> getRequestsbyPendingbyEmployee(int empId) {
		String sql = "SELECT * FROM ERS_REQUEST WHERE ERS_STATUS = 0 AND ERS_EMP_ID = ?";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, empId);
			ResultSet rs = ps.executeQuery();
			List<Request> results = new ArrayList<>();
			while(rs.next()) {
				int reqID = rs.getInt("ERS_REQ_ID");
				double amount = rs.getDouble("ERS_AMOUNT");
				int status = rs.getInt("ERS_STATUS");
				int empID = rs.getInt("ERS_EMP_ID");
				String manager = rs.getString("ERS_MAN_USERNAME");
				Request request = new Request(reqID, amount, status, empID, manager);
				results.add(request);
			}
			return results;

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Request> getRequestsbyResolvedbyEmployee(int empId) {
		String sql = "SELECT * FROM ERS_REQUEST WHERE ERS_STATUS = 1 AND ERS_EMP_ID = ?";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, empId);
			ResultSet rs = ps.executeQuery();
			List<Request> results = new ArrayList<>();
			while(rs.next()) {
				int reqID = rs.getInt("ERS_REQ_ID");
				double amount = rs.getDouble("ERS_AMOUNT");
				int status = rs.getInt("ERS_STATUS");
				int empID = rs.getInt("ERS_EMP_ID");
				String manager = rs.getString("ERS_MAN_USERNAME");
				Request request = new Request(reqID, amount, status, empID, manager);
				results.add(request);
			}
			return results;

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Request> getAllPendingRequests() {
		String sql = "SELECT * FROM ERS_REQUEST WHERE ERS_STATUS = 0";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			List<Request> results = new ArrayList<>();
			while(rs.next()) {
				int reqID = rs.getInt("ERS_REQ_ID");
				double amount = rs.getDouble("ERS_AMOUNT");
				int status = rs.getInt("ERS_STATUS");
				int empID = rs.getInt("ERS_EMP_ID");
				String manager = rs.getString("ERS_MAN_USERNAME");
				Request request = new Request(reqID, amount, status, empID, manager);
				results.add(request);
			}
			return results;

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}


		return null;
	}

	@Override
	public List<Request> getAllRequestsFromPerson(int empId) {
		String sql = "SELECT * FROM ERS_REQUEST WHERE ERS_EMP_ID = ?";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, empId);
			ResultSet rs = ps.executeQuery();
			List<Request> results = new ArrayList<>();
			while(rs.next()) {
				int reqID = rs.getInt("ERS_REQ_ID");
				double amount = rs.getDouble("ERS_AMOUNT");
				int status = rs.getInt("ERS_STATUS");
				int empID = rs.getInt("ERS_EMP_ID");
				String manager = rs.getString("ERS_MAN_USERNAME");
				Request request = new Request(reqID, amount, status, empID, manager);
				results.add(request);
			}
			return results;

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Request> getAllRequestsResolvedByManager(String name) {
		String sql = "SELECT * FROM ERS_REQUEST WHERE ERS_MAN_USERNAME = ?";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			List<Request> results = new ArrayList<>();
			while(rs.next()) {
				int reqID = rs.getInt("ERS_REQ_ID");
				double amount = rs.getDouble("ERS_AMOUNT");
				int status = rs.getInt("ERS_STATUS");
				int empID = rs.getInt("ERS_EMP_ID");
				String manager = rs.getString("ERS_MAN_USERNAME");
				Request request = new Request(reqID, amount, status, empID, manager);
				results.add(request);
			}
			return results;

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Request> getAllEmployees() {
		String sql = "SELECT * FROM ERS_REQUEST";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			List<Request> results = new ArrayList<>();
			while(rs.next()) {
				int reqID = rs.getInt("ERS_REQ_ID");
				double amount = rs.getDouble("ERS_AMOUNT");
				int status = rs.getInt("ERS_STATUS");
				int empID = rs.getInt("ERS_EMP_ID");
				String manager = rs.getString("ERS_MAN_USERNAME");
				Request request = new Request(reqID, amount, status, empID, manager);
				results.add(request);
			}
			return results;

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean updateReinbursement(int approve, int reqId) {
		String sql = "UPDATE ERS_REQUEST SET ERS_STATUS = ? WHERE ERS_REQ_ID = ?";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, approve);
			ps.setInt(2, reqId);
			ps.executeUpdate();
			return true;
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
			return false;
		}
	}
