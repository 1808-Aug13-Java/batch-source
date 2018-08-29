package com.revature.banking;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bankingdao.BankCustomerDaoImpl;
import com.revature.bankingdao.BankingCustomerDao;
import com.revature.bankingmodel.BankCustomer;

//Deals with all banking operations 
public class Banker {
	
	private static Logger log = Logger.getRootLogger();
	private static BankingCustomerDao dataObject = new BankCustomerDaoImpl();
	
	public void deposit(BankCustomer bc)
	{
		//Deposit money into account balance
		Scanner sc = new Scanner(System.in);
		float amount = 0;
		log.info("How much would you like to deposit?");
		try {
			amount = sc.nextFloat();
			//Check for negative input
			if(amount < 0)
			{
				throw new InputMismatchException();
			}
				dataObject.updateBalance(bc.getId(), amount);
				log.info("New Balance is: " + dataObject.getBankCustomerById(bc.getId()).getBalance());
		}catch(InputMismatchException e) {
			log.info("Please enter a valid dollar amount! NO NEGATIVES!");
			deposit(bc);
		}
	}
	
	public void withdraw(BankCustomer bc)
	{
		try {
		Scanner sc = new Scanner(System.in);
		float amount = 0;
		log.info("How much would you like to withdraw?");
		amount = sc.nextFloat();
		//Check for negative input
		if(amount < 0)
		{
			throw new InputMismatchException();
		} 
		else if(bc.getBalance() - amount <= 0)
		{
			log.info("Insufficient enough funds!");
		}
		else
		{
			dataObject.updateBalance(bc.getId(), -1 * amount);
			log.info("Remaining Balance is: " + dataObject.getBankCustomerById(bc.getId()).getBalance());
		}
	}catch(InputMismatchException e) {
		log.info("Please enter a valid dollar amount! NO NEGATIVES!");
		withdraw(bc);
	}
	}
	
	public void showBalance(BankCustomer bc)
	{
		//Display the accounts current balance 
		log.info("Your current Balance is: $" + bc.getBalance());
	}

}
