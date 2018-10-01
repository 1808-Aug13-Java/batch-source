package com.revature.intercom;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.models.ReimbursementRequest;

@FeignClient(name="request-service", fallback=ReimbursementFallback.class)
public interface ReimbursementClient {

	@GetMapping(value="/requests/employee/{empId}")
	public List<ReimbursementRequest> getAllRequests(@PathVariable("empId") int empId);
}
