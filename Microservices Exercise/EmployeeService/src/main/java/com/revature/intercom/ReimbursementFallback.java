package com.revature.intercom;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.models.Reimbursement;

public class ReimbursementFallback {
	/** Returns a list of all the reimbursements. */
	@GetMapping("/reimbursements")
	public List<Reimbursement> getAllReimbursements() {
		return new LinkedList<>();
	}
	
	
	/** Returns a list of reimbursements for a particular employee id. */
	@GetMapping("/reimbursements/employees/{id}")
	public List<Reimbursement> getReimbursmentsByEmpId(@PathVariable("id") long empId) {
		return new LinkedList<>();
	}
}
