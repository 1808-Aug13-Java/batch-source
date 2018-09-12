package dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ReimbursementDaoImp implements ReimbursementDao {

	private static Logger loggy = Logger.getRootLogger();
	
	public List<ReimbursementRequest> getRequests(Connection con)
	{
		String sql = "SELECT * FROM REINBURSMENTS";
		List<ReimbursementRequest> ret = null;
		try(Statement ps = con.createStatement();
				ResultSet rs = ps.executeQuery(sql);)
		{
			ret = new ArrayList<>();
			while(rs.next())
			{
				int empId = rs.getInt("EMP_ID");
				int resolver = rs.getInt("MAN_ID");
				String text = getClobData(rs.getClob("LETTER"));
				String name = rs.getString("NAME_");
				int target = rs.getShort("MANAGER_TARGET");
				boolean denied = rs.getInt("IS_DENIED") > 0;
				ret.add(new ReimbursementRequest(empId,resolver, target, name, text, denied));
			}
		}
		catch(SQLException | IOException e)
		{
			loggy.error(e);
		}

		return ret;
	}
	
	protected String getClobData(Clob c) throws SQLException, IOException
	{
		Reader read = c.getCharacterStream();
		
		char str[] = new char[20];
		String ret = "";
		int characters = 0;
		while((characters = read.read(str)) == 20)
		{
			for(int rust = 0; rust < characters && rust < str.length; rust++)
			{
				ret += str[rust];
			}
		}
		
		for(int rust = 0; rust < characters && rust < str.length; rust++)
		{
			ret += str[rust];
		}
		return ret;
	}
	
	
	public ReimbursementRequest getRequestByName(String _name, Connection con)
	{
		String sql = "SELECT * FROM REINBURSMENTS WHERE NAME_ = ?";
		try(PreparedStatement ps = con.prepareStatement(sql);)
		{
			ps.setString(1, _name);
			ResultSet rs = ps.executeQuery();
			if(!rs.next())
				return null;
			int empId = rs.getInt("EMP_ID");
			int resolver = rs.getInt("MAN_ID");
			String text = getClobData(rs.getClob("LETTER"));
			String name = rs.getString("NAME_");
			int target = rs.getShort("MANAGER_TARGET");
			boolean denied = rs.getInt("IS_DENIED") > 0;
			ReimbursementRequest ret = new ReimbursementRequest(empId,resolver, target, name, text, denied);
			rs.close();
			return ret;
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			loggy.error(e);
		}
		return null;
	}
	
	
//	@Override
//	public List<ReimbursementRequest> getRequestsByEmployee(String un, Connection con)
//	{
//		String sql = "SELECT * FROM REINBURSMENTS WHERE EMP_ID=?";
//		List<ReimbursementRequest> ret = null;
//		try(PreparedStatement ps = con.prepareStatement(sql))
//		{
//			ps.setInt(1, id);
//			ret = getRequests(ps);
//		}
//		catch(SQLException e)
//		{
//			
//		}
//
//		return ret;
//	}

	
	@Override
	public List<ReimbursementRequest> getRequestsByEmployee(int id, Connection con)
	{
		String sql = "SELECT * FROM REINBURSMENTS WHERE EMP_ID=?";
		List<ReimbursementRequest> ret = null;
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, id);
			ret = getRequests(ps);
		}
		catch(SQLException e)
		{
			loggy.error(e);
		}

		return ret;
	}

	@Override
	public List<ReimbursementRequest> getRequestsByTargetManager(int manager, boolean includeNoMan, Connection con)
	{
		String sql = "";
		List<ReimbursementRequest> ret = null;
		if(includeNoMan)
		{
			sql = "SELECT * FROM REINBURSMENTS WHERE MAN_ID=? OR MAN_ID= -1";
		}
		else
		{
			sql = "SELECT * FROM REINBURSMENTS WHERE MAN_ID=?";
		}
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, manager);
			ret = getRequests(ps);
		}
		catch(SQLException e)
		{
			loggy.error(e);
		}

		return ret;
	}

	@Override
	public List<ReimbursementRequest> getRequestsByResolveStatus(boolean isResolved, Connection con,
			boolean omitDenied) 
	{
		String sql = "";
		List<ReimbursementRequest> ret = new ArrayList<>();
		if(isResolved)
		{
			if(omitDenied)
				sql = "SELECT * FROM REINBURSMENTS WHERE MAN_ID > 0 AND IS_DENIED = 0";
			else
				sql = "SELECT * FROM REINBURSMENTS WHERE MAN_ID > 0";
		}
		else
		{
			sql = "SELECT * FROM REINBURSMENTS WHERE MAN_ID=-1";
		}
		try(Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql))
		{
			while(rs.next())
			{
				int empId = rs.getInt("EMP_ID");
				int resolver = rs.getInt("MAN_ID");
				String text = getClobData(rs.getClob("LETTER"));
				String name = rs.getString("NAME_");
				int target = rs.getShort("MANAGER_TARGET");
				boolean denied = rs.getInt("IS_DENIED") > 0;
				ret.add(new ReimbursementRequest(empId,resolver, target, name, text, denied));
			}
		}
		catch(SQLException | IOException e)
		{
			loggy.error(e);
			return null;
		}
		return ret;
	}
	
	private List<ReimbursementRequest> getRequests(PreparedStatement ps)
	{
		List<ReimbursementRequest> ret = new ArrayList<>();
		try(ResultSet rs = ps.executeQuery())
		{
			while(rs.next())
			{
				int empId = rs.getInt("EMP_ID");
				int resolver = rs.getInt("MAN_ID");
				String text = getClobData(rs.getClob("LETTER"));
				String name = rs.getString("NAME_");
				int target = rs.getShort("MANAGER_TARGET");
				boolean denied = rs.getInt("IS_DENIED") > 0;
				ret.add(new ReimbursementRequest(empId,resolver, target, name, text, denied));
			}
		}
		catch(SQLException | IOException e)
		{
			loggy.error(e);
			return null;
		}
		return ret;
		
	}
	

	@Override
	public boolean checkUserName(String name, Connection con) 
	{
		boolean ret = false;
		try(PreparedStatement st = con.prepareStatement("SELECT * FROM REINBURSMENTS WHERE NAME_ = ?"))
		{
			st.setString(1, name);
			ResultSet rs = st.executeQuery();
			ret = rs.next();
			rs.close();
		}
		catch(SQLException e)
		{
			loggy.error(e);
		}
		return ret;
	}

	@Override
	public int createRequest(ReimbursementRequest rr, Connection con) 
	{
		String sql = "INSERT INTO REINBURSMENTS VALUES (?, ?, ?, ?, ?, ?)";
		int ret = 0;
		try (PreparedStatement ps = con.prepareStatement(sql);)
		{
			Clob clob = con.createClob();
			clob.setString(1, rr.getText());
			ps.setInt(1, rr.getEmployeeId());
			ps.setInt(2, rr.getResolvingManager());

			ps.setClob(3, clob);
			ps.setString(4, rr.getName());
			ps.setInt(5, rr.getTargetManager());
			ps.setInt(6, (rr.isDenied()? 1 : 0));

			ret = ps.executeUpdate();
		}
		catch(SQLException e)
		{
			loggy.error(e);
		}
		return ret;
	}

	@Override
	public int updateRequest(ReimbursementRequest rr, Connection con)
	{
		String sql = "UPDATE REINBURSMENTS SET EMP_ID=?, MAN_ID=?, LETTER=?, MANAGER_TARGET=?, IS_DENIED=? WHERE NAME_=?";
		int ret = 0;
		try (PreparedStatement ps = con.prepareStatement(sql);)
		{
			Clob clob = con.createClob();
			clob.setString(1, rr.getText());
			ps.setInt(1, rr.getEmployeeId());
			ps.setInt(2, rr.getResolvingManager());
			
			ps.setClob(3, clob);
			
			ps.setInt(4, rr.getTargetManager());
			ps.setInt(5, (rr.isDenied()? 1 : 0));
			ps.setString(6, rr.getName());
			ret = ps.executeUpdate();
//			System.out.println("Request Update executed: " + ret);
		}
		catch(SQLException e)
		{
			loggy.info(e);
		}
		return ret;
	}

	@Override
	public int deleteRequest(ReimbursementRequest rr, Connection con)
	{
		
		return 0;
	}

}
