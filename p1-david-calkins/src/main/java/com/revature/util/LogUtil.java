package com.revature.util;

import org.apache.log4j.Logger;


public class LogUtil {
	
	private static Logger log = Logger.getRootLogger();
	
	// Prevents instantiation
	private LogUtil() {}
	
	public static void logInfo(Object obj) {
		log.info(obj);
	}
	
	
	public static void logDebug(Object obj) {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
		StringBuilder sb = new StringBuilder();
		sb.append(ste.getClassName());
		sb.append(", line ");
		sb.append(ste.getLineNumber());
		sb.append(": ");
		
		// Account for null objects
		if (obj == null) {
			sb.append(""+null);
		} else {
			sb.append(obj.toString());
		}
		
		log.debug(sb);
	}
	
	
	
} // end of LogUtil
