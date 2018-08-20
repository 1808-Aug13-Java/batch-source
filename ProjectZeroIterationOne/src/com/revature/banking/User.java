package com.revature.banking;

import java.io.Serializable;
import java.util.Scanner;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7942974187506599870L;
	private String email;
	private String username;
	private String password;
	private BankAccount account = new BankAccount();
	
	private static Scanner sc = new Scanner(System.in);
	
	public double getAmount() {
		return account.getBalance();
	}
	
	

	public User(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public User() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		// authenticate user
		if(authenticated()) {
			return password;
		}
		
		return null;
	}

	public void setPassword(String password) {
		// authenticate user
		if(authenticated()) {
			this.password = password;
		}
		
		
		
	}
	
	
	// returns bool value if the use authenticated themselves
	public boolean authenticated() {
		int attempts = 0;
		
		String passwordGuess = getPasswordAttempt();
		
		while(attempts < 3) {
			
			if(passwordGuess.equals(password)) {
				System.out.println("Authentication succesful");
				System.out.println();
				return true;
			}
			System.out.println("Incorrect password,  try again.\n");
			passwordGuess = getPasswordAttempt();
			// if the user is incorrect we will try again unless
			// too many attempts have been made
			attempts++;
		}
		
		System.out.println("Too many attempts");
		return false;
	}
	
	private String getPasswordAttempt() {
		String passwordAttempt = "";
		System.out.printf("Enter the password for %s:\n", username);
		passwordAttempt = sc.nextLine();

		return passwordAttempt;
	}

	public BankAccount getAccount() {
		return account;
	}



	public void setAccount(BankAccount account) {
		authenticated();
		this.account = account;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	
	// modified to ensure that .contains on array list
	// will catch equality without checking password equality
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
