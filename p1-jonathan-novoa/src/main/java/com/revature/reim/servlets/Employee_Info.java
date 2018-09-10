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
import com.revature.reim.model.Employee;
import com.revature.reim.model.Reimbursement;

/**
 * Servlet implementation class Employee_Info
 */
public class Employee_Info extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employee_Info() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String idStr = request.getParameter("id");
		HttpSession session = request.getSession(false);
		String uname=(String) session.getAttribute("username");
		System.out.println(uname);
		EmployeeDao ed = new EmployeeDaoImpl();
		int empId= ed.getEmpId(uname);
		System.out.println(empId);
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		
		
		//if we get a parameter we want to display a single employee
//		if(uname != null) {
//			int id = Integer.parseInt(idStr);
//			Employee e = ed.getEmployeeById(id);
//			List<Reimbursement> employeeReim= ed.viewReimbursments(empId, 1);
			
			//if we don't get anything from the database our id will be 0
			if(empId==0) {
				pw.print("invalid employee id");
			//if we are returned an employee from the database we want to display it in json format
			} 
//			else {
//				String reimbursementString = om.writeValueAsString(e);
//				pw.write(reimbursementString);
//			}
		//if we do not get a valid parameter, we want to display all employees
		else {
			List<Reimbursement> reimbursementsList = ed.viewReimbursments(empId, 3);//denied reims
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
		doGet(request, response);
	}

}
