package com.chandrika.intercom;

import java.util.List;

import org.springframework.stereotype.Component;

import com.chandrika.models.Reimbursement;

@Component
public class ReimbursementClientFallback implements ReimbursementClient {

	@Override
	public List<Reimbursement> getAllReimbursements(int employeeId) {
		return null;
	}
	
}
