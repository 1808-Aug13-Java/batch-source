package com.revature.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;

/**
 * Servlet implementation class SaveProfileServlet
 */
public class SaveProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		EmployeeDao ed = new EmployeeDaoImpl();
		
		String name = request.getParameter("name");
		String user = request.getParameter("username");
		String privateInfo = request.getParameter("private");
		
		System.out.println(name);
		System.out.println(user);
		System.out.println(privateInfo);
		
		int id = Integer.parseInt(session.getAttribute("employeeId") + "");
		
		ed.updateEmployee(id, name, user, privateInfo);
		
		session.setAttribute("name", name);
		session.setAttribute("user", user);
		session.setAttribute("private", privateInfo);
		
		response.sendRedirect("employeeProfile");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
