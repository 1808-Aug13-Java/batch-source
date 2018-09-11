package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Location;
import com.revature.models.Profile;
import com.revature.util.ConnectionUtil;

public class ProfileDaoImpl implements ProfileDao{

	@Override
	public Profile getProfileById(int id) {
		Profile p = new Profile();
		
		String sql = "SELECT * FROM P1_PROFILE WHERE EMP_ID = ?";
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String firstname = rs.getString("FIRSTNAME");
				p.setFirstname(firstname);
				
				String lastname = rs.getString("LASTNAME");
				p.setLastname(lastname);
				
				int locId = rs.getInt("LOC_ID");
				LocationDao ldi = new LocationDaoImpl();
				Location l = ldi.getLocationById(locId);
				p.setLocId(l);
				
				int phone = rs.getInt("PHONE");
				p.setPhone(phone);
				
				String email = rs.getString("EMAIL");
				p.setEmail(email);
				
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return p;
	}

	@Override
	public List<Profile> getProfiles() {
		List<Profile> profileList = new ArrayList<Profile>();
		
		String sql = "SELECT * FROM P1_PROFILE";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while(rs.next()) {
				Profile p = new Profile();
				String firstname = rs.getString("FIRSTNAME");
				p.setFirstname(firstname);
				
				String lastname = rs.getString("LASTNAME");
				p.setLastname(lastname);
				
				int locId = rs.getInt("LOC_ID");
				LocationDao ldi = new LocationDaoImpl();
				Location l = ldi.getLocationById(locId);
				p.setLocId(l);
				
				int phone = rs.getInt("PHONE");
				p.setPhone(phone);
				
				String email = rs.getString("EMAIL");
				p.setEmail(email);
				
				profileList.add(p);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}		
		return profileList;
	}

	@Override
	public int updateProfile(Profile profile) {
		int profsUpdated = 0;
		String sql = "UPDATE P1_PROFILE "
				+ "SET FIRSTNAME = ? "
				+ "SET LASTNAME = ? "
				+ "SET LOC_ID = ? "
				+ "SET PHONE = ? "
				+ "SET EMAIL = ? "
				+ "WHERE EMP_ID = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, profile.getFirstname());
			ps.setString(2,  profile.getLastname());
			ps.setInt(3, profile.getLocId().getlocId());
			ps.setInt(4, profile.getPhone());
			ps.setString(5, profile.getEmail());
			ps.setInt(6, profile.getId());
			profsUpdated = ps.executeUpdate();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return profsUpdated;
	}

}
