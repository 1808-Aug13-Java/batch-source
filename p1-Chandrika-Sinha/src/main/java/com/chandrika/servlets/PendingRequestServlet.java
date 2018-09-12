package com.chandrika.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chandrika.dao.RequestDao;
import com.chandrika.dao.RequestDaoImpl;
import com.chandrika.model.Request;
import com.chandrika.util.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PendingRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 4845802073597259148L;

	public PendingRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!User.isNull() && !User.isManager()) {
			RequestDao rd = new RequestDaoImpl();
			ObjectMapper om = new ObjectMapper();
			PrintWriter pw = response.getWriter();
			List<Request> requests = rd.getPendingRequestsByEmployeeId(User.getUserId());
			String requestString = om.writeValueAsString(requests);
			requestString = "{\"requests\":"+requestString+"}";
			pw.print(requestString);
		} else {
			request.getRequestDispatcher("Views/Login.html").forward(request, response);
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