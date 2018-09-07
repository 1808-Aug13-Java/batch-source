package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.model.Reimbursement;

/**
 * @author nozuko
 *
 */
public interface ReimbursementDao {

	public List<Reimbursement> getRequests();
	public Reimbursement getRequestById(int id);
	public Reimbursement getRequestById(int id, Connection con);
	public int createRequest(Reimbursement reimbursement); // employee can submit a reimbursement request
	public int updateReqStatus(Reimbursement reimbursement); // manager can approve/deny pending reimbursement request
}
