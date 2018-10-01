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
		reimbursements.add(new Reimbursement(1,1,79.32));
		reimbursements.add(new Reimbursement(2,1,196.97));
		reimbursements.add(new Reimbursement(3,2,224.78));
		reimbursements.add(new Reimbursement(4,2,57368.76));
		reimbursements.add(new Reimbursement(5,3,340.22));
		reimbursements.add(new Reimbursement(6,3,12.09));
		reimbursements.add(new Reimbursement(7,4,762.14));
		reimbursements.add(new Reimbursement(8,4,90.99));
		
	}

	@GetMapping
	public List<Reimbursement> getAllReimbursements() {
		logger.info("ReimbursementController.getAllReimbursements()");
		return reimbursements;
	}

	@GetMapping(value = "/{accountId}")
	public Reimbursement getAccountById(@PathVariable("reimbursementId") int reimbursementId) {
		logger.info("finding reimbursement by id");
		for (Reimbursement a : reimbursements) {
			if (reimbursementId == a.getReimbursementId()) {
				return a;
			}
		}
		return null;
	}

	@GetMapping(value = "/customers/{customerId}")
	public List<Reimbursement> getReimbursementsByCustomerId(@PathVariable("customerId") int customerId) {
		logger.info("finding reimbursements by customer");
		return reimbursements.stream().filter(acct -> acct.getEmployeeId() == customerId).collect(Collectors.toList());
	}

}
