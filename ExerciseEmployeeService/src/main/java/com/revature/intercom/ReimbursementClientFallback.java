package com.revature.intercom;

import java.util.List;

import org.springframework.stereotype.Component;

import com.revature.models.Reimbursement;

@Component
public class ReimbursementClientFallback implements ReimbursementClient{

	@Override
	public List<Reimbursement> getReimbursements(int id) {
		return null;
	}

}