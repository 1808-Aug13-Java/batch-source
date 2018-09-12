package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;

/**
 * Servlet implementation class ProfileFieldTable
 */
public class ProfileFieldTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileFieldTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDao ed = new EmployeeDaoImpl();
		HttpSession session = request.getSession();
		PrintWriter pw = response.getWriter();
		String name = (String) session.getAttribute("name");
		String username = (String) session.getAttribute("username");
		String privateInfo = (String) session.getAttribute("private");
		response.setContentType("application/json");
		
		if(session!=null) 
		{
			pw.write("{\"fields\":[");
			pw.write("{\"name\":\""+session.getAttribute("name")+"\",");
			pw.write("\"username\":\""+session.getAttribute("username")+"\",");
			pw.write("\"private\":\""+session.getAttribute("private")+"\"}");
			pw.write("]}");
		}
		else
		{
			pw.write("{\"name\":\"null\",");
			pw.write("\"username\":\"null\",");
			pw.write("\"private\":\"null\"}");
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
