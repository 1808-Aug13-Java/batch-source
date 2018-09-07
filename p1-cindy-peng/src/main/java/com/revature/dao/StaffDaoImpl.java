package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Staff;
import com.revature.util.ConnectionUtil;
import com.revature.util.LoggerSingleton;

public class StaffDaoImpl implements StaffDao {
	private static Logger log = LoggerSingleton.getLogger();
	
	@Override
	public List<Staff> listStaffMembers(String sql) 
	{
		//according to Lint, dont do ArrayList<Staff> cuz List<Staff> already has
		//dont do ArrayList() either. Do empty <>
		List<Staff> list = new ArrayList<>(); 
		ResultSet rs = null;
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql); )
		{
			rs = ps.executeQuery();
	
			while(rs.next())
			{
				int staffId = rs.getInt("STAFF_ID");
				String name = rs.getString("STAFF_NAME");
				String userN = rs.getString("USER_N");
				String passW = rs.getString("PASS_W");
				int manager = rs.getInt("MAN_ID");
				String role = rs.getString("STAFF_ROLE");
				String phone = rs.getString("PHONE");
				
				list.add(new Staff(staffId, name, userN, passW, manager, role, phone));
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
	} // listStaffMembers()

	@Override
	public Staff getStaffByUserName(String userN) {
		
		String sql = "SELECT * FROM P1_STAFF WHERE USER_N = ?";
		ResultSet rs = null;
		Staff staffMember = null;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql); )
		{
			//set those ? params lol. Remember the param index is just 
			//which numbered ? you're filling...
			ps.setString(1, userN);
			rs = ps.executeQuery();
			
			//NOTE**: without rs.next(), itll say "ResultSet.next was not called"
			if(rs.next())
			{
				int staffId = rs.getInt("STAFF_ID");
				String name = rs.getString("STAFF_NAME");
				//already have the username as method parameter
				String passW = rs.getString("PASS_W");
				int manager = rs.getInt("MAN_ID");
				String role = rs.getString("STAFF_ROLE");
				String phone = rs.getString("PHONE");
				
				staffMember = new Staff(staffId, name, userN, 
											  passW, manager, role, phone);
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
		
		return staffMember;
	} // getStaffByUserName()

	@Override
	public int updateStaffProfile(Staff s) {
		
		String sql = "UPDATE P1_STAFF "+
					 "SET STAFF_ID = ?, STAFF_NAME = ?, USER_N = ?, PASS_W = ?, " +
					 	"MAN_ID = ?, STAFF_ROLE = ?, PHONE = ? " +
					 "WHERE STAFF_ID = ?";
		int numRowsChanged = 0;
		
		try( Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql); )
		{
			ps.setInt(1, s.getStaffId());
			ps.setString(2, s.getStaffName());
			ps.setString(3, s.getUserN());
			ps.setString(4, s.getPassW());
			ps.setInt(5, s.getManId());
			ps.setString(6, s.getStaffRole());
			ps.setString(7, s.getPhone());
			ps.setInt(8, s.getStaffId());
			
			numRowsChanged = ps.executeUpdate();
			
		} catch (SQLException | IOException e) {
			log.error(e.getMessage());
		}
		
		return numRowsChanged;
	}// updateStaffProfile()

}
