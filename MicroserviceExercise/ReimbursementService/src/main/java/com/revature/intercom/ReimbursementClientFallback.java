package com.revature.intercom;

import java.util.List;

import org.springframework.stereotype.Component;

import com.revature.models.Reimbursement;

@Component
public class ReimbursementClientFallback implements ReimbursementClient{

	@Override
	public List<Reimbursement> getAllReimbursements(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

}