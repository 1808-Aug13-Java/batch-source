/**
 * 
 */
package com.revature.bank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author nozuko
 *
 */
public class BankDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Modify this!!
		Scanner sc = new Scanner(System.in);
		int numUsers = 0;
		Bank bank = new Bank();
		Client[] cust = bank.getClient();

		String path = "src/com/revature/bank/bankdata.txt";
		//String content = "";

		try {

			//specify the file we want to write to
			File file = new File(path);

			//checking first to see if the file exists, creating it if it doesn't
			if(!file.exists()) {
				file.createNewFile();
			}

			// our FileWriter has an optional argument which specifies if it's appending to the file
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);

			while(true) {
				System.out.println("Please select a number for one of the options:");
				System.out.println("1: Sign up (new users)");
				System.out.println("2: Deposit Money"); // requires account number for login
				System.out.println("3: Withdraw Money");
				System.out.println("4: Check my Balance");
				System.out.println("5: Exit");
				try {
					int choice = Integer.parseInt(sc.nextLine());

					switch(choice) {
					case 1:
						System.out.println("Thank you for choosing to bank with us.");
						System.out.println("Creating an account for a new customer: ");
						System.out.println("Please Enter the initial amount in your account: ");
						double balance = Double.parseDouble(sc.nextLine());
						System.out.println("Please Enter an account number: ");
						String accNumEntered = sc.nextLine();
						Account account = new Account(balance, accNumEntered);
						System.out.println("Please Enter your full name: ");
						String name = sc.nextLine();
						System.out.println("Please Enter a new username: ");
						String user = sc.nextLine();
						bw.write("username:  " + user+ "\n");
						System.out.println("Please Enter a new password: ");
						String pw = sc.nextLine();
						bw.write("password:  " + pw + "\n");
						System.out.println("Make sure to keep this password somewhere safe.");
						Client client = new Client(name, user, pw, account);
						cust[0] = client; // only one customer
						numUsers+=1;
						break; // prevents fall-through
					case 2: // must change functionality so that anyone can deposit $$
						boolean isCustomer = false;
						System.out.println("Please Enter your account number: ");
						accNumEntered = sc.nextLine();
						if (numUsers == 0) {
							System.out.println("You are not yet a customer with us.");
							break;
						}
						Account acc = cust[0].getAccount();
						String accNumCheck = acc.getAccountNumber();
						if(accNumCheck.equals(accNumEntered)) {
							isCustomer = true;
							System.out.println("Hello, "+cust[0].getName()+". Welcome back!");
							System.out.println("Please Enter the amount you want to deposit: ");
							double depAmt = Double.parseDouble(sc.nextLine());
							acc.deposit(depAmt);
						}
						if(isCustomer == false) {
							System.out.println("Account number not in our records.");
						}
						break;
					case 3:
						boolean isCustomer2 = false;
						System.out.println("Please Enter your account number: ");
						accNumEntered = sc.nextLine();
						if (numUsers == 0) {
							System.out.println("You are not yet a customer.");
							break;
						}
						Account acc2 = cust[0].getAccount();
						String accNumCheck2 = acc2.getAccountNumber();
						if(accNumCheck2.equals(accNumEntered)) {
							isCustomer2 = true;
							System.out.println("Hello, "+cust[0].getName()+". Welcome back!");
							System.out.println("Please Enter the amount you want to withdraw: ");
							double wdAmt = Double.parseDouble(sc.nextLine());
							acc2.withdraw(wdAmt);	
						}
						if(isCustomer2 == false) {
							System.out.println("Account number not in our records.");
						}
						break;
					case 4:
						boolean isCustomer3 = false;
						System.out.println("Please Enter your account number: ");
						accNumEntered = sc.nextLine();
						if (numUsers == 0) {
							System.out.println("You are not yet a customer.");
							break;
						}
						Account acc3 = cust[0].getAccount();
						String accNumCheck3 = acc3.getAccountNumber();
						if(accNumCheck3.equals(accNumEntered)) {
							isCustomer3 = true;
							System.out.println("Hello, "+cust[0].getName()+". Welcome back!");
							System.out.println("Your balance is: $" + acc3.getBalance());
							bw.write("current balance:  $" + acc3.getBalance() + "\n");
						}
						if(isCustomer3 == false) {
							System.out.println("Account number not in our records.");
						}
						break;
					case 5:
						System.out.println("You have chosen to exit. Thank you for banking"
								+ " with us. You are a valued customer. Goodbye :)");
						System.exit(0);
						bw.close();
						break;
					default:
						break;
					} // end switch
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // end catch

			} // end while

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // end main
} // end class
