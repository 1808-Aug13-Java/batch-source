package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.StaffDao;
import com.revature.dao.StaffDaoImpl;
import com.revature.models.Staff;
import com.revature.util.RequestHelper;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Views/login.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get the string username and password. Then compare to in the database
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		StaffDao s = new StaffDaoImpl();
		Staff staffMember = s.getStaffByUserName(username);  
		if( staffMember == null || !password.equals( staffMember.getPassW() ) ) //either no user or bad password
		{
			response.sendRedirect("login");		//btw doing sendRedirect doesnt mean functions done
			return;
		}
		
		//valid user, then set up a session
		HttpSession session = request.getSession();
		session.setAttribute("username", username); 		//dont do "user", staffMember because session 
														//just saves strings. Could only do staffMember if
													//you made the object Serializable (smash into string)
		
//		Redirect to the correct home page (manager, both, or employee)
		response.sendRedirect( RequestHelper.getLoginHomepageRedirect(staffMember) );
		
	}

}
