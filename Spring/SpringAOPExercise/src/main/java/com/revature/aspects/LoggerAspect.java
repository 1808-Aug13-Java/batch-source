package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
	private Logger l = Logger.getRootLogger();
	
	@AfterReturning(pointcut = "execution(* add(..)) && args(x, y)", returning = "returnValue")
	public void addLogger(JoinPoint jp, int x, int y, int returnValue) {
		System.out.println(x + " + " + y + " = " + returnValue);
		l.info(x + " + " + y + " = " + returnValue);
	}
	
	@AfterReturning(pointcut = "execution(* subtract(..)) && args(x, y)", returning = "returnValue")
	public void subtractLogger(JoinPoint jp, int x, int y, int returnValue) {
		System.out.println(x + " - " + y + " = " + returnValue);
		l.info(x + " - " + y + " = " + returnValue);
	}
	
	@AfterReturning(pointcut = "execution(* multiply(..)) && args(x, y)", returning = "returnValue")
	public void multiplyLogger(JoinPoint jp, int x, int y, int returnValue) {
		System.out.println(x + " * " + y + " = " + returnValue);
		l.info(x + " * " + y + " = " + returnValue);
	}
	
	@Around("execution(* divide(..)) && args(x, y)")
	public void divideAroundLogger(ProceedingJoinPoint pjp, Double x, Double y) throws Throwable {
		if (y != 0.0) {
			Double returnValue = (Double) pjp.proceed();
			System.out.println(x + " / " + y + " = " + returnValue);
			l.info(x + " / " + y + " = " + returnValue);
		} else {
			l.error("Error: DivideByZero");
		}
	}
	
//	@AfterReturning(pointcut = "execution(* divide(..)) && args(x, y)", returning = "returnValue")
//	public void divideAfterLogger(JoinPoint jp, Double x, Double y, Double returnValue) {
//		System.out.println(x + " / " + y + " = " + returnValue);
////		l.info(x + " / " + y + " = " + returnValue);
//	}
}
