package com.revature.operations;

import java.util.Scanner;

import com.revature.accounts.Account;
import com.revature.utils.InputScanner;

public class AccountDeposit implements AccountOperations{

	@Override
	public void performOperation(Account account) {
		// TODO Auto-generated method stub
		double balance = account.getBalance();
		Scanner sc = new Scanner(System.in);
		double userInputBalance = sc.nextDouble();
        System.out.println("Current balance: " + balance);
        System.out.println("Please insert the sum of money you wish to deposit in account: ");

        balance = balance + Double.parseDouble(InputScanner.getInstance().readInput());

        account.setBalance(balance);

        System.out.println("New account balance: " + account.getBalance());
	}
	
}
