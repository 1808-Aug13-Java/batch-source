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
import com.revature.dao.ZukemployeeDao;
import com.revature.dao.ZukemployeeDaoImpl;
import com.revature.model.Reimbursement;
import com.revature.model.Zukemployee;

/**
 * Servlet implementation class SinglePendingReimbServlet
 */
public class SinglePendingReimbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SinglePendingReimbServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String idStr = request.getParameter("id");
		
		HttpSession session = request.getSession();
		String currentUser = "" + session.getAttribute("username");
		
		ZukemployeeDao zdi = new ZukemployeeDaoImpl();
		Zukemployee z = zdi.getEmployeeByUsername(currentUser);
		int currentUserId = z.getId();

		ReimbursementDao rd = new ReimbursementDaoImpl();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();

		//if we get a parameter we want to display reimbursements from ONE employee
			//int id = Integer.parseInt(idStr);
			List<Reimbursement> reimbursements = rd.getPendingRequestsById(currentUserId);
			String reimbursementString = om.writeValueAsString(reimbursements);
			reimbursementString = "{\"reimbursements\":"+reimbursementString+"}";
			pw.print(reimbursementString);
			//if we don't get anything from the database our id will be 0
			if(currentUserId==0) {
				pw.print("invalid employee id"); // because searching by emplyee ids
				//if we are returned a reimbursement from the database we want to display it in json format
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
