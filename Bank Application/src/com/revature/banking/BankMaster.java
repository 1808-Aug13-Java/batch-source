package com.revature.banking;

import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;

import com.revature.bankingdao.BankCustomerDaoImpl;
import com.revature.bankingdao.BankingCustomerDao;

public class BankMaster {
	public static void main(String[] args) {
		BasicConfigurator.configure();
		MenuObject menu = new MenuObject();
		menu.login();
	}
}
