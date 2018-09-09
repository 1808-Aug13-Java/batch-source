package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.controller.ServiceController;

/**
 * Servlet implementation class LogIn
 */
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getRootLogger();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uName = request.getParameter("userName");
		String pswd = request.getParameter("pswd");
		String logInOp = request.getParameter("loginOption");
		log.info("userName :" + uName + " pswd : " + pswd + " log in option: " + logInOp);
		
		switch (request.getParameter("loginOption")) {
		case "manager":
			if(ServiceController.managerLogIn(uName, pswd)) {
				request.getRequestDispatcher("managerhome").forward(request, response);
			}else {
				request.getRequestDispatcher("home?login=fail").forward(request, response);
			}
			break;
		case "employee":
			if(ServiceController.employeeLogIn(uName, pswd)) {
				request.getRequestDispatcher("employeeHome").forward(request, response);
			}else {
				request.getRequestDispatcher("home?login=fail").forward(request, response);
			}
			break;
		default:
			response.sendRedirect("home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
