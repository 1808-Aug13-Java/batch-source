package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.controller.ReimbursementsController;

/**
 * Servlet implementation class UpdateReimbursementServlet
 */
public class UpdateReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getRootLogger();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateReimbursementServlet() {
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

		ReimbursementsController.updateReimbursement(request, session.getAttribute("username").toString());
		
		request.getRequestDispatcher("/Views/managerhome.html").forward(request, response);
	}

}
