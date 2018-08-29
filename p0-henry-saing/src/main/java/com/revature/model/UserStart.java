package com.revature.model;


import org.apache.log4j.Logger;
import java.util.Scanner;

public class UserStart {
	
	private static Logger log = Logger.getRootLogger();
	
	public void introText() {
//		String response = "";

		log.info("What would you like to do?");
		log.info("1. Login");
		log.info("2. Create Account");
		log.info("3. Exit Bank");
		
		Scanner sc = new Scanner(System.in);
		
		String input= sc.nextLine();
		input = input.toLowerCase();
//		switch(input) {
//		case "login":
//			response = "login";
//			break;
//		case "create":
//			response = "create";
////			creation.creation();
//			break;
//		default:
//			log.info("Invalid Input Please try again\n");
//			introText();
//		}
		
//		return response;
	}

}
