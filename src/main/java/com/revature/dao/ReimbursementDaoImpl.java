/**
 * 
 */
package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Reimbursement;
import com.revature.model.Zukemployee;
import com.revature.util.ConnectionUtil;

/**
 * @author nozuk
 *
 */
public class ReimbursementDaoImpl implements ReimbursementDao {

	private static Logger log = Logger.getRootLogger();

	@Override
	public List<Reimbursement> getAllRequests() {
		List<Reimbursement> reimbursementsList = new ArrayList<Reimbursement>();

		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENT";

		try(Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();) {
			rs = s.executeQuery(sql);

			while(rs.next()) {
				Reimbursement r = new Reimbursement();

				int requestId = rs.getInt("REIMBURSEMENT_ID");
				r.setId(requestId);

				String requestReason = rs.getString("REIMBURSEMENT_DESC");
				r.setReason(requestReason);

				String reqStatus = rs.getString("REIMBURSEMENT_STATUS");
				r.setStatus(reqStatus);

				BigDecimal reqAmount = rs.getBigDecimal("REIMBURSEMENT_AMT");
				r.setAmount(reqAmount);

				Date subDate = rs.getDate("SUBMIT_DATE");
				r.setSubmitDate(subDate);

				int managerId = rs.getInt("REIMBURSEMENT_HANDLER");
				r.setRequestHandlerId(managerId);

				int empId = rs.getInt("EMP_ID");
				r.setEmpId(empId);

				reimbursementsList.add(r);
			}
		} catch (SQLException | IOException e) {
			log.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.info(e.getMessage());
				}
			}
		}
		return reimbursementsList;
	}

	@Override //(employee id)
	public List<Reimbursement> getRequestsById(int id) {
		List<Reimbursement> reimbursementsList = new ArrayList<Reimbursement>();

		String sql = "SELECT * FROM REIMBURSEMENT WHERE EMP_ID = ?";
		ResultSet rs = null;

		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while(rs.next()) {
				Reimbursement r = new Reimbursement();

				int requestId = rs.getInt("REIMBURSEMENT_ID");
				r.setId(requestId);

				String requestReason = rs.getString("REIMBURSEMENT_DESC");
				r.setReason(requestReason);

				String reqStatus = rs.getString("REIMBURSEMENT_STATUS");
				r.setStatus(reqStatus);

				BigDecimal reqAmount = rs.getBigDecimal("REIMBURSEMENT_AMT");
				r.setAmount(reqAmount);

				Date subDate = rs.getDate("SUBMIT_DATE");
				r.setSubmitDate(subDate);

				int managerId = rs.getInt("REIMBURSEMENT_HANDLER");
				r.setRequestHandlerId(managerId);

				int empId = rs.getInt("EMP_ID");
				r.setEmpId(empId);

				reimbursementsList.add(r);
			}
		} catch (SQLException | IOException e) {
			log.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.info(e.getMessage());
				}
			}
		}
		return reimbursementsList;
	}

	@Override
	public List<Reimbursement> getAllPendingRequests() {
		List<Reimbursement> reimbursementsList = new ArrayList<Reimbursement>();

		String sql = "SELECT * FROM REIMBURSEMENT WHERE REIMBURSEMENT_STATUS = 'PENDING'";
		ResultSet rs = null;

		try(Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();) {

			rs = s.executeQuery(sql);

			while(rs.next()) {
				Reimbursement r = new Reimbursement();

				int requestId = rs.getInt("REIMBURSEMENT_ID");
				r.setId(requestId);

				String requestReason = rs.getString("REIMBURSEMENT_DESC");
				r.setReason(requestReason);

				String reqStatus = rs.getString("REIMBURSEMENT_STATUS");
				r.setStatus(reqStatus);

				BigDecimal reqAmount = rs.getBigDecimal("REIMBURSEMENT_AMT");
				r.setAmount(reqAmount);

				Date subDate = rs.getDate("SUBMIT_DATE");
				r.setSubmitDate(subDate);

				int managerId = rs.getInt("REIMBURSEMENT_HANDLER");
				r.setRequestHandlerId(managerId);

				int empId = rs.getInt("EMP_ID");
				r.setEmpId(empId);

				reimbursementsList.add(r);
			}
		} catch (SQLException | IOException e) {
			log.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.info(e.getMessage());
				}
			}
		}
		return reimbursementsList;
	}

	@Override //(employee id)
	public List<Reimbursement> getPendingRequestsById(int id) {
		List<Reimbursement> reimbursementsList = new ArrayList<Reimbursement>();

		String sql = "SELECT * FROM REIMBURSEMENT "
				+ "WHERE REIMBURSEMENT_STATUS = 'PENDING' "
				+ "AND EMP_ID = ?";

		ResultSet rs = null;

		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while(rs.next()) {
				Reimbursement r = new Reimbursement();

				int requestId = rs.getInt("REIMBURSEMENT_ID");
				r.setId(requestId);

				String requestReason = rs.getString("REIMBURSEMENT_DESC");
				r.setReason(requestReason);

				String reqStatus = rs.getString("REIMBURSEMENT_STATUS");
				r.setStatus(reqStatus);

				BigDecimal reqAmount = rs.getBigDecimal("REIMBURSEMENT_AMT");
				r.setAmount(reqAmount);

				Date subDate = rs.getDate("SUBMIT_DATE");
				r.setSubmitDate(subDate);

				int managerId = rs.getInt("REIMBURSEMENT_HANDLER");
				r.setRequestHandlerId(managerId);

				int empId = rs.getInt("EMP_ID");
				r.setEmpId(empId);

				reimbursementsList.add(r);
			}
		} catch (SQLException | IOException e) {
			log.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.info(e.getMessage());
				}
			}
		}
		return reimbursementsList;
	}

	@Override
	public List<Reimbursement> getAllResolvedRequests() {
		List<Reimbursement> reimbursementsList = new ArrayList<Reimbursement>();

		String sql = "SELECT * FROM REIMBURSEMENT "
				+ "WHERE REIMBURSEMENT_STATUS = 'APPROVED' "
				+ "OR REIMBURSEMENT_STATUS = 'REJECTED'";
		ResultSet rs = null;

		try(Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();) {

			rs = s.executeQuery(sql);

			while(rs.next()) {
				Reimbursement r = new Reimbursement();

				int requestId = rs.getInt("REIMBURSEMENT_ID");
				r.setId(requestId);

				String requestReason = rs.getString("REIMBURSEMENT_DESC");
				r.setReason(requestReason);

				String reqStatus = rs.getString("REIMBURSEMENT_STATUS");
				r.setStatus(reqStatus);

				BigDecimal reqAmount = rs.getBigDecimal("REIMBURSEMENT_AMT");
				r.setAmount(reqAmount);

				Date subDate = rs.getDate("SUBMIT_DATE");
				r.setSubmitDate(subDate);

				int managerId = rs.getInt("REIMBURSEMENT_HANDLER");
				r.setRequestHandlerId(managerId);

				int empId = rs.getInt("EMP_ID");
				r.setEmpId(empId);

				reimbursementsList.add(r);
			}
		} catch (SQLException | IOException e) {
			log.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.info(e.getMessage());
				}
			}
		}
		return reimbursementsList;
	}

	@Override //(employee id)
	public List<Reimbursement> getResolvedRequestsById(int id) {
		List<Reimbursement> reimbursementsList = new ArrayList<Reimbursement>();

		String sql = "SELECT * FROM REIMBURSEMENT "
				+ "WHERE REIMBURSEMENT_STATUS = 'APPROVED' "
				+ "OR REIMBURSEMENT_STATUS = 'REJECTED' "
				+ "AND EMP_ID = ?";

		ResultSet rs = null;

		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while(rs.next()) {
				Reimbursement r = new Reimbursement();

				int requestId = rs.getInt("REIMBURSEMENT_ID");
				r.setId(requestId);

				String requestReason = rs.getString("REIMBURSEMENT_DESC");
				r.setReason(requestReason);

				String reqStatus = rs.getString("REIMBURSEMENT_STATUS");
				r.setStatus(reqStatus);

				BigDecimal reqAmount = rs.getBigDecimal("REIMBURSEMENT_AMT");
				r.setAmount(reqAmount);

				Date subDate = rs.getDate("SUBMIT_DATE");
				r.setSubmitDate(subDate);

				int managerId = rs.getInt("REIMBURSEMENT_HANDLER");
				r.setRequestHandlerId(managerId);

				int empId = rs.getInt("EMP_ID");
				r.setEmpId(empId);

				reimbursementsList.add(r);
			}
		} catch (SQLException | IOException e) {
			log.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.info(e.getMessage());
				}
			}
		}
		return reimbursementsList;
	}

	@Override //(request id)
	public Reimbursement getSingleRequestById(int id) {
		Reimbursement r = new Reimbursement();
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENT WHERE REIMBURSEMENT_ID = ?";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {

				int requestId = rs.getInt("REIMBURSEMENT_ID");
				r.setId(requestId);

				String requestReason = rs.getString("REIMBURSEMENT_DESC");
				r.setReason(requestReason);

				String reqStatus = rs.getString("REIMBURSEMENT_STATUS");
				r.setStatus(reqStatus);

				BigDecimal reqAmount = rs.getBigDecimal("REIMBURSEMENT_AMT");
				r.setAmount(reqAmount);

				Date subDate = rs.getDate("SUBMIT_DATE");
				r.setSubmitDate(subDate);

				int managerId = rs.getInt("REIMBURSEMENT_HANDLER");
				r.setRequestHandlerId(managerId);

				int empId = rs.getInt("EMP_ID");
				r.setEmpId(empId);
			}
		} catch (SQLException | IOException e) {
			log.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.info(e.getMessage());
				}
			}
		}
		return r;
	}

	@Override
	public int createRequest(Reimbursement reimbursement) {
		int requestsCreated = 0;
		String sql = "INSERT INTO REIMBURSEMENT (REIMBURSEMENT_DESC, REIMBURSEMENT_STATUS, "
				+ "REIMBURSEMENT_AMT, REIMBURSEMENT_HANDLER, EMP_ID) "
				+ "VALUES (?,?,?,?,?)";

		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, reimbursement.getReason());
			ps.setString(2, reimbursement.getStatus());
			ps.setBigDecimal(3, reimbursement.getAmount());
			//ps.setDate(4, reimbursement.getSubmitDate());
			ps.setInt(4, reimbursement.getRequestHandlerId());
			ps.setInt(5, reimbursement.getEmpId());

			requestsCreated = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			log.info(e.getMessage());
		}

		return requestsCreated;
	}

	@Override
	public int denyRequestById(int id) {
		int requestsDenied = 0;

		String sql = "UPDATE REIMBURSEMENT "
				+ "SET REIMBURSEMENT_STATUS = 'REJECTED' "
				+ "WHERE REIMBURSEMENT_ID = ?";

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){

			ps.setInt(1, id);
			requestsDenied = ps.executeUpdate();
			con.commit(); // CRUD operation should have commit

		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 

		return requestsDenied;
	}

	@Override
	public int approveRequestById(int id) {
		int requestsApproved = 0;

		String sql = "UPDATE REIMBURSEMENT "
				+ "SET REIMBURSEMENT_STATUS = 'APPROVED' "
				+ "WHERE REIMBURSEMENT_ID = ?";

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){

			ps.setInt(1, id);
			requestsApproved = ps.executeUpdate();
			con.commit(); // CRUD operation should have commit

		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 

		return requestsApproved;
	}

}
