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
import com.revature.reim.dao.ManagerDao;
import com.revature.reim.dao.ManagerDaoImpl;
import com.revature.reim.model.Employee;
import com.revature.reim.model.Reimbursement;

/**
 * Servlet implementation class ManViewOneReim
 */
public class ManViewOneReim extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManViewOneReim() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get request recieved");
		HttpSession session = request.getSession(false);
		
		String uname=(String) session.getAttribute("username");
		int employeeID=(int) session.getAttribute("empId");
		
//		System.out.println(uname);
		EmployeeDao ed = new EmployeeDaoImpl();
		ManagerDao man= new ManagerDaoImpl();
		int empId= ed.getEmpId(uname);
		System.out.println(empId);
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
			if(empId==0) {
				pw.print("invalid employee id");
			}
		else {
//			List<Employee> reimbursementsList = man.viewAllEmployees(3);//pending reims
			List<Reimbursement> reimList= man.getReimbursementsByEmp(employeeID);
			System.out.println(reimList);
			String reimString = om.writeValueAsString(reimList);
			reimString = "{\"reimbursements\":"+reimString+"}";
			pw.print(reimString);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reim=request.getParameter("empId");
		int empId=Integer.parseInt(reim);
		System.out.println(reim);
		HttpSession session = request.getSession(false);
		session.setAttribute("empId", empId);
		response.sendRedirect("manager");
	}

}
