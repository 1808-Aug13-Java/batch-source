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

import com.revature.models.Reimbursment;
import com.revature.util.ConnectionUtil;

public class ReimbursmentDaoImpl implements ReimbursmentDao {
	
	Logger logger = Logger.getLogger(ReimbursmentDaoImpl.class);
	
	public ReimbursmentDaoImpl() {
		super();
	}

	@Override
	public int createReimbursment(Reimbursment reimbursment) {
		int createdReimbursment = 0;
		// triggers handle reimbursmentId and dateCreated
		String sql = "INSERT INTO REIMBURSMENT (EMPLOYEE_ID, AMOUNT, "
				+ "CURRENT_STATE, IMAGE_URL, REASON) "
				+ "VALUES (?,?,?,?,?)";
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, reimbursment.getEmployeeId());
			ps.setBigDecimal(2, reimbursment.getAmount());
			ps.setString(3, reimbursment.getCurrentState());
			ps.setString(4, reimbursment.getImageUrl());
			ps.setString(5, reimbursment.getReason());
			createdReimbursment = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			logger.info(e.getMessage());
		}
		
		
		return createdReimbursment;
	}

	@Override
	public int denyReimbursmentById(int id) {
		int deniedReimbursment = 0;
		String sql = "UPDATE REIMBURSMENT SET CURRENT_STATE = 'DENIED'"
				+ " WHERE REIMBURSMENT_ID = ?";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			deniedReimbursment = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			logger.info(e.getMessage());
		}
		
		
		return deniedReimbursment;
	}

	@Override
	public int approveRequestById(int id) {
		int approvedReimbursment = 0;
		String sql = "UPDATE REIMBURSMENT SET CURRENT_STATE = 'APPROVED'"
				+ " WHERE REIMBURSMENT_ID = ?";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			approvedReimbursment = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			logger.info(e.getMessage());
		}
		
		
		return approvedReimbursment;
	}

	@Override
	public Reimbursment getReimbursmentById(int id) {
		Reimbursment reimbursment = new Reimbursment();
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSMENT WHERE REIMBURSMENT_ID = ?";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				reimbursment.setReimbursmentId(rs.getInt("REIMBURSMENT_ID"));
				reimbursment.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				reimbursment.setCurrentState(rs.getString("CURRENT_STATE"));
				reimbursment.setDateCreated(rs.getDate("DATE_CREATED"));
				reimbursment.setImageUrl(rs.getString("image_URL"));
				reimbursment.setAmount(rs.getBigDecimal("AMOUNT"));
				reimbursment.setReason(rs.getString("REASON"));
			}
		} catch (SQLException | IOException e) {
			logger.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return reimbursment;
	}

	@Override
	public List<Reimbursment> getPendingByEmployeeId(int id) {
		List<Reimbursment> reimbursments = new ArrayList<Reimbursment>();
		
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSMENT WHERE CURRENT_STATE = 'PENDING' "
				+ "AND EMPLOYEE_ID = ?";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				Reimbursment reimbursment = new Reimbursment();
				reimbursment.setReimbursmentId(rs.getInt("REIMBURSMENT_ID"));
				reimbursment.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				reimbursment.setCurrentState(rs.getString("CURRENT_STATE"));
				reimbursment.setDateCreated(rs.getDate("DATE_CREATED"));
				reimbursment.setImageUrl(rs.getString("image_URL"));
				reimbursment.setAmount(rs.getBigDecimal("AMOUNT"));
				reimbursment.setReason(rs.getString("REASON"));
				reimbursments.add(reimbursment);
			}
		} catch (SQLException | IOException e) {
			logger.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		}
		return reimbursments;
	}

	@Override
	public List<Reimbursment> getResolvedByEmployeeId(int id) {
		List<Reimbursment> reimbursments = new ArrayList<Reimbursment>();
		
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSMENT WHERE CURRENT_STATE = 'APPROVED' "
				+ "OR CURRENT_STATE = 'DENIED' "
				+ "AND EMPLOYEE_ID = ?";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				Reimbursment reimbursment = new Reimbursment();
				reimbursment.setReimbursmentId(rs.getInt("REIMBURSMENT_ID"));
				reimbursment.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				reimbursment.setCurrentState(rs.getString("CURRENT_STATE"));
				reimbursment.setDateCreated(rs.getDate("DATE_CREATED"));
				reimbursment.setImageUrl(rs.getString("image_URL"));
				reimbursment.setAmount(rs.getBigDecimal("AMOUNT"));
				reimbursment.setReason(rs.getString("REASON"));
				reimbursments.add(reimbursment);
			}
		} catch (SQLException | IOException e) {
			logger.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		}
		return reimbursments;
	}

	@Override
	public List<Reimbursment> getAllReimbursments() {
		List<Reimbursment> reimbursments = new ArrayList<Reimbursment>();
		
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSMENT";
		try(Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				) {
			rs = s.executeQuery(sql);
			while(rs.next()) {
				Reimbursment reimbursment = new Reimbursment();
				reimbursment.setReimbursmentId(rs.getInt("REIMBURSMENT_ID"));
				reimbursment.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				reimbursment.setCurrentState(rs.getString("CURRENT_STATE"));
				reimbursment.setDateCreated(rs.getDate("DATE_CREATED"));
				reimbursment.setImageUrl(rs.getString("IMAGE_URL"));
				reimbursment.setAmount(rs.getBigDecimal("AMOUNT"));
				reimbursment.setReason(rs.getString("REASON"));
				reimbursments.add(reimbursment);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		}
		return reimbursments;
	}

	@Override
	public List<Reimbursment> getReimbursmentsByEmployeeId(int id) {
		List<Reimbursment> reimbursments = new ArrayList<Reimbursment>();
		
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSMENT WHERE EMPLOYEE_ID = ?";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				Reimbursment reimbursment = new Reimbursment();
				reimbursment.setReimbursmentId(rs.getInt("REIMBURSMENT_ID"));
				reimbursment.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				reimbursment.setCurrentState(rs.getString("CURRENT_STATE"));
				reimbursment.setDateCreated(rs.getDate("DATE_CREATED"));
				reimbursment.setImageUrl(rs.getString("IMAGE_URL"));
				reimbursment.setAmount(rs.getBigDecimal("AMOUNT"));
				reimbursment.setReason(rs.getString("REASON"));
				reimbursments.add(reimbursment);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		}
		return reimbursments;
	}

	@Override
	public List<Reimbursment> getAllPendingReimbursments() {
		List<Reimbursment> reimbursments = new ArrayList<Reimbursment>();
		
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSMENT WHERE CURRENT_STATE = 'PENDING'";
		try(Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				) {
			rs = s.executeQuery(sql);
			while(rs.next()) {
				Reimbursment reimbursment = new Reimbursment();
				reimbursment.setReimbursmentId(rs.getInt("REIMBURSMENT_ID"));
				reimbursment.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				reimbursment.setCurrentState(rs.getString("CURRENT_STATE"));
				reimbursment.setDateCreated(rs.getDate("DATE_CREATED"));
				reimbursment.setImageUrl(rs.getString("image_URL"));
				reimbursment.setAmount(rs.getBigDecimal("AMOUNT"));
				reimbursment.setReason(rs.getString("REASON"));
				reimbursments.add(reimbursment);
			}
		} catch (SQLException | IOException e) {
			logger.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		}
		return reimbursments;
	}

	@Override
	public List<Reimbursment> getAllResolvedReimbursments() {
		List<Reimbursment> reimbursments = new ArrayList<Reimbursment>();
		
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSMENT WHERE "
				+ "UPPER(CURRENT_STATE) = 'APPROVED' OR UPPER(CURRENT_STATE) = 'DENIED'";
		try(Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				) {
			rs = s.executeQuery(sql);
			while(rs.next()) {
				Reimbursment reimbursment = new Reimbursment();
				reimbursment.setReimbursmentId(rs.getInt("REIMBURSMENT_ID"));
				reimbursment.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				reimbursment.setCurrentState(rs.getString("CURRENT_STATE"));
				reimbursment.setDateCreated(rs.getDate("DATE_CREATED"));
				reimbursment.setImageUrl(rs.getString("IMAGE_URL"));
				reimbursment.setAmount(rs.getBigDecimal("AMOUNT"));
				reimbursment.setReason(rs.getString("REASON"));
				reimbursments.add(reimbursment);
			}
		} catch (SQLException | IOException e) {
			
			logger.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		}
		return reimbursments;
	}

}
