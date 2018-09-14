package com.revature.daos;

import java.util.List;

import com.revature.models.Bear;

public interface BearDao {
	public List<Bear> getBears();
	public Bear getBearById(int id);
	public int createBear(Bear b);
	List<Bear> getBearsByCave(int id);
	public List<Bear> getBearsByName(String name);
	public List<Bear> getSBears();
	public long getBearCount();
}
