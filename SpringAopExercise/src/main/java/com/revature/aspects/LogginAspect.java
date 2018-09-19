package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogginAspect {
    static Logger logger = Logger.getRootLogger();

    @After("within(com.revature.beans.*)")
    public void logMethodSignatureOfOperation(JoinPoint jp) {
    	logger.info(jp.getSignature());
    }
    
    @AfterReturning(pointcut = "execution(* add(*, *))", returning = "result")
    public void logAdd(JoinPoint jp, Object result) {
    	Object[] args = jp.getArgs();
    	logger.info(args[0] + " + " + args[1] + " = " + result);
    }
    
    @AfterReturning(pointcut = "execution(* subtract(*, *))", returning = "result")
    public void logSubtract(JoinPoint jp, Object result) {
    	Object[] args = jp.getArgs();
    	logger.info(args[0] + " - " + args[1] + " = " + result);
    }
    
    @AfterReturning(pointcut = "execution(* multiply(*, *))", returning = "result")
    public void logMultiply(JoinPoint jp, Object result) {
    	Object[] args = jp.getArgs();
    	logger.info(args[0] + " * " + args[1] + " = " + result);
    }
    
    @AfterReturning(pointcut = "execution(* divide(*, *))", returning = "result")
    public void logDivide(JoinPoint jp, Object result) {
    	Object[] args = jp.getArgs();
    	logger.info(args[0] + " / " + args[1] + " = " + result);
    }
    
    @Around("execution(* divide(*, *))")
    public void stopZeroDivision(ProceedingJoinPoint pjp) {
    	Object[] args = pjp.getArgs();
    	System.out.println(args[1]);
    	
    	if(args[1].toString().equals("0")) {
    		System.out.println("division by zero attempted");
    		logger.info("division by zero attempted");
    	}
    		
    	
    }
    
    
}
