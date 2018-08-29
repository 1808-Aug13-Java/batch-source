package com.revature.bank;

//import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.UserDaoImpl;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

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
	
	private static Logger log = Logger.getRootLogger();
	private static Scanner scan = new Scanner(System.in); //NOTE: put it outside of
											//the functions so u can use it anywhere
									//and private static so theres only 1 and u can use it 
						//in multiple functions...i think
					//it's private so other classes in the package wont see it. I have
	//				other scanners in other classes too like Bank
					//also this way you dont have to close it
	
	public static void main(String[] args) {
	/*BEGIN: TESTING OUR CONNECTION, DAOS*/	
//		try {		//make connection. will say Oracle JDBC if connected
//			Connection con = ConnectionUtil.getConnection();
//			log.info(con.getMetaData().getDriverName());
//		} catch (SQLException | IOException e) {
//			e.printStackTrace();
//		}
//		
//		UserDaoImpl userDao = new UserDaoImpl();
//		log.info(userDao.getUsers());
//		log.info(userDao.getUserByUsername("Cindy"));
//		User userrr = new User("star", "star@gmail", "star5", (float) 0.0);
//		userDao.createUser(userrr);
//		log.info(userDao.getUsers());
//		userDao.updateUser(new User("star", "star@gmail", "star5", (float) 9673.23));
//		userDao.deleteUser(userrr);
	/*END: TESTING OUR CONNECTION, DAOS*/
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
			default:
			}	//switch()
		}	//while loop for general menu
		
		//on exit, read all bank info into a file using a method
//		writeToFile(bank);
//		log.info("Here is what we just wrote to the file: ");
//		display(bank);	//displaying everything at the end for now
		scan.close(); 			//close scanner	
		log.info("The bank is closed now.");
	} // main()
	
	
//	public static void writeToFile(Bank bank)
//	{
//		String fileContentString = bank.toString(); //All the stuff we'll write to file
//		
//		String fileName = "src/com/revature/bank/bankrecords.txt";
//		BufferedWriter bw = null;	//we make a File object, but we don't work with the File
//								//we will use the BufferedWriter to actually write things
//		
//		try {	
//			File file = new File(fileName);
//			if(!file.exists())
//				file.createNewFile();
//			
//			FileWriter fWriter = new FileWriter(file);
//			bw = new BufferedWriter(fWriter);	//create a buffer out of your Writer to work with
//					//if you just put file, not a fw, it wont accept a File argument
//			
//			bw.write(fileContentString);
//			
//		} catch(IOException e) {
//			e.printStackTrace();
//		} finally {
//			
//			try {
//				bw.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 	//we dont have to close the file or filewriter we made, but we just
//							//close the bufferedwriter
//		}
//		
//	}
	
	public static String prompt1()
	{
		String s;
		String generalMenu = "Enter a letter choice:\n" + 
							 "A: Create a new account\n" + 
							 "B: Log in to an existing account\n" +
							 "C: Exit\n";
		
		s = prompt(generalMenu).toLowerCase();
		log.info("entered: "+s);
		
		while(!checkInput(s))	
		{
			log.info("That's an invalid choice\n");
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
		log.info(message);
		return scan.nextLine();
	}
//	
//	public static void display(Bank bank)
//	{
//		//fornow,just print it all out
//		log.info("Here is a list of all the customer information: ");
//		log.info(bank);
//		log.info("");
//	}
	
}// Driver class
