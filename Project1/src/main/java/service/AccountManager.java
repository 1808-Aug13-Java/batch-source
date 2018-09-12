package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import dao.Employee;
import dao.EmployeeDao;
import dao.EmployeeDaoImp;
import dao.ReimbursementDaoImp;
import dao.ReimbursementRequest;



public class AccountManager extends Employee 
{
	private static HashMap<Integer, Employee> activeAccounts = new HashMap<>();	
	private static HashMap<Employee, Integer> activeAccountsRev = new HashMap<>();
	
	/**
	 * getAvailableToken - Checks the list of active accounts for a token
	 * @param ua - the User Account to add if available
	 * @return the token to use for the account's session
	 */
	private static int getAvailableToken(Employee ua)
	{
		Integer i = activeAccountsRev.get(ua);
		if(i != null)
			return i;
		
		// Go through the list of possible positive integers for a space
		// Starting at 1 since 0 is used by accounts as no logged on
		for(int c = 1; c < Integer.MAX_VALUE; c++)
		{
			if(!activeAccounts.containsKey(c))
			{
				// Found a space, use it
				activeAccounts.put(c, ua);
				activeAccountsRev.put(ua,c);
				return c;
			}
		}
		
		// No space available, report the User Level value for not logged on
		return -1;
	}
	
	
	// Manages Connection to database
	static Connection dbConnection = null;
	
	private static void prepareConnection( ) throws IOException, SQLException
	{

		if(dbConnection == null || dbConnection.isClosed()) {
			Properties prop = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			prop.load(loader.getResourceAsStream("connection.properties"));
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			
			Driver driver = new oracle.jdbc.OracleDriver();
			DriverManager.registerDriver(driver);
		
			dbConnection = DriverManager.getConnection(url, username, password);
		}
		//return connection;
	}
	
	public static int createAccount(String fn, String ln, String un, String pw, boolean man)
	{
		try
		{
			prepareConnection();
		}
		catch (IOException | SQLException e1) 
		{
			e1.printStackTrace();
			return -1;
		}
		
		
		EmployeeDaoImp ed = new EmployeeDaoImp();
		
		int i = ed.GetAvalableId(dbConnection);
		if(i < 0)
			return -6;
		Employee e = null;
		if(man)
			e = Employee.getNewManager(i, fn, ln, un);
		else
			e = Employee.getNewEmployee(i, fn, ln, un);
	
		
		
		if(ed.createEmployee(e,pw,dbConnection) == 0)
			return -2;
		
		int ret = getAvailableToken(e);
		
		if(ret > 0)
			return ret;
		return -3;
		
	}
	
	public static List<Employee> getManagerList()
	{
		EmployeeDaoImp ed = new EmployeeDaoImp();
		return ed.GetManagerList(dbConnection);
	}
	
	public static boolean properCredentials(String user, String pass)
	{
		try
		{
			prepareConnection();
		}
		catch (IOException | SQLException e1) 
		{
			e1.printStackTrace();
			return false;
		}
		
		EmployeeDao ed = new EmployeeDaoImp();
		
		Employee e = ed.GetEmployeeByCredentials(user, pass, dbConnection);
		return e != null;
	}
	
	public static int logOn(String user, String pass)
	{
		try
		{
			prepareConnection();
		}
		catch (IOException | SQLException e1) 
		{
			e1.printStackTrace();
			return -1;
		}
		
		EmployeeDao ed = new EmployeeDaoImp();
		
		Employee e = ed.GetEmployeeByCredentials(user, pass, dbConnection);
		
		if(e == null)
			return -2;
		
		
		
		int ret = getAvailableToken(e);
		
		if(ret > 0)
			return ret;
		return -3;
	}
	
	public static boolean RequestExists(String name)
	{
		ReimbursementDaoImp rdi = new ReimbursementDaoImp();
		return rdi.checkUserName(name, dbConnection);
	}
	
	
 	public static void logOut(int token)
	{
		Employee e = activeAccounts.get(token);
		activeAccountsRev.remove(e);
		activeAccounts.remove(token);
	}
	
	public static ReturnPack<List<ReimbursementRequest>> getReinbursmentsbyEmployee(int token, int empId)
	{
		Employee e = activeAccounts.get(token);
		if(e == null)
			return new ReturnPack<List<ReimbursementRequest>>(1, "Not Properly Logged On!");
		
		if(empId < 1)
		{
			if(e.isManager())
			{
				ReimbursementDaoImp rdi = new ReimbursementDaoImp();
				return new ReturnPack<List<ReimbursementRequest>>(0,rdi.getRequests(dbConnection));
			}
			return new ReturnPack<List<ReimbursementRequest>>(2, "Only Managers have Access!");
		}
		else
		{
			if(e.getId() == empId || e.isManager())
			{
				ReimbursementDaoImp rdi = new ReimbursementDaoImp();
				return new ReturnPack<List<ReimbursementRequest>>(0,rdi.getRequestsByEmployee(empId,dbConnection));
			}
			return new ReturnPack<List<ReimbursementRequest>>(2, "Only Managers have Access!");
		}
	}
	
	public static ReturnPack<Employee> getEmployee(int token, int empId)
	{
		Employee e = activeAccounts.get(token);
		if(e == null)
			return new ReturnPack<Employee>(1, "Not Properly Logged On!");
		if(e.getId() == empId || e.isManager())
		{
			return new ReturnPack<Employee>(0, e);
		}
		return new ReturnPack<Employee>(2, "Only Managers have Access!");
	}
	
	public static ReturnPack<Employee> getEmployee(String un)
	{
		EmployeeDaoImp edi = new EmployeeDaoImp();
		Employee e = edi.GetEmployeeByUserName(un, dbConnection);
		if(e == null)
			return new ReturnPack<Employee>(3, "Does not have Employee");
		return new ReturnPack<Employee>(0, e);
	}
	
	public static ReturnPack<Employee> getEmployee(int token)
	{
		Employee e = activeAccounts.get(token);
		if(e == null)
			return new ReturnPack<Employee>(1, "Not Properly Logged On!");
		return new ReturnPack<Employee>(0, e);
	}
	
	public static ReturnPack<List<ReimbursementRequest>> getReinbursmentsbyResolveStatus(int token, boolean resolved, boolean omitDenied)
	{
		Employee e = activeAccounts.get(token);
		if(e == null)
			return new ReturnPack<List<ReimbursementRequest>>(1, "Not Properly Logged On!");
		
		ReimbursementDaoImp rdi = new ReimbursementDaoImp();
		return new ReturnPack<List<ReimbursementRequest>>(0, rdi.getRequestsByResolveStatus(resolved, dbConnection, omitDenied));
	}
	
	public static ReturnPack<Object> updateEmployee(int token, Employee e)
	{
		Employee e1 = activeAccounts.get(token);
		if(e != e1)
			return new ReturnPack<Object>(4, "Wrong Account");
		EmployeeDaoImp edi = new EmployeeDaoImp();
		int ret = edi.updateEmployee(e, dbConnection);
		if(ret == 0)
			return new ReturnPack<Object>(3, "Update Issue!");
		return new ReturnPack<Object>(0, null);
	}
	
	public static ReturnPack<Object> updateEmployee(int token, Employee e, String oldPassword, String newPassword)
	{
		Employee e1 = activeAccounts.get(token);
		if(e != e1)
			return new ReturnPack<Object>(4, "Wrong Account");
		EmployeeDaoImp edi = new EmployeeDaoImp();
		int ret = edi.updateEmployee(e, dbConnection, oldPassword, newPassword);
		if(ret == 0)
			return new ReturnPack<Object>(3, "Update Issue! Did you misenter your old password?");
		return new ReturnPack<Object>(0, null);
	}
	
	public static ReturnPack<Object> settleRequest(int token, String requestName, boolean approve)
	{
		Employee e = activeAccounts.get(token);
		if(e == null)
			return new ReturnPack<Object>(1, "Not Properly Logged On!");
		
		if(!e.isManager())
			return new ReturnPack<Object>(2, "Only Managers have Access!");
		
		ReimbursementDaoImp rdi = new ReimbursementDaoImp();
		ReimbursementRequest rr = rdi.getRequestByName(requestName, dbConnection);
		
		if(rr == null)
			return new ReturnPack<Object>(5, "That Request Does not exist!");
		
		rr.setDenied(!approve);
		rr.setResolvingManager(e.getId());
		if(rdi.updateRequest(rr, dbConnection) == 0)
			return new ReturnPack<Object>(3, "Update Issue");
		return new ReturnPack<Object>(0, null);
		
	}
	
	public static ReturnPack<Object> submitRequest(int token, ReimbursementRequest rr)
	{
		Employee e = activeAccounts.get(token);
		//System.out.println("Account Count: " + activeAccounts.size() + " and token=" + token);
		if(e == null)
			return new ReturnPack<Object>(1, "Not Properly Logged On!");
		ReimbursementDaoImp rdi = new ReimbursementDaoImp();
		
		if(rdi.checkUserName(rr.getName(), dbConnection))
			return new ReturnPack<Object>(5, "There is already a Reimbursement Request with that name! Pick Another!");
		
		if(rdi.createRequest(rr, dbConnection) > 0)
			return new ReturnPack<Object>(0, null);
		return new ReturnPack<Object>(3, "Addition Issue");
		
		
	}
	
	public static int getEmployeeIdByUserName(int token, String un)
	{
		Employee e = activeAccounts.get(token);
		if(e == null)
			return -1;
		EmployeeDaoImp edi = new EmployeeDaoImp();
		List<Employee> emps = edi.GetEmployeeList(dbConnection);
		if(emps == null)
			return -1;
		for(Employee e1 : emps)
		{
			if(e1.getUserName().equals(un))
				return e1.getId();
		}
		return -1;
	}
	
	public static ReturnPack<List<Employee>> getEmployeeList(int token)
	{
		Employee e = activeAccounts.get(token);
		if(e == null)
			return new ReturnPack<List<Employee>>(1, "Not Properly Logged On");
		if(!e.isManager())
			return new ReturnPack<List<Employee>>(2, "Only Managers can view this");
		EmployeeDaoImp edi = new EmployeeDaoImp();
		List<Employee> emps = edi.GetEmployeeList(dbConnection);
		
		if(emps == null)
			return new ReturnPack<List<Employee>>(3, "Error Retrieving the Employees");
		return new ReturnPack<List<Employee>>(0, emps);
	}
}
