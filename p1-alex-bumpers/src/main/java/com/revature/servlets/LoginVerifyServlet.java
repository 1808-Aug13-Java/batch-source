package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.util.LoginAccountManager;
import com.revature.util.SessionManager;

/**
 * Servlet implementation class LoginVerifyServlet
 */
public class LoginVerifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    LoginAccountManager accountManager = new LoginAccountManager();
    
    public LoginVerifyServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String endpoint = accountManager.determineAccountType(request);
			
			if (endpoint.equals("login")) {
				response.sendRedirect(endpoint);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("username", request.getParameter("username"));
				String username = (String) session.getAttribute("username");
				SessionManager sessionManager = new SessionManager();
				
				sessionManager.createSessionForManagerAccounts(username, session);
				response.sendRedirect(endpoint);
				System.out.println(username);
			}
			
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
