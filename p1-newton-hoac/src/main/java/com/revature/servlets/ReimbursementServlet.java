package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.models.Location;
import com.revature.models.Reimbursement;

/**
 * Servlet implementation class ReimbursementServlet
 */
public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		
		ReimbursementDao rdi = new ReimbursementDaoImpl();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		
		if(idStr != null) {
			int id = Integer.parseInt(idStr);
			List<Reimbursement> r = rdi.getReimbursementsByEmpId(id);
			
			String reimbursementString = om.writeValueAsString(r);
			
			reimbursementString = "{\"reimbursement\":"+reimbursementString+"}";
			
			pw.println(reimbursementString);
		} else {
			List<Reimbursement> reimbursements = rdi.getReimbursements();
			
			String reimbursementString = om.writeValueAsString(reimbursements);
			
			reimbursementString = "{\"reimbursement\":"+reimbursementString+"}";
			
			pw.println(reimbursementString);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
