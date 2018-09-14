package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.dao.RequestDao;
import com.revature.dao.RequestDaoInterface;

/**
 * Servlet implementation class RequestData
 */
public class RequestData extends HttpServlet {
	private static Logger log = Logger.getRootLogger();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestData() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		String aprDen = request.getParameter("do");
		int man = (int) request.getSession().getAttribute("id");
		RequestDaoInterface rd = new RequestDao();
		if (aprDen.equals("approve"))
			rd.approveRequest(Integer.parseInt(idStr), man);
		else
			rd.denyRequest(Integer.parseInt(idStr), man);
		request.getRequestDispatcher("manager").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {/////////////TODO ADD REQUEST
		response.getWriter().write("RESPONDING FIRST");//request.getParameter("id"));
	}
}
