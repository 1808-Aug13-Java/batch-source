/*package com.revature.project0;
import java.util.Scanner;

import com.revature.accounts.Account;
import com.revature.operations.AccountOperations;
import com.revature.operations.OperationFactory;
import com.revature.utils.InputScanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class BankingAppDriver {
	
	private static boolean successLogin;

    private static InputScanner input;
    private static HashMap<Integer, Account> accounts;
    private static Account userAccount;

    public static void main(String[] args)  {

        accounts = new HashMap<Integer, Account>();
        successLogin = false;


        login();

        if(successLogin) {
            performOperation();
        }

    }
    private static void login() {
        int loginTries = 0;

        input = InputScanner.getInstance();

        String path = "src/com/revature/project0/username.txt";
           
        
		 System.out.println("Create Account no.: ");
	        Scanner scan = new Scanner(System.in);
	        String text = scan.nextLine();
	        FileWriter fWriter=null;
	        BufferedWriter writer=null;
	        try {
	        File file = new File(path);
	        if(!file.exists()) {
				file.createNewFile();
		}
	        fWriter = new FileWriter(path);
	        writer = new BufferedWriter(fWriter);
	        writer.write(text);
	        writer.newLine();
	        writer.close();
	        System.err.println("Your input of " + text.length() + " characters was saved.");
	        } catch (Exception e) {
	            System.out.println("Error!");
	        }
        
	        Account account1 = new Account(Integer.parseInt(text), "", 0.00);
	        accounts.put(account1.getAccountNo(), account1);
	    //wanted to implement verification that account was created:
	        // BufferedReader br = new BufferedReader(new FileReader(path)); 
	        //didn't have time to work out the logic for it
	       
	    System.out.println("Enter Account no.: ");      
	    String userInput = input.readInput();

        System.out.println("Searching for account no. " + text);
        //converts string to int
        userAccount = accounts.get(Integer.parseInt(userInput));

    	System.out.println("Please create password: ");
        Scanner scan1 = new Scanner(System.in);
        String text1 = scan.nextLine();
        FileWriter fWriter1=null;
        BufferedWriter writer1=null;
        try {
        File file = new File(path);
        
        if(!file.exists()) {
			file.createNewFile();
	}
        fWriter = new FileWriter(path);
        writer = new BufferedWriter(fWriter);
        writer.write(text + "\n" + text1);
        writer.newLine();
        writer.close();
        System.err.println("Your input of " + text1.length() + " characters was saved.");
    
        } catch (Exception e) {
            System.out.println("Error!");
            System.out.println("Please enter password: ");
        userInput = input.readInput();
        }
        
      
            if(!text .equals (userInput)) {
                System.out.println("Account no. is invalid!");
            } else {
            //while(true)	
            while(text .equals (userInput)) {
        
                if(text1.equals(text1)) {
                	 System.out.println("Please enter password: ");
                     userInput = input.readInput();
                     if(!text1 .equals (userInput)) {
                         System.out.println("Password is invalid!");
                     }
                     else {
                    System.out.println("Login was performed successfully!");
                    successLogin = true;
                    break;}
                } else {
                    System.out.println("Invalid password, please try again!");
                }

                loginTries ++;
                if(loginTries == 3) {
                    System.out.println("Maximum number of tries reached!");
                    break;
                }
            }
        }
        }
    
    private static void performOperation() {
        OperationFactory factory = new OperationFactory();

        boolean stop = false;
//can withdraw money by depositing a negative amount
        while(!stop) {
            System.out.println("Please select one of the following options: ");
            System.out.println("1. Check Account Summary");
            System.out.println("2. Deposit money in account");

            System.out.println("Your choice: ");
            String userInput = input.readInput();
            
            AccountOperations operation = factory.getOperation(userInput);
            operation.performOperation(userAccount);

            while(true) {
                System.out.println("Do you wish to make another operation? (Y/N)");
                userInput = input.readInput();

                if(userInput.equalsIgnoreCase("n")) {
                    stop = true;
                    break;
                } else if(userInput.equalsIgnoreCase("y")) {
                    break;
                } else {
                    System.out.println("Invalid option!");
                }
            }
        }
    }
	
}

*/