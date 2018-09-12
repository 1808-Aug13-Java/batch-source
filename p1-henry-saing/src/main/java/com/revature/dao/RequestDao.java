package com.revature.dao;

import java.util.List;

import com.revature.model.Request;


public interface RequestDao {
	public boolean createReimbursement(double amount, int empId);
	public List<Request> getRequestsbyPendingbyEmployee(int empId);
	public List<Request> getRequestsbyResolvedbyEmployee(int empId);
	public List<Request> getAllPendingRequests();
	public List<Request> getAllRequestsFromPerson(int empId);
	public List<Request> getAllRequestsResolvedByManager(String name);
	public List<Request> getRequestsbyEmployee(int empId);
	public List<Request> getAllEmployees();
	public boolean updateReinbursement(int approve, int reqId);
	
	
}

