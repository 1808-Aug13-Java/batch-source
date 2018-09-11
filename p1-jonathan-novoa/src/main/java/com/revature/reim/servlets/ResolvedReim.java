package com.revature.reim.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reim.dao.EmployeeDao;
import com.revature.reim.dao.EmployeeDaoImpl;
import com.revature.reim.model.Reimbursement;

/**
 * Servlet implementation class ResolvedReim
 */
public class ResolvedReim extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResolvedReim() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String uname=(String) session.getAttribute("username");
		System.out.println(uname);
		EmployeeDao ed = new EmployeeDaoImpl();
		int empId= ed.getEmpId(uname);
		System.out.println(empId);
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		
			if(empId==0) {
				pw.print("invalid employee id");
			} 

		else {
			List<Reimbursement> reimbursementsList = ed.viewReimbursments(empId, 2);//pending reims
			System.out.println(reimbursementsList);
			String reimString = om.writeValueAsString(reimbursementsList);
			reimString = "{\"reimbursements\":"+reimString+"}";
			pw.print(reimString);
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
