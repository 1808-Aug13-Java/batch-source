package com.revature.project0;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.io.BufferedReader;
public class Bank {
	Scanner scan = new Scanner(System.in);
	String path = "src/com/revature/project0/bankInfo.txt";
	BufferedReader br = null;
	String temp;
	public void mainMenu() {
		System.out.println("Welcome to the Iron Bank of Braavos! How may we help you today?");
		System.out.println("Register\nLog In\nExit");
		String option = scan.nextLine();
		System.out.println();
		
		switch(option.toLowerCase()) {
		case "register" : 
		case "1" :
			register();
			break;
		case "log in" :
		case "login" :
		case "2" :
			logIn();
			break;
		case "exit" :
		case "3" :
			System.out.println("Thank you for using the Iron Bank of Braavos. \nRemember to always repay your debts! \nHave a great rest of the day!");
			System.exit(0);
		default:
			System.out.println("Please enter a valid option.");
			System.out.println();
			mainMenu();
		}
	}
	
	public void register( ) {
		System.out.println("Thank you for wanting to make an account with us! Below please provide your name \n(To go back, type 'back'):");
		String cusName = scan.nextLine();
		System.out.println();
		if(cusName.toLowerCase().equals("back")) {
			mainMenu();
		}
		System.out.println("Hello " + cusName + "! Please enter a Username \n(To go back, type 'back'):");
		String userName = scan.nextLine();
		System.out.println();
		if(userName.toLowerCase().equals("back")) {
			register();
		}
		System.out.println("We need a password \n(To go back, type 'back'):");
		String password = scan.nextLine();
		System.out.println();
		if(password.toLowerCase().equals("back")) {
			register();
		}
		System.out.println("Finally your inital deposit amount? \n(To go back, type 'back')");
		String entry = scan.nextLine();//getAmount();
		System.out.println();
		double amount;
		if(entry.toLowerCase().equals("back")) {
			register();
		} else {
			amount = Double.parseDouble(entry); 
			bankWrite(cusName, userName, password, amount);
		}
	}
	
	public void logIn() {
		System.out.println("Please provide your username \n(To go back, type 'back'): ");
		String returnUser = scan.nextLine();
		System.out.println();
		if(returnUser.toLowerCase().equals("back")) {
			mainMenu();
		}
		System.out.println("Please provide the password associated with the username \n(To go back, type 'back'): ");
		String returnPass = scan.nextLine();
		System.out.println();
		if(returnPass.toLowerCase().equals("back")) {
			logIn();
		}
		logInCheck(returnUser, returnPass);
	}
	
	public void logInCheck(String returnUser, String returnPass) {
		try {
			br = new BufferedReader(new FileReader(path));
			String name = br.readLine();
			String userName = br.readLine();
			String password = br.readLine(); 
			if(name == null) {
				System.out.println("We're sorry, those credentials do not match our records. Please try again");
				System.out.println();
				logIn();
			}else if(userName.equals(returnUser) && password.equals(returnPass)) {
				System.out.println("Welcome back " + name + "!");
				System.out.println();
				transactions();
			} else {
				System.out.println("We're sorry, those credentials do not match our records. Please try again.");
				System.out.println();
				logIn();
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public double getAmount() {
		double amount;
		try {
			amount = Double.parseDouble(scan.nextLine());
			return amount;
		} catch(NumberFormatException e) {
			System.out.println("Invalid input. Please input a numeric value.");
			return getAmount();
		}
	}
	public void bankWrite(String cusName, String userName, String password, double amount) {
		
		BufferedWriter bw = null;
		try {
			File file = new File(path);
			if(!file.exists()) {
				file.createNewFile();
			}
		
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(cusName + "\n");
			bw.write(userName + "\n");
			bw.write(password + "\n");
			bw.write(String.valueOf(amount));
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		transactions();
		
	}
	
	public void transactions() {
		System.out.println("What can we do for you today?");
		System.out.println("Withdraw\nDeposit\nCheck Balance\nLog Out");
		String option = scan.nextLine();
		System.out.println();
		
		switch(option.toLowerCase()) {
		case "withdraw" :
		case "withdrawal" :
		case "withdrawals" :
		case "1" :
			withdraw();
			break;
		case "deposit" :
		case "2" :
			deposit();
			break;
		case "check balance" :
		case "balance" :
		case "3" :
			System.out.println("Your current balance is: " + NumberFormat.getCurrencyInstance().format(getBalance()));
			System.out.println();
			transactions();
			break;
		case "log out" :
		case "logout" :
		case "4" :
			mainMenu();
			break;
		default:
			System.out.println("Please enter a valid option");
			System.out.println();
			transactions();
		}
	}
	
	public void withdraw() {
		System.out.println("How much would you like to withdrawal? (Type '0' to go back)");
		double amount = Double.parseDouble(scan.nextLine());
		if(amount == 0) {
			transactions();
		} else if(amount > 0) {
			double balance = getBalance();
			if(amount > balance) {
				System.out.println("Your withdrawal requst exceeds your current balance.");
				System.out.println();
				withdraw();
			} else {
				balance = balance - amount;
				System.out.println("You withdrew " + NumberFormat.getCurrencyInstance().format(amount));
				System.out.println("Bringing your balance to " + NumberFormat.getCurrencyInstance().format(balance));
				System.out.println();
				setBalance(balance);
			}
		} else {
			System.out.println("Please enter a valid amount.");
			withdraw();
		}
	}
	
	public void setBalance(double newBalance) {
		try {
			br = new BufferedReader(new FileReader(path));
			String cusName = br.readLine();
			String userName = br.readLine();
			String password = br.readLine();
			bankWrite(cusName, userName, password, newBalance);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public double getBalance() {
		double balance = 0.0;
		try {
			br = new BufferedReader(new FileReader(path));
			temp = br.readLine();
			temp = br.readLine();
			temp = br.readLine();
			balance = Double.parseDouble(br.readLine());
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return balance;
	}
	
	public void deposit() {
		System.out.println("How much would you like to deposit? (Type '0' to go back)");
		double amount = Double.parseDouble(scan.nextLine());
		if(amount == 0) {
			transactions();
		} else if(amount > 0) {
			double balance = getBalance();
			balance = balance + amount;
			System.out.println("You deposited " + NumberFormat.getCurrencyInstance().format(amount));
			System.out.println("Bringing your balance to " + NumberFormat.getCurrencyInstance().format(balance));
			System.out.println();
			setBalance(balance);
		} else {
			System.out.println("Please enter a valid amount.");
			deposit();
		}
	}
	
}

