package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.util.RequestHelper;

/**
 * Servlet implementation class ProfileServlet
 */
public class EmpProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			request.getRequestDispatcher("Views/EmpProfile.html").forward(request, response);
		} else {
			response.sendRedirect("login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int id = Integer.parseInt(session.getAttribute("id").toString());
//		System.out.println(request.getParameter("Submit"));
//		System.out.println(request.getParameter("address"));
//		System.out.println();
		String destination = RequestHelper.process(request);
		if (destination.equals("Logout")) {
			response.sendRedirect("login");
			return;
		}
		response.sendRedirect("empprofile?id=" +id);
	}

}
