package brian.bank;

import java.util.Scanner;

public class BankAccount {

	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	
	System.out.println("Please login to your account!");
	
	Login login = new Login();
	login.login();
	
	int userInput;
	boolean exit = false;
	float balance = 0f;
	do {
		
		System.out.println("1. Deposit money");
        System.out.println("2. Withdraw money");
        System.out.println("3. View balance");
		System.out.println("Choose an option, 0 to Logout: ");
		userInput = input.nextInt();
		
		switch(userInput)	{
		case 1:
			//deposit money
			float amount;
			System.out.println("Deposit Amount?");
			amount = input.nextFloat();
			if(amount <= 0)
				System.out.println("Must deposit positive amount.");
			else {
			balance += amount;
			System.out.println("$" + amount + "has been deposited to account.");
			}
			break;
			
		case 2:
			//withdraw money
			System.out.println("Withdraw Amount?");
			amount = input.nextFloat();
			if(amount <= 0 || amount > balance)
				System.out.println("Cannot complete withdrawal.");
			else{ 
				balance -= amount;
				System.out.println("$" + amount + "has been withdrawn.");
			}
			break;
			
		case 3:
			//view balance
			System.out.println("Your current balance is: $" + balance);		
			break;
		case 0:
			exit = true;
			System.out.println("You have been logged out");
			break;
			
			default:
				System.out.println("Invalid Input!");
				break;	
		}
		System.out.println();
		
		if(userInput == 0)
			exit = true;
	} while (!exit);
	System.out.println("Goodbye");
	input.close();
	
	}
	
	
			
}