package com.revature.doa;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reimbursment;
import com.revature.util.ConnectionUtil;

public class ReimbursmentDAOImpl implements ReimbursmentDAO {

	@Override
	public List<Reimbursment> getReimbursments() {
		List<Reimbursment> reimbList = new ArrayList<>();
		String sql = "SELECT * FROM REIMBURSMENT";

		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)) {
			while (rs.next()) {
				Reimbursment r = new Reimbursment();
				r.setReimbId(rs.getInt("REIMB_ID"));
				r.setEmpId(rs.getInt("EMP_ID"));
				r.setStatus(rs.getString("STATUS"));
				r.setSubDate(rs.getDate("SUB_DATE"));
				r.setResolvedDate(rs.getDate("RESOLV_DATE"));
				r.setDescription(rs.getString("DESCRIPTION"));
				r.setResolvedBy(rs.getInt("RESOLVED_BY"));
				reimbList.add(r);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			// REPLACE WITH LOG4J
		}
		return reimbList;
	}

	@Override
	public Reimbursment getReimbursmentById(int id) {
		String sql = "SELECT * FROM REIMBURSMENT WHERE REIMB_ID = ?";
		Reimbursment r = new Reimbursment();
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				r.setReimbId(rs.getInt("REIMB_ID"));
				r.setEmpId(rs.getInt("EMP_ID"));
				r.setStatus(rs.getString("STATUS"));
				r.setSubDate(rs.getDate("SUB_DATE"));
				r.setResolvedDate(rs.getDate("RESOLV_DATE"));
				r.setDescription(rs.getString("DESCRIPTION"));
				r.setResolvedBy(rs.getInt("RESOLVED_BY"));
			}
		} catch (SQLException | IOException e1) {
			// TODO log4j
			e1.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					if (rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException e1) {
					// TODO log4j
					e1.printStackTrace();
				}
			}
		}
		return r;
	}

	@Override
	public List<Reimbursment> getReimbursmentByEmpId(int empId) {
		List<Reimbursment> reimbList = new ArrayList<>();
		String sql = "SELECT * FROM REIMBURSMENT WHERE EMP_ID = ?";
		Reimbursment r = new Reimbursment();
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, empId);
			rs = ps.executeQuery();

			while (rs.next()) {
				r.setReimbId(rs.getInt("REIMB_ID"));
				r.setEmpId(rs.getInt("EMP_ID"));
				r.setStatus(rs.getString("STATUS"));
				r.setSubDate(rs.getDate("SUB_DATE"));
				r.setResolvedDate(rs.getDate("RESOLV_DATE"));
				r.setDescription(rs.getString("DESCRIPTION"));
				r.setResolvedBy(rs.getInt("RESOLVED_BY"));
				reimbList.add(r);
			}
		} catch (SQLException | IOException e1) {
			// TODO log4j
			e1.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					if (rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException e1) {
					// TODO log4j
					e1.printStackTrace();
				}
			}
		}
		return reimbList;
	}

	@Override
	public List<Reimbursment> getReimbursmentByStatus(String status) {
		List<Reimbursment> reimbList = new ArrayList<>();
		String sql = "SELECT * FROM REIMBURSMENT WHERE STATUS = ?";
		Reimbursment r = new Reimbursment();
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, status);
			rs = ps.executeQuery();

			while (rs.next()) {
				r.setReimbId(rs.getInt("REIMB_ID"));
				r.setEmpId(rs.getInt("EMP_ID"));
				r.setStatus(rs.getString("STATUS"));
				r.setSubDate(rs.getDate("SUB_DATE"));
				r.setResolvedDate(rs.getDate("RESOLV_DATE"));
				r.setDescription(rs.getString("DESCRIPTION"));
				r.setResolvedBy(rs.getInt("RESOLVED_BY"));
				reimbList.add(r);
			}
		} catch (SQLException | IOException e1) {
			// TODO log4j
			e1.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					if (rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException e1) {
					// TODO log4j
					e1.printStackTrace();
				}
			}
		}
		return reimbList;
	}

	@Override
	public int createReimbursment(Reimbursment reimbursment) {

		int reimbursmentsCreated = 0;
		String sql = "INSERT INTO REIMBURSMENT (EMP_ID, STATUS, DESCRIPTION) VALUES (?, ?, ?)";
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, reimbursment.getEmpId());
			ps.setString(2, reimbursment.getStatus());
			ps.setString(3, reimbursment.getDescription());
			reimbursmentsCreated = ps.executeUpdate();

		} catch (SQLException | IOException e) {
			// TODO Log4j
			e.printStackTrace();
		}
		return reimbursmentsCreated;
	}

	@Override
	public int updateReimbursment(Reimbursment reimbursment) {
		int reimbUpdated = 0;

		String sql = "UPDATE REIMBURSMENT " + "SET STATUS = ?, " + "RESOLVED_BY = ? " + "WHERE REIMB_ID = ?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, reimbursment.getStatus());
			ps.setInt(2, reimbursment.getResolvedBy());
			ps.setInt(3, reimbursment.getReimbId());
			reimbUpdated = ps.executeUpdate();

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reimbUpdated;
	}

	@Override
	public int createReimbursment(Reimbursment reimbursment, Connection con) {

		int reimbursmentsCreated = 0;
		String sql = "INSERT INTO REIMBURSMENT (EMP_ID, STATUS, DESCRIPTION) VALUES (?, ?, ?)";
		ResultSet rs = null;

		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, reimbursment.getEmpId());
			ps.setString(2, reimbursment.getStatus());
			ps.setString(3, reimbursment.getDescription());
			reimbursmentsCreated = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Log4j
			e.printStackTrace();
		}
		return reimbursmentsCreated;
	}

	@Override
	public int updateReimbursment(Reimbursment reimbursment, Connection con) {

		int reimbUpdated = 0;
		String sql = "UPDATE REIMBURSMENT " + "SET STATUS = ?, " + "RESOLVED_BY = ? " + "WHERE REIMB_ID = ?";

		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, reimbursment.getStatus());
			ps.setInt(2, reimbursment.getResolvedBy());
			ps.setInt(3, reimbursment.getReimbId());
			reimbUpdated = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reimbUpdated;
	}

}
