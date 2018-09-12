package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.ZukemployeeDao;
import com.revature.dao.ZukemployeeDaoImpl;
import com.revature.model.Reimbursement;
import com.revature.model.Zukemployee;

/**
 * Servlet implementation class UpdateProfileServlet
 */
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfileServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// we want a forward
		request.getRequestDispatcher("Views/UpdateProfile.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * note: should NOT have logic in servlets. They should only be for getting and retrieving info really
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String currentUser = "" + session.getAttribute("username");
		
		System.out.println("currentUser:  " + currentUser);
		
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		
		ZukemployeeDao zdi = new ZukemployeeDaoImpl();
		Zukemployee z = zdi.getEmployeeByUsername(currentUser);
		int currentUserId = z.getId();
		String currentUserPosition = z.getPosition();
		int managerId = z.getReportsTo();
		String password = z.getPassword();
		System.out.println("current user id:  " + currentUserId);
		
		Zukemployee zed = new Zukemployee(currentUserId, name, currentUserPosition, username, password, managerId);
		
		System.out.println("employee: "+zed);

		int numCreated = zdi.updateEmployee(zed);
		System.out.println("num created:  "+ numCreated);

		// check if numCreated is 1, if it is, then success, if not error
		if(numCreated == 1) {
			response.sendRedirect("success");

		} else {
			response.sendRedirect("error");
		}
	}

}
