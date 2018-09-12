package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.TransactionDao;
import com.revature.dao.TransactionDaoImpl;
import com.revature.model.Transaction;

/**
 * Servlet implementation class TransactionsOfEmployeeTable
 */
public class TransactionsOfEmployeeTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionsOfEmployeeTable() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TransactionDao td = new TransactionDaoImpl();
		PrintWriter pw = response.getWriter();
		ObjectMapper om = new ObjectMapper();
		String employeeId = request.getParameter("employeeId");
		System.out.println(employeeId);
		
		List<Transaction> transactions = td.getTransactionsOfEmployee(Integer.parseInt(employeeId));
		String transactionString = om.writeValueAsString(transactions);
		transactionString = "{\"transactions\":"+transactionString+"}";
		pw.print(transactionString);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);	
	}

}
