package com.revature.dao;

import java.util.List;

import com.revature.models.Bear;

public interface BearDao {
	public List<Bear> getBears();
	public Bear getBearById(int id);
	public int createBear(Bear b);
	public void updateBear(Bear b);
	public void deleteBearById(int id);
	public List<Bear> getBearsByCave(int caveId);
	public List<Bear> getBearByName(String name);
	public List<Bear> getSBears();
	public Long getBearCount();
	public List<Bear> get90andGBear();
	public List<Bear> get90orGBear();
}
