package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursmentDao;
import com.revature.dao.ReimbursmentDaoImpl;
import com.revature.models.Reimbursment;
import com.revature.util.ReimbursmentHelper;
import com.revature.util.SessionHelper;

/**
 * Servlet implementation class ReimbursmentServlet
 */
public class ReimbursmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ReimbursmentServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionHelper sh = new SessionHelper();
		
		String currentState = request.getParameter("currentState");
		String employeeId = request.getParameter("employeeId");
		String reimbursmentId = request.getParameter("reimbursmentId");
		String action = request.getParameter("action");
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		ReimbursmentDao rd = new ReimbursmentDaoImpl();
		List<Reimbursment> reimbursments = new ArrayList<Reimbursment>();
		if(action != null && reimbursmentId != null) {
			try {
				int rIdInt = Integer.parseInt(reimbursmentId);
				Reimbursment r = rd.getReimbursmentById(rIdInt);
				if(action.equalsIgnoreCase("approve") && r != null) {
					rd.approveRequestById(rIdInt);
				} else if (action.equalsIgnoreCase("deny") && r != null) {
					rd.denyReimbursmentById(rIdInt);
				} else {
					pw.write("null");
				}
			} catch (Exception e) {
				logger.info("invalid reimbusrmentId");
				pw.write("null");
			}
			
			
		}
		
		
		if(currentState != null) {
			// if param present that is looking for a pending reimbursment
			if(currentState.equalsIgnoreCase("pending")) {
				reimbursments = rd.getAllPendingReimbursments();
			// if param present that is looking for resolved
			} else if (currentState.equalsIgnoreCase("resolved")) {
				reimbursments = rd.getAllResolvedReimbursments();
			}
			// if there is an employeeId param present
			// get the reimbursments associated with that employeeId
		} else if (employeeId != null) {
			try {
				int id = Integer.parseInt(employeeId);
				reimbursments = rd.getReimbursmentsByEmployeeId(id);
			} catch (Exception e){
				pw.write("null");
			}
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
		doGet(request, response);
	}

}
