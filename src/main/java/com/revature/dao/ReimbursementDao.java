package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.model.Reimbursement;

/**
 * @author nozuko
 *
 */
public interface ReimbursementDao {
	
	// methods to retrieve the reimbursement requests
	public List<Reimbursement> getAllRequests();
	public List<Reimbursement> getRequestsById(int id);
	public List<Reimbursement> getAllPendingRequests();
	public List<Reimbursement> getPendingRequestsById(int id);
	public List<Reimbursement> getAllResolvedRequests();
	public List<Reimbursement> getResolvedRequestsById(int id);
	
	public Reimbursement getSingleRequestById(int id);
	//public Reimbursement getSingleRequestById(int id, Connection con); // overload
	
	// employee can submit a reimbursement request
	public int createRequest(Reimbursement reimbursement);
	
	//(maybe in future do method to get requests by date)
	
	// methods for managers to resolve (approve/deny) reimbursement requests
	public int denyRequestById(int id);
	public int approveRequestById(int id);
	
}
