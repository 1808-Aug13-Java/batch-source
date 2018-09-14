package com.revature.dao;

import java.util.List;

import com.revature.models.Request;

public interface RequestDaoInterface {

	public Request getRequestByID(int id);
	public List<Request> getPendingRequestsByEmpID(int id);
	public List<Object[]> getAllPendingRequests();
	public List<Object[]> getRespondedRequestsByEmpID(int id);
	public List<Object[]> getAllRespondedRequests();
	public int addRequest(int emp, float amount, String description);
	public int approveRequest(int id, int man);
	public int denyRequest(int id, int man);
}
