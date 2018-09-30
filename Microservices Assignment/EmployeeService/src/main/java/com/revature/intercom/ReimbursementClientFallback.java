package com.revature.intercom;

import java.util.List;

import com.revature.models.Reimbursement;

public class ReimbursementClientFallback implements ReimbursementClient {

	@Override
	public List<Reimbursement> getAllReimbursements(int employeeId) {
		return null;
	}

}
