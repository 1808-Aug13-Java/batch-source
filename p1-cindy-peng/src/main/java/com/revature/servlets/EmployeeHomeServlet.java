package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EmployeeHomeServlet
 */
public class EmployeeHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeHomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) 
			response.sendRedirect("login");
		else
			request.getRequestDispatcher("Views/employee_home.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("login");
			return;
		}
		
		String logoutButton = request.getParameter("logout");
		if(logoutButton != null && logoutButton.equals("Log Out"))
			response.sendRedirect("logout");
	}

}
