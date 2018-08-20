package proj0.iter1.frontend;

import java.util.Scanner;
import proj0.iter1.backend.AccountManager;

/**
 * Console - the Interface to the system "front-end"
 * @author jljac
 *
 */
public class Console {

	
	/**
	 * main - entry point to the application
	 * @param args - String array for command-line arguments (unused)
	 */
	public static void main(String[] args)
	{
		// Token to maintain connection to account
		int token = -1;
		
		// Sets up basic instructions and welcome for the user
		System.out.println("Welcome to Project 0 Version 0.1 of the Banking Clan Engine\n");
		System.out.println("Type in 'help' at the '-->' for a list of commands! Each command is 1 word!");
		
		// Set up input system
		Scanner scan = new Scanner(System.in);
		String command = "";
		
		// Set up loop and the command "exit" as an exit point
		while(!command.equals("exit"))
		{
			// Prompt the user for a command
			System.out.print("[Main Command]-->");
			command = scan.nextLine();
			
			// Analyze the command
			switch (command.toLowerCase().trim())
			{
			case "create":
			case "create account":
				// Don't allow account to be created if already logged on
				if(token != -1)
					System.out.println("You need to log out of your current account befoe creating a new one!");
				token = createAccount(scan);
				break;
			case "help":
				System.out.println("Each command is one or two words, depending on your preference!");
				System.out.println("Arguments are provided after the command is entered! Each command will request informtion!\n");
				System.out.println("'create' and 'create account': allow you to create an account.");
				System.out.println("Note that the account cannot currently exist!\n");
				
				System.out.println("'deposit', 'deposit money' or 'deposit funds' allow you to make a deposit!");
				System.out.println("Note that you need to be logged on and you need to provide a value!\n");
				
				System.out.println("'withdrawl', 'withdrawl money' or 'withdrawl funds' allow you to withdrawl money!");
				System.out.println("Note that you need to be logged on and you need to provide a value! You also need to have that money available\n");
				
				System.out.println("'view' allows you to view your account balance! You should be logged in for this\n");
				
				System.out.println("'log on' or 'log-on' allows you to access your account!");
				System.out.println("Note: Make sure the account exists and you enter your username and password correctly 'when prompted'!\n");
				
				System.out.println("'log out' or 'log-out' allows you close your account!");
				System.out.println("Note: Make sure your are logged on!!\n");
				
				System.out.println("'exit' allows you to leave the session!\n");
				
				System.out.println("Note: commands are case-insensitive. However, their arguments are not!");
				
				break;
				
			case "deposit":
			case "deposit money":
			case "deposit funds":
				depositMoney(scan, token);
				break;
			case "withdrawl":
			case "withdrawl money":
			case "withdrawl funds":
				withdrawlMoney(scan, token);
				break;
			case "view":
				viewBalance(token);
				break;
			case "log on":
			case "log-on":
				token = logIn(scan, token);
				break;
			case "log out":
			case "log-out":
				logOut(token);
				token = -1;
				break;
			case "exit":
				break;
			default:
				System.out.println("That command is not recognized at this time!");
			}
		}
		
		// Shut down the system
		scan.close();
		System.out.println("Shutting down!");
	}
	
	/**
	 * createAccount - Gets the information needed to set up an account
	 * @param scan - the Scanner to get input with
	 * @return int - the token to access the account from
	 */
	private static int createAccount(Scanner scan)
	{
		System.out.print("What is your first name?\n-->");
		String first = scan.nextLine();
		System.out.print("What is your last name?\n-->");
		String last = scan.nextLine();
		System.out.print("What username would you like to use?\n-->");
		String user = scan.nextLine();
		System.out.print("What email address would you like to use?\n-->");
		String email = scan.nextLine();
		
		String pass1 = "", pass2 = " ";
		
		System.out.print("What password would you like to use? \nType 'exit' to forgo account construction?\n-->");
		
		// Most systems have users enter a new password twice, do so here
		// But also allow them an exit
		while(!pass1.equals(pass2))
		{
			pass1 = scan.nextLine();
			if(pass1 == "exit")
				return -1;
			System.out.print("-->");
			pass2 = scan.nextLine();
			if(pass2 == "exit")
				return -1;
			
			// Alert user to the need to type the same password twice
			if(!pass1.equals(pass2))
				System.out.print("Sorry! You're passwords need to match! Try again or type 'exit' to cancel!\n-->");
		}
		
		// Attempt account creation
		int tok = AccountManager.createAccount(first, last, user, email, pass1);
		
		// Warn user if account creation failed
		if(tok == -1)
			System.out.println("Sorry! This account appears to already exist! Try running this command again!");
		else
			System.out.println("Account created! You should be logged on!");
		return tok;
	}
	
	/**
	 * logIn - logs the user into the system
	 * @param scan - allows input to be gathered
	 * @param token - make sure user is not already logged it
	 * @return - the account of the new log-in
	 */
	private static int logIn(Scanner scan, int token)
	{
		// Prevent a double log-on
		if(token != -1)
		{
			System.out.println("You need to log out before you can log on again!");
			return token;
		}
		
		// Get the username and log-in
		System.out.print("Please enter your user name:\n-->");
		String user = scan.nextLine();
		System.out.print("Please enter your password:\n-->");
		String pass = scan.nextLine();
		
		// Attempt a log in
		token = AccountManager.logIn(user, pass);
		
		// Report the results of the attempted log-in to the user
		if(token == -1)
		{
			System.out.println("Your username and/or password appear to be incorrect!");
		}
		else if(token < 0)
		{
			System.out.println("Some system error appears to have occured!");
		}
		else
			System.out.println("Logged on");
		return token;
	}
	
	/**
	 * logOut - logs the user out of the system
	 * @param token
	 */
	private static void logOut(int token)
	{
		// Attempt the log-out
		if(!AccountManager.logOut(token))
		{
			System.out.println("It appears you might not be logged in!");
		}
	}
	
	/**
	 * depositMoney - Adds money to an account
	 * @param scan - Scanner to get input from
	 * @param token - the account to work with
	 */
	private static void depositMoney(Scanner scan, int token)
	{
		boolean worked = false;
		
		// Get the value to work with first
		int dollars = 0;
		byte cents = 0;
		while(!worked)
		{
			// Instruct the user to get number
			System.out.print("Please enter the amount of money you'd like to deposit ('exit' to cancel)\n-->");
			String strMoney = scan.nextLine();
			
			// Check for the exit command
			if(strMoney == "exit")
			{
				System.out.println("Cancelling Deposit!");
				return;
			}
			
			// Now attempt to get the values
			try 
			{
				float amount = Float.parseFloat(strMoney);
				dollars = (int) amount;
				amount -= dollars;
				amount *= 100;
				cents = (byte)amount;
				worked = true;
				
			}
			catch(NumberFormatException e)
			{
				// Alert the user to the failure and attempt again
				System.out.println("Value entered needs to be a number!");
			}
		}
		
		// Attempt a deposit and report failure if it occurs
		if(!AccountManager.deposit(token, dollars, cents))
		{
			System.out.println("Either you are not logged on or you entered a negative amount!");;
		}
	}
	
	/**
	 * withdrawlMoney - withdraws money from an account
	 * @param scan - the input device to work with
	 * @param token - the account to work with
	 */
	private static void withdrawlMoney(Scanner scan, int token)
	{
		// Make sure user is logged on
		if(token == -1)
		{
			System.out.println("You are not logged on! You cannot withdrawl money at this time!");
			return;
		}
		
		boolean worked = false;
		
		// Prepare to get values to work with
		int dollars = 0;
		byte cents = 0;
		while(!worked)
		{
			System.out.print("Please enter the amount of money you'd like to withdrawl ('exit' to cancel)\n-->");
			String strMoney = scan.nextLine();
			
			// Allow user exit opportunity
			if(strMoney == "exit")
			{
				System.out.println("Cancelling Withdrawl!");
				return;
			}
			
			// Attempt to retrieve values and exit the loop if successful
			try 
			{
				float amount = Float.parseFloat(strMoney);
				dollars = (int) amount;
				amount -= dollars;
				amount *= 100;
				cents = (byte)amount;
				worked = true;
				
			}
			catch(NumberFormatException e)
			{
				// Report failure to user
				System.out.println("Value entered needs to be a number!");
			}
		}
		
		// Report any errors
		switch(AccountManager.withdrawl(token, dollars, cents))
		{
		case 1:
			System.out.println("Withdrawl was not successful!");
			return;
		case -1:
			System.out.println("Your account is not currently logged in!");
			return;
		}
		System.out.println("Money Successfully withdrawed");
	}
	
	/**
	 * viewBalance - prints out the accounts balance (if logged in)
	 * @param token - account to work with
	 */
	private static void viewBalance(int token)
	{
		System.out.println(AccountManager.getAccountInformation(token));
	}
}
