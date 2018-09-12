package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Employee;
import service.AccountManager;

/**
 * Servlet implementation class ProfileServlet
 */
public class ProfileServlet extends Project1Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		
		HttpSession hs = req.getSession();
		if(hs == null)
		{
			res.sendRedirect("/Project1/LogInServlet");
			return;
		}
		else
		{
			int token = (Integer)hs.getAttribute("token");
			Employee e = AccountManager.getEmployee(token).getObject();
			//System.out.println(e);
			
			if(e == null)
			{
				res.sendRedirect("/Project1/LogInServlet");
				return;
			}
			
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream is = loader.getResourceAsStream("pages/Profile.html");
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			Scanner scan = new Scanner(is);
			String content = "";
			while(scan.hasNextLine())
				content+= scan.nextLine() + "\n";
			
			Date hd = e.getHiredate();
			Date bd = e.getBirthdate();
			
			String hireDate = (hd == null) ? "N/A" : hd.toString();
			String birthday = (bd == null) ? "N/A" : bd.toString();
			content = content.replace("id='bd'>", "id='bd'>" + birthday);
			content = content.replace("id='hd'>", "id='bd'>" + hireDate);
			content = content.replace("id='fn'", "id='fn' value='" + e.getFirstName()+ "'");
			content = content.replace("id='ln'", "id='ln' value='" + e.getLastName()+ "'");
			pw.print(content);
			scan.close();
			pw.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{		
		HttpSession hs = req.getSession();
		
		//System.out.println("Profile do post");
		Integer token = null;
		String sessionPurp= null;
		Cookie cook = null;
		Integer CookieToken = -1;
		try {
			sessionPurp =(String) hs.getAttribute("purpose");
			CookieToken = Integer.parseInt(getCookieOfInterest(req).getValue());
			token = (Integer)hs.getAttribute("token");
		}
		catch(NullPointerException | ClassCastException | NumberFormatException e)
		{
			res.sendRedirect("/Project1/LogInServlet");
		}
		
		//if(sessionPurp.equals("get")) 
		//{
			//System.out.println("Profile Get Detected");
			//doGet(req, res);
			//return;
		//}
		
		if(!token.equals(CookieToken))
		{
			res.sendRedirect("/Project1/LogInServlet");
		}
		
		Employee e = AccountManager.getEmployee(token).getObject();
			
		if(e == null)
		{
			res.sendError(500, "Employee Could not be retrieved! ");
		}
		else
		{
			String strValue = req.getParameter("firstName");
			//System.out.println("firstname: " + strValue);
			
			if(strValue != null)
				e.setFirstName(strValue);
			strValue = req.getParameter("lastName");
			//System.out.println("lastname: " + strValue);
			
			
			if(strValue != null)
				e.setLastName(strValue);
			strValue = req.getParameter("oldPass");
			String newP1 = req.getParameter("newPass1");
			String newP2 = req.getParameter("newPass2");
			
			
			if(updatePassword(e.getUserName(), strValue, newP1, newP2))
				AccountManager.updateEmployee(token, e, strValue, newP1);
			else
				AccountManager.updateEmployee(token, e);
			
			//System.out.println("password0: " + strValue);
			//System.out.println("password1: " + newP1);
			//System.out.println("password2: " + newP2);
			
			
			doGet(req,res);
		}			
	}
	
	
	private boolean updatePassword(String un, String old, String new1, String new2)
	{
		try
		{
			return new1.equals(new2) && AccountManager.properCredentials(un, old); 
		}
		catch(NullPointerException e)
		{
			return false;
		}
	}

}
