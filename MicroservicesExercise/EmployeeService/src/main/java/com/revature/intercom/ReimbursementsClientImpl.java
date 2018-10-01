package com.revature.intercom;

import java.util.List;

import org.springframework.stereotype.Component;

import com.revature.models.Reimbursements;

@Component
public class ReimbursementsClientImpl implements ReimbursementsClient {

	@Override
	public List<Reimbursements> getReimbursementsByCustomerId(int id) {
		return null;
	}

}
