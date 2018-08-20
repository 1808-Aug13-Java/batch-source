package com.revature.userinput;

import java.util.Scanner;

public class CreateAccount {
	
	private static Scanner sc = new Scanner(System.in);
	private String username;
	private String pw;
	
	public CreateAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateAccount(String username, String pw) {
		super();
		this.username = username;
		this.pw = pw;
	}
	
	public void createAccount() {
		System.out.println("Follow prompts to create online account.");
		System.out.println("Enter email address or username: ");
		String userin = sc.nextLine();
		System.out.println("Enter password: ");
		String password = sc.next();
		
		if(password.equals(userin)) {
			System.out.println("they are the same");
		} else {
			System.out.println("they are not the same");
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pw == null) ? 0 : pw.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateAccount other = (CreateAccount) obj;
		if (pw == null) {
			if (other.pw != null)
				return false;
		} else if (!pw.equals(other.pw))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CreateAccount [username=" + username + ", pw=" + pw + "]";
	}
	
	

}
