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
	private List<Reimbursement> reimbursements = new ArrayList<>();
	
	public ReimbursementController() {
		reimbursements.add(new Reimbursement(1, 1, 79.32, "business trip", "Pending"));
		reimbursements.add(new Reimbursement(2, 1, 196.97, "client dinner", "Approved"));
		reimbursements.add(new Reimbursement(3, 1, 224.78, "family vacation", "Denied"));
		reimbursements.add(new Reimbursement(4, 2, 57368.76, "company car repair", "Pending"));
		reimbursements.add(new Reimbursement(5, 3, 340.22, "relocation", "Approved"));
		reimbursements.add(new Reimbursement(6, 3, 224.78, "client dinner", "Pending"));
		reimbursements.add(new Reimbursement(7, 4, 57368.76, "client dinner", "Pending"));
		reimbursements.add(new Reimbursement(8, 4, 340.22, "travel", "Denied"));
	}
	
	@GetMapping
	public List<Reimbursement> getAllReimbursements() {
		logger.info("ReimbursementController.getAllReimbursements()");
		return reimbursements;
	}
	
	@GetMapping(value="/{reimbursementId}")
	public Reimbursement getReimbursementById(@PathVariable("reimbursementId") int reimbursementId) {
		logger.info("finding reimbursement by id");
		for(Reimbursement r: reimbursements) {
			if(reimbursementId == r.getReimburseID()) {
				return r;
			}
		}
		return null;
	}
	
	@GetMapping(value="/employees/{employeeId}")
	public List<Reimbursement> getAccoutsByCustomerId(@PathVariable("employeeId") int employeeId) {
		logger.info("finding reimbursements by employee");
		return reimbursements.stream().filter(reimburs -> reimburs.getEmployeeID() == employeeId).collect(Collectors.toList());
	}
}	
