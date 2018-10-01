package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmpDaoImpl;
import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;

/**
 * Servlet implementation class SessionServlet
 */
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		// ensure json content
		response.setContentType("application/json");
		PrintWriter pw = response.getWriter();
		if(session != null) {
			String email = (String) session.getAttribute("email");
			Integer isManager = (Integer) session.getAttribute("isManager");
			Integer login = (Integer) session.getAttribute("login");
			EmployeeDao ed = new EmpDaoImpl();
			Employee employee = ed.getEmployeeByEmail(email);
			session.setAttribute("id", employee.getId());
			if(email != null && isManager != null) {
				String json = "{\"email\": \"" + email;
				json += "\", \"" + "isManager\": " + isManager;
				json +=  ", \"id\": " +  employee.getId() + ", " + 
						"\"name\":" + "\""+employee.getName() + "\", " +
						"\"username\":" + "\"" + employee.getUsername() +"\"}";
				
				pw.write(json);
			} else if (login != null) {
				String json = "{\"login\": " + login + "}";
				pw.write(json);
			}
			
			
			
		} else {
			pw.write("null");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
