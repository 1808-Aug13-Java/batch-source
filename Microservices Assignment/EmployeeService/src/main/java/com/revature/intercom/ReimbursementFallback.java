package com.revature.intercom;

import java.util.List;

import org.springframework.stereotype.Component;

import com.revature.models.ReimbursementRequest;

@Component
public class ReimbursementFallback implements ReimbursementClient {

	@Override
	public List<ReimbursementRequest> getAllRequests(int empId) 
	{	
		return null;
	}

}
