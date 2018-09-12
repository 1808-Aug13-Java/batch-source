package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.StaffDao;
import com.revature.dao.StaffDaoImpl;
import com.revature.models.Staff;
import com.revature.util.RequestHelper;

/**
 * Servlet implementation class EmployeeViewPendingRequestsServlet
 */
public class EmployeeViewPendingRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeViewPendingRequestsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    //url : /emp_view_pend
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) 
			response.sendRedirect("login");
		else
			request.getRequestDispatcher("Views/employeeViewPendingRequests.html").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) 
			response.sendRedirect("login");
		else
		{
			String button = request.getParameter("homeButton");
			if( button != null && button.equals("Back to Home") )
			{
				StaffDao sd = new StaffDaoImpl();
				//use session username to get Staff object. Pass into RequestHelper to get String. Redirect
				String userN = (String) session.getAttribute("username");
				Staff staff = sd.getStaffByUserName(userN);
				response.sendRedirect( RequestHelper.getLoginHomepageRedirect(staff) ); //redirect to right home pg
			}
		}
	}

}
