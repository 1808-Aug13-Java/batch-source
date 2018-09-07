package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursements;

public interface ReimbursementsDao {
	
	int createReimbursement(Reimbursements reimbursement);
	int approveReimbursementByErbId(int id);
	int denyReimbursementByErbId(int id);
	
	Reimbursements getReimbursementById(int id);
	

	List<Reimbursements> getAllDeniedReimbursements();
	List<Reimbursements> getAllPendingReimbursementsByEmployeeId(int id);
	List<Reimbursements> getAllResolvedReimbursementsEmployeeId(int id);
	List<Reimbursements> getReimbursementsByEmployeeId(int id);
	List<Reimbursements> getAllPendingReimbursements();
	List<Reimbursements> getAllResolvedReimbursements();
	
	List<Reimbursements> getAllReimbursements();

	
}
