package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
	
	private static Logger log = Logger.getRootLogger();
	
	@AfterReturning("within(com.revature.beans.*)")
	public void performedOperation(JoinPoint jp) {
		log.info(jp.getSignature()+ " was executed.");	
	}
	
	@AfterThrowing("within(com.revature.beans.*)")
	public void logAfterThrowing(JoinPoint jp) {
		log.error(jp.getSignature()+" threw a divide by zero exception.");
	}
	
	

}
