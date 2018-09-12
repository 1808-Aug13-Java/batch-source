package com.revature.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmpDaoImpl;
import com.revature.dao.EmployeeDao;
import com.revature.util.LoginHelper;
import com.revature.util.SessionHelper;

public class LoginHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final LoginHelper helper = new LoginHelper();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String destination = helper.process(request);
		SessionHelper sh = new SessionHelper();
		HttpSession session = request.getSession();
		if(destination.equals("login")) {
			sh.addInvalidLoginToSession(session);
			response.sendRedirect(destination);
		} else {
			// session management
			session.setAttribute("email", request.getParameter("email"));
			String email = (String) session.getAttribute("email");
			EmployeeDao ed = new EmpDaoImpl();
			session.setAttribute("employee", ed.getEmployeeByEmail(email));
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
