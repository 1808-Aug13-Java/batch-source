package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Reimbursement;

@RestController
@RequestMapping
public class ReimbursementController {

	private Logger logger = Logger.getLogger(ReimbursementController.class.getName());

	private List<Reimbursement> reimbursements = new ArrayList<>();

	public ReimbursementController() {
		reimbursements.add(new Reimbursement(10,10,101.01));
		reimbursements.add(new Reimbursement(11,10,110.11));
		reimbursements.add(new Reimbursement(3,2,5.2));
		reimbursements.add(new Reimbursement(111,101010, 10.1));
		reimbursements.add(new Reimbursement(7,42,2.1));
		
		
	}

	@GetMapping
	public List<Reimbursement> getAllReimbursements() {
		logger.info("ReimbursementController.getAllReimbursements() called");
		return reimbursements;
	}

	@GetMapping(value = "/{accountId}")
	public Reimbursement getAccountById(@PathVariable("reimbursementId") int reimbursementId) {
		logger.info("finding reimbursement by id: " + reimbursementId);
		for (Reimbursement a : reimbursements) {
			if (reimbursementId == a.getReimbursementId()) {
				return a;
			}
		}
		return null;
	}

	@GetMapping(value = "/customers/{customerId}")
	public List<Reimbursement> getReimbursementsByCustomerId(@PathVariable("customerId") int customerId) {
		logger.info("finding reimbursements by customerId: " + customerId);
		return reimbursements.stream().filter(acct -> acct.getEmployeeId() == customerId).collect(Collectors.toList());
	}

}
