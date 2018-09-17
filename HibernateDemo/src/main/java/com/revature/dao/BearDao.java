package com.revature.dao;

import java.util.List;

import com.revature.models.Bear;

public interface BearDao {
	
	public List<Bear> getBears();
	public Bear getBearById(int id);
	public int createBear(Bear b);
	public List<Bear> getBearsByCave(int caveId);
	public List<Bear> getBearsByName(String name);
	public List<Bear> getSBears();
	public int getBearCount();
	public List<Bear> get90andGBear();
	public List<Bear> get90orGBear();

}
