package com.revature.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Manager;

public interface ManagerDao {
	
	public Manager getManagerById(long managerId, Connection con) throws SQLException;
	
	public Manager getManagerByUsername(String username, Connection con) throws SQLException;
	
	
	public Manager tryCastManager(Employee e, Connection con) throws SQLException;
	
	
	public List<Manager> getAllManagers(Connection con) throws SQLException;
	
	public long insertManagerRelation(Manager man, Employee emp, Connection con) throws SQLException;
	
	
	
}
