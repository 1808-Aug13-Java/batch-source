package com.revature.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class BankAccount {

	
	public static void main(String[] args) {
		Logger log = Logger.getRootLogger();
		
		try {
			Connection con = ConnectionUtil.getConnection();
			System.out.println(con.getMetaData().getDriverName());
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
	UserDao udi = new UserDaoImpl();
	User usr = null;
	log.info("Enter 1 to create a new account.");
	System.out.println("Enter 2 to login to an existing account.");
	Scanner input = new Scanner(System.in);
	String userInput = (input.nextLine());
	switch (userInput) {
	
	case "1":
		User username = new User();
		System.out.println("Enter Username");
		String s = input.nextLine();
		username.setUsername(s);
		System.out.println("Enter Password");
		String p = input.nextLine();
		username.setPassword(p);
		int u = udi.createUser(username);
		System.out.println("User creation succesful!");
		break;
		
	case "2": 
		System.out.println("Enter Username");
		s = input.nextLine();
		System.out.println("Enter Password");
		p = input.nextLine();
			usr = udi.getUsersByUsername(s);
		
	}
	
	
	boolean exit = false;
	float balance = 0f;
	do { //
		
		System.out.println("1. Deposit money");
        System.out.println("2. Withdraw money");
        System.out.println("3. View balance");
		System.out.println("Choose an option, 0 to Logout: ");
		userInput = input.nextLine();
		
		switch(userInput)	{
		case "1":
			System.out.println("Enter deposit amount: ");
			String dep = input.nextLine();
			System.out.println(dep);
			float amount = Float.parseFloat(dep); 
			udi.makeDeposit(usr.getUsername(), amount);
//			if(amount <= 0)
//				System.out.println("Must deposit positive amount.");
//			else {
//			System.out.println("$" + amount + "has been deposited to account.");
//			}
			break;
			
		case "2":
			//withdraw money
			System.out.println("Enter withdraw amount");
			String wtd = input.nextLine();
			//check here
			amount = Float.parseFloat(wtd);
			udi.makeWithdrawal(usr.getUsername(), amount);
//			if(amount <= 0 || amount > balance)
//				System.out.println("Cannot complete withdrawal.");
//			else{ 
//				balance -= amount;
//				System.out.println("$" + amount + "has been withdrawn.");
//			}
			break;
			
		case "3":
			//view balance
			
			usr = udi.getUsersByUsername(usr.getUsername());
			System.out.println("Your current balance is: $" + usr.getBalance());		
			break;
		case "0":
			exit = true;
			System.out.println("You have been logged out");
			break;
			
			default:
				System.out.println("Invalid Input!");
				break;	
		}
		System.out.println();
		
		if(userInput == "0")
			exit = true;
	} while (!exit);
	System.out.println("Goodbye");
	input.close();
	
	
/*	
	//	List<User> userList = udi.getUsers();
//	for(User u:userList) {
//		System.out.println(u);
//	}
	

//	User u = udi.getUsersByUsername("simmonbr");
//	System.out.println(u);
	
//	System.out.println(udi.createUser(new User("neo","matrix",2500)));
	
//	User u = new User("neo","theone",5000);
//	udi.updateUser(u);
	
//	System.out.println(udi.deleteUsers("neo"));
	
//	udi.makeDeposit("revature", 250);
	
//	udi.makeWithdrawal("revature", 7500);
	}
	*/
	
	}
}
