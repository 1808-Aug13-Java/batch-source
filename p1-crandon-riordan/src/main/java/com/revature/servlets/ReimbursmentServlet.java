package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursmentDao;
import com.revature.dao.ReimbursmentDaoImpl;
import com.revature.models.Reimbursment;

/**
 * Servlet implementation class ReimbursmentServlet
 */
public class ReimbursmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReimbursmentDao rd = new ReimbursmentDaoImpl();
		
		List<Reimbursment> reimbursments = rd.getAllReimbursments();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		String reimbursmentString = om.writeValueAsString(reimbursments);
		reimbursmentString = "{\"reimbursments\": " + reimbursmentString + "}";
		pw.write(reimbursmentString);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
