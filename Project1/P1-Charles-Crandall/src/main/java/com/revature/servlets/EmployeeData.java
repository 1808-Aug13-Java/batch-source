package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.util.RequestHelper;

/**
 * Servlet implementation class EmployeeData
 */
public class EmployeeData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getRootLogger();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeData() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (int) request.getSession().getAttribute("id");
		String table = request.getParameter("directive");
		PrintWriter pw = response.getWriter();
		pw.write(RequestHelper.director(id, table));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {///////TODO MAKE THIS ONE DO THE EDITS
	}

}
