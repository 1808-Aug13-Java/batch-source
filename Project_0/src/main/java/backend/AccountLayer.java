package backend;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import message.Message;



public class AccountLayer 
{
	
	private AccountLayer()
	{
		
	}
	
	static String needBankAccountMessage = "Error! You need to attach to an available bank account! Run the 'switch bank' command to fix this!";
	
	static Connection dbConnection = null;
	static UserAccount loggedOnAccount = null;
	
	static String notLoggedOn ="You're not currently logged on!";
	
	private static void prepareConnection( ) throws IOException, SQLException
	{

		if(dbConnection == null || dbConnection.isClosed())
		{
			Properties prop = new Properties();
			InputStream in = new FileInputStream("connection.properties_");
			prop.load(in);
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			dbConnection = DriverManager.getConnection(url, username, password);
		}
	}
	
	
	public static void endConnection() throws SQLException
	{
		if(dbConnection != null && !dbConnection.isClosed())
			dbConnection.close();
	}
	
	public static Message createNewAccount(String userName, String password, String firstName, String lastName,
			String bankingName, boolean isChecking)
	{
		try 
		{
			prepareConnection();
		}
		catch (IOException|SQLException e)
		{
			return new Message("Error Establishing connection " + e.getMessage(), 1);
		}
		
		UserDao ud = new UserDaoImp();

		
		UserAccount ua = new UserAccount(userName, firstName, lastName);
		BankingAccount ba = new BankingAccount(0,(byte)0, bankingName, isChecking, -1);


		BankingDao bd = new BankingDaoImp();
		if(ud.createUserAccount(ua, password, dbConnection) == 0)
		{
			return new Message("Error in creating User Account! User Name might already exist!",3);
		}
		
		ba.setMainUser(ua.getAccountId());
		if(bd.createBankingAccount(ba, dbConnection) == 0)
			return new Message("Error in creating Banking account! Bank Account name might already exist!",2);
		
		
		ua.setAttachedAccount(ba);
		loggedOnAccount = ua;
		
		return new Message("Account Created! You are now logged on as <" + userName +">",0);
	}
	
	public static Message logOnAccount(String userName, String password)
	{
		try 
		{
			prepareConnection();
		}
		catch (IOException|SQLException e)
		{
			return new Message("Error Establishing connection " + e.getMessage(), 1);
		}
		
		UserDao ud = new UserDaoImp();
		loggedOnAccount = ud.getAccountByName(userName, password, dbConnection);
		if(loggedOnAccount == null)
			return new Message("Error logging on! Either Username or passord are incorrect!",4);
		return new Message("Logged on as <"+userName+">!",0);
	}
	
	public static Message logOff()
	{
		if(loggedOnAccount == null)
			return new Message("You're not currently logged on!", -1);
		loggedOnAccount = null;
		return new Message("Logged off!", 0);
	}
	
	public static Message getAlternateBankAccounts()
	{
		if(loggedOnAccount == null)
			return new Message(notLoggedOn,-1);
		List<String> bankNames = new BankingDaoImp().getUserAccountsByUser(dbConnection, loggedOnAccount.getAccountId());
		
		if(bankNames == null)
			return new Message("Internal Error retrieving Attached bank-accounts!", 5);
		
		if(bankNames.size() == 1)
			return new Message("Single Bank Account: " + bankNames.get(0), 0);
		
		String ret = "Attached bank accounts:\n";
		
		for(int rust = 0; rust < bankNames.size();rust++)
			ret = ret + bankNames.get(rust) + "\n";
		
		return new Message(ret, 0);
	}
	
	public static Message switchBankAccounts(String bankName)
	{
		if(loggedOnAccount == null)
			return new Message(notLoggedOn, -1);
		
		BankingDao bd = new BankingDaoImp();
		
		List<String> bankNames = bd.getUserAccountsByUser(dbConnection, loggedOnAccount.getAccountId());
		
		if(bankNames == null)
			return new Message("Internal Error retrieving Attached bank-accounts!", 5);
		
		boolean found = false;
		for(String name : bankNames)
		{
			if(name != null && name.equals(bankName))
			{
				found = true;
				break;
			}
		}
		
		String noBankAccount = "Error! Either you do not have access to this bank account or it does not exist!";
		
		if(!found)
			return new Message(noBankAccount, 5);
		
		BankingAccount ba = bd.getAccountByName(bankName, dbConnection);
		if(ba == null)
			return new Message(noBankAccount, 5);
		
		loggedOnAccount.setAttachedAccount(ba);
		
		return new Message("Banking Account set to " + ba.getName(), 0);
	}
	
	public static Message createNewBankingAccount(String bankName, boolean isChecking)
	{
		if(loggedOnAccount == null)
			return new Message(notLoggedOn, -1);
		
		BankingDao bd = new BankingDaoImp();
		
		BankingAccount ba = new BankingAccount(0,(byte)0, bankName, isChecking, loggedOnAccount.getAccountId());


		if(bd.createBankingAccount(ba, dbConnection) == 0)
			return new Message("Error in creating Baniking account! Bank Account name might already exist!", 5);
		loggedOnAccount.setAttachedAccount(ba);
		
		return new Message("Bank Account switched to " + bankName, 0);
	}
	
	public static Message getAccountInfo()
	{
		if(loggedOnAccount == null)
			return new Message(notLoggedOn, -1);
		return new Message(loggedOnAccount.toString() + getAlternateBankAccounts().toString(), 0);
	}
	
	public static Message shareBankingAccount(String password, String otherUser)
	{
		if(loggedOnAccount == null)
			return new Message(notLoggedOn, -1);
		UserDao ud = new UserDaoImp();
		
		if(!ud.doesUserExist(otherUser, dbConnection))
			return new Message("Error! The user does not appear to exist!", 6);
		
		if(ud.getAccountByName(loggedOnAccount.getUsername(), password, dbConnection) == null)
			return new Message("Your password is incorrect! We cannot proceed unless we are sure it is you!", 7);
		
		BankingAccount ba = loggedOnAccount.getAttachedAccount();
		if(ba == null)
			return new Message(needBankAccountMessage, 5);
		
		String sql = "{call ADD_USER_TO_BANKING(?,?,?)}";
		
		String ret = "";
		int retCode = 0;
		
		try (CallableStatement cs = dbConnection.prepareCall(sql))
		{
			cs.setString(1, otherUser);
			cs.setString(2, ba.getName());
			int result = 0;
			cs.setInt(3, result);
			if(cs.execute())
			{
				result = cs.getInt(3);
				switch(result)
				{
				case 0:
					ret = "Joint Account Updated! Your banking account is now shared with " + otherUser;
					break;
				case 1:
					ret = "Error! The user does not appear to exist!";
					retCode = 6;
					break;
				case 2:
					ret = "Error! Your account does not appear to exist!";
					retCode = 7;
					break;
				case 3:
					ret = "Error! The banking account is already shared with this user!";
					retCode = 8;
					break;
				default:
					ret = "Error! Unknown Error occured!";
					retCode = 9;
				}
			}
		}
		catch(SQLException e)
		{
			ret = "Database Update Error!";
		}
		
		return new Message(ret, retCode);
	}
	
	public static Message deleteUserAccount(String password)
	{
		if(loggedOnAccount == null)
			return new Message(notLoggedOn, -1);
		

		
		
		
		return new Message("Not Implemented!", 8);
	}
	
	public static Message depositMoney(float money)
	{
		if(loggedOnAccount == null)
			return new Message(notLoggedOn, -1);
		
		if(money < 0.0f)
			return new Message("Need to specifiy a positive value!", 8);
		
		BankingAccount ba = loggedOnAccount.getAttachedAccount();
		if(ba == null)
			return new Message(needBankAccountMessage, 5);
		
		float updatedBalance = ba.getMoney() + money;
		
		
		return new Message(setMoneyToAccount(false, ba, updatedBalance, ba.getMoney()), 0);
	}
	
	public static Message withdrawMoney(float money)
	{
		if(loggedOnAccount == null)
			return new Message(notLoggedOn, -1);
		
		if(money < 0.0f)
			return new Message("Need to specifiy a positive value!", 8);
		
		BankingAccount ba = loggedOnAccount.getAttachedAccount();
		if(ba == null)
			return new Message(needBankAccountMessage, 5);
		
		float updatedBalance = ba.getMoney() - money;
		
		if(updatedBalance < 0.0f)
			return new Message("Sorry! You do not have enough mone for withdraw!",8);
		
		
		
		return new Message(setMoneyToAccount(true, ba, updatedBalance, ba.getMoney()), 0);
	}
	
	private static String setMoneyToAccount(boolean isWithdraw, BankingAccount ba, float newBalance, float originalBalance)
	{
		int dollars = (int)newBalance;
		newBalance -= dollars;
		byte cents = (byte) (newBalance * 100.0);
		
		ba.setDollars(dollars);
		ba.setCents(cents);
		
		dollars = (int)originalBalance;
		originalBalance -= dollars;
		cents = (byte) (originalBalance * 100.0);
		
		
		String ret = "Funds $" + dollars + ".";
		if(cents < (byte)10)
			ret += '0';
		ret+= cents;
		if(isWithdraw)
			ret = ret + " withdrawn! Balance is now $" + ba.getDollars() + ".";
		else
			ret = ret + " Deposited! Balance is now $" + ba.getDollars() + ".";
		
		if(ba.getCents() < (byte)10)
			ret += '0';
		ret += ba.getCents() + "!";
		
		// Update the Database
		BankingDao bd = new BankingDaoImp();
		bd.updateBankingAccount(ba, dbConnection);
		
		return ret;
	}
}
