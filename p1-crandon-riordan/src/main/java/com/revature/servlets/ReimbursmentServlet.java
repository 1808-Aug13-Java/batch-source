package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmpDaoImpl;
import com.revature.dao.EmployeeDao;
import com.revature.dao.ReimbursmentDao;
import com.revature.dao.ReimbursmentDaoImpl;
import com.revature.models.Employee;
import com.revature.models.Reimbursment;
import com.revature.util.ReimbursmentHelper;
import com.revature.util.SessionHelper;

/**
 * Servlet implementation class ReimbursmentServlet
 * 
 * SERVLET RESPONSIBILITY : REIMBURSMENT API ENDPOINT
 */
public class ReimbursmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ReimbursmentServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ENDPOINT FOR GETTING REIMBURSMENT DATA
		
		SessionHelper sh = new SessionHelper();
		
		String currentState = request.getParameter("currentState");
		String employeeId = request.getParameter("employeeId");
		String reimbursmentId = request.getParameter("reimbursmentId");
		String action = request.getParameter("action");
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		ReimbursmentDao rd = new ReimbursmentDaoImpl();
		EmployeeDao ed = new EmpDaoImpl();
		
		List<Reimbursment> reimbursments = new ArrayList<Reimbursment>();
		
		// get the current session manager and get his empId
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		
		// find the employee based on the email
		Employee manager = ed.getEmployeeByEmail(email);
		
		if(action != null && reimbursmentId != null) {
			// ?action=X&reimbusmentId=Y
			try {
				int rIdInt = Integer.parseInt(reimbursmentId);
				Reimbursment r = rd.getReimbursmentById(rIdInt);
				if(action.equalsIgnoreCase("approve") && r != null) {
					
					rd.approveRequestById(rIdInt, manager.getId());
				} else if (action.equalsIgnoreCase("deny") && r != null) {
					rd.denyReimbursmentById(rIdInt, manager.getId());
				} else {
					pw.write("null");
				}
			} catch (Exception e) {
				logger.info("invalid reimbursmentId");
				pw.write("null");
			}
			
			
		}
		
		
		if(currentState != null) {
			// check for ?currentState=Y
			// if param present that is looking for a pending reimbursment
			if(currentState.equalsIgnoreCase("pending")) {
				// check for emp id attr
				if(employeeId != null) {
					// check for ?currentState=Y&employeeId=X
					try {
						int id = Integer.parseInt(employeeId);
						reimbursments = rd.getPendingByEmployeeId(id);
					} catch (Exception e){
						pw.write("null");
					}
				} else {
					reimbursments = rd.getAllPendingReimbursments(); 
				}
				
			// if param present that is looking for resolved
			} else if (currentState.equalsIgnoreCase("resolved")) {
				// check for emp id attr
				if(employeeId != null) {
					try {
						int id = Integer.parseInt(employeeId);
						reimbursments = rd.getResolvedByEmployeeId(id);
					} catch (Exception e){
						pw.write("null");
					}
				} else {
					reimbursments = rd.getAllResolvedReimbursments();
				}
			}
			// if there is an employeeId param present
			// get the reimbursments associated with that employeeId
		} else if (employeeId != null && currentState == null) {
			reimbursments = rd.getReimbursmentsByEmployeeId(Integer.parseInt(employeeId));
		} else {
			// didn't find a current state param
			reimbursments = rd.getAllReimbursments();
		}
		
		boolean foundUser = sh.checkValidUser(request);
		ReimbursmentHelper rh = new ReimbursmentHelper();
		reimbursments = rh.addEmployee(reimbursments);
		if(foundUser) {
			String reimbursmentString = om.writeValueAsString(reimbursments);
			reimbursmentString = "{\"reimbursments\": " + reimbursmentString + "}";
			pw.write(reimbursmentString);
		} else {
			response.sendRedirect("login");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReimbursmentHelper rh = new ReimbursmentHelper();
		// ReimbursmentHelper will handle creation
		rh.createReimbursment(request);
	}

}
