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
		reimbursements.add(new Reimbursement(1, 22.50, 1));
		reimbursements.add(new Reimbursement(2, 10.50, 1));

	}
	
	@GetMapping(value="/{reimbursementId}")
	public Reimbursement getRemibursementById(@PathVariable("reimbursementId") int reimbursementId) {
		logger.info("finding reimbursement by id");
		for(Reimbursement a: reimbursements) {
			if(reimbursementId == a.getReimbursementId()) {
				return a;
			}
		}
		return null;
	}
	
	@GetMapping(value="/employees/{employeeId}")
	public List<Reimbursement> getReimbursementsByEmployeeId(@PathVariable("employeeId") int employeeId) {
		logger.info("finding reimbursements by employee");
		return reimbursements.stream().filter(reim -> reim.getEmployeeId() == employeeId).collect(Collectors.toList());
	}
}
