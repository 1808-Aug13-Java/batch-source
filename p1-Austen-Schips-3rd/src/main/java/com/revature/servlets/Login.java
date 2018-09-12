package com.revature.servlets;

import com.revature.dao.*;
import com.revature.models.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SystemDAO dao = new SystemDAOImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("Index.html").forward(request, response);;
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			String username = request.getParameter("user");
			String password = request.getParameter("pass");
			String isManager = request.getParameter("managerSelect");
			out.write("<body onload='myFunction()'><a href='#' onclick='myFunction()'>Redirecting...Click here to continue if nothing happens</a></body>\n<script>function myFunction(){\n");
			int user = 0;
			boolean managerstatus;
			if(isManager.equals("n")) {
				managerstatus= false;
			} else {managerstatus = true;}
			try {
				user = dao.userLogin(username, password, isManager);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			if(user != 0) {
				out.write(dao.returnUserAsSession(username, password));
				List<String> reimbursement = dao.returnRList(user, managerstatus);
				if(reimbursement == null || reimbursement.isEmpty()) {
					System.out.println("result empty");
				out.write("\nsessionStorage.table = \"<tr>" + 
						"      <th>1</th>" + 
						"      <td>Dummy</td>" + 
						"      <td>Dummy</td>" + 
						"      <td>Dummy</td>" + 
						"      <td>Dummy</td>" + 
						"    </tr>\";\n"); //dummy code for a blank table.
				} else {
					out.write("\nsessionStorage.table = \"");
					for(String temp : reimbursement) {
						out.write(temp);
					}
					out.write("\";\n");
				}
				if(isManager.equals("n")) {
					out.write("window.location.href = 'Employee.html';");
				}else {
					out.write("window.location.href = 'Manager.html';");
				}
				
			} else {
				out.write("sessionStorage.removeItem('user');"
						+ "sessionStorage.removeItem('pass');"
						+ "sessionStorage.removeItem('email');"
						+ "sessionStorage.removeItem('table');"
						+ "alert('USER DOES NOT EXIST OR PASSWORD INCORRECT.');");
				out.write("window.location.href = 'Index.html';");
			}
			out.write("\n}</script>");
	}
}