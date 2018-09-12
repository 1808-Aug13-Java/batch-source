package com.revature.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;

import com.revature.controller.ServiceCon;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getRootLogger();
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uName = request.getParameter("userName");
		String pswd = request.getParameter("pswd");
		String logInOp = request.getParameter("loginOption");
		log.info("userName :" + uName + " pswd : " + pswd + " log in option: " + logInOp);
		
		switch (request.getParameter("loginOption")) {
		case "manager":
			if(ServiceCon.managerLogIn(uName, pswd)) {
				request.getRequestDispatcher("managerhome").forward(request, response);
			}else {
				request.getRequestDispatcher("home?login=fail").forward(request, response);
			}
			break;
		case "employee":
			if(ServiceCon.employeeLogIn(uName, pswd)) {
				request.getRequestDispatcher("employeeHome").forward(request, response);
			}else {
				request.getRequestDispatcher("home?login=fail").forward(request, response);
			}
			break;
		default:
			response.sendRedirect("home");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}