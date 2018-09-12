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
import com.revature.dao.ZukemployeeDao;
import com.revature.dao.ZukemployeeDaoImpl;
import com.revature.model.Reimbursement;
import com.revature.model.Zukemployee;

/**
 * Servlet implementation class ProfileJServlet
 */
public class ProfileJServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileJServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		
		HttpSession session = request.getSession();
		String currentUser = "" + session.getAttribute("username");
		
		ZukemployeeDao ed = new ZukemployeeDaoImpl();
		Zukemployee z = ed.getEmployeeByUsername(currentUser);
		int currentUserId = z.getId();
		
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		
			int id = Integer.parseInt(idStr);
			Zukemployee e = ed.getEmployeeById(id);
			//if we don't get anything from the database our id will be 0
			if(e.getId()==0) {
				pw.print("invalid employee id");
			//if we are returned an employee from the database we want to display it in json format
			} else {
				String employeeString = om.writeValueAsString(e);
				employeeString = "{\"employees\":"+employeeString+"}";
				pw.print(employeeString);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
