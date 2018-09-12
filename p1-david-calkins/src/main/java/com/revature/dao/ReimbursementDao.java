package com.revature.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;

public interface ReimbursementDao {
	
	
	public Reimbursement getById(long id, Connection con) throws SQLException;
	
	public List<Reimbursement> getAll(Connection con) throws SQLException;
	
	public List<Reimbursement> getAllPending(Connection con) throws SQLException;
	
	public List<Reimbursement> getAllResolved(Connection con) throws SQLException;
	
	
	public List<Reimbursement> getByRequester(Employee emp, Connection con) throws SQLException;
	
	
	public List<Reimbursement> getPendingByRequester(Employee emp, Connection con) throws SQLException;
	
	public List<Reimbursement> getResolvedByRequester(Employee emp, Connection con) throws SQLException;
	
	public long createReimRequest(Reimbursement reim, Connection con) throws SQLException;
	
	public int updateReimRequest(Reimbursement reim, Connection con) throws SQLException;
}
