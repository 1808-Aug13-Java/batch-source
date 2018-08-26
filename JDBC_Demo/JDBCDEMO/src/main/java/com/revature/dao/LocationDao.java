package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.model.Location;

public interface LocationDao {

	public List<Location> getLocations();
	public Location getLocationById(int id);
	public int createLocation(Location location);
	public int updateLocation(Location location);
	public int deleteLocationById(int id);
	public Location getLocationById(int id, Connection con);
}
