package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementsDao;
import com.revature.dao.ReimbursementsDaoImpl;
import com.revature.models.Reimbursements;

/**
 * Servlet implementation class ReimbursementServlet
 */
public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReimbursementServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ReimbursementsDao rd = new ReimbursementsDaoImpl();
		
		List<Reimbursements> reimbursements = rd.getAllReimbursements();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		String reimbursementsString = om.writeValueAsString(reimbursements);
		reimbursementsString = "{\"reimbursments\": " + reimbursementsString + "}";
		pw.write(reimbursementsString);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
