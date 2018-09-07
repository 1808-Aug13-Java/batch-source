package com.revature.util;

import org.apache.log4j.Logger;

public class LoggerSingleton {
	
	private static Logger log;
	private LoggerSingleton() {}    
	
	public static Logger getLogger()
	{
		if(log != null)
			return log;
		else // make a logger
		{
			log = Logger.getRootLogger();
			return log;
		}
	}
	
	
}
