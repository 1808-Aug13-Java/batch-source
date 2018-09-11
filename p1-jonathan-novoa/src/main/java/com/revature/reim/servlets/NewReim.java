package com.revature.reim.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.reim.dao.EmployeeDaoImpl;
import com.revature.reim.model.Reimbursement;



/**
 * Servlet implementation class NewReim
 */
public class NewReim extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewReim() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rq =request.getRequestDispatcher("Views/NewReim.html");
		rq.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		System.out.println(username);
		double amount= Double.parseDouble(request.getParameter("amount"));
		System.out.println(amount);
		
		EmployeeDaoImpl emp = new EmployeeDaoImpl();
		int empId=emp.getEmpId(username);
		int result =emp.submitReimbursement(empId, amount);
		
		System.out.println(result);
		response.sendRedirect("employee");
	
//		if (result==1) {
//			response.sendRedirect("success");
//		
//		}
//		else {
//			response.sendRedirect("error");
//		}
	}

		
	
		
		
		
	

}
