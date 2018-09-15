package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.controller.EmployeeController;
import com.revature.controller.ReimbursementsController;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;

/**
 * Servlet implementation class ReimbursementCreateServlet
 */
public class ReimbursementCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Logger log = Logger.getRootLogger();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReimbursementCreateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/Views/employeehome.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReimbursementsController.createNewReimbursement(request);
		request.getRequestDispatcher("/Views/employeehome.html").forward(request, response);
		;
	}

}
