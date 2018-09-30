package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
		reimbursements.add(new Reimbursement(1,1,79.32));
		reimbursements.add(new Reimbursement(2,1,196.97));
		reimbursements.add(new Reimbursement(3,1,224.78));
		reimbursements.add(new Reimbursement(4,2,57368.76));
		reimbursements.add(new Reimbursement(5,3,340.22));
		reimbursements.add(new Reimbursement(6,3,12.09));
		reimbursements.add(new Reimbursement(7,4,762.14));
		reimbursements.add(new Reimbursement(8,4,90.99));
		
	}
	
	@GetMapping
	public List<Reimbursement> getAllReimbursements(){
		logger.info("ReimbursementController.getAllReimbursements()");
		return reimbursements;
	}
	
	@GetMapping(value="/{reimbursementId}")
	public Reimbursement getReimbursementById(@PathVariable("reimbursementId") int reimbursementId) {
		logger.info("finding reimbursement by id");
		for(Reimbursement a: reimbursements) {
			if(reimbursementId == a.getReimbursementId()) {
				return a;
			}
		}
		return null;
	}
	
	@GetMapping(value="/employees/{employeeId}")
	public List<Reimbursement> getReimbursementsByEmployeeId(@PathVariable("employeeId") int employeeId){
		logger.info("finding accounts by employee");
		return reimbursements.stream().filter(reimb -> reimb.getEmployeeId() == employeeId).collect(Collectors.toList());
	}
	
}
