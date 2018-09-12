package com.revature.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;
import com.revature.util.LoggerSingleton;

public class ReimbursementDaoImpl implements ReimbursementDao {
	private static Logger log = LoggerSingleton.getLogger();
	
	@Override
	public List<Reimbursement> listReimbursements(String sql) {
		List<Reimbursement> list = new ArrayList<>(); 
		ResultSet rs = null;
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql); )
		{
			rs = ps.executeQuery();
	
			while(rs.next())
			{		
				int reqId = rs.getInt("REQUEST_ID");
				int sId = rs.getInt("STAFF_ID");
				String status = rs.getString("STATUS");
				int manId = rs.getInt("MAN_ID");
				float amount = rs.getFloat("AMOUNT");
				String date = rs.getString("REQ_DATE");
				String msg = rs.getString("MESSAGE");
				
				list.add(new Reimbursement(reqId, sId, status, manId,
						 amount, date, msg));
			}
			
		} catch (SQLException | IOException e) {
			log.error(e.getMessage());
		} finally {
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
		} // try/catch/finally clause
		
		return list;
	}

	@Override
	public Reimbursement getRequestById(int requestId) {
		String sql = "SELECT * FROM P1_REIMBURSEMENTS WHERE REQUEST_ID = ?";
		ResultSet rs = null;
		Reimbursement reimbursementRequest = null;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql); )
		{
			//set those ? params lol. Remember the param index is just 
			//which numbered ? you're filling...
			ps.setInt(1, requestId);
			rs = ps.executeQuery();
			
			//NOTE**: without rs.next(), itll say "ResultSet.next was not called"
			if(rs.next())
			{			
				int sId = rs.getInt("STAFF_ID");
				String status = rs.getString("STATUS");
				int manId = rs.getInt("MAN_ID");
				float amount = rs.getFloat("AMOUNT");
				String date = rs.getString("REQ_DATE");
				String msg = rs.getString("MESSAGE");
			    
				reimbursementRequest = new Reimbursement(requestId, sId, status, manId,
						 								 amount, date, msg);
			}
				
		} catch (SQLException | IOException e) {
			log.error(e.getMessage());
		} finally {
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
		} // try/catch/finally block
		
		return reimbursementRequest;
	}

	@Override 
	public int createRequest(Reimbursement r)
	{
		String sql = "INSERT INTO P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT,  MESSAGE) " +
					"VALUES(?, ?, ?, ?, ?)";
		int numRowsChanged = 0;
		try(Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql); )
		{
			ps.setInt(1, r.getStaffId());
			ps.setString(2, r.getStatus());
			ps.setInt(3, r.getManId());
			ps.setFloat(4, r.getAmount());
//			ps.setString(5, r.getReqDate());
			ps.setString(5, r.getMessage());
			
			numRowsChanged = ps.executeUpdate();
			
		} catch (SQLException | IOException e) {
			log.error(e.getMessage());
		}
		return numRowsChanged;
	}
	
	@Override
	public int updateRequest(Reimbursement r) {  
		String sql = "UPDATE P1_REIMBURSEMENTS "+
				 "SET STAFF_ID = ?, STATUS = ?, MAN_ID = ?, " +
//				 	"AMOUNT = ?, REQ_DATE = ?, MESSAGE = ? " +
					"AMOUNT = ?, MESSAGE = ? " +
				 "WHERE REQUEST_ID = ?";
		int numRowsChanged = 0;
		
		try( Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql); )
		{	
			ps.setInt(1, r.getStaffId());
			ps.setString(2, r.getStatus());
			ps.setInt(3, r.getManId());
			ps.setFloat(4, r.getAmount());
//			ps.setString(5, r.getReqDate());
			ps.setString(5, r.getMessage());
			ps.setInt(6, r.getRequestId());
			
			numRowsChanged = ps.executeUpdate();
			
		} catch (SQLException | IOException e) {
			log.error(e.getMessage());
		}
		
		return numRowsChanged;
	}

}
