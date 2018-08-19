package com.revature.bank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
	/* Structure:
	 * General menu looping, getting prompt for operation option
	 * Structure to hold users. Updated with creation of User classes during new accounts
	 * Keep updating that class for other menu options
	 * On "log out" option, go back to General menu loop
	 * On "exit" option, write all changes to file
	 * 
	 * comments with "NOTE: " are for yourself to look back on
	 */
	
	private static Scanner scan = new Scanner(System.in); //NOTE: put it outside of
											//the functions so u can use it anywhere
									//and private static so theres only 1 and u can use it 
						//in multiple functions...i think
					//it's private so other classes in the package wont see it. I have
	//				other scanners in other classes too like Bank
					//also this way you dont have to close it
	
	public static void main(String[] args) {
		boolean exitFlag = false;
		String userInput;
		Bank bank = new Bank();
		
		//general menu
		while(exitFlag == false)
		{
			userInput = prompt1();//menu method
			
			switch( userInput )
			{
			case "a":
				bank.createUser();	// create new user
				break;
			case "b":
				bank.login();
				break;
			case "c":			//exit out of while loop
				exitFlag = true;
				break;
			}	//switch()
		}	//while loop for general menu
		
		//on exit, read all bank info into a file using a method
		writeToFile(bank);
		System.out.println("Here is what we just wrote to the file: ");
		display(bank);	//displaying everything at the end for now
		scan.close(); 			//close scanner	
		System.out.println("The bank is closed now.");
	} // main()
	
	
	public static void writeToFile(Bank bank)
	{
		String fileContentString = bank.toString(); //All the stuff we'll write to file
		
		String fileName = "src/com/revature/bank/bankrecords.txt";
		BufferedWriter bw = null;	//we make a File object, but we don't work with the File
								//we will use the BufferedWriter to actually write things
		
		try {	
			File file = new File(fileName);
			if(!file.exists())
				file.createNewFile();
			
			FileWriter fWriter = new FileWriter(file);
			bw = new BufferedWriter(fWriter);	//create a buffer out of your Writer to work with
					//if you just put file, not a fw, it wont accept a File argument
			
			bw.write(fileContentString);
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	//we dont have to close the file or filewriter we made, but we just
							//close the bufferedwriter
		}
		
	}
	
	public static String prompt1()
	{
		String s;
		String generalMenu = "Enter a letter choice:\n" + 
							 "A: Create a new account\n" + 
							 "B: Log in to an existing account\n" +
							 "C: Exit\n";
		
		s = prompt(generalMenu).toLowerCase();
		System.out.println("entered: "+s);
		
		while(checkInput(s) == false)	
		{
			System.out.println("That's an invalid choice\n");
			s = prompt(generalMenu).toLowerCase();
		}
				
		return s;		//string.toLowerCase();
	}
	
	public static boolean checkInput(String s)
	{
		switch(s) {
		case "a":
			return true;
		case "b":
			return true;
		case "c":
			return true;
		default:
			return false;
		}
	}
	
	public static String prompt(String message)
	{
		System.out.print(message);
		return scan.nextLine();
	}
	
	public static void display(Bank bank)
	{
		//fornow,just print it all out
		System.out.println("Here is a list of all the customer information: ");
		System.out.println(bank);
		System.out.println();
	}
	
}// Driver class
