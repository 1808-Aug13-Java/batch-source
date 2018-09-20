package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


import com.revature.beans.Calculator;

@Aspect
@Component
public class CalculatorAspect {

private static Logger log = Logger.getRootLogger();
	
	@AfterReturning("within(com.revature.beans.*)")
	public void logAfter(JoinPoint jp) {
		//jp.getSignature will tell us what method was called
		log.info(jp.getSignature()+" method was called");
	}
	@AfterReturning(
	pointcut="execution(* add*(..)) && args(a,b)", 
	returning ="res")
	public void addStatus(int a, int b, int res) throws Throwable{
		
		log.info("add is called "+a+" + "+b+" = " + res);
	
	}
	@AfterReturning(
			pointcut="execution(* subtract*(..)) && args(a,b)", 
			returning ="res")
			public void subStatus(int a, int b, int res) throws Throwable{
				
				log.info("add is called "+a+" - "+b+" = " + res);
			
			}
	@AfterReturning(
			pointcut="execution(* multiply*(..)) && args(a,b)", 
			returning ="res")
			public void multStatus(int a, int b, int res) throws Throwable {
				log.info("add is called "+a+" * "+b+" = " + res);
			
			}
	
	
	

	@Around("execution(* divide*(..))")
		public double divCheckZero(ProceedingJoinPoint pjp) throws Throwable {
		Calculator c = (Calculator) pjp.getTarget();
		//System.out.println("hello from divcheckzero");
				Object[] params = pjp.getArgs();
		
				double a = (double) params[0];
				double b = (double) params[1];
				System.out.println( "a = " + a + " b = " + b);
				if(b == 0) {
					log.info("cannot divide by zero!");
					System.out.println("cannot divide by zero!");
					
				}
				else {
					System.out.println("proceeding with divide");
					pjp.proceed();
					
				}
				return 0;
				
			
			}
	
	@AfterReturning(
			pointcut="execution(* divide*(..)) && args(a,b)", 
			returning ="res")
			public void divStatus(double a, double b, double res) throws Throwable{
				System.out.println("hello from divStatus");
				System.out.println("divide is called "+a+" / "+b+" = " + res);
				log.info("divide is called "+a+" / "+b+" = " + res);
			}
	
	


	
}
