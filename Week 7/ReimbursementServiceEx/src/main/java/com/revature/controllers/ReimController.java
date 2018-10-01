package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Reimbursement;

@RestController
@RequestMapping("/remibursement")
public class ReimController {
	
private List<Reimbursement> reimbursements= new ArrayList<>();
private Logger logger= Logger.getLogger(ReimController.class.getName());

public ReimController() {
	this.reimbursements.add(new Reimbursement(1L,1L,"moving expensees",400.47));
	this.reimbursements.add(new Reimbursement(2L,2L,"employee social",3849.93));
	this.reimbursements.add(new Reimbursement(3L,3L,"Conference travel",834.34));
	this.reimbursements.add(new Reimbursement(4L,5L, "certification examination", 75.00));	
	this.reimbursements.add(new Reimbursement(5L,5L, "reasons", 735.00));	
	this.reimbursements.add(new Reimbursement(6L,3L, "certification examination", 75.00));	
}
	
@GetMapping
public List<Reimbursement> getAllReimbursements(){
	logger.info("getAllReimbursements() call made");
	return this.reimbursements;
}

@PostMapping (value="/{id}")
public Reimbursement getReimbursementById(@PathVariable ("id") long id) {
	Reimbursement reim = reimbursements.stream().filter(filt -> filt.getReimId() == id).findFirst().get();
	return reim;
}

@GetMapping(value="/employees/{employeeId}")
public List<Reimbursement> getReimbursementByCustomerId(@PathVariable("employeeId")long employeeId){
	logger.info("finding reimbursements by employee Id");
	return reimbursements.stream().filter(reim -> reim.getEmployeeId() == employeeId).collect(Collectors.toList());
}



}
