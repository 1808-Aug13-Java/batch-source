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

	@Override
	public List<Location> getLocations() {
		// TODO Auto-generated method stub
		List<Location> locationList = new ArrayList<>();
		String sql="SELECT * FROM LOCATIONS";
		
		
		try (
				Connection con =ConnectionUtil.getConnection();
				Statement s=con.createStatement(); //no user input so statement is okay
				ResultSet rs=s.executeQuery(sql) ;		
				)
				{					
					while( rs.next() )
					{
					Location l = new Location();
					int locId= rs.getInt("LOC_ID");
					l.setid(locId);
					
					String street=rs.getString("STREET");
					l.setStreet(street);
					
					String city=rs.getString("CITY");
					l.setCity(city);
					
					String state=rs.getString("STATE");
					l.setState(state);
			
					int zipcode=rs.getInt("ZIPCODE");
					l.setZipcode(zipcode);
						
					locationList.add(l);
				}
			} catch (IOException | SQLException l) {
				// TODO Auto-generated catch block
				l.printStackTrace();
			}

		
		return locationList;
	}

	@Override
	public Location getLocationById(int id) {
		// TODO Auto-generated method stub
Location l = null;
		
		String sql = "SELECT * FROM LOCATIONS WHERE LOCATION_ID = ?";
		ResultSet rs = null;
		
		try(Connection con = ConnectionUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String street = rs.getString("STREET");
				String city = rs.getString("CITY");
				String state = rs.getString("STATE");
				int zipcode = rs.getInt("ZIPCODE");
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
	
	@Override
	public Location getLocationById(int id, Connection con) {
		Location l = null;
		
		String sql = "SELECT * FROM LOCATIONS WHERE LOC_ID = ?";
		ResultSet rs = null;
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String street = rs.getString("STREET");
				String city = rs.getString("CITY");
				String state = rs.getString("STATE");
				int zipcode = rs.getInt("ZIPCODE");
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

	@Override
	public int createLocation(Location location) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateLocation(Location location) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteLocationById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
