package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.SystemDAO;
import com.revature.dao.SystemDAOImpl;

public class Submit extends HttpServlet{
	private static final long serialVersionUID = 1L;
	SystemDAO dao = new SystemDAOImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String userIDstr = request.getParameter("HiddenID");
		int userID = Integer.parseInt(userIDstr);
		String amountstr = request.getParameter("amount");
		if (amountstr.equals("")) {
			amountstr = "0.00";
		}
		double amount = Double.parseDouble(amountstr);
		String desc = request.getParameter("reimbdesc");
		out.write("<body onload='myFunction()'><a href='#' onclick='myFunction()'>Redirecting...Click here to continue if nothing happens</a></body>\n<script>function myFunction(){\n");
		boolean submitted = dao.submitFields(userID, amount, desc);
		if(submitted) {
			List<String> reimbursement = dao.returnRList(userID, false);
			if(reimbursement == null || reimbursement.isEmpty()) {
				System.out.println("result empty");
			out.write("alert('Submission Failed');\n"); //dummy code for a blank table.
			} else {
				out.write("\nsessionStorage.table = \"");
				for(String temp : reimbursement) {
					out.write(temp);
				}
				out.write("\";\n");
			}
		}
		out.write("window.location.href = 'Employee.html';}</script>");
	}
}