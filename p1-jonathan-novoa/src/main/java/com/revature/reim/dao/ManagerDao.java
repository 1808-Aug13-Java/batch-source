package com.revature.reim.dao;

public interface ManagerDao {
	
	public void viewAllEmployees();
	public void getPendingRequest();
	public void getResolvedRequest();
	public int denyRequest();
	public int approveRequest();
	public void getAllEmployees();
	public void getReimbursementsByEmp();
	public void createEmployee();
	public void createManager();
	
	
}
