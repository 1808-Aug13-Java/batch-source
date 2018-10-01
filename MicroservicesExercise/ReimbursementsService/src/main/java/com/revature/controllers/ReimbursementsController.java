package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Reimbursements;

@RestController
@RequestMapping("/reimbursements")
public class ReimbursementsController {

	private Logger logger = Logger.getLogger(ReimbursementsController.class.getName());
	
	private List<Reimbursements> reimbursements = new ArrayList<>();
	
	public ReimbursementsController() {
		reimbursements.add(new Reimbursements(1, 1, 303.03));
		reimbursements.add(new Reimbursements(2, 1, 1200.00));
		reimbursements.add(new Reimbursements(3, 3, 40.50));
		reimbursements.add(new Reimbursements(4, 2, 10.00));
		reimbursements.add(new Reimbursements(5, 1, 600.66));
	}
	
	@GetMapping
	public List<Reimbursements> getAllReimbursements() {
		logger.info("ReimbursementsController.getAllReimbursements():");
		return reimbursements;
	}
	
	@GetMapping(value="/{reimbursementId")
	public Reimbursements getReimbursementsById(@PathVariable("reimbursementId") int reimbursementId) {
		logger.info("Reimbursement by ID");
		for (Reimbursements r : reimbursements) {
			if (reimbursementId == r.getReimbursementsId()) {
				return r;
			}
		}
		return null;
	}
	
	@GetMapping(value="/employees/{id}")
	public List<Reimbursements> getReimbursementsByCustomerId(@PathVariable("id") int id) {
		return reimbursements.stream().filter(r -> r.getCustomerId() == id).collect(Collectors.toList());
	}
}
