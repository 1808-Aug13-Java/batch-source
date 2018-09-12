package backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.ReimbursementRequest;
import service.*;

public class Employee2Session 
{
	private static Logger loggy = Logger.getRootLogger();
	private static int token = -1;
	
	@BeforeClass
	public static void logOn()
	{
		token = AccountManager.logOn("rvos", "rayPass");
		loggy.info("token: "+ token);
		assertTrue(token > 0);
	}
	
	@Test
	public void makeRequest1()
	{
		ReimbursementRequest rr = new ReimbursementRequest(5,-1, -1, "ReyReq1", "Give Me Money!", false);
		ReturnPack<Object> ret = AccountManager.submitRequest(token, rr);
		loggy.info(ret.getErrorCode() + ret.getErrorMessage());
		assertEquals(0, ret.getErrorCode());
	}
	
	@Test
	public void makeRequest2()
	{
		ReimbursementRequest rr = new ReimbursementRequest(5,-1, -1, "Req1", "Give Me Money!", false);
		ReturnPack<Object> ret = AccountManager.submitRequest(token, rr);
		loggy.info(ret.getErrorCode() + ret.getErrorMessage());
		assertEquals(5, ret.getErrorCode());
	}
	
	@Test
	public void viewRequests()
	{
		ReturnPack<List<ReimbursementRequest>> ret = AccountManager.getReinbursmentsbyEmployee(token, 5);
		assertEquals(0, ret.getErrorCode());
	}
	
	@Test
	public void viewRequestsNoAccess()
	{
		ReturnPack<List<ReimbursementRequest>> ret = AccountManager.getReinbursmentsbyEmployee(token, 3);
		assertEquals(2, ret.getErrorCode());
	}
	
	@Test
	public void failRequestResolve()
	{
		ReturnPack<Object> ret = AccountManager.settleRequest(token, "Req1", true);
		assertEquals(2, ret.getErrorCode());
	}
	
	@AfterClass
	public static void logOff()
	{
		AccountManager.logOut(token);
	}
}
