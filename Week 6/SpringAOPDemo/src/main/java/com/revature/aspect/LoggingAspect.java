package com.revature.aspect;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	public static Logger log= Logger.getRootLogger();
	
	
	@AfterReturning("within(com.revature.beans.*)")
	public void logAfter(JoinPoint jp) {
		log.info(jp.getSignature()+ "method was called");
		
	}
	@AfterThrowing("within(com.revature.beans.*)")
	public void logAfterThrowing( JoinPoint jp) {
		log.error(jp.getSignature()+ "exception was thrown");
	}
	@AfterReturning("execution (* bearHibernates())")
	public void hibernateAttempt() {
		log.info("bear is trying to hibernate");
	}
	
	
	
}
