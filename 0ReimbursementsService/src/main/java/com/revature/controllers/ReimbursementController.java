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
@RequestMapping(value="/reimbursements")
public class ReimbursementController {
	//need the list of all reimbursements and logger inside this class
	private Logger logger = Logger.getLogger(Reimbursement.class.getName());
	private List<Reimbursement> reimbursements = new ArrayList<>();
	
	//set up some reimbursements in the constructor since were not doing any posts
	public ReimbursementController() {
		reimbursements.add(new Reimbursement(1,1,2000.56));
		reimbursements.add(new Reimbursement(2,1,400.56));
		reimbursements.add(new Reimbursement(3,1,80412.56));
		reimbursements.add(new Reimbursement(4,2,7089.56));
		reimbursements.add(new Reimbursement(5,2,800.56));
		reimbursements.add(new Reimbursement(6,2,12900.56));
		reimbursements.add(new Reimbursement(7,2,200.56));
		reimbursements.add(new Reimbursement(8,3,3457.56));
		reimbursements.add(new Reimbursement(9,3,6876.56));
		reimbursements.add(new Reimbursement(10,3,90.56));
		reimbursements.add(new Reimbursement(11,4,9863.56));
		reimbursements.add(new Reimbursement(12,4,20980.56));
		reimbursements.add(new Reimbursement(13,4,3450.56));
		reimbursements.add(new Reimbursement(14,1,2670.56));
	}
	
	//doo the get all and get id, and get all reimbursements belonging to a certian employee
	//get all
	@GetMapping
	public List<Reimbursement> getAll(){
		return reimbursements;
	}
	//get id
	@GetMapping("/{id}")
	public Reimbursement getReimbursementById(@PathVariable("id") int reimbId) {
		for(Reimbursement r : reimbursements) 
			if(r.getEmpId() == reimbId)
				return r;
		return null;
	}
	
	//get by employee id
	@GetMapping("/employees/{id}")
	public List<Reimbursement> getReimbursementsByEmpId(@PathVariable("id") int empId){
		return reimbursements.stream().filter(r -> r.getEmpId() == empId).collect(Collectors.toList());
	}
}
