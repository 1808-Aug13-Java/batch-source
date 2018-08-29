package BankingApp;

import java.util.Scanner;

public class MainMenu extends UserInfo {
	
	public MainMenu() {
		super();
		
	}
	
	public void mainMenu() {

		int choice;
		Scanner choiceIn = new Scanner(System.in);
		
		System.out.println("\t\t\t\tWelcome");
		System.out.println("1: Create account");
		System.out.println("2: Log in to existing account");
		System.out.println("Please choose one of the menu options.");
		
		do {
			choice = choiceIn.nextInt();
						
			switch(choice) {
			
			case 1: 
				createAccount();
				System.out.println("Now press 2 to log in");
				break;
			
			case 2: 
				break;
					
			default: 
				System.out.println("You must enter a choice from the menu");
			}
			
		} while(choice != 2);
		
		logIn();
		
	}

}
