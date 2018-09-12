package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.ManagerDaoImpl;

/**
 * Servlet implementation class ManagerLoginServlet
 */
public class ManagerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagerLoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Views/ManagerLogin.html").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		System.out.println("This is user " + user);
		System.out.println("This is pass " + pass);

		ManagerDaoImpl mdi = new ManagerDaoImpl();

		if (mdi.validateUser(user,pass)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", user);
			response.sendRedirect("manSuccess");
			System.out.println("PASS");

		} else {
			response.sendRedirect("LoginFailed");
			System.out.println("FAIL");

		}
	}  

}


