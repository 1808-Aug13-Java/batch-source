package bank.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Driver {

	public static void main(String[] args) {
		ConsoleScript.run();
		User u = ConsoleScript.getUser();

		// THIS CODE SHOULD GO IN DATA ACCESS LAYER AND PASSED IN THROUGH STRING OR SOMETHING
		String path = "src/data.txt";
		String username = "username: " + u.getUsername() + "\n";
		String password = "password: " + u.getPassword() + "\n";
		String balance = "account balance: " + u.getBalance() + "\n";
		String content = username + password + balance + "\n";
		try {
			File file = new File(path);
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(content);
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Error creating file\n");
			e.printStackTrace();
		} 
	}
}
