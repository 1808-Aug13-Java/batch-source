package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Location;
import com.revature.util.connectionUtil;

public class LocationDaoImpl implements LocationDao{

	@Override
	public List<Location> getLocations() {
	List<Location> locationList = new ArrayList<Location>();
		
		String sql = "SELECT * FROM LOCATIONS";
		
		try (
			Connection con = connectionUtil.getConnection();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql)){
			while(rs.next()) {
				Location l = new Location();
				String street = rs.getString("STREET");

				String city = rs.getString("CITY");

				String state = rs.getString("STATE");

				int zipcode = rs.getInt("ZIPCODE");
				
				int id = rs.getInt("LOCATION_ID");

				l = new Location(id, street, city, state, zipcode);
				
				locationList.add(l);
			}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return locationList;
	}

	@Override
	public Location getLocationById(int id) {
Location l = null;

		

		String sql = "SELECT * FROM LOCATIONS WHERE LOCATION_ID = ?";

		ResultSet rs = null;

		

		try(Connection con = connectionUtil.getConnection();

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
	public int deletebLocationstById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
