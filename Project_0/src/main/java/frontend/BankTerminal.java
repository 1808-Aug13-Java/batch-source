package frontend;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import backend.AccountLayer;

public class BankTerminal {

	private static Logger loggy = Logger.getRootLogger();
	
	public static void main(String[] args) 
	{
		
		
		// Sets up basic instructions and welcome for the user
		loggy.info("Welcome to Project 0 Version 0.5 of the Banking Clan Engine\n");
		loggy.info("Type in '1' (for help) at the '--<>>' for a list of commands! Each command is a number!\n\n");
		
		// Set up input system
		Scanner scan = new Scanner(System.in);


		// Set an indicator that the user is not logged in or is logged in as someone
		String user = "";
		
		// Inform the User that User Accounts and Banking accounts are separate
		loggy.info("Note that User Accounts (which you log on to) are seperate from Bank Accounts (where the\n");
		loggy.info("money is stored). Consequently, when creating a User Account, you will be prompted for a\n");
		loggy.info("name for the banking account as well.\n\n");
		loggy.info("When logging on, you'll need to attach a bank account to your log-on session to deposit/\n");
		loggy.info("withdraw!\n\n");
		
		printHelp();
		
		int resp = 0;
		
		while(resp != 10)
		{
			resp = promptInt(scan, user);
			switch(resp)
			{
			case 1: // Create Account
				user = createAccount(scan, user);
				break;
			case 2: // Log on Account
				user = logIn(scan, user);
				break;
			case 3: // Log off
				loggy.info(AccountLayer.logOff().getMessage());
				user = "";
				break;
			case 4: // View Bank Accounts
				loggy.info(AccountLayer.getAlternateBankAccounts().getMessage());
				break;
			case 5: // Switch Bank Accounts
				switchBankAccount(scan, user);
				break;
			case 6: // View Account
				loggy.info(AccountLayer.getAccountInfo().getMessage());
				break;
			case 7: // Deposit Money
				deposit(scan, user);
				break;
			case 8: // Withdraw Money
				withdraw(scan, user);
				break;
			case 9: // Share Bank Account
				shareBankAccount(scan,user);
				break;
			case 10: // Exit Session
				appendNewBankAccount(scan,user);
				break;
			case 11: // Print help
				printHelp();
				break;
			case 12: // Create new Bank Account
				
				break;
			default: // Invalid command
				loggy.info("Invalid command\n");
				break;
				
			}
		}
		
		try {
			AccountLayer.endConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			loggy.error(e);
		}
		
	}
	
	private static void printHelp()
	{
		loggy.info("1. Create Account! Will prompt for User name, password (twice), first and last name, bank\n");
		loggy.info("\taccount name, and whether it is a checking or saving account!\n");
		loggy.info("2. Log in Account! Will prompt for User name and password. Use commands 4 and 5 to get access\n");
		loggy.info("\tto a bank account\n");
		loggy.info("3. Log out!\n");
		loggy.info("4. View Available Bank Accounts! View Bank Accounts you have access to!\n");
		loggy.info("5. Switch Bank Accounts! Will prompt for name of bank account! Run the 4th command for a list!\n");
		loggy.info("6. View Account! Prints out the details of your user account! If it is attached to a bank account,\n");
		loggy.info("\tthe balance of that account will print as well\n");
		loggy.info("7. Deposit Money! Will prompt for an amount to deposit. Need to be logged in and have a bank account\n");
		loggy.info("\tattached\n");
		loggy.info("8. Withdrawl money! Will prompt for an amount to withdraw. Attached bank account must have the\n");
		loggy.info("\tfunds available!\n");
		loggy.info("9. Share Bank Account! Enables joint bank accounts to be shared by other users! Will prompt for\n");
		loggy.info("\tuser name of other person, your password, and the bank account to share.\n");
		loggy.info("\tWARNING! This action is irreversable in the current build. Make sure you know what you're doing!\n");
		loggy.info("10. Exits the terminal! Will log you out and close the system!\n");
		loggy.info("11. Prints the help menu!\n\n");
	}
	
	private static String promptString(Scanner sc, String prompt)
	{
		loggy.info(prompt);
		
		return sc.nextLine();
	}
	
	private static int promptInt(Scanner sc, String userName)
	{
		boolean gotNumber = false;
		int ret = 0;
		while(!gotNumber)
		{
			loggy.info("--<" + userName + ">-->");
			String response = sc.nextLine();
			try
			{
				ret = Integer.parseInt(response);
				gotNumber = true;
			}
			catch(NumberFormatException e)
			{
				loggy.info("Please make sure your input is a whole number (10 for exit, 11 for help)!\n");
			}
		}
		return ret;
	}
	
	private static float promptFloat(Scanner sc, String userName, String purpose)
	{
		boolean gotNumber = false;
		float ret = 0;
		loggy.info("Enter a float! Note, extra precision is acceptable but may be cut off upon rounding!\n");
		while(!gotNumber)
		{
			loggy.info("--<" + userName + ">--["+purpose+"]-->");
			String response = sc.nextLine();
			try
			{
				ret = Float.parseFloat(response);
				gotNumber = true;
			}
			catch(NumberFormatException e)
			{
				loggy.info("Please make sure your input is a number!\n");
			}
		}
		return ret;
	}

	private static String parseUserName(String n)
	{
		if(n == null)
			return "";
		int locBeg = n.indexOf('<'), locEnd = n.indexOf('>');
		if(locBeg == -1 || locEnd == -1 || (locBeg >= locEnd))
			return "";
		
		return n.substring(locBeg + 1, locEnd);
		
	}
	
	private static String createAccount(Scanner sc, String userName)
	{
		String userAccount = promptString(sc, "Please enter a User name for your account!\n--<"+userName+">-->");
		String firstName = promptString(sc, "What is your FIRST name?\n --<"+userName+">-->");
		String lastName = promptString(sc, "What is your LAST name?\n --<"+userName+">-->");
		
		String pw1 = "-";
		String pw2 = "";
		
		loggy.info("Please Enter a password for your Account! You'll have to enter it twice!\n");
		
		while(!pw1.equals(pw2))
		{
			pw1 = promptString(sc, "--<"+userName+">-->");
			pw2 = promptString(sc, "--<"+userName+">-->");
			if(!pw1.equals(pw2))
				loggy.info("Please Make sure your passwords match!\n");
		}
		
		String bankName = promptString(sc, "Please enter a Banking name for your Bank Account!\n--<"+userName+">-->");
		
		String isCheck = promptString(sc, "Is this a checkings account! [y/Y] if yes, anything else for savings-->");
		boolean isCheckingAccount = false;
		if(isCheck.length() > 0 && isCheck.toLowerCase().charAt(0) == 'y')
			isCheckingAccount = true;
		
		String results = AccountLayer.createNewAccount(userAccount, pw2, firstName, lastName, bankName, isCheckingAccount).getMessage();
		
		loggy.info(results +"\n");
		return parseUserName(results);
	}
	
	private static void appendNewBankAccount(Scanner sc, String userName)
	{
String bankName = promptString(sc, "Please enter a Banking name for your Bank Account!\n--<"+userName+">-->");
		
		String isCheck = promptString(sc, "Is this a checkings account! [y/Y] if yes, anything else for savings-->");
		boolean isCheckingAccount = false;
		if(isCheck.length() > 0 && isCheck.toLowerCase().charAt(0) == 'y')
			isCheckingAccount = true;
		
		loggy.info(AccountLayer.createNewBankingAccount(bankName, isCheckingAccount).getMessage() + "\n");
	}
	

	private static String logIn(Scanner sc, String userName)
	{
		String userAccount = promptString(sc, "Username--<"+userName+">-->");
		String password = promptString(sc, "Password--<"+userName+">-->");
		
		String results = AccountLayer.logOnAccount(userAccount, password).getMessage();
		loggy.info(results + "\n");
		
		String newUser = parseUserName(results);
		if(newUser == null || newUser == "")
			return userName;
		return newUser;
	}
	
	private static void switchBankAccount(Scanner sc, String userName)
	{
		String input = promptString(sc, "Please Enter the Bank ccount to switch to!\n--<"+userName+">-->");
		
		loggy.info(AccountLayer.switchBankAccounts(input) + "\n");
	}
	
	private static void deposit(Scanner sc, String userName)
	{
		float money = promptFloat(sc, userName, "deposit");
		loggy.info(AccountLayer.depositMoney(money).getMessage() + "\n");
	}
	
	private static void withdraw(Scanner sc, String userName)
	{
		float money = promptFloat(sc, userName, "withdraw");
		loggy.info(AccountLayer.withdrawMoney(money).getMessage() +"\n");
	}
	
	private static void shareBankAccount(Scanner sc, String userName)
	{
		String otherUser = promptString(sc, "Who would you like to share your Bank Account with?\n-->"+userName+">-->");
		String password = promptString(sc, "This is a SERIOUS action! Please enter your password. If you do not wish to do this\n then please etner a false one! --<"+userName+">-->");
		loggy.info(AccountLayer.shareBankingAccount(password, otherUser).getMessage() + "\n");
	}
	
}
