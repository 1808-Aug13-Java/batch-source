package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class BankingDaoImp implements BankingDao
{

	private static Logger loggy = Logger.getRootLogger();
	
	@Override
	public BankingAccount getAccountByName(String un, Connection con) 
	{
		String sql = "SELECT * FROM BANKING_ACCOUNT WHERE ACCOUNT_NAME = ?";
		BankingAccount ba = null;
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(sql);)	
		{
			ps.setString(1, un);
			 rs = ps.executeQuery();
			
			while(rs.next())
			{
				int mainUserId = rs.getInt("MAIN_USER_ID");
				float money = rs.getFloat("MONEY");
				boolean isChecking = rs.getInt("IS_CHECKING") > 0;
				
				int dollars = (int) money;
				money = money - dollars;
				
				byte cents = (byte)(money * 100);
				
				ba = new BankingAccount(dollars, cents, un, isChecking, mainUserId);
				
			}
			
		} catch (SQLException e) {
			loggy.error(e);
		}
		finally
		{
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					loggy.error(e);
				}
		}
		return ba;
	}

	@Override
	public List<BankingAccount> getUserAccounts(Connection con)
	{
		String sql = "SELECT * FROM BANKING_ACCOUNT";
		List<BankingAccount> ret = new ArrayList<BankingAccount>();
		
		try (Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql);)
		{
			while(rs.next())
			{
				int mainUserId = rs.getInt("MAIN_USER_ID");
				float money = rs.getFloat("MONEY");
				boolean isChecking = rs.getInt("IS_CHECKING") > 0;
				String accountName = rs.getString("ACCOUNT_NAME");
				
				int dollars = (int) money;
				byte cents = (byte)((money - (float)dollars) * 100);

				ret.add(new BankingAccount(dollars, cents, accountName, isChecking, mainUserId));
			}
		}
		catch(SQLException e)
		{
			loggy.info("Error: " + e);
		}
		
		return ret;
		
	}

	@Override
	public int createBankingAccount(BankingAccount ua, Connection con) 
	{
		String sql = "INSERT INTO BANKING_ACCOUNT VALUES (?, ?, ?, ?)";
		try (PreparedStatement ps = con.prepareStatement(sql);)
		{
			ps.setFloat(1, ua.getMoney());
			ps.setString(2, ua.getName());
			ps.setInt(3, ua.isChecking() ? 1 : 0);
			ps.setInt(4, ua.getMainUser());
			
			return ps.executeUpdate();
		}
		catch (SQLException e) 
		{
			loggy.error(e);
		}
		
		
		return 0;
	}

	@Override
	public int updateBankingAccount(BankingAccount ua, Connection con) 
	{
		String sql = "UPDATE BANKING_ACCOUNT SET MONEY=?, MAIN_USER_ID=? WHERE ACCOUNT_NAME=?";
		int ret = 0;
		try(PreparedStatement ps = con.prepareStatement(sql);)
		{
			ps.setFloat(1, ua.getMoney());
			ps.setInt(2, ua.getMainUser());
			ps.setString(3, ua.getName());
			ret = ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			loggy.error(e);
		}
		
		return ret;
	}

	@Override
	public int deleteBankingAccount(BankingAccount ua, Connection con)
	{
		return 0;
	}

	@Override
	public List<String> getUserAccountsByUser(Connection con, int id) 
	{
		String sql = "SELECT * FROM BANKING_ACCOUNT WHERE MAIN_USER_ID = ? ";
		List<String> ret = new ArrayList<String>();
		
		ResultSet rs = null;
		
		try (PreparedStatement s = con.prepareStatement(sql);)
		{
			s.setInt(1, id);
			rs = s.executeQuery();
			while(rs.next())
			{
				ret.add(rs.getString("ACCOUNT_NAME"));
			}
			rs.close();
		}
		catch(SQLException e)
		{
			loggy.info("Error: " + e);
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
