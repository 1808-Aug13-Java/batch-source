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
import com.revature.models.Staff;

/**
 * Servlet implementation class EmployeeSubmitRequestServlet
 */
public class EmployeeSubmitRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeSubmitRequestServlet() {
        super();
    }
// url:   /submitRequest
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session == null)
			response.sendRedirect("login");
		else
			request.getRequestDispatcher("Views/employeeSubmitRequest.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session == null)
			response.sendRedirect("login");
		else
		{
			float amount = Float.parseFloat( request.getParameter("amount") );
			String message = request.getParameter("message");
			
			StaffDao sd = new StaffDaoImpl();
			Staff s = sd.getStaffByUserName( (String) session.getAttribute("username") );
			int staffId = s.getStaffId();
												//man_id?
			ReimbursementDao rd = new ReimbursementDaoImpl();
			if(rd.createRequest( new Reimbursement (1, staffId, "PENDING", 1 , amount, "", message) ) == 1)
				response.sendRedirect("success");
		}
	}

}
