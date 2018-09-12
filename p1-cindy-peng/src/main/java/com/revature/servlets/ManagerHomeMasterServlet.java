package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ManagerHomeMasterServlet
 */
public class ManagerHomeMasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ManagerHomeMasterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);
		if(session == null) 
			response.sendRedirect("login");
		else
			request.getRequestDispatcher("Views/manager_home.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String view_employees = request.getParameter("view_employees");
		String logout = request.getParameter("logout");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("login");
			return;
		}
		
		if(view_employees != null && view_employees.equals("View All Employees")) {
			response.sendRedirect("roster");
			return;
		}
//		System.out.println(request.getParameter("logout")); //testing see wat the parameter value actually is...
											//turns out is just wat we put as attribute value
		if(logout != null && logout.equals("Log Out")) {//value was set to "Log out"
			response.sendRedirect("logout"); //this logout refers to the logout servlet's url path
		}
		else
			System.out.println("This is logout: "+request.getParameter("logout"));
	}

}
