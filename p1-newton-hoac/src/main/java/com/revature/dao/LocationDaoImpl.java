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
import com.revature.util.ConnectionUtil;

public class LocationDaoImpl implements LocationDao{

	@Override
	public Location getLocationById(int locId) {
		Location l = new Location();
		
		String sql = "SELECT * FROM P1_LOCATION WHERE LOC_ID = ?";
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, locId);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int locationId = rs.getInt("LOC_ID");
				l.setlocId(locationId);
				
				String street = rs.getString("STREET");
				l.setStreet(street);
				
				String city = rs.getString("CITY");
				l.setCity(city);
				
				String state = rs.getString("STATE");
				l.setState(state);
				
				int zip = rs.getInt("ZIP");
				l.setZip(zip);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return l;
	}
	
	@Override
	public Location getLocationById(int locId, Connection con) {
		Location l = new Location();
		
		String sql = "SELECT * FROM P1_LOCATION WHERE LOC_ID = ?";
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, locId);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int locationId = rs.getInt("LOC_ID");
				l.setlocId(locationId);
				
				String street = rs.getString("STREET");
				l.setStreet(street);
				
				String city = rs.getString("CITY");
				l.setCity(city);
				
				String state = rs.getString("STATE");
				l.setState(state);
				
				int zip = rs.getInt("ZIP");
				l.setZip(zip);
			}
		} catch (SQLException e) {
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
		return l;
	}

	@Override
	public List<Location> getLocations() {
		List<Location> locList = new ArrayList<Location>();
		
		String sql = "SELECT * FROM P1_LOCATION";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while(rs.next()) {
				Location l = new Location();
				int locationId = rs.getInt("LOC_ID");
				l.setlocId(locationId);
				
				String street = rs.getString("STREET");
				l.setStreet(street);
				
				String city = rs.getString("CITY");
				l.setCity(city);
				
				String state = rs.getString("STATE");
				l.setState(state);
				
				int zip = rs.getInt("ZIP");
				l.setZip(zip);
				
				locList.add(l);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
		return locList;
	}

	@Override
	public int updateLocation(Location location) {
		int locsUpdated = 0;
		String sql = "UPDATE P1_LOCATION "
				+ "SET STREET = ? ,"
				+ " CITY = ? ,"
				+ " STATE = ? ,"
				+ " ZIP = ? "
				+ "WHERE LOC_ID = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, location.getStreet());
			ps.setString(2, location.getCity());
			ps.setString(3, location.getState());
			ps.setInt(4, location.getZip());
			ps.setInt(5, location.getlocId());
			locsUpdated = ps.executeUpdate();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return locsUpdated;
	}
	
	@Override
	public int createLocation(Location location) {
		int locsCreated = 0;
		
		String sql = "INSERT INTO P1_LOCATION(STREET, CITY, STATE, ZIP) "
				+ "VALUES (?, ?, ?, ?)";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, location.getStreet());
			ps.setString(2, location.getCity());
			ps.setString(3, location.getState());
			ps.setInt(4, location.getZip());
			locsCreated = ps.executeUpdate();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return locsCreated;
	}
}
