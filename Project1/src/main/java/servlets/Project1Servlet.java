package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Employee;
import service.AccountManager;

/**
 * Servlet implementation class Project1Servlet
 */
public abstract class Project1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Project1Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected abstract void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected abstract void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	
	protected Employee ValidateSession(HttpServletRequest req)
	{
		HttpSession hs = req.getSession();
		if(hs == null)
			return null;
		try {
			//String sessionPurp =(String) hs.getAttribute("purpose");
			Integer CookieToken = Integer.parseInt(getCookieOfInterest(req).getValue());
			Integer token = (Integer)hs.getAttribute("token");
			if(token.equals(CookieToken))
				return AccountManager.getEmployee(token).getObject();
		}
		catch(NullPointerException | ClassCastException | NumberFormatException e)
		{
			return null;
		}
		return null;
		
	}
	
	protected Integer GetToken(HttpServletRequest req)
	{
		HttpSession hs = req.getSession();
		if(hs == null)
			return null;
		try
		{
			return (Integer)hs.getAttribute("token");
		}
		catch(NullPointerException | NumberFormatException | ClassCastException e)
		{
			return null;
		}
	}

	protected Cookie getCookieOfInterest(HttpServletRequest req)
	{
		Cookie[] co = req.getCookies();
		if(co == null)
			return null;
		for(Cookie c : co)
		{
			if(c.getName().equals("emp_proj_1"))
				return c;
		}
		return null;
	}
}
