package com.revature.servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.ZukemployeeDao;
import com.revature.dao.ZukemployeeDaoImpl;
import com.revature.model.Reimbursement;
import com.revature.model.Zukemployee;

/**
 * Servlet implementation class NewEmployeeServlet
 */
public class NewRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewRequestServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// we want a forward
		request.getRequestDispatcher("Views/CreateRequest.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * note: should NOT have logic in servlets. They should only be for getting and retrieving info really
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		//int reimbursementId = Integer.parseInt(request.getParameter(""));
		String requestAmountDollars= request.getParameter("dollars");
		String requestAmountCents= request.getParameter("cents");
		String requestAmtString = requestAmountDollars + "." + requestAmountCents;
		BigDecimal totalRequestAmt = new BigDecimal(requestAmtString);

		Zukemployee e = new Zukemployee();
		e.setName(name);
		Reimbursement r = new Reimbursement();
		//r.setId(reimbursementId);

		System.out.println(e);
		System.out.println(totalRequestAmt);

		ReimbursementDao rd = new ReimbursementDaoImpl();

		int numCreated = rd.createRequest(r);
		System.out.println("num created:  "+ numCreated);

		// check if numCreated is 1, if it is, then success, if not error
		if(numCreated == 1) {
			response.sendRedirect("success");

		} else {
			response.sendRedirect("error");
		}
	}

}
