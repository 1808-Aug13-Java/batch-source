package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.doa.EmployeeDAO;
import com.revature.doa.EmployeeDoaImp;
import com.revature.doa.ManagerDAO;
import com.revature.doa.ManagerDaoImpl;
import com.revature.doa.ReimbursmentDAO;
import com.revature.doa.ReimbursmentDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Reimbursment;

public class ReimbursmentController {

	private static Logger log = Logger.getRootLogger();

	private ReimbursmentController() {
		super();
	}

	public static String getReimbursementByUserName(String userName) {

		ReimbursmentDAO reimb = new ReimbursmentDaoImpl();
		EmployeeDAO ed = new EmployeeDoaImp();
		ObjectMapper om = new ObjectMapper();

		List<Reimbursment> reimbursements = reimb.getReimbursmentByEmpId(ed.getEmployeeByUserName(userName).getEmpId());
		String reimbursementStr = null;
		try {
			reimbursementStr = om.writeValueAsString(reimbursements);
		} catch (JsonProcessingException a) {
			log.error(a);
		}
		return "{\"reimbursment\":" + reimbursementStr + "}";
	}

	public static String getReimbursementForManView() {

		ReimbursmentDAO reimb = new ReimbursmentDaoImpl();
		EmployeeDAO ed = new EmployeeDoaImp();
		ManagerDAO manD = new ManagerDaoImpl();
		List<Reimbursment> reimbursements = new ArrayList<>();
		ObjectMapper om = new ObjectMapper();

		List<Reimbursment> r = reimb.getReimbursments();
		for (Reimbursment rb : r) {
			log.info("in getreimbformanview method adding man name");
			rb.setEmpName(
					ed.getEmployeeById(rb.getEmpId()).getfName() + " " + 
							ed.getEmployeeById(rb.getEmpId()).getfName());
			if (rb.getResolvedBy() != 0 && (Integer)rb.getResolvedBy() != null) {
				rb.setManagerName(manD.getManagerById(rb.getResolvedBy()).getName());
			}
			reimbursements.add(rb);
		}

		String reimbursementStr = null;
		try {
			reimbursementStr = om.writeValueAsString(reimbursements);
		} catch (JsonProcessingException a) {
			log.error(a);
		}
		return "{\"reimbursment\":" + reimbursementStr + "}";
	}
	
	public static void CreateReimbursement(HttpServletRequest request, String userName) {
		
		ReimbursmentDAO reimbD = new ReimbursmentDaoImpl();
		EmployeeDAO ed = new EmployeeDoaImp();
		log.info("in create reimb controller username: " + userName);
		Employee e = ed.getEmployeeByUserName(userName);
		Reimbursment reimb = new Reimbursment();
		log.info("in createReimb in controller desc: " + request.getParameter("description"));
		reimb.setEmpId(e.getEmpId());
		reimb.setStatus("pending");
		reimb.setDescription(request.getParameter("description"));
		int success = reimbD.createReimbursment(reimb);
		log.info("create reimb Success? " + success);
		
		
	}
	
	public static void updateReimbursement(HttpServletRequest request, String username) {
		ManagerDAO manD = new ManagerDaoImpl();
		ReimbursmentDAO rd = new ReimbursmentDaoImpl();	
		Reimbursment r = new Reimbursment();
		log.info("in reimburs update control man name: " + username);
		log.info("in reimburs update control manID: " + manD.getManagerByUserName(username).getManId());
		r.setResolvedBy(manD.getManagerByUserName(username).getManId());
		r.setStatus(request.getParameter("reimOption"));
		r.setReimbId(Integer.parseInt(request.getParameter("reimbursment")));
		
		rd.updateReimbursment(r);
	}

}
