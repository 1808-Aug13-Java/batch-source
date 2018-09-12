package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao{

	@Override
	public Reimbursement getReimbursementByRId(int RId) {
		Reimbursement r = new Reimbursement();
		
		String sql = "SELECT * FROM P1_REIMBURSEMENT WHERE R_ID = ?";
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, RId);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int reimbId = rs.getInt("R_ID");
				r.setRId(reimbId);
				
				int empId = rs.getInt("EMP_ID");
				r.setEmpId(empId);
				
				int manId = rs.getInt("MAN_ID");
				r.setManId(manId);
				
				String status = rs.getString("STATUS");
				r.setStatus(status);
				
				String action = rs.getString("ACTION");
				r.setAction(action);
				
				String desc = rs.getString("DESCRIPTION");
				r.setDescription(desc);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!= null){
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
	public List<Reimbursement> getReimbursements() {
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		
		String sql = "SELECT * FROM P1_REIMBURSEMENT";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement();
				int reimbId = rs.getInt("R_ID");
				r.setRId(reimbId);
				
				int empId = rs.getInt("EMP_ID");
				r.setEmpId(empId);
				
				int manId = rs.getInt("MAN_ID");
				r.setManId(manId);
				
				String status = rs.getString("STATUS");
				r.setStatus(status);
				
				String action = rs.getString("ACTION");
				r.setAction(action);
				
				String desc = rs.getString("DESCRIPTION");
				r.setDescription(desc);
				
				reimbList.add(r);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return reimbList;
	}

	@Override
	public List<Reimbursement> getReimbursementsByEmpId(int empId) {
		List<Reimbursement> reimbList = new ArrayList<>();
		
		String sql = "SELECT * FROM P1_REIMBURSEMENT WHERE EMP_ID = ?";
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, empId);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement();
				int reimbId = rs.getInt("R_ID");
				r.setRId(reimbId);
				
				r.setEmpId(empId);
				
				int manId = rs.getInt("MAN_ID");
				r.setManId(manId);
				
				String status = rs.getString("STATUS");
				r.setStatus(status);
				
				String action = rs.getString("ACTION");
				r.setAction(action);
				
				String desc = rs.getString("DESCRIPTION");
				r.setDescription(desc);
				reimbList.add(r);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!= null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return reimbList;
	}

	@Override
	public int createReimbursement(Reimbursement reimb) {
		int reimbCreated = 0;
		
		String sql = "INSERT INTO P1_REIMBURSEMENT(EMP_ID, MAN_ID, "
		+ "STATUS, ACTION, DESCRIPTION) VALUES (?, ?, ?, ?, ?)";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, reimb.getEmpId());
			ps.setInt(2, reimb.getManId());
			ps.setString(3, reimb.getStatus());
			ps.setString(4, reimb.getAction());
			ps.setString(5, reimb.getDescription());
			reimbCreated = ps.executeUpdate();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return reimbCreated;
	}

	@Override
	public int updateReimbursement(Reimbursement reimb) {
		int reimbUpdated = 0;
		
		String sql = "UPDATE P1_REIMBURSEMENT "
				+ "SET EMP_ID = ? ,"
				+ " MAN_ID = ? ,"
				+ " STATUS = ? ,"
				+ " ACTION = ? ,"
				+ " DESCRIPTION = ? "
				+ "WHERE R_ID = ?";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, reimb.getEmpId());
			ps.setInt(2, reimb.getManId());
			ps.setString(3, reimb.getStatus());
			ps.setString(4, reimb.getAction());
			ps.setString(5, reimb.getDescription());
			ps.setInt(6, reimb.getRId());
			reimbUpdated = ps.executeUpdate();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return reimbUpdated;
	}
}
