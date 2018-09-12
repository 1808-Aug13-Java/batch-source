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
 * Servlet implementation class EmployeeUpdateServlet
 */
public class EmployeeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session == null)
			response.sendRedirect("login");
		else
			request.getRequestDispatcher("Views/employeeUpdate.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session == null)
			response.sendRedirect("login");
		else
		{
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			
			StaffDao sd = new StaffDaoImpl();
			Staff s = sd.getStaffByUserName( (String) session.getAttribute("username") );
			s.setStaffName(name);
			s.setPhone(phone);
												//man_id?
			if(sd.updateStaffProfile(s) == 1)
				response.sendRedirect("success");
		}
	}

}
