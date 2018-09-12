package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutSessionServlet
 */
public class LogoutSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutSessionServlet() {
        super();
    }

	//Whenever you wanna logout, go to this url: /logout. It takes you to a page that'll invalidate the current
    //session, and display some text saying ur logged out
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
			
//			request.getRequestDispatcher("Views/login.html").forward(request, response);
//			response.sendRedirect("login");
		}

		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		pw.print("<p>Successfully logged off!</p>");
		pw.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
