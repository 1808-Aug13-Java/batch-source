package com.chandrika.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chandrika.models.Reimbursement;

@RestController
@RequestMapping("/reimbursements")
public class ReimbursementController {
	private Logger logger = Logger.getLogger(ReimbursementController.class.getName());
	
	private List<Reimbursement> reimbursements = new ArrayList<>();
	
	public ReimbursementController() {
		reimbursements.add(new Reimbursement(1, 1, "", 20.00));
		reimbursements.add(new Reimbursement(2, 1, "", 12.95));
		reimbursements.add(new Reimbursement(3, 2, "", 3.48));
		reimbursements.add(new Reimbursement(4, 2, "", 6.14));
		reimbursements.add(new Reimbursement(5, 3, "", 3.19));
		reimbursements.add(new Reimbursement(6, 3, "Pay card", 2.00));
		reimbursements.add(new Reimbursement(7, 4, "Gas", 39.92));
		reimbursements.add(new Reimbursement(8, 4, "Lunch", 14.00));
	}
	
	@GetMapping
	public List<Reimbursement> getAllReimbursements() {
		logger.info("ReimbursementController.getAllReimbursements()");
		return reimbursements;
	}
	
	@GetMapping(value="/{reimbursementId}")
	public Reimbursement getReimbursementById(@PathVariable("reimbursementId") int reimbursementId) {
		logger.info("finding reimbursement by id");
		for (Reimbursement r: reimbursements) {
			if (reimbursementId == r.getReimbursementId()) {
				return r;
			}
		}
		return null;
	}
	
	@GetMapping(value="/employees/{employeeId}")
	public List<Reimbursement> getReimbursementsByEmployeeId(@PathVariable("employeeId") int employeeId) {
		logger.info("finding reimbursements by employee)");
		return reimbursements.stream().filter(reim -> (reim.getEmployeeId()==employeeId)).collect(Collectors.toList());
	}
}
