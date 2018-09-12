package com.revature.dao;

import java.util.List;

import com.revature.models.Manager;

public interface ManagerDao {

	public Manager getManagerById(int id);
	public List<Manager> getManagers();
	public int createManager(Manager manager);
}
