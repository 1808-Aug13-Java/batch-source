package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursements;
import com.revature.util.ConnectionUtil;

public class ReimbursementsDaoImpl implements ReimbursementsDao {

	@Override
	public int createReimbursement(Reimbursements reimbursement) {
		int reimbursementsCreated = 0;
		// TODO: Create SQL trigger for increment erb_id
		String sql = "INSERT INTO REIMBURSEMENTS (ERB_AMOUNT, ERB_STATUS, MANAGER_ID, EMP_ID) VALUES (?,?,?,?)";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setFloat(1, reimbursement.getReimbursementAmount());
			ps.setString(2, reimbursement.getReimbursementStatus());
			ps.setInt(3, reimbursement.getManagerId());
			ps.setInt(4,  reimbursement.getEmployeeId());
			
			reimbursementsCreated = ps.executeUpdate();
			
			
			
		} catch (SQLException|IOException e) {
			e.printStackTrace();
		}
		
//		if (reimbursementsCreated > 0) {
//			System.out.println("Congratulations, your account has been successfully created!");
//		}
		
		return reimbursementsCreated;
	}

	@Override
	public int approveReimbursementByErbId(int id) {
		int approvedReimbursements = 0;
		String sql = "UPDATE REIMBURSEMENTS SET ERB_STATUS = 'APPROVED' WHERE ERB_ID = ?";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			approvedReimbursements = ps.executeUpdate();
		} catch (SQLException|IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return approvedReimbursements;
	}

	@Override
	//deny reimbursement by id
	public int denyReimbursementByErbId(int id) {
		int deniedReimbursements = 0;
		String sql = "UPDATE REIMBURSEMENTS SET ERB_STATUS = 'DENIED' WHERE ERB_ID = ?";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			deniedReimbursements = ps.executeUpdate();
		} catch (SQLException|IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deniedReimbursements;
	}

	@Override
	public Reimbursements getReimbursementById(int id) {
		Reimbursements r = new Reimbursements();
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE ERB_ID = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				r.setReimbursementId(rs.getInt("ERB_ID"));
				r.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				r.setReimbursementStatus(rs.getString("ERB_STATUS"));
				r.setManagerId(rs.getInt("MANAGER_ID"));
				r.setEmployeeId(rs.getInt("EMP_ID"));
			}
			
			
		} catch (SQLException|IOException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return r;
	}

	@Override
	public List<Reimbursements> getAllPendingReimbursementsByEmployeeId(int id) {
		
		List<Reimbursements> r = new ArrayList<Reimbursements>();
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE ERB_STATUS = 'PENDING' AND EMP_ID = ?";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			
			ps.setInt(1, id);
			rs = ps.executeQuery(sql);
			
			while (rs.next()) {
				Reimbursements reimbursement = new Reimbursements();
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));
				r.add(reimbursement);
			}
			
			
		} catch (SQLException|IOException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		return r;
	}

	@Override
	public List<Reimbursements> getAllResolvedReimbursementsEmployeeId(int id) {
		List<Reimbursements> r = new ArrayList<Reimbursements>();
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE ERB_STATUS = 'APPROVED' OR ERB_STATUS = 'DENIED' AND EMP_ID = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			
			ps.setInt(1, id);
			rs = ps.executeQuery(sql);
			
			while (rs.next()) {
				Reimbursements reimbursement = new Reimbursements();
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));
				r.add(reimbursement);
			}
			
			
		} catch (SQLException|IOException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		return r;
	}

	@Override
	public List<Reimbursements> getReimbursementsByEmployeeId(int id) {
		List<Reimbursements> r = new ArrayList<Reimbursements>();
		
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE EMP_ID = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			
			ps.setInt(1, id);
			rs = ps.executeQuery(sql);
			
			while (rs.next()) {
				Reimbursements reimbursement = new Reimbursements();
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));
				r.add(reimbursement);
			}
			
			
		} catch (SQLException|IOException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		return r;
	}

	@Override
	public List<Reimbursements> getAllPendingReimbursements() {
		List<Reimbursements> r = new ArrayList<Reimbursements>();
		
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE ERB_STATUS = 'PENDING'";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();) {
			rs = s.executeQuery(sql);
			
			while (rs.next()) {
				Reimbursements reimbursement = new Reimbursements();
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));
				r.add(reimbursement);
				
			}
			
		} catch (SQLException|IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return r;
	}

	@Override
	public List<Reimbursements> getAllResolvedReimbursements() {
		List<Reimbursements> r = new ArrayList<Reimbursements>();
		
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE ERB_STATUS = 'APPROVED' OR ERB_STATUS = 'DENIED'";
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();) {
			rs = s.executeQuery(sql);
			
			while (rs.next()) {
				Reimbursements reimbursement = new Reimbursements();
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));
				r.add(reimbursement);
				
			}
		} catch (SQLException|IOException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return r;
	}

	@Override
	public List<Reimbursements> getAllReimbursements() {
		List<Reimbursements> reimbursementsList = new ArrayList<Reimbursements>();
		
		String sql = "SELECT * FROM REIMBURSEMENTS";

		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();) {
			
			rs = s.executeQuery(sql);
			
			while (rs.next()) {
				Reimbursements r = new Reimbursements();
				
				int reimbursementId = rs.getInt("ERB_ID");
				r.setEmployeeId(reimbursementId);
				
				float reimbursementAmount = rs.getFloat("ERB_AMOUNT");
				r.setReimbursementAmount(reimbursementAmount);
				
				String reimbursementStatus = rs.getString("ERB_STATUS");
				r.setReimbursementStatus(reimbursementStatus);
				
				int reimbursementManagerId = rs.getInt("MANAGER_ID");
				r.setManagerId(reimbursementManagerId);
				
				int reimbursementEmployeeId = rs.getInt("EMP_ID");
				r.setEmployeeId(reimbursementEmployeeId);
				
				reimbursementsList.add(r);
				
				
			}
			
		} catch (SQLException|IOException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return reimbursementsList;
	}

	@Override
	public List<Reimbursements> getAllDeniedReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}

}
