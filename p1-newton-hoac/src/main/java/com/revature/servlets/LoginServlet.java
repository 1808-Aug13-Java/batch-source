package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.LoginDao;
import com.revature.dao.LoginDaoImpl;
import com.revature.models.Login;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Views/Login.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		LoginDao ldi = new LoginDaoImpl();
		Login l = ldi.getLoginByUser(user);
//		System.out.println(l.getId() + " " + l.getUser() + " " + l.getPwsd());
//		System.out.println(user + " " + pass);
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		// Create a new session
		HttpSession session = request.getSession();
		
		if (user.equals(l.getUser()) && pass.equals(l.getPwsd())) {
			session.setAttribute("username", user);
			session.setAttribute("id", l.getId());
			response.sendRedirect("choose?id=" + l.getId());
		} else {
			response.sendRedirect("loginfail");
		}
	}

}
