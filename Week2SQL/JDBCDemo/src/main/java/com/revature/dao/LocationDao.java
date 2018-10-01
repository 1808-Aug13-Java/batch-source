package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.model.Location;

public interface LocationDao {

	
	public List<Location> getLocations();
	public Location getLocationById(int id);
	public Location getLocationById(int id, Connection con);
	public int createLocation(Location Location);
	public int updateLocation(Location Location);
	public int deleteLocationById(int id);
	
}
