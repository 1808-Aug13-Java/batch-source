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

public class ManagerSession 
{
	private static Logger loggy = Logger.getRootLogger();
	private static int token = -1;
	
	@BeforeClass
	public static void logOn()
	{
		token = AccountManager.logOn("ltrooper", "lordPass");
		loggy.info("token: "+ token);
		assertTrue(token > 0);
	}
	
	@Test
	public void viewRequests()
	{
		ReturnPack<List<ReimbursementRequest>> ret = AccountManager.getReinbursmentsbyEmployee(token, -1);
		assertEquals(0, ret.getErrorCode());
	}

	@Test
	public void viewRequestsEmp1()
	{
		ReturnPack<List<ReimbursementRequest>> ret = AccountManager.getReinbursmentsbyEmployee(token, 1);
		assertEquals(0, ret.getErrorCode());
	}
	
	@Test
	public void viewRequestsEmp2()
	{
		ReturnPack<List<ReimbursementRequest>> ret = AccountManager.getReinbursmentsbyEmployee(token, 2);
		assertEquals(0, ret.getErrorCode());
	}
	
	@Test
	public void viewResolvedRequests()
	{
		ReturnPack<List<ReimbursementRequest>> ret = AccountManager.getReinbursmentsbyResolveStatus(token, true, true);
		assertEquals(0, ret.getErrorCode());
	}
	
	@Test
	public void viewResolvedRequestsWithDenied()
	{
		ReturnPack<List<ReimbursementRequest>> ret = AccountManager.getReinbursmentsbyResolveStatus(token, true, false);
		assertEquals(0, ret.getErrorCode());
	}
	
	@Test
	public void viewPendingRequests()
	{
		ReturnPack<List<ReimbursementRequest>> ret = AccountManager.getReinbursmentsbyResolveStatus(token, false, true);
		assertEquals(0, ret.getErrorCode());
	}
	
	@Test
	public void viewPendingRequestsWithDenied()
	{
		ReturnPack<List<ReimbursementRequest>> ret = AccountManager.getReinbursmentsbyResolveStatus(token, false, false);
		assertEquals(0, ret.getErrorCode());
	}
	
	@Test
	public void resolveStatusToAccept()
	{
		ReturnPack<Object> ret = AccountManager.settleRequest(token, "Req1", true);
		loggy.info(ret.getErrorCode() + " " + ret.getErrorMessage());
		assertEquals(0, ret.getErrorCode());
	}
	
	@Test
	public void resolveStatusToError()
	{
		ReturnPack<Object> ret = AccountManager.settleRequest(token, "Req4", true);
		assertEquals(5, ret.getErrorCode());
	}
	
	@Test
	public void resolveStatusToDeny()
	{
		ReturnPack<Object> ret = AccountManager.settleRequest(token, "ReyReq1", false);
		System.out.println(ret.getErrorCode() + " " + ret.getErrorMessage());
		assertEquals(0, ret.getErrorCode());
	}
	
	@AfterClass
	public static void logOff()
	{
		AccountManager.logOut(token);
	}
}
