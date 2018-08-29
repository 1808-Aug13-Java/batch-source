package backend;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class UserDaoImp implements UserDao
{

	private static Logger loggy = Logger.getRootLogger();
	
	@Override
	public UserAccount getAccountByName(String un, String pw, Connection con) 
	{
		String sql = "SELECT * FROM USER_ACCOUNT WHERE USER_NAME = ?";
		UserAccount ua = null;
		ResultSet rs = null;
		
		try (
			PreparedStatement ps = con.prepareStatement(sql);)
			
		{
			ps.setString(1, un);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				int userId = rs.getInt("UA_ID");
				String password = rs.getString("PASSWORD_");
				
				if(!password.equals(pw))
				{
					loggy.info("Password or User name is incorrect!\n");
				}
				else
				{
					String firstName = rs.getString("FIRST_NAME");
					String lastName = rs.getString("LAST_NAME");
					ua = new UserAccount(un, userId, firstName, lastName);
					loggy.info("Logged On!\n");
				}
				rs.close();
			}
			
		} catch (SQLException e) {
			loggy.error(e);
		}
		finally
		{
			if(rs != null)
			{
				try
				{
					rs.close();
				}catch(SQLException e) {loggy.error(e);}
			}
		}
		
		
		return ua;
	}

	@Override
	public List<UserAccount> getUserAccounts(Connection con) 
	{
		String sql = "SELECT * FROM USER_ACCOUNT";
		List<UserAccount> ret = new ArrayList<UserAccount>();
		
		try (Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql);)
		{
			while(rs.next())
			{
				int userId = rs.getInt("UA_ID");
				String userName = rs.getString("USER_NAME");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				ret.add(new UserAccount(userName, userId, firstName, lastName));
			}
		}
		catch(SQLException e)
		{
			loggy.info("Error: " + e);
		}
		
		return ret;
	}

	@Override
	public int createUserAccount(UserAccount ua, String pw, Connection con)
	{
		String sql = "INSERT INTO USER_ACCOUNT VALUES (?, ?, ?, ?, ?)";
		
		int ret = 0;
		int id = getAvailableId(con);
		
		try (PreparedStatement ps = con.prepareStatement(sql);)
		{
			ps.setString(1, ua.getUsername());
			ps.setString(2, pw);
			ps.setString(3, ua.getFirstName());
			ps.setString(4, ua.getLastName());
			ps.setInt(5, id);
			
			ret = ps.executeUpdate();
			
			
			ua.setAccountId(id);
			
		}
		catch (SQLException e) 
		{
			loggy.error(e);
		}
		
		
		
		return ret;
	}

	@Override
	public int updateUserAccount(UserAccount ua, Connection con) 
	{

		String sql = "UPDATE USER_ACCOUNT SET USER_NAME=?, FIRST_NAME=?, LAST_NAME=? WHERE UA_ID=?";
		int ret = 0;
		try(PreparedStatement ps = con.prepareStatement(sql);)
		{
			ps.setString(1, ua.getUsername());
			ps.setString(2, ua.getFirstName());
			ps.setString(3, ua.getLastName());
			ps.setInt(4, ua.getAccountId());
			ret = ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			loggy.error(e);
		}
		
		return ret;
	}

	@Override
	public int deleteUserAccount(UserAccount ua, Connection con) 
	{
		
		
		return 0;
	}

	@Override
	public int getAvailableId(Connection con) 
	{
		String sql = "{call GET_AVAILABLE_USER_ID(?)}";
		int ret = -1;
		try(CallableStatement cs = con.prepareCall(sql);)
		{
			cs.registerOutParameter(1, java.sql.Types.NUMERIC);
			cs.execute();
			ret = cs.getInt(1);
		} catch (SQLException e) {
			ret = -2;
		}
		
		return ret;
		
	}

	@Override
	public boolean doesUserExist(String un, Connection con) 
	{
		String sql = "SELECT * FROM USER_ACCOUNT WHERE USER_NAME = ?";
		boolean ret = false;
		
		ResultSet rs = null;
		try (
			PreparedStatement ps = con.prepareStatement(sql);)
		{
			ps.setString(1, un);
			rs = ps.executeQuery();
			ret = rs.next();
			rs.close();
		}
		catch (SQLException e)
		{
			loggy.error(e);
		}
		finally
		{
			if(rs != null)
			{
				try
				{
					rs.close();
				}catch(SQLException e) {loggy.error(e);}
			}
		}
		return ret;

	}

}
