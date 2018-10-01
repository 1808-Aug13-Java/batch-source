package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursment;

public interface ReimbursmentDao {
	
	int createReimbursment(Reimbursment reimbursment);
	int denyReimbursmentById(int id, int managerId);
	int approveRequestById(int id, int managerId);
	Reimbursment getReimbursmentById(int id);
	List<Reimbursment> getPendingByEmployeeId(int id);
	List<Reimbursment> getResolvedByEmployeeId(int id);
	List<Reimbursment> getAllReimbursments();
	List<Reimbursment> getReimbursmentsByEmployeeId(int id);
	List<Reimbursment> getAllPendingReimbursments();
	List<Reimbursment> getAllResolvedReimbursments();
	
}
