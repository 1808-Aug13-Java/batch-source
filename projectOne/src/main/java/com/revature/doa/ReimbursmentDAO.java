package com.revature.doa;

import java.sql.Connection;
import java.util.List;

import com.revature.model.Reimbursment;


public interface ReimbursmentDAO {
	
	public List<Reimbursment> getReimbursments();
	public Reimbursment getReimbursmentById(int id);
	public List<Reimbursment> getReimbursmentByEmpId(int empId);
	public List<Reimbursment> getReimbursmentByStatus(String status);
	public List<Reimbursment> getReimbursmentByManId(int manId);
	public int createReimbursment(Reimbursment reimbursment);
	public int updateReimbursment(Reimbursment reimbursment);
	public int createReimbursment(Reimbursment reimbursment, Connection con);
	public int updateReimbursment(Reimbursment reimbursment, Connection con);
}
