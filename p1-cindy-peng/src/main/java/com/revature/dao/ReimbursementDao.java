package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDao {
		//I use List not ArrayList because lint told me not to :((((
	public List<Reimbursement> listReimbursements(String sql);
	public Reimbursement getRequestById(int requestId);
	public int updateRequest(Reimbursement r);
	public int createRequest(Reimbursement r);
}
