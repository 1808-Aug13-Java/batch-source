package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
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
 * Servlet implementation class LogInServlet
 */
public class LogInServlet extends Project1Servlet {
	private static final long serialVersionUID = 1L;
       private boolean authenticated = false;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		if(ValidateSession(req) != null)
			res.sendRedirect("/Project1/ProfileServlet");
			
		if(CheckAuthentication(req) < 1)
		{
		
			
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream is = loader.getResourceAsStream("pages/Login.html");
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			Scanner scan = new Scanner(is);
			String content = "";
			while(scan.hasNextLine())
				content+= scan.nextLine() + "\n";
			pw.write(content);
			scan.close();
			res.setStatus(HttpServletResponse.SC_OK);
		}
		else
		{
			doPost(req,res);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException
	{

		int token = CheckAuthentication(req);
		authenticated = token > 0;
		
		if(authenticated)
		{
			Employee e = AccountManager.getEmployee(token).getObject();
			if(e == null)
			{
				response.sendError(500, "Employee Could not be retrieved! ");
			}
			//System.out.println("LoggedOn!");
			HttpSession hs = req.getSession();
			hs.setAttribute("token", new Integer(token));
			hs.setAttribute("purpose", "get");
			Cookie c = new Cookie("emp_proj_1", new Integer(token).toString());
			response.addCookie(c);
			response.sendRedirect("/Project1/ProfileServlet");
		}
		else
		{
			if(req.getHeader("submit") == null)
			{
				doGet(req,response);
				//System.out.println("Not Submiting");
			}
			else
			{
				
				response.sendError(401,"Invalid Credentials");
			}
		}
	}
	
	protected int CheckAuthentication(HttpServletRequest req)
	{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		//System.out.println("username: '" + username + "' password: '" + password + "'");
		return AccountManager.logOn(username, password);
	}

}
