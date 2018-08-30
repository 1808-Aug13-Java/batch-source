package com.revature.engine;

import org.apache.log4j.Logger;

public class Driver {
	
	static final Logger logger = Logger.getLogger(Driver.class);
//	BigDecimal
	public static void main(String[] args) {
		Engine engine = Engine.getEngine();
		engine.start();
	}

}
