package com.revature.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Reimbursement;


@RestController
@RequestMapping(value="/reimbursements")
public class ReimbursementController {

	private List<Reimbursement> reimbursements = new ArrayList<>();
	
	public ReimbursementController() {
		reimbursements.add(new Reimbursement(1,1, new BigDecimal(35.00), "Need Lunch", false));
		reimbursements.add(new Reimbursement(2,1, new BigDecimal(60.00), "Office Resupply", true));
		reimbursements.add(new Reimbursement(3,2, new BigDecimal(750.00), "Relocation", true));
		reimbursements.add(new Reimbursement(4,2, new BigDecimal(160.00), "Towed out of company parking", true));
		reimbursements.add(new Reimbursement(5,3, new BigDecimal(250.00), "Made a bet with manager. Won.", false));
	}
	
	@GetMapping
	public List<Reimbursement> getReimmbursements(){
		return reimbursements;
	}
	
	@GetMapping(value="/{id}")
	public Reimbursement getAccountById(@PathVariable("id") int id) {
		Reimbursement reimbursement = reimbursements.stream().filter(reim -> reim.getReimbursementId()==id).findFirst().get();
		return reimbursement;
	}
	
	@GetMapping(value="/employee/{empId}")	
	public List<Reimbursement> getReimbursementsByEmployeeId(@PathVariable("empId") int empId){
		List<Reimbursement> reimByEmp = new ArrayList<>();
		
		return reimbursements.stream().filter(reim -> reim.getEmployeeId()==empId).collect(Collectors.toList());
	}
}
