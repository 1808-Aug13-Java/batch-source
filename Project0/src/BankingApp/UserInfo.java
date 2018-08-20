package BankingApp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserInfo {
	
	public UserInfo() {
		super();
	}

	Scanner sc = new Scanner(System.in);
	private String createUserName;
	private String userName = "";
	private String createPassword;
	
	public void createAccount() {
		System.out.println("Follow prompts to create online account.");
		System.out.println("Enter email address or username: ");
		createUserName = sc.nextLine();
		System.out.println("Enter password: ");
		createPassword = sc.nextLine();
		writeData("Username: ");
		writeData(createUserName);
		writeData("Password: ");
		writeData(createPassword);
	}
	
	public void logIn() {	
		System.out.println("Please enter your username: ");
		userName = sc.nextLine();
		while(!(userName.equals(createUserName))) {
			System.out.println("Try again: ");
			userName = sc.nextLine();
		} 
		
		System.out.println("Enter password:");
		String password = sc.nextLine();
		while(!password.equals(createPassword)) {
			System.out.println("Try again: ");
			password = sc.nextLine();
			} 
		
		BankingAccount bank = new BankingAccount();
		bank.accountMenu();

}

	String path = "src/BankingApp/userdata.txt";
				
	
	public void writeData(String content) {
	
		try {
			File file = new File(path);
			
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.write('\n');			
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeData(int content) {
		
		try {
			File file = new File(path);
			
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("$");
			bw.write(Integer.toString(content));
			bw.write('\n');
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createPassword == null) ? 0 : createPassword.hashCode());
		result = prime * result + ((createUserName == null) ? 0 : createUserName.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((sc == null) ? 0 : sc.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		UserInfo other = (UserInfo) obj;
		if (createPassword == null) {
			if (other.createPassword != null)
				return false;
		} else if (!createPassword.equals(other.createPassword))
			return false;
		if (createUserName == null) {
			if (other.createUserName != null)
				return false;
		} else if (!createUserName.equals(other.createUserName))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (sc == null) {
			if (other.sc != null)
				return false;
		} else if (!sc.equals(other.sc))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	

}
