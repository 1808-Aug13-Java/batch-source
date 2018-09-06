package com.revature.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmpDaoImpl;
import com.revature.util.LoginHelper;
import com.revature.util.SessionHelper;

public class LoginHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginHelper helper = new LoginHelper();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String destination = helper.process(request);
		if(destination.equals("login")) {
			response.sendRedirect(destination);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("email", request.getParameter("email"));
			String email = (String) session.getAttribute("email");
			SessionHelper sh = new SessionHelper();
			// add the is manager check to our session
			sh.addIsManagerToSessionByEmail(email, session);
			response.sendRedirect(destination);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
