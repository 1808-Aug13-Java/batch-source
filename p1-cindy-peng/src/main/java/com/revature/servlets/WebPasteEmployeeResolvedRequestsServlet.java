package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.StaffDao;
import com.revature.dao.StaffDaoImpl;
import com.revature.models.Reimbursement;

/**
 * Servlet implementation class WebPasteEmployeeResolvedRequestsServlet
 */
public class WebPasteEmployeeResolvedRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebPasteEmployeeResolvedRequestsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // url : /emp_resolved
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("login");
			return;
		}
		
		ReimbursementDao rd = new ReimbursementDaoImpl();
		StaffDao sd = new StaffDaoImpl();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		
		String userN = (String) session.getAttribute("username");
		int staff_Id = sd.getStaffByUserName(userN).getStaffId();
		String queryPendingRequests = "SELECT * FROM P1_REIMBURSEMENTS WHERE (NOT STATUS = \'PENDING\') AND "
									+ "STAFF_ID = " + staff_Id + "";
		List<Reimbursement> list = rd.listReimbursements(queryPendingRequests);
		
		String data = om.writeValueAsString(list);
		data = "{\"resolved\": " + data + "}";
		pw.print(data);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
