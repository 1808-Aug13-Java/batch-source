package com.revature.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.util.ConnectionUtil;

public class Driver {
		

		static final Logger logger = Logger.getLogger(Driver.class);
		
		public static void main(String[] args) {
			BankCore bankCore = BankCore.setSingleton();
			bankCore.start();
		}
	
	
	
}
