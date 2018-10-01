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
	
	private List<Reimbursement> reimbursements = new ArrayList();

	public ReimbursementController() {
		super();
		reimbursements.add(new Reimbursement(1,1,10.89));
		reimbursements.add(new Reimbursement(2, 1, 43.76));
		reimbursements.add(new Reimbursement(3,1,56.78));
		reimbursements.add(new Reimbursement(4, 2, 58.84));
		reimbursements.add(new Reimbursement(5, 2, 30.92));
		reimbursements.add(new Reimbursement(6, 2, 93.56));
		reimbursements.add(new Reimbursement(7, 3, 25.87));
		reimbursements.add(new Reimbursement(8, 3, 76.54));
		reimbursements.add(new Reimbursement(9, 4, 34.56));
		
	}
	
	@GetMapping
	public List<Reimbursement> getAllReimbursements(){
		logger.info("ReimbursementController.getAllReimbursements()");
		return reimbursements;
	}
	
	@GetMapping(value= "/{reimbursementId}")
	public Reimbursement getReimbursementById(@PathVariable("reimbursementId") int reimbursementId) {
		
		if(reimbursements.get(reimbursementId-1) != null) {
			return reimbursements.get(reimbursementId-1);
		}
		
		return null;
	}
	
	@GetMapping(value="employees/{employeeId}")
	public List<Reimbursement> getReimbursementsByEmployeeId(@PathVariable("employeeId") int employeeId){
		logger.info("finding reimbursements by employeeId");
		return reimbursements.stream().filter(r -> r.getEmployeeId() == employeeId).collect(Collectors.toList());	
		
	}
	
}

	