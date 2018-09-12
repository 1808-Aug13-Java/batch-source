package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.Employee;
import dao.ReimbursementRequest;
import service.AccountManager;
import service.ReturnPack;

/**
 * Servlet implementation class DataServlet
 */
public class DataServlet extends Project1Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataServlet() {
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
			res.sendError(401, "Employee authentication failed");
			return;
		}
		String purpose = req.getParameter("purpose");
		if(purpose == null)
		{
			//System.out.println("Null Param Detected");
			return;
		}
		
		//System.out.println(purpose);
		
		switch(purpose)
		{
		case "employee":
			GetEmployees(e, req, res, false);
			break;
		case "requests":
			GetRequests(e, req, res);
			break;
		case "manager":
			GetEmployees(e, req, res, true);
			break;
		case "requestExists":
			checkRequest(req, res);
			break;
			default:
				Logger.getRootLogger().info(("Default Param Detected: '" + purpose + "'"));
		}
	}
	
	private void GetEmployees(Employee e, HttpServletRequest req, HttpServletResponse res, boolean targetMan) throws ServletException, IOException
	{
		Integer token = (Integer)req.getSession().getAttribute("token");
		if(e.isManager() && !targetMan)
		{
			ReturnPack<List<Employee>> rp = AccountManager.getEmployeeList(token);
			if(rp == null || rp.getObject() == null)
				return;
			List<Employee> emps = rp.getObject();
			
			ObjectMapper om = new ObjectMapper();
			String employeeString = om.writeValueAsString(emps);
			
			
			employeeString = "{\"employees\":" + employeeString + "}";
			
			res.getWriter().write(employeeString);
			
		}
		else
		{
			List<Employee> rp = AccountManager.getManagerList();
			if(rp == null)
				return;
			
			
			ObjectMapper om = new ObjectMapper();
			String employeeString = om.writeValueAsString(rp);
			
			
			employeeString = "{\"employees\":" + employeeString + "}";
//			System.out.println(employeeString);
//			System.out.println("Employee Numbers: " + rp.size());
			res.getWriter().write(employeeString);
		}
	}
	
	private void GetRequests(Employee e, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		Integer token = (Integer)req.getSession().getAttribute("token");
		if(e.isManager())
		{
			String empParam = req.getParameter("employee");
			String resolved = req.getParameter("resolved");
			String omitDeny = req.getParameter("omitdenies");
			
			//System.out.println("emp: '" + empParam + "' resolved: '" + resolved + "' omitDeny: '" + omitDeny + "'");
			
			boolean od = (omitDeny == null || !omitDeny.toLowerCase().trim().equals("true")) ? false : true;
			
			if(empParam != null)
			{
				Employee e1 = AccountManager.getEmployee(empParam).getObject();
				if(e1 == null)
					return;
				ReturnPack<List<ReimbursementRequest>> rp = AccountManager.getReinbursmentsbyEmployee(token, e1.getId());
				wrapRequestList(rp.getObject(), res);
			}
			else
			{
				if(resolved != null)
				{
					boolean resolve = resolved.toLowerCase().trim().equals("true");
					ReturnPack<List<ReimbursementRequest>> rp = AccountManager.getReinbursmentsbyResolveStatus(token, resolve, od);
					wrapRequestList(rp.getObject(), res);
				}
				else
				{
					ReturnPack<List<ReimbursementRequest>> rp = AccountManager.getReinbursmentsbyResolveStatus(token, false, od);
					ReturnPack<List<ReimbursementRequest>> rp2 = AccountManager.getReinbursmentsbyResolveStatus(token, true, od);
					
					if(rp != null && rp.getObject() != null && rp2 != null && rp2.getObject() != null)
					{
						rp.getObject().addAll(rp2.getObject());
					}
					
					wrapRequestList(rp.getObject(), res);
				}
			}
		}
		else
		{
			ReturnPack<List<ReimbursementRequest>> rp = AccountManager.getReinbursmentsbyEmployee(token, e.getId());
			wrapRequestList(rp.getObject(), res);
		}
	}
	
	private void checkRequest(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		String name = req.getParameter("name");
		if(name == null)
			return;
		Boolean taken = AccountManager.RequestExists(name);
		String strTaken = (taken) ? "true" : "false";
		res.getWriter().write(strTaken);
	}
	
	private void wrapRequestList(List<ReimbursementRequest> rr, HttpServletResponse res) throws IOException
	{
		if(rr == null)
			return;
		ObjectMapper om = new ObjectMapper();
		String employeeString = om.writeValueAsString(rr);
		
		
		employeeString = "{\"requests\":" + employeeString + "}";
		
		res.getWriter().write(employeeString);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
		
	}

}
