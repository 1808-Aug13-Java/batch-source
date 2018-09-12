package com.revature.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.TransactionDao;
import com.revature.dao.TransactionDaoImpl;
import com.revature.model.Transaction;

/**
 * Servlet implementation class CreateRequestServlet
 */
public class CreateRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRequestServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TransactionDao td = new TransactionDaoImpl();
		Date newDate = Date.valueOf(request.getParameter("date"));
		System.out.println(newDate);
		float newAmount = Float.parseFloat(request.getParameter("amount"));
		System.out.println(newAmount);
		String newReason = request.getParameter("reason");
		System.out.println(newReason);
		int id = Integer.parseInt(request.getSession().getAttribute("employeeId")+"");
		
		td.createTransaction(new Transaction(1, newDate,  id, "PENDING", "n/a", newAmount, newReason));
		request.getRequestDispatcher("Views/CreateRequest.html").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
