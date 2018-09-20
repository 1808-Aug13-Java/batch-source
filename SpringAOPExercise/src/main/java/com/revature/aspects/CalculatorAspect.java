package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorAspect {
	
	private static final Logger log = Logger.getRootLogger();
	
	@Around("execution(* divide(..)) && within(com.revature.math.Calculator)")
	public int preventDivideByZero(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("Before Aspect Divide");
		Object[] args = jp.getArgs();
		
		if (jp.getArgs().length >= 2 && (Integer) jp.getArgs()[1] == 0) {
			System.out.println("Cannot divide by 0");
			log.error("Cannot divide by 0");
			throw new ArithmeticException("Cannot divide by 0");
		} else {
			return (int) jp.proceed();
		}
	}
	
	
	@Around("within(com.revature.math.Calculator) && execution(* add(..)) && args(int,int)")
	public int beforeAdd(ProceedingJoinPoint jp) throws Throwable {
		return beforeMath(jp, "+");
	}
	
	@Around("within(com.revature.math.Calculator) && execution(* subtract(..)) && args(int,int)")
	public int beforeSubtract(ProceedingJoinPoint jp) throws Throwable {
		return beforeMath(jp, "-");
	}
	
	@Around("within(com.revature.math.Calculator) && execution(* multiply(..)) && args(int,int)")
	public int beforeMultiply(ProceedingJoinPoint jp) throws Throwable {
		return beforeMath(jp, "*");
	}
	
	@Around("within(com.revature.math.Calculator) && execution(* divide(..)) && args(int,int)")
	public int beforeDivide(ProceedingJoinPoint jp) throws Throwable {
		return beforeMath(jp, "/");
	}
	
	@Around("within(com.revature.math.Calculator) && execution(* remainder(..)) && args(int,int)")
	public int beforeRemainder(ProceedingJoinPoint jp) throws Throwable {
		return beforeMath(jp, "%");
	}
	
	@Around("within(com.revature.math.Calculator) && execution(* modulo(..)) && args(int,int)")
	public int beforeModulo(ProceedingJoinPoint jp) throws Throwable {
		return beforeMath(jp, "%/");
	}
	
	
	public int beforeMath(ProceedingJoinPoint jp, String op) throws Throwable {
		System.out.println("Before Aspect Math");
		Object[] args = jp.getArgs();
		String mathString = "";
		
		
		int returnVal = (int) jp.proceed();
		
		if (args.length >= 2) {
			mathString = args[0] + op + args[1] + "=" + returnVal;
			System.out.println(mathString);
			log.info(mathString);
		}
		
		return returnVal;
	}
}
