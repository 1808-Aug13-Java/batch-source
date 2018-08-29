package com.revature.bankApp;

import com.revature.dao.*;

public class Driver {

	public static void main(String[] args) {
		
		BankDaoImp active = new BankDaoImp();
		active.oldAccounts();
		active.menu();

	}

}
