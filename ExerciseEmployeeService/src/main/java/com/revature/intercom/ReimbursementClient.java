package com.revature.intercom;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.models.Reimbursement;

public interface ReimbursementClient {
	
	@GetMapping("reimbursements/employees/{id}")
	List<Reimbursement> getReimbursements(@PathVariable("id") int id);
}
