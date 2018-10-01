package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;
import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Reimbursement;

@RestController
@RequestMapping(value="/reimbursements")
public class ReimbursementController {
  public ReimbursementController() {}

  private List<Reimbursement> reimbursements = new ArrayList<>();
  {
    reimbursements.add(new Reimbursement(1, new BigDecimal(89.01)));
    reimbursements.add(new Reimbursement(2, new BigDecimal(12.71)));
    reimbursements.add(new Reimbursement(3, new BigDecimal(5.00)));
    reimbursements.add(new Reimbursement(4, new BigDecimal(49.99)));
  }

  @GetMapping(value="/employees/{username}")
  public List<Reimbursement> getReimbursements(@PathVariable(name="username") String username) {
    return reimbursements.stream().filter(reimbursement -> reimbursement.getEmployee().equals(username)).collect(Collectors.toList());
  }
    
	@GetMapping(value="/{reimbursementId}")
	public Reimbursement getReimbursementById(@PathVariable("reimbursementId") int reimbursementId) {
		for(Reimbursement r: reimbursements) {
			if(reimbursementId == r.getReimbursementId()) {
				return r;
			}
		}
		return null;
	}
}
