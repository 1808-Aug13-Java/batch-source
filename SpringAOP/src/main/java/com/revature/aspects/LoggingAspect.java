package com.revature.aspects;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import com.revature.beans.Calculater;
@Aspect
@Component
public class LoggingAspect {
	
	private static Logger log = LogManager.getLogger(LoggingAspect.class.getName());
	
	
  @AfterReturning("execution(* *Operation(..))") 
  public void operate(JoinPoint jp) throws Throwable {
    Calculater c = (Calculater) jp.getTarget();
    log.info(jp.getArgs()[0] + " " + c.getOp() + " "  + jp.getArgs()[1] + " = " + c.getResult());
  }

	@AfterThrowing(pointcut = "within(com.revature.beans.*)")
	public void logAfterException(JoinPoint jp) {
		log.error(jp.getSignature());
	}
//  	
//	@Before("execution(* bearHibernates())")
//	public void hibernateAttempt() {
//		log.info("bear is trying to hibernate");
//	}
//	
//  @AfterReturning("execution(* *Operation(..))") 
//  public void calcuLog() {
//    log.info("calculating");
//  }
	/*
	 * trace
	 * debug
	 * info
	 * warn
	 * error
	 * fatal
	 * 
	 */

}
