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

	private Logger log = Logger.getLogger(ReimbursementController.class.getName());
	private List<Reimbursement> reimbursements = new ArrayList<>();
	
	public ReimbursementController() {
		
	}
	
	@GetMapping
	public List<Reimbursement> getAllReimbursements() {
		log.info("Finding all Reimbursements");
		return reimbursements;
	}
	
	@GetMapping(value="/{id}")
	public Reimbursement getReimbursementById(@PathVariable("id") int id) {
		log.info("Finding Reimbursement by ID");
		return reimbursements.stream().filter(rem -> rem.getReimbursementId() == id)
		.findFirst().get();
	}
	
	@GetMapping(value="/employees/{id}")
	public List<Reimbursement> getReimbursementsByEmployeeId(@PathVariable("id") int id) {
		log.info("Finding Reimbursements by Employee ID");
		return reimbursements.stream().filter(rem -> rem.getEmployeeId() == id).collect(Collectors.toList());
	}
	
}
