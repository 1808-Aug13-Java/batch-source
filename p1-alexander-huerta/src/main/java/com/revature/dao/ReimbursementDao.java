package com.revature.dao;

import java.util.List;
import java.sql.Connection;

import com.revature.models.Reimbursement;


public interface ReimbursementDao {
	
	public List<Reimbursement> getReimbursements();
	public Reimbursement getReimbursementById(int id);
	public List<Reimbursement> getReimbursementByEmpId(int empId);
	public List<Reimbursement> getReimbursementByStatus(String status);
	public List<Reimbursement> getReimbursementByManId(int manId);
	public int createReimbursement(Reimbursement reimbursement);
	public int updateReimbursement(Reimbursement reimbursement);
	public int createReimbursement(Reimbursement reimbursement, Connection con);
	public int updateReimbursement(Reimbursement reimbursement, Connection con);
}