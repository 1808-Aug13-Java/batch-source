package com.revature.aop;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	private static Logger log = Logger.getRootLogger();
	
	@Around("execution(* com.revature.main.CalculatorService.add(..))")
	public int logAdd(ProceedingJoinPoint joinPoint) throws Throwable {
		
		String args = Arrays.toString(joinPoint.getArgs());
		String[] processed = args.split(",");
		int i = Integer.parseInt(processed[0].replaceAll("\\[", "").trim());
		int k = Integer.parseInt(processed[1].replaceAll("\\]", "").trim());
		log.info("Method: " + joinPoint.getSignature().getName() + " called on " + i + " + " +k +" = " + joinPoint.proceed());
		log.info("\n");
		return (int) joinPoint.proceed();
		
	}
	
	@Around("execution(* com.revature.main.CalculatorService.subtract(..))")
	public int logSubtract(ProceedingJoinPoint joinPoint) throws Throwable {
		
		String args = Arrays.toString(joinPoint.getArgs());
		String[] processed = args.split(",");
		int i = Integer.parseInt(processed[0].replaceAll("\\[", "").trim());
		int k = Integer.parseInt(processed[1].replaceAll("\\]", "").trim());
		log.info("Method: " + joinPoint.getSignature().getName() + " called on " + i + " - " +k +" = " + joinPoint.proceed());
		log.info("\n");
		return (int) joinPoint.proceed();
		
	}
	
	@Around("execution(* com.revature.main.CalculatorService.divide(..))")
	public int logDivide(ProceedingJoinPoint joinPoint) throws Throwable {
		
		String args = Arrays.toString(joinPoint.getArgs());
		String[] processed = args.split(",");
		int i = Integer.parseInt(processed[0].replaceAll("\\[", "").trim());
		int k = Integer.parseInt(processed[1].replaceAll("\\]", "").trim());
		
		if(i == 0) {
			log.error("ERROR: "+ i +"/" +k +" is undefined :Can not divide by zero!!");
			log.info("Try again");
			log.info("\n");
			return 0;
		}else {
		log.info("Method: " + joinPoint.getSignature().getName() + " called on " + i + "/" +k +" = " + joinPoint.proceed());
		log.info("\n");
		return (int) joinPoint.proceed();
		}
		
	}

}
