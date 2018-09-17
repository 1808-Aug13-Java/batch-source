package com.revature.daos;

import java.util.List;

import org.hibernate.Session;

import com.revature.models.Bear;
import com.revature.util.HibernateUtil;

public interface BearDao {
	
	public List<Bear> getBears();
	public Bear getBearById (int id);
	public int createBear(Bear b);
	public List<Bear> getBearsByCave(int caveId);
	public List<Bear> getBearsByName(String name);
	public List<Bear> getSBears();
	public int getBearCount();
	public List<Bear> get90andGBears();
	public List<Bear> get90orGBears(); 


}
