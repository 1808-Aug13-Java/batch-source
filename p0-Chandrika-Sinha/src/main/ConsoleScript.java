package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.AccountDaoImpl;
import dao.UserDaoImpl;
import model.Account;
import model.User;

public final class ConsoleScript {
	private static Scanner sc = new Scanner(System.in);
	private static User u;
	private Account a;
	
	private ConsoleScript() {
		run();
	}

	public static void run() {
		u = new User();
		
		// home
		home();

		// post-login page
		postLogIn();
	}
	
	private static void home() {
		boolean badInput = true;
		while (badInput) {
			System.out.println("Enter 'c' to create an account or enter your username to log in.\n");
			String input = sc.nextLine();
			
			UserDaoImpl ud = new UserDaoImpl();
			if (input.equals("c")) {
				u = createUser(sc);
				// Bank.addUser(u); ADD USER TO THE BANK!!!
				badInput = false;
			} else if (ud.getUserById(input)==null) { // check this!
				System.out.println("No such username exists.\n");
			} else { // success! go to log in screen
				u = ud.getUserById(input);
				login(sc);
				badInput = false;
			}
		}
	}
	
	private static User createUser(Scanner sc) {
		String username = "";
		String password = "";
		
		username = createUsername(sc);
		password = createPassword(sc);
		
		System.out.println("Enter your firstname:");
		String firstname = sc.nextLine();
		
		System.out.println("Enter your lastname:");
		String lastname = sc.nextLine();
		
		User u = new User(username, password, firstname, lastname, true);
		
		UserDaoImpl ud = new UserDaoImpl();
		ud.createUser(u);
		
		return u;
	}
	
	private static String createUsername(Scanner sc) {
		while (true) {
			System.out.println("Enter a username or email address:");
			String input = sc.nextLine();
			
			UserDaoImpl ud = new UserDaoImpl();
			if (ud.getUserById(input)!=null) {
				System.out.print("Username unavailable, try a different one. ");
				createUser(sc);
			} else {
				return input;
			}
		}
	}
	
	private static String createPassword(Scanner sc) {
		while (true) {
			System.out.println("Enter a password:");
			String input = sc.nextLine();
			
			// check if password is valid
			return input;
		}
	}
	
	private static Account createNewAccount(User u, Scanner sc) {
		AccountDaoImpl ad = new AccountDaoImpl();
		int id = ad.getNextAccountId();
		Account a = new Account(id, new BigDecimal("0"), "checking", false);
		ad.createAccount(a, u);
		return a;
	}
	
	private static void login(Scanner sc) {
		boolean badInput = true;
		while(badInput) {
			System.out.println("Enter your password.");
			String input = sc.nextLine();
			input = input.trim();
			if (input.equals(u.getPassword())) { // success!
				badInput = false;
				u.setLoggedIn(true);
				System.out.println("You've just been logged in.");
				// go to post-login page
			} else if (input.equals("b")) {
				badInput = false;
				home();
			} else {
				System.out.println("Incorrect password. Please try again or press 'b' to go back.");
			}
		}
	}
	
	private static void postLogIn() {
		boolean badInput = true;
		while (badInput && u.isLoggedIn()) {
			System.out.println("Enter 'n' to create a new account. \n");
			System.out.println("Enter 'd' to deposit money, 'w' to withdraw money, 'v' to view balance,\n");
			System.out.println("Or press 'q' to quit.");
			String input = sc.nextLine();
			
			input = input.toLowerCase();
			
			switch (input) {
			case "n":
				createNewAccount(u,sc);
			case "d":
				deposit(u, sc);
				break;
			case "w":
				withdraw(u);
				break;
			case "v":
				showBalance(u);
				break;
			case "q":
				// logout in quit. otherwise if q is not pressed, wait a while and then logout
				u.setLoggedIn(false);
				badInput = false;
				break;
			default:
				System.out.print("Command not understood. ");
			}

			if (u.isLoggedIn())
				System.out.println("Would you like to make another transaction?\n");
		}
	}

	private static void deposit(User u, Scanner sc) {
		List<Account> userAccounts = new ArrayList<>();
		UserDaoImpl ud = new UserDaoImpl();
		userAccounts = ud.getAcounts(u.getUsername());
		
		Account a = null;
		while (true) {
			System.out.println("Your accounts are: ");
			for (Account acc: userAccounts) {
				System.out.println(" "+acc.getId());
			}
			System.out.println("Which account would you like to deposit into?");
			String input = sc.nextLine();
			int accountId = Integer.parseInt(input);
			for (int i = 0; i < userAccounts.size(); i++) {
				if (userAccounts.get(i).getId()==accountId) {
					a = userAccounts.get(i);
				}
			}
			if (a==null) {
				System.out.print("Sorry, that is not a valid account number. ");
			} else {
				break;
			}
		}
		
		System.out.println("Enter the amount you'd like to deposit.");
		// Throw number format type exception if it's not a legit amount
		BigDecimal amount = null;
		if (sc.hasNextBigDecimal()) {
			amount = sc.nextBigDecimal();
		}
		if (amount.compareTo(new BigDecimal("0")) <= 0) {
			System.out.println("Transaction failed: invalid deposit amount.");
		} else {
			a.setBalance(a.getBalance().add(amount));
			AccountDaoImpl ad = new AccountDaoImpl();
			ad.updateAccount(a);
			System.out.println("$" + amount + " deposited. Your balance is now " + a.getBalance() + ".");
		}
	}
	
	private static void withdraw(User u) {
		List<Account> userAccounts = new ArrayList<>();
		UserDaoImpl ud = new UserDaoImpl();
		userAccounts = ud.getAcounts(u.getUsername());
		
		Account a = null;
		while (true) {
			System.out.println("Your accounts are: ");
			for (Account acc: userAccounts) {
				System.out.println(" "+acc.getId());
			}
			System.out.println("Which account would you like to withdraw from?");
			String input = sc.nextLine();
			int accountId = Integer.parseInt(input);
			for (int i = 0; i < userAccounts.size(); i++) {
				if (userAccounts.get(i).getId()==accountId) {
					a = userAccounts.get(i);
				}
			}
			if (a==null) {
				System.out.print("Sorry, that is not a valid account number. ");
			} else {
				break;
			}
		}
		
		System.out.println("Enter the amount you'd like to withdraw.");
		BigDecimal amount = null;
		if (sc.hasNextBigDecimal()) {
			amount = sc.nextBigDecimal();
		}
		if (amount.compareTo(a.getBalance()) >= 0) {
			System.out.println("Transaction failed: invalid withdrawal amount.");
		} else {
			a.setBalance(a.getBalance().subtract(amount));
			AccountDaoImpl ad = new AccountDaoImpl();
			ad.updateAccount(a);
			System.out.println("$" + amount + " withdrawn. Your balance is now " + a.getBalance() + ".");
		}
	}
	
	private static void showBalance(User u) {
		List<Account> userAccounts = new ArrayList<>();
		UserDaoImpl ud = new UserDaoImpl();
		userAccounts = ud.getAcounts(u.getUsername());
		
		for (Account a: userAccounts) {
			System.out.println("Account no. " + a.getId() + ": $" + a.getBalance());
		}
	}

	public static User getUser() {
		return u;
	}
}
