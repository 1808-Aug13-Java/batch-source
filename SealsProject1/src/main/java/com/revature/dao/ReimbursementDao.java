package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDao {
 
	public List<Reimbursement> getReimbursement();
	public int createReimbursement(Reimbursement reimbursement);
	public List<Reimbursement> getReimbursementByEmployeeId(int id);
	public int updateReimbursement(Reimbursement reimbursement);
	public List<Reimbursement> getReimbursementsByStatus(int status);
	public List<Reimbursement> getReimbursementByManagerId(int id);
	public List<Reimbursement> getReimbursementById(int id);
	

}
