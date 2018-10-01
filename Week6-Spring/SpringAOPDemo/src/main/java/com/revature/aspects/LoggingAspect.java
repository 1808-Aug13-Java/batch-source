package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static Logger log = Logger.getRootLogger();
	
	@AfterReturning("within(com.revature.beans.*)")
	public void logAfter(JoinPoint jp) {
		log.info(jp.getSignature()+" was called");
	}
	
	@AfterThrowing("within(com.revature.beans.*)")
	public void logAfterThrowing(JoinPoint jp) {
		log.error(jp.getSignature()+" threw an exception");
	}
	
	@AfterReturning("execution(* bearHibernates())")
	public void hibernateAttempt() {
		log.info("Bear is trying to hibernate");
	}
	
}
