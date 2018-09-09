package com.revature.doa;

import java.sql.Connection;
import java.util.List;

import com.revature.model.Manager;


public interface ManagerDAO {

	public List<Manager> getManagers();
	public Manager getManagerById(int id);
	public Manager getManagerByUserName(String userName);
	public int updateManager(Manager manager);
	public int updateManager(Manager manager, Connection con);
}
