package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.Employee;
import service.AccountManager;
import service.ReturnPack;

/**
 * Servlet implementation class ViewRequestServlet
 */
public class ViewRequestServlet extends Project1Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		Employee e = ValidateSession(req);
		if(e == null)
		{
			res.sendRedirect("/Project1/LogInServlet");
			return;
		}
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream is = null;
		if(e.isManager())
			is = loader.getResourceAsStream("pages/RequestListMan.html");
		else
			is = loader.getResourceAsStream("pages/RequestListEmp.html");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		Employee e = ValidateSession(req);
		if(e == null || !e.isManager())
		{
			//System.out.println("Session Invalidated");
			return;
		}
		
		String reqName = req.getHeader("RequestName");
		String reqRes = req.getHeader("Approve");
		
		if(reqName == null || reqRes == null)
		{
			Logger.getRootLogger().info("Value found to be null");
			return;
		}
		
		if(AccountManager.RequestExists(reqName))
		{
			ReturnPack<Object>rp;
			if(reqRes.toLowerCase().trim().equals("true"))
				rp = AccountManager.settleRequest(GetToken(req), reqName, true);
			else
				rp = AccountManager.settleRequest(GetToken(req), reqName, false);
			
			if(rp.getErrorCode() == 0)
				res.getWriter().write("true");
			else
			{
				Logger.getRootLogger().info(rp.getErrorMessage());
				res.getWriter().write(rp.getErrorMessage());
			}
		}
		else
			Logger.getRootLogger().info("request " + reqName + " does not exist!");
	}

}
