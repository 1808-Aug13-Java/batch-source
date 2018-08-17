package com.revature.bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Commands {

	private static Scanner sc = new Scanner(System.in);
	private BufferedWriter bw = null;
	private boolean loggedIn = false;
	String choice="";
	double money=0.00;
	public void newAccount(){
		System.out.println("Please enter your desired username: ");
		String input = sc.nextLine();
		String path = "src/com/revature/bank/Username.txt";
		fileWrite(path, input);
		
		System.out.println("Please enter your desired password: ");
		input = sc.nextLine();
		path = "src/com/revature/bank/Password.txt";
		fileWrite(path, input);
		
		path = "src/com/revature/bank/Balance.txt";
		fileWrite(path, "0.00");
		System.out.println("Your balance has been initialized to 0.00.");
	}
	public void fileWrite(String path, String input) {
		try {
			
			File file = new File(path);
			
				if(!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, false);
				bw = new BufferedWriter(fw);
				
				bw.write(input);
				System.out.println("Your information has been saved.");
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	public boolean isThereAUser() {
		File file = new File("src/com/revature/bank/Username.txt");
		if(!file.exists())
			return false;
		file = new File("src/com/revature/bank/Password.txt");
		if(!file.exists())
			return false;
		return true;
	}
	public void logIn() {
		String path = "src/com/revature/bank/Username.txt";
		String username;
		String uInput;
		String password;
		String pInput;
		username = fileRead(path);
		System.out.println("Please input your username: ");
		uInput = sc.nextLine();
		path = "src/com/revature/bank/Password.txt";
		password = fileRead(path);
		System.out.println("Please input your password: ");
		pInput = sc.nextLine();
		if(username.equals(uInput)&&password.equals(pInput)){
			System.out.println("Successful login!");
			loggedIn = true;
		}else{
			System.out.println("Incorrect username or password. Please try again.");
			loggedIn=false;
		}
	}
	public String fileRead(String path) {
		String information = "Default info";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			information = br.readLine();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return information;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void menu() {
		System.out.println("--------------Main Menu--------------");
		System.out.println("Deposit/Withdraw/View Balance/Log Out");
		System.out.println("-------------------------------------");
		choice=sc.nextLine();
		choice=choice.toLowerCase();
		switch(choice) {
			case "deposit":
				System.out.println("How much would you like to deposit?");
				money = sc.nextDouble();
				deposit(money);
				break;
			case "withdraw":
				System.out.println("How much would you like to withdraw?");
				money = sc.nextDouble();
				while(money>Double.parseDouble(viewBalance())) {
					System.out.println("Cannot overdraft account.");
					System.out.println("Current balance: "+viewBalance());
					System.out.println("Choose new withdrawal amount:");
					money = sc.nextDouble();
				}
				withdraw(money);
				break;
			case "view balance":
				System.out.println("Your current balance is: "+viewBalance());
				break;
			case "log out":
				logOut();
				break;
			default:
				System.out.println("Invalid input.");
				break;
		}
	}
	public void deposit(double money) {
		String path = "src/com/revature/bank/Balance.txt";
		String old = fileRead(path);
		double bal = Double.parseDouble(old);
		bal+=money;
		String balance = Double.toString(bal);
		fileWrite(path, balance);
	}
	public void withdraw(double money) {
		String path = "src/com/revature/bank/Balance.txt";
		String old = fileRead(path);
		double bal = Double.parseDouble(old);
		bal-=money;
		String balance = Double.toString(bal);
		fileWrite(path, balance);
	}
	public String viewBalance() {
		String balance = fileRead("src/com/revature/bank/Balance.txt");
		return balance;
	}
	public void logOut() {
		loggedIn = false;
		System.out.println("Thank you and have a good day!");
	}
}
