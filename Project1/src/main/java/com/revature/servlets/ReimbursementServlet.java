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
import com.revature.dao.ZukemployeeDao;
import com.revature.dao.ZukemployeeDaoImpl;
import com.revature.model.Reimbursement;
import com.revature.model.Zukemployee;

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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");

		ReimbursementDao rd = new ReimbursementDaoImpl();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		
		//if we get a parameter we want to display a single reimbursement
		if(idStr != null) {
			int id = Integer.parseInt(idStr);
			Reimbursement r = rd.getSingleRequestById(id);
			//if we don't get anything from the database our id will be 0
			if(r.getId()==0) {
				pw.print("invalid reimbursement id");
			//if we are returned a reimbursement from the database we want to display it in json format
			} else {
				String reimbursementString = om.writeValueAsString(r);
				pw.write(reimbursementString);
			}
		//if we do not get a valid parameter, we want to display all reimbursements
		} else {
			List<Reimbursement> reimbursements = rd.getAllRequests();
			String reimbursementString = om.writeValueAsString(reimbursements);
			reimbursementString = "{\"reimbursements\":"+reimbursementString+"}";
			pw.print(reimbursementString);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
