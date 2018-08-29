package com.revature.login;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Login {
	
	boolean pw = true;
	String password;
	String username;
	String passwordCheck;
	int lineNumber = 0;
	boolean usernameCheck = false;
	boolean authenticated = false;
	boolean next = true;
	
	createAccount creation = new createAccount();
	private static Scanner sc = new Scanner(System.in);
	
	public void askPrompt(String response) {
		
		/*
		 * Based on response switch case to know what to do next.
		 * If the user responds login, go towards login methods, similarly goes for create
		 * Otherwise show that the input is invalid
		 */
		
		switch(response) {
		case "login":
			attemptlogin();
			break;
		case "create":
			creation.creation();
			break;
		default:
			System.out.println("This is the default and the end if something wasn't input correctly");
		}
	}
	
	public void attemptlogin() {
		int count = 0;				//Find line number that username/password is on in the text file
		int endCount = 0;			//Copy of count at the end to be used seperately
		System.out.println("Enter Username: ");
		username = sc.nextLine();
		File file = new File("src\\com\\revature\\login\\LoginAccounts.txt");
		File file1 = new File("src\\com\\revature\\login\\LoginPasswords.txt");
	    try {
	        Scanner sc = new Scanner(file);		//scan the LoginPasswords.txt file
	        /*
	         * loop to find the line number that has the username that matches
	         * the username from the input. Also confirms that the username
	         * exists in the file, showing that it is an account that already
	         * exists in the bank system.
	         */
	        
	        while (sc.hasNextLine()) {
	            String i = sc.nextLine();
	            if (i.equals(username)) {
	            	usernameCheck = true;
	            	count++;
	            	endCount = count;
	            	lineNumber = endCount;
	            }
	            count++;
	        }
	        /*
	         * Further confirms that the username is in the system. Next this gets the
	         * value of the password and stores it into a string passwordCheck. Then 
	         * calls pwchecker();
	         */
	        if (usernameCheck) {
	        	try {
	        		Scanner sc1 = new Scanner(file1);
	        			for (int i =0; i<endCount; i++) {
	        			passwordCheck = sc1.nextLine();
	        			}
	        		sc1.close();
	        		pwchecker();
	        		} catch (FileNotFoundException e) {
	        		// TODO Auto-generated catch block
	        		e.printStackTrace();
	        		}
	        	
	        } else {
	        	System.out.println("Username not recognized");
	        	System.out.println("Invalid Username, run program again.");
	        	}
	    }
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }

	}
	/*
	 * Asks for input by the user, which is the password and compares it to the passwords
	 * in the password file. The way my program is currently implemented is that it matches
	 * usernames and passwords by their line position in their respective text files. 
	 * So an example being the user name on line 4 has the password of the password on line 4
	 * in the password text file.
	 */
	
	public void pwchecker() {
		File file2 = new File("src\\com\\revature\\login\\accountInfo.txt");
		System.out.println("Enter Password:");
//		Scanner sc = new Scanner(System.in);
		String info= "";
		password = sc.nextLine();

		/*
		 * After confirmin the password, ask the user what bank action they would like to take.
		 * The options are "View" for view balance, "Deposit" increase balance, and "Withdraw 
		 * decrease balance. Based on the user input, the program performs the action accordingly
		 */
		
		if (password.equals(passwordCheck)) {
//			Scanner sc = new Scanner(System.in);
			System.out.println("Successfully logged in!");
			System.out.println("What would you like to do next?");
			System.out.println("View, Deposit, or Withdraw?");
			String bankAction = sc.nextLine();
//			sc.close();
			
			// Switches based on user input into bankAction
			
			switch(bankAction) {
			
			case "View":
				try {
					
					Scanner money = new Scanner(file2);
					
					/*
					 * This while loop finds the final line in the txt file. This occurs for the other
					 * operations as well (deposit and withdraw). The way the bank info is currently stored
					 * is that it is kept in a file with a single int. This currently represents the account
					 * in the system. All accounts created would also share this balance. The file constantly
					 * gets added to, but the true balance is the final line in the file.
					 */
					
					while(money.hasNextLine()) {
						info = money.nextLine();					
					}
					System.out.println(info);
					break;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			case "Deposit":
				try {
					System.out.println("How much money are you going to deposit? (Whole Numbers Only)");
					
					String next = sc.nextLine();
					int convert = Integer.parseInt(next);
					
					Scanner money = new Scanner(file2);
					
					while(money.hasNextLine()) {
						info = money.nextLine();					
					}

					convert += Integer.parseInt(info);
					System.out.println("Money that is now in the account: " + convert);
					
					/*
					 * add the final result back into the text file, updating the account balance
					 * This applies to the similar code in "Withdraw" as well
					 */
					
					try {
						FileWriter writer = new FileWriter("src\\com\\revature\\login\\accountInfo.txt", true);
						BufferedWriter bufferedWriter = new BufferedWriter(writer);
						bufferedWriter.write(convert + "\n");
						bufferedWriter.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					break;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case "Withdraw":

				try {
					System.out.println("How much money are you going to withdraw? (Whole Numbers Only)");
					
					String next = sc.nextLine();
					int minus = Integer.parseInt(next);
					
					Scanner money = new Scanner(file2);
					
					while(money.hasNextLine()) {
						info = money.nextLine();					
					}
					int convert = Integer.parseInt(info) - minus;
					System.out.println("Money that is now in the account: " + convert);
					
					try {
						FileWriter writer = new FileWriter("src\\com\\revature\\login\\accountInfo.txt", true);
						BufferedWriter bufferedWriter = new BufferedWriter(writer);
						bufferedWriter.write(convert + "\n");
						bufferedWriter.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					break;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			default:
				System.out.println("Invalid input, restart program.");
				break;
			}
		}
		else {
			System.out.println("Invalid Password. Please run program again.");
		}
	}

}