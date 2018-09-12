package com.revature.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.ZukemployeeDao;
import com.revature.dao.ZukemployeeDaoImpl;
import com.revature.model.Reimbursement;
import com.revature.model.Zukemployee;

/**
 * Servlet implementation class UpdateRequestServlet
 */
public class UpdateRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRequestServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// we want a forward
		request.getRequestDispatcher("Views/UpdateRequest.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * note: should NOT have logic in servlets. They should only be for getting and retrieving info really
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String currentUser = "" + session.getAttribute("username");
		
		System.out.println("currentUser:  " + currentUser);
		
		
		ZukemployeeDao zdi = new ZukemployeeDaoImpl();
		Zukemployee z = zdi.getEmployeeByUsername(currentUser);
		int currentUserId = z.getId();
		
		ReimbursementDao rd = new ReimbursementDaoImpl();
		
		System.out.println(currentUserId);
		
		//int reimbId = Integer.parseInt(request.getParameter("reimbId"));
		String operation = request.getParameter("operation");
		
		System.out.println("reimb: " +request.getParameter("reimbId"));
		
		//System.out.println(reimbId);
		
		int numResolved = 0;
		
//		if (operation == "APPROVED") { 
//		numResolved = rd.denyRequestById(reimbId);
//		}
//		else if (operation == "DENIED") { 
//			numResolved = rd.approveRequestById(reimbId);
//		}
		
		// check if numCreated is 1, if it is, then success, if not error
		if(numResolved == 1) {
			response.sendRedirect("success");

		} else {
			response.sendRedirect("errorappden");
		}
	}

}