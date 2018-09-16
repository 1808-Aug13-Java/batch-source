package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.model.Reimbursement;

/**
 * Servlet implementation class AllPendingReimbServlet
 */
public class AllPendingReimbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllPendingReimbServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		
		HttpSession session = request.getSession();
		String currentUser = "" + session.getAttribute("username");

		ReimbursementDao rd = new ReimbursementDaoImpl();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();

		//if we get a parameter we want to display reimbursements from ONE employee
		if(idStr != null) {
			int id = Integer.parseInt(idStr);
			List<Reimbursement> reimbursements = rd.getPendingRequestsById(id);
			String reimbursementString = om.writeValueAsString(reimbursements);
			reimbursementString = "{\"reimbursements\":"+reimbursementString+"}";
			pw.print(reimbursementString);
			//if we don't get anything from the database our id will be 0
			if(Integer.parseInt(idStr)==0) {
				pw.print("invalid employee id"); // because searching by emplyee ids
				//if we are returned a reimbursement from the database we want to display it in json format
			}
			//if we do not get a valid parameter, we want to display all reimbursements
		} else {
			List<Reimbursement> reimbursements = rd.getAllPendingRequests();
			String reimbursementString = om.writeValueAsString(reimbursements);
			reimbursementString = "{\"reimbursements\":"+reimbursementString+"}";
			pw.print(reimbursementString);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
