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
	private Logger logger = Logger.getLogger(Reimbursement.class);
	
	private List<Reimbursement> reimbursements = new ArrayList<>();
	
	public ReimbursementController() {
		reimbursements.add(new Reimbursement(1, 1, "test"));
		reimbursements.add(new Reimbursement(2, 1, "test1"));
		reimbursements.add(new Reimbursement(3, 2, "test2"));
		reimbursements.add(new Reimbursement(4, 2, "test3"));
		reimbursements.add(new Reimbursement(5, 3, "test4"));
	}
	
	@GetMapping
	public List<Reimbursement> getAllReimbursements(){
		logger.info("ReimbursementController.getAllReimbursements");
		return reimbursements;
	}
	
	@GetMapping(value="/{reimbursementId}")
	public Reimbursement getReimbursementById(@PathVariable("reimbursementId") int reimbursementId) {
		logger.info("ReimbursementController.getReimbursementById");
		for (Reimbursement r: reimbursements) {
			if(reimbursementId == r.getReimbursementId()) {
				return r;
			}
		}
		return null;
	}
	
	@GetMapping(value="/employee/{employeeId}")
	public List<Reimbursement> getAccountsByCustomerId(@PathVariable("employeeId") int employeeId){
		logger.info("finding reimbursements by employee");
		return reimbursements.stream().filter(reimb -> reimb.getEmployeeId() == employeeId).collect(Collectors.toList());
	}
}
