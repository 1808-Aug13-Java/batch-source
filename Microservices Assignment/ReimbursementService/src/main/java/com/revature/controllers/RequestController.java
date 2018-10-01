package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.ReimbursementRequest;

@RestController
@RequestMapping("/requests")
public class RequestController 
{
	private Logger loggy = Logger.getLogger(RequestController.class);
	private List<ReimbursementRequest> accounts = new ArrayList<>();
	
	public RequestController()
	{
		accounts.add(new ReimbursementRequest(1, 1, -1, -1, "req1", "Money", false));
		accounts.add(new ReimbursementRequest(2, 3, -1, -1, "req2", "Money", false));
		accounts.add(new ReimbursementRequest(3, 5, -1, -1, "req3", "Money", false));
		accounts.add(new ReimbursementRequest(4, 1, -1, -1, "req4", "Money", false));
		accounts.add(new ReimbursementRequest(5, 3, -1, -1, "req5", "Money", false));
		
	}
	
	@GetMapping
	public List<ReimbursementRequest> getAllRequests()
	{
		loggy.info("RequestController.getAllRequests()");
		return accounts;
	}
	
	@GetMapping(value="/{id}")
	public ReimbursementRequest getRequestById(@PathVariable("id") int reqId)
	{
		loggy.info("RequestController.getRequestById(" + reqId + ")");
		return accounts.stream().filter(req -> req.getId() == reqId).findFirst().get();
	}
	
	@GetMapping(value="/employee/{empId}")
	public List<ReimbursementRequest> getRequestsByEmployee(@PathVariable("empId") int empId)
	{
		loggy.info("RequestController.getRequestByEmployee(" + empId + ")");
		return accounts.stream().filter( req -> req.getEmployeeId() == empId).collect(Collectors.toList());
	}
	
}
