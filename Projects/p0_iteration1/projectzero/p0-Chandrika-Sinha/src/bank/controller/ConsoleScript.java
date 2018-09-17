package bank.controller;

import java.util.Scanner;

public final class ConsoleScript {
	private static Scanner sc = new Scanner(System.in);
	static User u;
	
	private ConsoleScript() {
		run();
	}

	public static void run() {
		//User u = new User();
		u = new User();
		// home
		boolean badInput = true;
		while (badInput) {
			System.out.println("Enter 'c' to create an account or enter your username to log in.\n");
			String input = sc.nextLine();

			if (input.equals("c")) {
				u = new User(sc);
				Bank.addUser(u);
				badInput = false;
			} else if (!Bank.hasUser(input)) {
				System.out.println("No such username exists.\n");
			} else { // success! go to log in screen
				u = Bank.getUser(input);
				u.login(sc);
				badInput = false;
			}
		}

		// post-login page
		badInput = true;
		while (badInput && u.isLoggedIn()) {
			System.out.println("Enter 'd' to deposit money, 'w' to withdraw money, 'v' to view balance,\n");
			System.out.println("Or press 'q' to quit.");
			String input = sc.nextLine();

			switch (input) {
			case "d":
				deposit(u);
				break;
			case "w":
				withdraw(u);
				break;
			case "v":
				showBalance(u);
				break;
			case "q":
				// logout in quit. otherwise if q is not pressed, wait a while and then logout
				u.logout();
				badInput = false;
				break;
			default:
				System.out.print("Command not understood. ");
			}

			if (u.isLoggedIn())
				System.out.println("Would you like to make another transaction?\n");
		}
	}
	
	private static void deposit(User u) {
		System.out.println("Enter the amount you'd like to deposit.");
		// Throw number format type exception if it's not a legit amount
		String input = sc.nextLine();
		float amount = Float.parseFloat(input);
		if (amount <= 0) {
			System.out.println("Transaction failed: invalid deposit amount.");
		} else {
			u.deposit(amount);
			System.out.println("$" + amount + " deposited. Your balance is now " + u.getBalance() + ".");
		}
	}
	
	private static void withdraw(User u) {
		System.out.println("Enter the amount you'd like to withdraw.");
		// Throw number format type exception if it's not a legit amount
		String input = sc.nextLine();
		float amount = Float.parseFloat(input);
		if (amount > u.getBalance()) {
			System.out.println("You do not have enough funds in your account to complete this transaction.");
			showBalance(u);
		} else {
			u.withdraw(amount);
			System.out.println("$" + amount + " withdrawn. Your balance is now " + u.getBalance());
		}
	}
	
	private static void showBalance(User u) {
		String balance = String.format("%.2f", u.getBalance());
		System.out.println("Your balance total is currently $" + balance);
	}

	public static User getUser() {
		return u;
	}
}
