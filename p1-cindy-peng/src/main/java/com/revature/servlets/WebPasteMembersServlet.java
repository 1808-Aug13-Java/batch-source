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
import com.revature.dao.StaffDao;
import com.revature.dao.StaffDaoImpl;
import com.revature.models.Staff;

/**
 * Servlet implementation class EmployeeDirectoryServlet
 */
public class WebPasteMembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebPasteMembersServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("login");
			return;
		}
		
//		gotta grab all the employees using listEmployee from dao. Put it in the object mapper. present it, then
		//the js file will pull all of these and append rows to html table in a separate staffList.html file
//		https://github.com/1808-Aug13-Java/batch-source/blob/Carolyn_Rehm/Week4-Servlets/ServletJDBCDemo/src/main/java/com/revature/servlets/EmployeeServlet.java
		//the url path for this servlet is  "/employees" so basically this servlet is gonna be a web page
		//that we use PrintWriter to print employees to
		
		StaffDao sd = new StaffDaoImpl();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		
		String allStaffQuery = "SELECT * FROM P1_STAFF";
		List<Staff> staffMembersList =  sd.listStaffMembers(allStaffQuery);
		//list as a string the list, usuig Object Mapper
		String data = om.writeValueAsString(staffMembersList);
		data = "{\"staffMembers\": " + data + "}"; 
		pw.print(data);
		
		//test
//		http://localhost:8082/p1-cindy-peng/employees     
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
