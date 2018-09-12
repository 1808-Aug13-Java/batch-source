package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.AccountManager;

/**
 * Servlet implementation class LogOutServlet
 */
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		AccountManager.logOut(getToken(req));
		Cookie cook = getCookieOfInterest(req);
		if(cook != null)
		{
			cook.setValue("-1");
		}
		
		
		HttpSession hs = req.getSession();
		hs.setAttribute("token", -1);
		
		res.sendRedirect("/Project1/LogInServlet");
	}
	
	
	protected Integer getToken(HttpServletRequest req)
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
