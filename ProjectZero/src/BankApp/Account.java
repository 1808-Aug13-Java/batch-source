package BankApp;

import java.io.Console;
import java.util.Scanner;
import ProjectDao.UserBankingDao;
import ProjectDao.UserBankingDaoImpl;
import ProjectModel.UserBanking;
import org.apache.log4j.Logger;
public class Account {

		private static Logger log = Logger.getRootLogger();
		
		private static int id;		
		private static String username;
		private static double userbalance;

		
		
		Account( int UID, String UFN )
		{
			username = UFN;
		    id = UID;
		    
		}
		
		static void deposit(String username, double dollar) { 
			if (dollar != 0.0)
			{
				UserBankingDaoImpl udao = new UserBankingDaoImpl();
				UserBanking user = udao.getUserBankingByName(username);
				userbalance = userbalance + dollar;
				user.setUserBalance(userbalance);
				udao.updateUserBanking(user);
			}
		}
		static void withdraw(double dollar)
		{
			if(dollar !=0.0)
			{
			userbalance = userbalance - dollar;	
			}
		}
		static void insertUsername(String newuser, int password)
		{
			if(newuser != null)
			{
				UserBanking user = new UserBanking();
				UserBankingDaoImpl udao = new UserBankingDaoImpl();
				user.setUserName(username);
				user.setBankingId(password);
				udao.addUserBanking(user);
				
			}
		}
		
		static void viewer(UserBanking user)
		{
			char menu= '\0';
			do {
				Scanner scanner = new Scanner(System.in);
		
				log.info("<<<<<<<<<<<><<<<<<<<<W.F.W.B>>>>>>>><>>>>>>>>>>>>>>");
				log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				log.info("Welcome to one of the largest banks in the world.");
				log.info("You can trust us, we are here to serve you!");
				log.info("{Partnered with Oracle Database}");
				log.info("\n");
		
				log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				log.info("Here is the menu that will get you to where you desire.");
				log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				log.info("\n");
				log.info("*****************************************************");
				log.info("");
				log.info("*****************************************************");
				log.info("{L} Login");
				log.info("*****************************************************");
			
				log.info("\n");
				menu = scanner.next().charAt(0);
				//log.info("\n");		
				
				switch(menu) {
				
				case 'C':
					log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					log.info("Create your username");
					String newuser = scanner.nextLine();
					log.info("Create your password");
					Integer password = Integer.parseInt(scanner.nextLine());
					insertUsername(newuser, 123);
					log.info("\n");
					break;
				
				case 'L':
					log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					log.info("Enter in your username ");
					newuser = scanner.nextLine();
					log.info("Enter in your password ");
					//password = Integer.parseInt(scanner.nextLine());
					
					if(verify(newuser, 123)) {
						homePage("JON DOE");
					}
					log.info("\n");
					break;
					

				default:
					log.info("Sorry try again! Options are (F)(S)(U)(N)");
					log.info("MAKE SURE CAPS LOCK IS ON!");
					break;
				}
			
			} while(menu != 'N');
			
			log.info("Thank you for banking with WFWB, Good Bye" );
			
			}
		static boolean verify(String newuser, int password) {
			return true;
		}
		static void homePage(String username) {
			char menu= '\0';
			Scanner scanner = new Scanner(System.in);

			log.info("*****************************************************");
			log.info("Hello "+username + "! Welcome to your account. " );
			log.info("*****************************************************");
			log.info("\n");
			
	
	
			log.info("\n");
			log.info("*****************************************************");
			log.info("{F} Will allow you to Check your current balance");
			log.info("*****************************************************");
			log.info("{S} Will allow you to Deposit money into your account");
			log.info("*****************************************************");
			log.info("{U} Will allow you to Withdraw from you account");
			log.info("*****************************************************");
			log.info("{N} Will allow you to Exit your account");
			log.info("*****************************************************");
			log.info("\n");
			
			do {
			log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			log.info("Enter your command with CAPS LOCK ON ");
			log.info("Your Options are (F) (S) (U) (N)");
			log.info("{F} I want to CHECK My BALANCE");
			log.info("{S} I want to MAKE A DEPOSIT");
			log.info("{U} I want to WITHDRAW FROM ACCOUNT");
			log.info("{N} I want to Exit ACCOUNT");
			log.info("You May Select Your Command");
			menu = scanner.next().charAt(0);
			log.info("\n");		
			
			switch(menu) {
				case 'F':
					log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					log.info("Your current balance is: " + userbalance);
					
					log.info("\n");
					break;
				
				case 'S':
					log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					log.info("How much are you looking to deposit: ");
					double dollar = scanner.nextDouble();
					deposit(username, dollar);
					log.info("\n");
					break;
					
				case 'U':
					log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					log.info("How much are you looking to withdraw: ");
					double dollar2 = scanner.nextDouble();
					withdraw(dollar2);
					log.info("\n");
					break;
					
				case 'N':
					log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					break;
					
				default:
					log.info("Sorry try again! Options are (F)(S)(U)(N)");
					log.info("MAKE SURE CAPS LOCK IS ON!");
					break;
				}
			} while(menu != 'N');
			
			log.info("Thank you for banking with WFWB, Good Bye" );
			
			}
	}


