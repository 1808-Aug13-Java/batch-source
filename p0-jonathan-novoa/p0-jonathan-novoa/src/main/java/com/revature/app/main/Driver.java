package com.revature.app.main;




import com.revature.app.dao.AccountDaoImpl;

public class Driver {


	public static void main(String[] args) {

		AccountDaoImpl running= new AccountDaoImpl();
		running.getAccounts();//need to have the username at the ready
		running.screen();
	}
}
