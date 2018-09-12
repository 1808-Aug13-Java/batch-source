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
import com.revature.models.Reimbursement;


//Find this on the url /pendingRequests

public class WebPastePendingRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public WebPastePendingRequests() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("login");
			return;
		}
		
		ReimbursementDao rd = new ReimbursementDaoImpl();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		
		String queryPendingRequests = "SELECT * FROM P1_REIMBURSEMENTS WHERE STATUS = \'PENDING\'";
		List<Reimbursement> list = rd.listReimbursements(queryPendingRequests);
		
		String data = om.writeValueAsString(list);
		data = "{\"pending\": " + data + "}";
		pw.print(data);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
