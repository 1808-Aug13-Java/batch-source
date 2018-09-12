package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.StaffDao;
import com.revature.dao.StaffDaoImpl;
import com.revature.models.Reimbursement;

/**
 * Servlet implementation class UpdateRequests
 */

//url is /updateRequest
public class UpdateRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateRequests() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("login");
			return;
		}
		request.getRequestDispatcher("Views/UpdateRequests.html").forward(request, response);
		//can use dao getRequestById to test the request
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("login");
			return;
		}
		
		int requestId = Integer.parseInt (request.getParameter("requestId"));
		String status = request.getParameter("selectStatus");
		
		ReimbursementDao rd = new ReimbursementDaoImpl();
		Reimbursement rRequest = rd.getRequestById(requestId);
		if(rRequest != null)
		{
			rRequest.setStatus(status);
			String sessionManagerUsername = (String) session.getAttribute("username");
			StaffDao sd = new StaffDaoImpl();
			rRequest.setManId( sd.getStaffByUserName(sessionManagerUsername).getStaffId() );
			int rowsUpdated = 0;
			rowsUpdated = rd.updateRequest(rRequest); //update in database
			if(rowsUpdated == 1)
				response.sendRedirect("success");
		}
		else
			System.out.println("nothing found");
	}

}
