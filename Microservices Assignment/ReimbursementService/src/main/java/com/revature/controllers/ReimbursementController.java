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
@RequestMapping("/reimbursements")
public class ReimbursementController {
	
	private Logger logger = Logger.getLogger(ReimbursementController.class.getName());
	
	private List<Reimbursement> reimbursements = new ArrayList();
	
	public ReimbursementController() {
		reimbursements.add(new Reimbursement(1, 1, 50.34, "travel expenses", true, 43));
		reimbursements.add(new Reimbursement(2, 1, 25.65, "tuition reimbursement", false, 22));
		reimbursements.add(new Reimbursement(3, 1, 251.25, "just give me money!!!", true, 43));
		reimbursements.add(new Reimbursement(4, 2, 5.13, "office supplies", false, 35));
		reimbursements.add(new Reimbursement(5, 3, 123.38, "travel expenses", true, 22));
		reimbursements.add(new Reimbursement(6, 3, 43.54, "car loan", true, 36));
		reimbursements.add(new Reimbursement(7, 4, 99.98, "business trip", false, 57));
		reimbursements.add(new Reimbursement(8, 4, 25.74, "random business expense", true, 36));
		
	}
	
	@GetMapping
	public List<Reimbursement> getAllReimbursements() {
		logger.info("ReimbursementController.getAllReimbursements");
		return reimbursements;
	}
	
	@GetMapping(value="/{reimbursementId}")
	public Reimbursement getReimbursementById(@PathVariable("reimbursementId") int reimbursementId) {
		logger.info("getReimbursementById");
		for(Reimbursement r: reimbursements) {
			if (reimbursementId == r.getReimbursementId()) {
				return r;
			}
		}
		return null;
	}
	
	@GetMapping(value="/employees/{employeeId}")
	public List<Reimbursement> getReimbursementsByEmployeeId(@PathVariable("employeeId") int employeeId) {
		logger.info("getReimbursementsByEmployeeId");
		return reimbursements.stream().filter(reimb -> reimb.getEmployeeId() == employeeId).collect(Collectors.toList());
	}
	
}
