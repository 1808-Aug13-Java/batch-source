package com.revature.intercom;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.models.Reimbursment;

@FeignClient(name="reimbursment-service", fallback=ReimbursmentClientImpl.class)
public interface ReimbursmentClient {

	@GetMapping(value="reimbursments/employees/{id}")
	public List<Reimbursment> getReimbursmentsByCustomerId(@PathVariable("id") int id);
	
}
