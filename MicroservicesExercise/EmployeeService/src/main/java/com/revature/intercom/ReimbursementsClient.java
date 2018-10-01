package com.revature.intercom;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.models.Reimbursements;

@FeignClient(name="reimbursements-service", fallback=ReimbursementsClientImpl.class)
public interface ReimbursementsClient {
	
	@GetMapping(value="reimbursements/employees/{id}")
	public List<Reimbursements> getReimbursementsByCustomerId(@PathVariable("id") int id);
}
