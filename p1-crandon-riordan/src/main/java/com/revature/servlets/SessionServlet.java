package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			if(email != null && isManager != null) {
				String json = "{\"email\": \"" + email;
				json += "\", \"" + "isManager\": " + isManager + "}"; 
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
