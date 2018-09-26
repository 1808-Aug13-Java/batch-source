package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Reimbursment;

@RestController
@RequestMapping("/reimbursments")
public class ReimbursmentController {
	
	private Logger logger = Logger.getLogger(ReimbursmentController.class.getName());
	
	private List<Reimbursment> reimbursments = new ArrayList<>();
	
	public ReimbursmentController() {
		reimbursments.add(new Reimbursment(1, 1, 534.23));
		reimbursments.add(new Reimbursment(2, 1, 52.23));
		reimbursments.add(new Reimbursment(3, 1, 5234.33));
		reimbursments.add(new Reimbursment(4, 1, 3234.23));
		reimbursments.add(new Reimbursment(5, 2, 534.23));
		reimbursments.add(new Reimbursment(6, 3, 5324));
		reimbursments.add(new Reimbursment(7, 5, 234.23));
		reimbursments.add(new Reimbursment(8, 4, 34.23));
		reimbursments.add(new Reimbursment(9, 3, 4.23));
		reimbursments.add(new Reimbursment(10, 2, 534.23));
		
	}
	
	@GetMapping
	public List<Reimbursment> getAllReimbursments(){
		logger.info("ReimbursmentController.getAllReimbursments()");
		return reimbursments;
	}
	
	@GetMapping(value="/{reimbursmentId}")
	public Reimbursment getReimbursmentById(@PathVariable("reimbursmentId") int reimbursmentId) {
		logger.info("finding account by id");
		for(Reimbursment r : reimbursments) {
			if(reimbursmentId == r.getReimbursmentId()) {
				return r;
			}
		}
		return null;
	}
	
	@GetMapping(value="/employees/{id}")
	public List<Reimbursment> getReimbursmentsByCustomerId(@PathVariable("id") int id) {
		return reimbursments.stream().filter(r -> r.getCustomerId() == id).collect(Collectors.toList());
	}
	
	

}
