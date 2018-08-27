package bank.controller;

import java.util.Scanner;

public class User {
	private String username;
	private transient String password;
	private transient float accountBalance;
	private boolean unlocked;
	
	public User() {}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.accountBalance = 0; // explicit
	}
	
	public User(Scanner sc) {
		createUser(sc);
	}
	
	private void createUser(Scanner sc) {
		while (true) {
			System.out.println("Enter a username or email address: \n");
			String input = sc.nextLine();
			
			if (Bank.hasUser(input)) {
				System.out.print("Username unavailable, try a different one. ");
				createUser(sc);
			} else {
				username = input;
				break;
			}
		}
		
		while (true) {
			System.out.println("Enter a password: \n");
			String input = sc.nextLine();
			
			// check if password is valid
			password = input;
			break;
		}
		
		unlocked = true;
	}
	
	public void deposit(float amount) { // async or sync?
		accountBalance+=amount;
	}
	
	public void withdraw(float amount) {
		if (accountBalance < amount) {
			// throw NotEnoughBalanceException
		}
		accountBalance-=amount;
	}
	
	public float getBalance() {
		return accountBalance;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login(Scanner sc) {
		boolean badInput = true;
		while(badInput) {
			System.out.println("Enter your password.\n");
			String input = sc.nextLine();
			input = input.trim();
			if (input.equals(password)) { // success!
				badInput = false;
				unlocked = true;
				System.out.println("You've just been logged in.");
				// go to post-login page (call another method)
			} else if (input.equals("b")) {
				badInput = false;
				// call home method
			} else {
				System.out.println("Incorrect password. Please try again or press 'b' to go back.\n");
			}
		}
	}
	
	public void logout() {
		unlocked = false;
	}

	public boolean isLoggedIn() {
		return unlocked;
	}
}
