package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.lang.model.element.Element;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.controller.EmployeeController;
import com.revature.controller.ManagerController;
import com.revature.controller.ReimbursmentController;

/**
 * Servlet implementation class SubmitRequestServlet
 */
public class SubmitRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getRootLogger();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitRequestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.isNew()) {
			log.info("user not logged in");
		}
		
			log.info("what? " );

		
		switch (request.getParameter("destination")) {
		case "submitReimb":
			ReimbursmentController.CreateReimbursement(request, session.getAttribute("username").toString());
			request.getRequestDispatcher("employeeHome").forward(request, response);
			break;
		case "updateUserProf":
			log.info("in servlet update prof: " + request.getParameter("fName"));
			EmployeeController.updateProfile(request, session.getAttribute("username").toString());
			request.getRequestDispatcher("employeeHome").forward(request, response);
			break;
		case "processReimb":
			log.info("in servlet update prof: " + request.getParameter("fName"));
			ReimbursmentController.updateReimbursement(request, session.getAttribute("username").toString());
			request.getRequestDispatcher("managerhome").forward(request, response);
			break;
		default:
			request.getRequestDispatcher("employeeHome").forward(request, response);
		}
	}

}
