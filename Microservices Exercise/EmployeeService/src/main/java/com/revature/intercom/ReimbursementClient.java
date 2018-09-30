package com.revature.intercom;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.models.Reimbursement;

@FeignClient(name = "Account-Service", fallback=ReimbursementFallback.class)
public interface ReimbursementClient {
	
	/** Returns a list of all the reimbursements. */
	@GetMapping("/reimbursements")
	public List<Reimbursement> getAllReimbursements();
	
	
	/** Returns a list of reimbursements for a particular employee id. */
	@GetMapping("/reimbursements/employees/{id}")
	public List<Reimbursement> getReimbursmentsByEmpId(@PathVariable("id") long empId);
}
