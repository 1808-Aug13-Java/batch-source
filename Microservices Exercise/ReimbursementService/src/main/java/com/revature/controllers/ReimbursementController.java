package com.revature.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Reimbursement;

@RestController
@RequestMapping("/reimbursements")
public class ReimbursementController {
	
	/** The list of reimbursements accessible by this service. */
	private List<Reimbursement> reimbursements = new ArrayList<>();
	
	
	public ReimbursementController() {
		// Initialize the list of reimbursements with mock data. 
		reimbursements.add(new Reimbursement(1, 1, "Approved", 10.0, new Date(), 
				"I ned monz", 3, "Hers fre monz", new Date()));
		reimbursements.add(new Reimbursement(2, 1, "Denied", 12.0, new Date(), 
				"Can I has more?", 3, "No", new Date()));
		reimbursements.add(new Reimbursement(3, 1, "Pending", 12.0, new Date(), 
				"Please", 0, null, null));
		
		reimbursements.add(new Reimbursement(4, 4, "Approved", 87.65, new Date(), 
				"Travel", 3, "", new Date()));
	} // end of constructor ReimbursementController
	
	
	
	/** Returns a list of all the reimbursements. */
	@GetMapping()
	public List<Reimbursement> getAllReimbursements() {
		return reimbursements;
	} // end of getAllReimbursements
	
	
	/** Returns a list of reimbursements for a particular employee id. */
	@GetMapping("/employees/{id}")
	public List<Reimbursement> getReimbursmentsByEmpId(@PathVariable("id") long empId) {
		ArrayList<Reimbursement> rems = new ArrayList<>();
		
		// Scan the list of reimbursements for any belonging to the provided 
		// employee id. 
		for (Reimbursement rem : reimbursements) {
			if (rem.getRequester() == empId) {
				rems.add(rem);
			}
		}
		
		return rems;
	} // end of getReimbursmentsByEmpId
	
} // end of class ReimbursementController
