package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImpl;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.models.Manager;
import com.revature.models.Reimbursement;

public class ReimbursementsController {
	
	private static Logger log = Logger.getRootLogger();

	public ReimbursementsController() {
		super();
	}
	
	public static String getReimbursementsByEmpId(String username) {
		ReimbursementDao rdi = new ReimbursementDaoImpl();
		EmployeeDao edi = new EmployeeDaoImpl();
		ObjectMapper om = new ObjectMapper();
		
		List<Reimbursement> reimbursements = rdi.getReimbursementByEmployeeId(edi.getEmployeeByUsername(username).getId());
		String reimbursementsString = null;
		
		try {
			reimbursementsString = om.writeValueAsString(reimbursements);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		
		return "{\"reimbursements\":" + reimbursementsString + "}";
	}

	
	public static String getReimbursementsByManId(int manId) {
		ReimbursementDao rdi = new ReimbursementDaoImpl();
		ManagerDao mdi = new ManagerDaoImpl();
		ObjectMapper om = new ObjectMapper();
		
		List<Reimbursement> reimbursements = rdi.getReimbursementByManagerId(manId);
		String reimbursementsString = null;
		
		try {
			reimbursementsString = om.writeValueAsString(reimbursements);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		
		return "{\"reimbursements\":" + reimbursementsString + "}";
	}

	public static int createNewReimbursement(HttpServletRequest request) {
		int reimbursementsCreated = 0;
		ReimbursementDao rdi = new ReimbursementDaoImpl();
		Reimbursement r = new Reimbursement();
		log.info("In Reimbursement controller");
		
		r.setEmpId(Integer.parseInt(request.getParameter("empId").toString()));
		r.setAmount(Double.parseDouble(request.getParameter("amount").toString()));
		r.setDescription(request.getParameter("description").toString());
		r.setStatus(Integer.parseInt(request.getParameter("status").toString()));
		r.setManagerId(Integer.parseInt(request.getParameter("managerId").toString()));
		
		rdi.createReimbursement(r);
		reimbursementsCreated = 1;
		
		return reimbursementsCreated;

	}
	
	public static int updateReimbursement(HttpServletRequest request, String username) {
		int reimbursementsUpdated = 0;
		ReimbursementDao rdi = new ReimbursementDaoImpl();
		Reimbursement r = new Reimbursement();
		ManagerDao mdi = new ManagerDaoImpl();
		
		
		r.setId(Integer.parseInt(request.getParameter("reimbursement")));
		r.setManagerId(mdi.getManagerByUsername(username).getId());
		System.out.println(request.getParameter("status"));
		r.setStatus(Integer.parseInt(request.getParameter("status")));
		
		rdi.createReimbursement(r);
		reimbursementsUpdated = 1;
		
		return reimbursementsUpdated;

	}
	
	public static String getAllReimbursements() {
		ReimbursementDao rdi = new ReimbursementDaoImpl();
		ObjectMapper om = new ObjectMapper();
				
		List<Reimbursement> reimbursement = rdi.getReimbursement();
		String reimbursementString = null;
		
		try {
			reimbursementString = om.writeValueAsString(reimbursement);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		
		return "{\"reimbursement\":" + reimbursementString + "}";
	}




}
