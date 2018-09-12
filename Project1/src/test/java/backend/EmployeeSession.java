package backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.ReimbursementRequest;
import service.*;

public class EmployeeSession 
{
	private static Logger loggy = Logger.getRootLogger();
	private static int token = -1;
	
	@BeforeClass
	public static void logOn()
	{
		token = AccountManager.logOn("jljacko", "johnPass");
		loggy.info("token: "+ token);
		assertTrue(token > 0);
	}
	
	@Test
	public void makeRequest1()
	{
		ReimbursementRequest rr = new ReimbursementRequest(3,-1, -1, "Req1", "Give Me Money!", false);
		ReturnPack<Object> ret = AccountManager.submitRequest(token, rr);
		loggy.info(ret.getErrorCode() + ret.getErrorMessage());
		assertEquals(0, ret.getErrorCode());
	}
	@Test
	public void makeRequest2()
	{
		ReimbursementRequest rr = new ReimbursementRequest(3,-1, 2, "Req2", "Give Me Money!", false);
		ReturnPack<Object> ret = AccountManager.submitRequest(token, rr);
		loggy.info(ret.getErrorCode() + ret.getErrorMessage());
		assertEquals(0, ret.getErrorCode());
	}
	@Test
	public void makeRequest3()
	{
		ReimbursementRequest rr = new ReimbursementRequest(3,-1, 1, "Req3", "Give Me Money!", false);
		ReturnPack<Object> ret = AccountManager.submitRequest(token, rr);
		loggy.info(ret.getErrorCode() + ret.getErrorMessage());
		assertEquals(0, ret.getErrorCode());
	}
	
	@AfterClass
	public static void logOff()
	{
		AccountManager.logOut(token);
	}

}
