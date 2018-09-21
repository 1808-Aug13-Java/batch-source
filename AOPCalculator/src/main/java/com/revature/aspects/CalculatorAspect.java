package com.revature.aspects;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorAspect {
	
private static Logger log = Logger.getRootLogger();
	
	@AfterReturning("within(com.revature.beans.Calculator)")
	public void logAfter(JoinPoint jp) {
		log.info(jp.getSignature().getName() + " was called");
	}
	
	@Around("execution(* addNums(..))")
	public int addOutput(ProceedingJoinPoint pjp) throws Throwable{	
			Object[] args = pjp.getArgs();
			String s1 = String.valueOf(args[0]);
			String s2 = String.valueOf(args[1]);
			String result = String.valueOf(pjp.proceed());
			log.info("Method called: " + pjp.getSignature().getName() + " Result: " + s1 + "+" + s2 + "=" + result);
		return 0;
	} 
	
	@Around("execution(* subtractNums(..))")
	public int subtractOutput(ProceedingJoinPoint pjp) throws Throwable{	
			Object[] args = pjp.getArgs();
			String s1 = String.valueOf(args[0]);
			String s2 = String.valueOf(args[1]);
			String result = String.valueOf(pjp.proceed());
			log.info("Method called: " + pjp.getSignature().getName() + " Result: " + s1 + "-" + s2 + "=" + result);
		return 0;
	}
		
		@Around("execution(* divideNums(..))")
		public int divideOutput(ProceedingJoinPoint pjp) throws Throwable{	
				Object[] args = pjp.getArgs();
				String s1 = String.valueOf(args[0]);
				String s2 = String.valueOf(args[1]);
				if(checkZero(pjp)){
					log.info("Error: Attempted division by 0");
				};
			return 0;
	} 
		
		@Around("execution(* multiplyNums(..))")
		public int multiplyOutput(ProceedingJoinPoint pjp) throws Throwable{	
				Object[] args = pjp.getArgs();
				String s1 = String.valueOf(args[0]);
				String s2 = String.valueOf(args[1]);
				String result = String.valueOf(pjp.proceed());
				log.info("Method called: " + pjp.getSignature().getName() + " Result: " + s1 + "*" + s2 + "=" + result);
			return 0;
		}
		
		public boolean checkZero(ProceedingJoinPoint pjp) throws Throwable {
			Object[] args = pjp.getArgs();
			int num1 = Integer.parseInt(String.valueOf(args[0]));
			int num2 = Integer.parseInt(String.valueOf(args[1]));
			if(num2 == 0) {
				return true;
			} else {
				int result = (int) pjp.proceed();
				log.info("Method called: " + pjp.getSignature().getName() + " Result: " + num1 + "/" + num2 + "=" + result);
				return false;
			}
		}
}
