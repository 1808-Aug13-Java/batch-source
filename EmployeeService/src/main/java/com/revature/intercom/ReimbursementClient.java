package com.revature.intercom;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.models.Reimbursement;

@FeignClient(name="reimbursment-service", fallback=ReimbursementClientFallback.class)
public interface ReimbursementClient {
	
	@HystrixCommand(fallbackMethod = "notFound")
	@GetMapping("reimbursements/employees/{empId}")
	List<Reimbursement> getReimbursements(@PathVariable("empId") int employeeId);

	String notFound();

}
