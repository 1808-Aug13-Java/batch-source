package com.revature.bank;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.CustomerDAO;
import com.revature.dao.CustomerDAOImpl;

//import com.revature.io.BankFileReader;
//import com.revature.io.BankFileWriter;

public class Customer {
	private int customerId;
 	private String customerName;
	private String password;
	//	private ArrayList<String> profile = new ArrayList<>();
	protected static final String PATH = "./User.txt";
	//	private BankFileWriter bfw = new BankFileWriter();
//	private BankFileReader bfr = new BankFileReader();
	private static Logger log = Logger.getRootLogger();
	
	public Customer() {
		super();
	}
	
	public Customer(String customerName, String password) {
		super();
		this.customerName = customerName;
		this.password = password;
//		this.balance = 0.00;
//		this.profile.clear();
//		this.profile.add(customerName);
//		this.profile.add(password);
//		this.profile.add(balance);
//		this.checkIfCustomerExists = bfr.readLines(PATH);
//		if (this.checkIfCustomerExists[0] == "") {
//			this.writeCustomer();
//		}
	}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerID(int id) {
		this.customerId = id;
	}
	
//	public void writeCustomer() {
////		bfw.writeFile(this.profile, PATH);
//	}
	public boolean validateIdentity(String customerName, String password) {
		CustomerDAO cdi = new CustomerDAOImpl();
		boolean isCustomerFound = false;
		List<Customer> allCustomers = cdi.getCustomers();
		log.info(allCustomers);
		for (Customer c: allCustomers) {
//			log.info(c);
//			log.info(c.getCustomerName());
//			log.info(c.getPassword());
//			log.info(customerName);
//			log.info(password);
			if (c.getCustomerName().equals(customerName) && c.getPassword().equals(password)) {
				log.info("User credentials validated.");
				return true;
			}
		}
//		String[] authorizedCustomer = bfr.readLines(PATH);
//		return authorizedCustomer[0].equals(this.customerName) && authorizedCustomer[1].equals(this.password);
		return isCustomerFound;
	}
	
	public String getCustomerName() {
		return this.customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
//	public double getBalance() {
//		return this.balance;
//	}
//	
//	public void setBalance(double balance) {
//		this.balance = balance;
////		this.profile.set(2, balance);
////		bfw.writeFile(this.profile, PATH);
//	}
 	public void performTransaction(int customerId, String transactionType, double transactionAmount) {
 		
		switch (transactionType) {
		case "deposit":
		case "1":
			if (transactionAmount <= 0.00) {
				log.error("Error: cannot deposit a zero or negative amount.");
			} else {
				AccountDAO adi = new AccountDAOImpl();
				Account a = adi.getAccountByCustomerId(customerId);
				adi.deposit(a.getAccountId(), transactionAmount);
				a = adi.getAccountByCustomerId(customerId);
				log.info("Deposit completed. Your new balance is " + a.getBalance(a.getAccountId()));
			}
// 			bfw.writeFile(this.profile, PATH);
			break;
		case "withdrawal":
		case "2":
			AccountDAO adi = new AccountDAOImpl();
			Account a = adi.getAccountByCustomerId(customerId);
			double balance = a.getBalance(a.getAccountId());
			if (balance >= transactionAmount) {
//				balanceStr = Double.toString(balance);		
//	 			this.profile.set(2, balanceStr);
//	 			bfw.writeFile(this.profile, PATH);
	 			adi.withdraw(a.getAccountId(), transactionAmount);
	 			log.info("Withdrawal completed. Your new balance is " + a.getBalance(a.getAccountId()));
			} else {
				log.error("Error: insufficient funds for withdrawal. No changes made.");
			}
			break;
		default:
			log.info("Invalid transaction type.");
		}
	}
 	@Override
	public String toString() {
		return "customerName=" + customerName
				+ ", password=" + password + "]";
	}
}