package com.revature.bank;

public class BankDriver {

	public static void main(String[] args) {
		Commands com = new Commands();
		if(!com.isThereAUser())
			com.newAccount();
		while(!com.isLoggedIn())
				com.logIn();
		while(com.isLoggedIn()) {
			com.menu();
		}
	}
}
