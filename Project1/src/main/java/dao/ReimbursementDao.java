package dao;

import java.sql.Connection;
import java.util.List;

public interface ReimbursementDao
{
	List<ReimbursementRequest> getRequestsByEmployee(int id, Connection con);
	List<ReimbursementRequest> getRequestsByTargetManager(int manager, boolean includeNoMan, Connection con);
	List<ReimbursementRequest> getRequestsByResolveStatus(boolean isResolved, Connection con, boolean omitDenied);
	
	boolean checkUserName(String name, Connection con);
	
	public int createRequest(ReimbursementRequest rr, Connection con);
	public int updateRequest(ReimbursementRequest rr, Connection con);
	
	public int deleteRequest(ReimbursementRequest rr, Connection con);
}
