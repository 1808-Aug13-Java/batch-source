package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.controller.ReimbursmentController;

/**
 * Servlet implementation class GetReimbursmetsByEmployee
 */
public class GetReimbursmetsByEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getRootLogger();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetReimbursmetsByEmployee() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if(session.isNew())
		{
			log.info("user not logged in");
		}
		log.info("in getReimbursementByEmployee " + session.getAttribute("username").toString());
		PrintWriter pw = response.getWriter();
		pw.print(ReimbursmentController.getReimbursementByUserName(session.getAttribute("username").toString()));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
