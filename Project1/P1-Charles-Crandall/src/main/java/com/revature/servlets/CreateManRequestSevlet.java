package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.RequestDao;

/**
 * Servlet implementation class CreateManRequestSevlet
 */
public class CreateManRequestSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateManRequestSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO THE HELL YOU DOIN, GO TO MANAGER PAGE
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (int) request.getSession(false).getAttribute("id");
		String description = (String) request.getParameter("description");
		String amtStr = (String) request.getParameter("amount");
		float amount = Float.parseFloat(amtStr);
		new RequestDao().addRequest(id, amount, description);
		response.sendRedirect("manager");
	}

}
