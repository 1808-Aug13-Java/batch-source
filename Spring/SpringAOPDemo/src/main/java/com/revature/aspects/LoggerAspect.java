package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
	private Logger l = Logger.getRootLogger();
	
	@AfterReturning(pointcut = "execution(* add(..)) && args(x, y)", returning = "returnValue")
	public void addLogger(JoinPoint jp, int x, int y, int returnValue) {
		System.out.println(x + " + " + y + " = " + returnValue);
	}
	
	@AfterReturning(pointcut = "execution(* subtract(..)) && args(x, y)", returning = "returnValue")
	public void subtractLogger(JoinPoint jp, int x, int y, int returnValue) {
		System.out.println(x + " - " + y + " = " + returnValue);
	}
	
	@AfterReturning(pointcut = "execution(* multiply(..)) && args(x, y)", returning = "returnValue")
	public void multiplyLogger(JoinPoint jp, int x, int y, int returnValue) {
		System.out.println(x + " * " + y + " = " + returnValue);
	}
	
	
}
