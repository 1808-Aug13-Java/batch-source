package com.revature.project0;
import java.util.Scanner;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;

public class Bank implements BankInterface{
	private static Logger log = Logger.getRootLogger();
	private static final String INVALID_AMOUNT = "Please enter a valid amount.";
	private static final String MORE_DECIMALS = "Please enter a value that does not exceed two decimal places.";
	Account a = new Account();
	Scanner scan = new Scanner(System.in);
	String temp;
	public void mainMenu() {
		log.info("Welcome to the Iron Bank of Braavos! How may we help you today?");
		log.info("Register\nLog In\nExit");
		String option = scan.nextLine();
		log.info("");
		
		switch(option.toLowerCase()) {
		case "register" : 
		case "1" :
			register();
			break;
		case "log in" :
		case "login" :
		case "2" :
			logIn();
			break;
		case "exit" :
		case "3" :
			log.info("Thank you for using the Iron Bank of Braavos. \nRemember to always repay your debts! \nHave a great rest of the day!");
			System.exit(0);
		default:
			log.info("Please enter a valid option.");
			log.info("");
			mainMenu();
		}
	}
		
	public void getName() {
		log.info("Please provide your name.\n (To go back, please enter 'back'): ");
		String name = scan.nextLine();
		if(name.length() > 1) {
			if(name.length() <= 50) {
				if(name.equalsIgnoreCase("back")) {
					mainMenu();
				} else {
					log.info("");
					a.setName(name);
					getUserName();
				}
			} else {
				log.info("Your name must be under 50 characters");
			}
		} else {
			log.info("Must enter at least two letters");
			getName();
		}
	}
	
	public void getUserName() {
		String checkUsername = "SELECT USERNAME FROM BANK";
		ResultSet rs = null;
		String tempName;
		log.info("Please enter your Username.\n (To go back, please enter 'back'):");
		String userName = scan.nextLine();
		log.info("");
		if(userName.length() > 0) {
			if(userName.length() <= 50) {
				if(userName.equals("back") ) {
					getName();
				} else {
					try(Connection con = ConnectionUtil.getConnection();
							Statement s = con.createStatement();) {
						rs = s.executeQuery(checkUsername);
						while(rs.next()) {
							tempName = rs.getString("USERNAME");
							if(tempName.equals(userName)) {
								log.info("That username is already taken, please try a different one.");
								getUserName();
							}
						}
					}catch(SQLException | IOException e) {
						log.error(e.getMessage());
					}
					log.info("");
					a.setUserName(userName);
					getPassword();
				}
			} else {
				log.info("Your username must be under 50 characters.");
				getUserName();
			}
		} else {
			log.info("Your username must have at least one character");
			getUserName();
		}
	}
	
	public void getPassword() {
		log.info("Please enter the password associated with the username.\n (To go back, please enter 'back'):");
		String password = scan.nextLine();
		log.info("");
		if(password.length() > 0) {
			if(password.length() <= 50) {
				if(password.equals("back")) {
					getUserName();
				} else {
					log.info("Please confirm your password by typing it again.\n (To go back, please enter 'back'):");
					String testPassword = scan.nextLine();
					log.info("");
					if(testPassword.equals("back")) {
						getPassword();
					} else if(testPassword.equals(password)) {
						a.setPassword(password);
						initialAmount();
					} else {
						log.info("Your passwords do not match! Please try again");
						log.info("");
						getPassword();
					}
				}
			} else {
				log.info("Your password must be under 50 characters");
				getPassword();
			}
		} else {
			log.info("Your password must have at least one character.");
			getPassword();
		}
	}
	
	public void initialAmount() {
		log.info("Please enter your initial deposit.\n (To go back, please enter 'back'):");
		String entry = scan.nextLine();
		log.info("");
		double amount;
		if(entry.length() > 0) {
			if(entry.equalsIgnoreCase("back")) {
				getPassword();
			} else {
				try { 
					amount = Double.parseDouble(entry);
					if(amount <= 1000000000) {
						if((amount * 100) % 1 != 0) {
							log.info(MORE_DECIMALS);
							initialAmount();
						} else if(amount < 0) {
							log.info(INVALID_AMOUNT);
							initialAmount();
						} else {
							a.setBalance(amount);
						}
					} else {
						log.info("Your deposit must be less than $1,000,000,000.00");
						initialAmount();
					}
				} catch (NumberFormatException e) {
					log.info(INVALID_AMOUNT);
					log.info("");
					initialAmount();
				}
			}
		} else {
			log.info("Your deposit must be greater than zero");
			log.info("");
			initialAmount();
		}
	}
	
	public void register( ) {
		String sql = "INSERT INTO BANK (CUST_NAME, USERNAME, PASSWORD, BALANCE) VALUES (?, ?, ?, ?)";
		String findID = "SELECT USER_ID FROM BANK WHERE USERNAME = ? AND PASSWORD = ?";
		String custName;
		String userName;
		String password;
		double balance;
		ResultSet rs = null;
		log.info("Thank you for wanting to make an account with us!");
		getName();
		custName = a.getName();
		userName = a.getUserName();
		password = a.getPassword();
		balance = a.getBalance();
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				PreparedStatement ps1 = con.prepareStatement(findID);) {
				ps.setString(1, custName);
				ps.setString(2, userName);
				ps.setString(3, password);
				ps.setDouble(4, balance);
				ps.executeUpdate();
				ps1.setString(1, userName);
				ps1.setString(2, password);
				rs = ps1.executeQuery();
				while(rs.next()) {
					a.setID(rs.getInt("USER_ID"));
				}
		} catch (SQLException | IOException e) {
			log.error(e.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				log.info(e.getMessage());
			}
		}
	transactions();
}
	
	public void logIn() {
		String sql = "SELECT * FROM BANK WHERE USERNAME = ? AND PASSWORD = ?";
		ResultSet rs = null;
		int numOfUsers = 0;
		String custName = "";
		double balance = 0.0;
		int userID = 0;
		log.info("Please provide your username \n(To go back, type 'back'): ");
		String returnUser = scan.nextLine();
		log.info("");
		if(returnUser.equalsIgnoreCase("back")) {
			mainMenu();
		}
		log.info("Please provide the password associated with the username \n(To go back, type 'back'): ");
		String returnPass = scan.nextLine();
		log.info("");
		if(returnPass.equalsIgnoreCase("back")) {
			logIn();
		}
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, returnUser);
			ps.setString(2, returnPass);
			rs = ps.executeQuery();
			while(rs.next()) {
				userID = rs.getInt("USER_ID");
				custName = rs.getString("CUST_NAME");
				balance = rs.getDouble("BALANCE");
				numOfUsers++;
			} 
			
			if(numOfUsers == 1) {
				log.info("Welcome back " + returnUser);
				a.setID(userID);
				a.setName(custName);
				a.setUserName(returnUser);
				a.setPassword(returnPass);
				a.setBalance(balance);
				transactions();
			} else {
				log.info("These credentials do not match any in our system. Please try again.");
				userID = 0;
				custName = "";
				balance = 0.0;
				logIn();
			}
		} catch (IOException | SQLException e) {
			log.error(e.getMessage());
		}		
	}
	
	public void transactions() {
		
		log.info("What can we do for you today?");
		log.info("Withdraw\nDeposit\nCheck Balance\nTransfer\nEdit Account\nLog Out");
		String option = scan.nextLine();
		double balance = a.getBalance();;
		log.info("");
		
		switch(option.toLowerCase()) {
		case "withdraw" :
		case "withdrawal" :
		case "withdrawals" :
		case "1" :
			withdraw();
			break;
		case "deposit" :
		case "2" :
			deposit();
			break;
		case "check balance" :
		case "balance" :
		case "3" :
			log.info("Your current balance is: " + NumberFormat.getCurrencyInstance().format(balance));
			log.info("");
			transactions();
			break;
		case "transfer" :
		case "4" :
			transfer();
			break;
		case "edit account":
		case "edit":
		case "5":
			editAccount();
			break;
		case "log out" :
		case "logout" :
		case "6" :
			mainMenu();
			break;
		default:
			log.info("Please enter a valid option");
			log.info("");
			transactions();
		}
	}
	
	public void withdraw() {
		log.info("How much would you like to withdraw? (Type 'back' to go back)");
		String update = "UPDATE BANK " +
				"SET BALANCE =  ? " +
				"WHERE USER_ID = ?";
		String entry = scan.nextLine();
		if(entry.length() > 0) {
			if(entry.equalsIgnoreCase("back")) {
				transactions();
			} else {
				try {
					double amount = Double.parseDouble(entry);
					double balance = a.getBalance();
					if(amount > 0) {
						if((amount * 100) % 1 != 0 ) {
							log.info(MORE_DECIMALS);
							withdraw();
						} else {
							try(Connection con = ConnectionUtil.getConnection();
									PreparedStatement ps = con.prepareStatement(update)) {
						
								if(amount > balance) {
									log.info("Your withdrawal requst exceeds your current balance.");
									log.info("");
									withdraw();
								} else {
									a.setBalance(balance - amount);
									ps.setDouble(1, a.getBalance());
									ps.setInt(2, a.getID());
									ps.executeUpdate();
									log.info("You withdrew " + NumberFormat.getCurrencyInstance().format(amount));
									log.info("Bringing your balance to " + NumberFormat.getCurrencyInstance().format(a.getBalance()));
									log.info("");
									transactions();
								}
							} catch (IOException | SQLException e) {
								log.error(e.getMessage());
							}
						}
					} else {
						log.info(INVALID_AMOUNT);
						withdraw();
					}
				} catch (NumberFormatException e) {
					log.info(INVALID_AMOUNT);
					withdraw();
				}
			}
		} else {
			log.info("The amount you wish to withdraw must be greater than 0");
		}
	}
	
	public void deposit() {
		log.info("How much would you like to deposit? (Type 'back' to go back)");
		String sql = "UPDATE BANK " +
				"SET BALANCE = ? " +
				"WHERE USER_ID = ?";
		String entry = scan.nextLine();
		double balance = a.getBalance();
		if(entry.equals("back")) {
			transactions();
		} else {
			try {
				double amount = Double.parseDouble(entry);
				if(amount > 0) {
					if((amount * 100) % 1 != 0) {
						log.info(MORE_DECIMALS);
						deposit();
					} else {
						try(Connection con = ConnectionUtil.getConnection();
								PreparedStatement ps = con.prepareStatement(sql);) {
							a.setBalance(balance + amount);
							ps.setDouble(1, a.getBalance());
							ps.setInt(2, a.getID());
							ps.executeUpdate();
							log.info("You deposited " + NumberFormat.getCurrencyInstance().format(amount));
							log.info("Bringing your balance to " + NumberFormat.getCurrencyInstance().format(a.getBalance()));
							log.info("");
							transactions();
						} catch(SQLException | IOException e) {
							log.error(e.getMessage());
						} 
					}
				} else {
					log.info(INVALID_AMOUNT);
					deposit();
				}
			} catch(NumberFormatException e) {
						log.info(INVALID_AMOUNT);
						deposit();
			}
			
		}
	}
	
	public void editAccount() {
		log.info("What part of your account would you like to edit?\nName\nUsername\nPassword\nDelete Account\nGo Back ");
		String choice = scan.nextLine();
		switch(choice.toLowerCase()) {
		case "name":
		case "1":
			editName();
			break;
		case "username":
		case "user name":
		case "2":
			editUsername();
			break;
		case "password":
		case "pass word":
		case "3":
			editPassword();
			break;
		case "delete account":
		case "delete":
		case "4":
			deleteAccount();
			break;
		case "go back":
		case "back":
		case "5":
			transactions();
			break;
		default:
			log.info("Please enter a valid response");
			editAccount();
		}
	}
	
	public void editName() {
		log.info("You chose to change your name in our system.\n What would you like to change it to?\n(To go back, please enter 'back')");
		String newName = scan.nextLine();
		if(newName.equals("back")) {
			editAccount();
		} else {
			if(newName.equals(a.getName())) {
				log.info("Your new name cannot be the same as your old one.");
				editName();
			} else {
				a.setName(newName);
				try(Connection con = ConnectionUtil.getConnection();
						CallableStatement cs = con.prepareCall("{call UPDATE_ACCOUNT_NAME(?, ?)}")) {
					cs.setInt(1, a.getID());
					cs.setString(2, a.getName());
					cs.executeUpdate();
					log.info("Your name has been updated!");
					editAccount();
				} catch(IOException | SQLException e) {
					log.error(e.getMessage());
				}
			}
		}
	}
	
	public void editUsername() {
		log.info("You chose to change your username in our system.\n What would you like to change it to?\n(To go back, please enter 'back')");
		String newUsername = scan.nextLine();
		if(newUsername.equalsIgnoreCase("back")) {
			editAccount();
		} else {
			if(newUsername.equals(a.getUserName())) {
				log.info("Your new username cannot be the same as your old one.");
				editUsername();
			} else {
				a.setUserName(newUsername);
				try(Connection con = ConnectionUtil.getConnection();
						CallableStatement cs = con.prepareCall("{call UPDATE_ACCOUNT_USERNAME(?, ?)}");) {
					cs.setInt(1, a.getID());
					cs.setString(2, a.getUserName());
					cs.executeUpdate();
					log.info("Your username has been updated!");
					editAccount();
				} catch (IOException | SQLException e) {
					log.error(e.getMessage());
				}
			}
		}
	}
	
	public void editPassword() {
		log.info("You chose to change your password in our system.\n What would you like to change it to?\n(To go back, please enter 'back')");
		String newPassword = scan.nextLine();
		if(newPassword.equalsIgnoreCase("back")) {
			editAccount();
		} else {
			if(newPassword.equals(a.getPassword())) {
				log.info("Your new password cannot be the same as your old one.");
				editPassword();
			} else {
				a.setPassword(newPassword);
				try(Connection con = ConnectionUtil.getConnection();
						CallableStatement cs = con.prepareCall("{call UPDATE_ACCOUNT_PASSWORD(?, ?)}");) {
					cs.setInt(1, a.getID());
					cs.setString(2, a.getPassword());
					cs.executeUpdate();
					log.info("Your password has been updated!");
					editAccount();
				} catch (IOException | SQLException e) {
					log.error(e.getMessage());
				}
			}
		}
	}
	
	public void deleteAccount() {
		log.info("Are you sure you want to delete your account?\nThere is no going back unless you enter 'no'. Otherwise, enter 'yes'.");
		String choice = scan.nextLine();
		if(choice.equalsIgnoreCase("no")) {
			editAccount();
		} else if(choice.equalsIgnoreCase("yes")){
			try(Connection con = ConnectionUtil.getConnection();
					CallableStatement cs = con.prepareCall("{call DELETE_ACCOUNT(?)}");) {
				cs.setInt(1,a.getID());
				cs.executeUpdate();
				log.info("Your account has been deleted... We hate to see you go :(");
				mainMenu();
			} catch(SQLException | IOException e) {
				log.error(e.getMessage());
			}
		}
		
	}
	
	public void transfer() {
		String sql = "SELECT USER_ID, USERNAME FROM BANK";
		ResultSet rs = null;
		String tempName = "";
		log.info("You have selected to transfer. Who would you like to transfer to?\nPlease enter their username. (To go back, please enter 'back')");
		String transferUser = scan.nextLine();
		double amount = 0.0;
		if(transferUser.equalsIgnoreCase("back")) {
			transactions();
		} else {
			log.info("How much would you like to transfer? (To go back, please enter 'back')");
			String transferAmount = scan.nextLine();
			if(transferAmount.equals("back")) {
				transfer();
			} else {
				try {
					amount = Double.parseDouble(transferAmount);
					if(amount <= a.getBalance() ) {
						if((amount * 100) % 1 == 0) {
							if(amount <= 0) {
								try(Connection con = ConnectionUtil.getConnection();
										Statement s = con.createStatement();
										PreparedStatement ps = con.prepareStatement("{call  TRANSFER(?, ?, ?)}");) {
									rs = s.executeQuery(sql);
									while(rs.next()) {
										tempName = rs.getString("USERNAME");
										if(transferUser.equals(tempName)) {
											break;
										}
									}
									if(transferUser.equals(tempName)) {
										ps.setInt(1, a.getID());
										ps.setString(2, tempName);
										ps.setDouble(3, amount);
										ps.executeUpdate();
										log.info("Transfer complete! You transferred " + NumberFormat.getCurrencyInstance().format(amount) + " to " + tempName);
										a.setBalance(a.getBalance() - amount);
										transactions();
									} else {
										log.info("No username in our system matches the one you entered");
										transfer();
									}
								} catch(SQLException | IOException e) {
									log.error(e.getMessage());
								}
							} else {
								log.info("Your amount must be greater than zero");
								transfer();
							}
						} else {
							log.info("Your amount cannot exceed two decimal places");
							transfer();
						}
					} else {
						log.info("Your transfer amount exceeds your current balance.");
						transfer();
					}
				} catch(NumberFormatException e) {
					log.info(INVALID_AMOUNT);
					transfer();
				}
			}
		}
	}
}
