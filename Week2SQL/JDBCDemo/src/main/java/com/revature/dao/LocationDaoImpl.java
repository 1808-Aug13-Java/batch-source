package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Location;
import com.revature.util.ConnectionUtil;

public class LocationDaoImpl implements LocationDao{

	public LocationDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	
	public List<Location> getLocations() {
		List<Location> locations = new ArrayList<Location>();
		String sql = "SELECT * FROM LOCATION";
		
		try(Connection con = ConnectionUtil.getConnection();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
				) {
			
			Location l = null;
			while(rs.next()) {
				int id = rs.getInt("location_id");
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = rs.getString("state");
				int zipcode = rs.getInt("zipcode");
				l = new Location(id, street, city, state, zipcode);
				locations.add(l);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return locations;
	}

	
	public Location getLocationById(int id) {
		Location l = null;
		ResultSet rs = null;
		String sql = "select * from location where location_id = ?";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = rs.getString("state");
				int zipcode = rs.getInt("zipcode");
				l = new Location(id, street, city, state, zipcode);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return l;
	}

	
	public int createLocation(Location Location) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateLocation(Location Location) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int deleteLocationById(int id) {
		return 0;
	}


	@Override
	public Location getLocationById(int id, Connection con) {
		Location l = null;
		ResultSet rs = null;
		String sql = "select * from location where location_id = ?";
		try(PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = rs.getString("state");
				int zipcode = rs.getInt("zipcode");
				l = new Location(id, street, city, state, zipcode);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return l;
		
	}

}
