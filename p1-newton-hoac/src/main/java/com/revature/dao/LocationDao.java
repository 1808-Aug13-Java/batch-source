package com.revature.dao;

import java.util.List;

import com.revature.models.Location;

public interface LocationDao {
	public Location getLocationById(int locId);
	public List<Location> getLocations();
	public int updateLocation(Location location);
	public int createLocation(Location location);
}
