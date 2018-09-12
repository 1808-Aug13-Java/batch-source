package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Employee;
import dao.ReimbursementRequest;
import service.AccountManager;
import service.ReturnPack;

/**
 * Servlet implementation class CreateRequestServlet
 */
public class CreateRequestServlet extends Project1Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		if(ValidateSession(req) == null)
		{
			res.sendRedirect("/Project1/LogInServlet");
			return;
		}
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream is = loader.getResourceAsStream("pages/NewRequest.html");
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
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Employee e = ValidateSession(req);
		Integer token = GetToken(req);
		if(e == null)
		{
			res.sendRedirect("/Project1/LogInServlet");
			return;
		}
		
		String reqLetter = req.getParameter("reqLetter");
		String reqName = req.getParameter("reqName");
		String manager = req.getParameter("TargetManager");
		
		Employee e1 = AccountManager.getEmployee(manager).getObject();
		int manId = -1;
		if(e1 != null)
			manId = e1.getId();
		
		ReimbursementRequest rr = new ReimbursementRequest(e.getId(), -1, manId, reqName, reqLetter,
			false);
		
//		System.out.println("Creating request: '" + reqName + "' with managerID '" +manId+ "'");
		
		ReturnPack<Object> rp = AccountManager.submitRequest(token, rr);
		//System.out.println("Return Pack code : " + rp.getErrorCode() + " and message '" + rp.getErrorMessage() + "'");
		doGet(req, res);
	}

}
