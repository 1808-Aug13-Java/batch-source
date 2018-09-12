package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImp implements ReimbursementDao {
	private static Logger log = Logger.getRootLogger();

	@Override
	public List<Reimbursement> getReimbursements() {
		List<Reimbursement> reimbList = new ArrayList<>();
		String sql = "SELECT * FROM REIMBURSEMENT";
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)) {
			while (rs.next()) {
				Reimbursement r = new Reimbursement();
				r.setReimbId(rs.getInt("REIMB_ID"));
				r.setEmpId(rs.getInt("EMP_ID"));
				r.setStatus(rs.getString("STATUS"));
				r.setDescription(rs.getString("DESCRIPTION"));
				r.setResolvedBy(rs.getInt("RESOLVED_BY"));
				reimbList.add(r);
			}
		} catch (IOException | SQLException a) {
			log.error(a);
		}
		return reimbList;
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		String sql = "SELECT * FROM Reimbursement WHERE REIMB_ID = ?";
		Reimbursement r = new Reimbursement();
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				r.setReimbId(rs.getInt("REIMB_ID"));
				r.setEmpId(rs.getInt("EMP_ID"));
				r.setStatus(rs.getString("STATUS"));
				r.setDescription(rs.getString("DESCRIPTION"));
				r.setResolvedBy(rs.getInt("RESOLVED_BY"));
			}
		} catch (SQLException | IOException a) {
			log.error(a);
		} finally {
			if (rs != null) {
				try {
					if (!rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException a) {
					log.error(a);
				}
			}
		}
		return r;
	}

	@Override
	public List<Reimbursement> getReimbursementByManId(int manId) {
		List<Reimbursement> reimbList = new ArrayList<>();
		String sql = "SELECT * FROM Reimbursement WHERE RESOLVED_BY = ?";
		Reimbursement r = new Reimbursement();
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, manId);
			rs = ps.executeQuery();

			while (rs.next()) {
				r.setReimbId(rs.getInt("REIMB_ID"));
				r.setEmpId(rs.getInt("EMP_ID"));
				r.setStatus(rs.getString("STATUS"));
				r.setDescription(rs.getString("DESCRIPTION"));
				r.setResolvedBy(rs.getInt("RESOLVED_BY"));
				reimbList.add(r);
			}
		} catch (SQLException | IOException a) {
			log.error(a);
		} finally {
			if (rs != null) {
				try {
					if (!rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException a) {
					log.error(a);
				}
			}
		}
		return reimbList;
	}

	@Override
	public List<Reimbursement> getReimbursementByEmpId(int empId) {
		List<Reimbursement> reimbList = new ArrayList<>();
		String sql = "SELECT * FROM Reimbursement WHERE EMP_ID = ?";
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, empId);
			rs = ps.executeQuery();

			while (rs.next()) {
				Reimbursement r = new Reimbursement();
				r.setReimbId(rs.getInt("REIMB_ID"));
				r.setEmpId(rs.getInt("EMP_ID"));
				r.setStatus(rs.getString("STATUS"));
				r.setDescription(rs.getString("DESCRIPTION"));
				r.setResolvedBy(rs.getInt("RESOLVED_BY"));
				reimbList.add(r);
			}
		} catch (SQLException | IOException a) {
			log.error(a);
		} finally {
			if (rs != null) {
				try {
					if (!rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException a) {
					log.error(a);
				}
			}
		}
		return reimbList;
	}

	@Override
	public List<Reimbursement> getReimbursementByStatus(String status) {
		List<Reimbursement> reimbList = new ArrayList<>();
		String sql = "SELECT * FROM Reimbursement WHERE STATUS = ?";
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, status);
			rs = ps.executeQuery();

			while (rs.next()) {
				Reimbursement r = new Reimbursement();
				r.setReimbId(rs.getInt("REIMB_ID"));
				r.setEmpId(rs.getInt("EMP_ID"));
				r.setStatus(rs.getString("STATUS"));
				r.setDescription(rs.getString("DESCRIPTION"));
				r.setResolvedBy(rs.getInt("RESOLVED_BY"));
				reimbList.add(r);
			}
		} catch (SQLException | IOException a) {
			log.error(a);
		} finally {
			if (rs != null) {
				try {
					if (!rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException a) {
					log.error(a);
				}
			}
		}
		return reimbList;
	}

	@Override
	public int createReimbursement(Reimbursement Reimbursement) {

		int ReimbursementsCreated = 0;
		String sql = "INSERT INTO Reimbursement (EMP_ID, STATUS, DESCRIPTION) VALUES (?, ?, ?)";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, Reimbursement.getEmpId());
			ps.setString(2, Reimbursement.getStatus());
			ps.setString(3, Reimbursement.getDescription());
			ReimbursementsCreated = ps.executeUpdate();

		} catch (SQLException | IOException a) {
			log.error(a);
		}
		return ReimbursementsCreated;
	}

	@Override
	public int updateReimbursement(Reimbursement Reimbursement) {
		int reimbUpdated = 0;

		String sql = "UPDATE Reimbursement " + "SET STATUS = ?, " + "RESOLVED_BY = ? " + "WHERE REIMB_ID = ?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, Reimbursement.getStatus());
			ps.setInt(2, Reimbursement.getResolvedBy());
			ps.setInt(3, Reimbursement.getReimbId());
			reimbUpdated = ps.executeUpdate();

		} catch (SQLException | IOException a) {
			log.error(a);
		}

		return reimbUpdated;
	}

	@Override
	public int createReimbursement(Reimbursement Reimbursement, Connection con) {

		int ReimbursementsCreated = 0;
		String sql = "INSERT INTO Reimbursement (EMP_ID, STATUS, DESCRIPTION) VALUES (?, ?, ?)";

		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, Reimbursement.getEmpId());
			ps.setString(2, Reimbursement.getStatus());
			ps.setString(3, Reimbursement.getDescription());
			ReimbursementsCreated = ps.executeUpdate();

		} catch (SQLException a) {
			log.error(a);
		}
		return ReimbursementsCreated;
	}

	@Override
	public int updateReimbursement(Reimbursement Reimbursement, Connection con) {

		int reimbUpdated = 0;
		String sql = "UPDATE Reimbursement " + "SET STATUS = ?, " + "RESOLVED_BY = ? " + "WHERE REIMB_ID = ?";

		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, Reimbursement.getStatus());
			ps.setInt(2, Reimbursement.getResolvedBy());
			ps.setInt(3, Reimbursement.getReimbId());
			reimbUpdated = ps.executeUpdate();

		} catch (SQLException a) {
			log.error(a);
		}

		return reimbUpdated;
	}

}