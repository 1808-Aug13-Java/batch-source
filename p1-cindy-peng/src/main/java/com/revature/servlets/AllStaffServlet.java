package com.revature.servlets;

import java.io.IOException;

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
 * Servlet implementation class AllStaffServlet
 */
public class AllStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AllStaffServlet() {
        super();
    }

    //this one's url is localhost:8082/p1-cindy-peng/roster
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//forward the request to be allstaff.html webpage. This doesnt change the url. The url is currently from
		//the servlet's url-mapping.  p1-cindy-peng/roster. This forwards to correct custom html page we want without
		//changing the url.
		HttpSession session = request.getSession(false); //get session if there is, but dont create one if there isnt
		if(session == null) {//getSession with false, rtns null if no curr session
			response.sendRedirect("login");}
		else {
			request.getRequestDispatcher("Views/AllStaff.html").forward(request, response);}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) 
			response.sendRedirect("login");
		else
		{
			String button = request.getParameter("homeButton");
			if( button != null && button.equals("Back to Home") )
			{
				StaffDao sd = new StaffDaoImpl();
				//use session username to get Staff object. Pass into RequestHelper to get String. Redirect
				String userN = (String) session.getAttribute("username");
				Staff staff = sd.getStaffByUserName(userN);
				response.sendRedirect( RequestHelper.getLoginHomepageRedirect(staff) ); //redirect to right home pg
			}
		}
		//nothing in doPost. The aaction we need to take to display that table of our staff roster is 
		//really already done in the AllStaff.html and AllStaff.js files. Then either doGet or doPost really
		//would just do same thing: display/ forward to that new html view.
	}

}
