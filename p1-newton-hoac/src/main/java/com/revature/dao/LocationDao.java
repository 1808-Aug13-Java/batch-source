package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.models.Location;

public interface LocationDao {
	public Location getLocationById(int locId);
	public Location getLocationById(int locId, Connection con);
	public List<Location> getLocations();
	public int updateLocation(Location location);
	public int createLocation(Location location);
}
