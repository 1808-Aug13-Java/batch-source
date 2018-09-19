package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect {

	private static Logger log = Logger.getRootLogger();
	
	@AfterReturning("within(com.revature.beans.*)")
	public void logAfter() {
		log.info("method was called");
	}
	
	@AfterThrowing("within(com.revature.beans.*)")
	public void logAfterThrowning() {
		log.info("exception was thrown");
	}
}
