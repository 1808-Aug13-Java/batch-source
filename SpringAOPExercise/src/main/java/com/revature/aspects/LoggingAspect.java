package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.revature.beans.Calculator;

@Aspect
@Component
public class LoggingAspect {
	/* Note to self:
	 * If you use @Before, @AfterThrowing, @AfterReturning and @After use Joinpoint.
	 * If you use @Around use Proceedingjoinpoint .They are just interface.
	 * Proceedingjoinpoint is extending Joinpoint 
	 */

	private static Logger log = Logger.getRootLogger();
	
//	@AfterReturning("within(com.revature.beans.*)")
//	public void logAfter(JoinPoint jp) {
//		log.info(jp.getSignature()+" was called");
//	}
	
	@AfterThrowing("within(com.revature.beans.*)")
	public void logAfterThrowing(JoinPoint jp) {
		log.error(jp.getSignature()+" threw an IllegalArgumentException");
	}
	
	// Can only return value from @Around advice
	// execution matches method, within matches type
	@Around("execution(* add(..))") 
	public Object addThings(ProceedingJoinPoint pjp) throws Throwable {
		//Calculator c = (Calculator) pjp.getTarget();
		log.info(pjp.getSignature() + "is now adding");
		try {
			log.info("The addition result: " + pjp.proceed());
		} catch (Throwable e) {
			log.error(pjp.getSignature() + "threw an exception");
		}
		return pjp.proceed();
	}
	
	@AfterReturning(pointcut = "execution(* add(..)) && args(a,b))", returning = "total") 
	public double addThings(JoinPoint jp, double a, double b, double total) {
		log.info(jp.getSignature() + "is now adding");
		try {
			log.info(a +" added to " + b + " = " + total);
		} catch (Throwable e) {
			log.error(jp.getSignature() + "threw an exception");
		}
		return 0;
	}
	
	@Around("execution(* subtract(..))") 
	public Object subtractThings(ProceedingJoinPoint pjp) throws Throwable {
		//Calculator c = (Calculator) pjp.getTarget();
		log.info(pjp.getSignature() + "is now subtracting");
		try {
			log.info("The subtraction result: " + pjp.proceed());
		} catch (Throwable e) {
			log.error(pjp.getSignature() + "threw an exception");
		}
		return pjp.proceed();
	}
	
	@AfterReturning(pointcut = "execution(* subtract(..)) && args(a,b))", returning = "total") 
	public double subtractThings(JoinPoint jp, double a, double b, double total) {
		log.info(jp.getSignature() + "is now subtracting");
		try {
			log.info(a +" subtract " + b + " = " + total);
		} catch (Throwable e) {
			log.error(jp.getSignature() + "threw an exception");
		}
		return 0;
	}
	
	@Around("execution(* multiply(..))") 
	public Object multiplyThings(ProceedingJoinPoint pjp) throws Throwable {
		//Calculator c = (Calculator) pjp.getTarget();
		log.info(pjp.getSignature() + "is now multiplying");
		try {
			log.info("The multiplication result: " + pjp.proceed());
		} catch (Throwable e) {
			log.error(pjp.getSignature() + "threw an exception");
		}
		return pjp.proceed();
	}
	
	@AfterReturning(pointcut = "execution(* multiply(..)) && args(a,b))", returning = "total") 
	public double multiplyThings(JoinPoint jp, double a, double b, double total) {
		//Calculator c = (Calculator) pjp.getTarget();
		log.info(jp.getSignature() + "is now multiplying");
		try {
			log.info(a +" multiplied by " + b + " = " + total);
		} catch (Throwable e) {
			log.error(jp.getSignature() + "threw an exception");
		}
		return 0;
	}
	
	@Around("execution(* divide(..))") 
	public Object divideThings(ProceedingJoinPoint pjp) throws Throwable {
		//Calculator c = (Calculator) pjp.getTarget();
		log.info(pjp.getSignature() + "is now dividing");
		try {
			log.info("The division result: " + pjp.proceed());
		} catch (Throwable e) {
			log.error(pjp.getSignature() + "threw an exception");
		}
		return pjp.proceed();
	}
	
	@AfterReturning(pointcut = "execution(* divide(..)) && args(a,b))", returning = "total") 
	public double divideThings(JoinPoint jp, double a, double b, double total) {
		log.info(jp.getSignature() + "is now dividing");
		try {
			log.info(a +" divided by " + b + " = " + total);
		} catch (Throwable e) {
			log.error(jp.getSignature() + "threw an exception");
		}
		return 0;
	}
	
	@AfterReturning("execution(* add(..))")
	public void addAttempt() {
		log.info("Calculator finished adding");
	}
	
	@AfterReturning("execution(* subtract(..))")
	public void subtractAttempt() {
		log.info("Calculator finished adding");
	}
	
	@AfterReturning("execution(* multiply(..))")
	public void multiplyAttempt() {
		log.info("Calculator finished adding");
	}
	
	@AfterReturning("execution(* divide(..))")
	public void divideAttempt() {
		log.info("Calculator finished adding");
	}
}
