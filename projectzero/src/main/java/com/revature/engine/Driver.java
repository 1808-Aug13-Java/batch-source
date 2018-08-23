package com.revature.engine;
import org.apache.log4j.Logger;

import com.revature.*;


public class Driver {
	
	final static Logger logger = Logger.getLogger(Driver.class);
	
	public static void main(String[] args) {
		
		Engine engine = new Engine("jdbc:oracle:thin@chinook-db.ced7fvnhpfjw.us"
				+ "-west-2.rds.amazonaws.com:1521:orcl"
				, "crandon", "Mymomis2!");
		engine.start();
	}

}
