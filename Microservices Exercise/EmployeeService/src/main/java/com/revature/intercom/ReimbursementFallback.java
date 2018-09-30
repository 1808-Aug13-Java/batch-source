package com.revature.intercom;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.models.Reimbursement;

@Component
public class ReimbursementFallback implements ReimbursementClient {
	
	/** Returns a list of all the reimbursements. */
	@Override
	public List<Reimbursement> getAllReimbursements() {
		return new LinkedList<>();
	}
	
	
	/** Returns a list of reimbursements for a particular employee id. */
	@Override
	public List<Reimbursement> getReimbursmentsByEmpId(@PathVariable("id") long empId) {
		return new LinkedList<>();
	}
}
