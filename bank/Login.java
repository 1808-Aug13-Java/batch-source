package brian.bank;

import java.util.Scanner;

public class Login {
	
Scanner input = new Scanner(System.in);
	
	public void login() {
	String username;
	String password;
	boolean auth = false;
	
	System.out.println("Enter your username: ");
	username = input.nextLine();
	
	System.out.println("Enter your password");
	password = input.nextLine();
	
	
	while (auth == false) {	
	if(username.equals("Neo") && (password.equals("Matrix"))) {
		System.out.println("Access granted");
		auth = true;
	}else {
		System.out.println("Invalid username or password. Please re-enter credentials");
		auth = false;
	}
		
		}
	}
}


