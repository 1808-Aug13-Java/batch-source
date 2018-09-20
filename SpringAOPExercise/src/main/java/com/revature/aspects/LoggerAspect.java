package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
	private Logger log = Logger.getRootLogger();
	
	@AfterReturning(pointcut = "execution(* add(..)) && args(a, b)", returning = "total")
	public void add(JoinPoint jp, int a, int b, int total) {
		log.info(a + "+" + b + " = " + total);
	}
	
	@AfterReturning(pointcut = "execution(* subtract(..)) && args(a, b)", returning = "total")
	public void subtract(JoinPoint jp, int a, int b, int total) {
		log.info(a + "-" + b + " = " + total);
	}
	
	@AfterReturning(pointcut = "execution(* multiply(..)) && args(a, b)", returning = "total")
	public void multiply(JoinPoint jp, int a, int b, int total) {
		log.info(a + "*" + b + " = " + total);
	}
	
	@AfterReturning(pointcut = "execution(* divide(..)) && args(a, b)", returning = "total")
	public void divide(JoinPoint jp, int a, int b, int total) {
		log.info(a + "/" + b + " = " + total);
	}
}
