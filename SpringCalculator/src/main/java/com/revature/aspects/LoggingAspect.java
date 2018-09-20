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
public class LoggingAspect {
	
	private static Logger log = Logger.getRootLogger();
	
	@AfterReturning(value="within(com.revature.beans.*)")
	public void logExecutingMethod(JoinPoint jp){
		log.info(jp.getSignature() + " was called");
	}
	
	@AfterReturning(value="execution(* add(..))", returning="returnValue")
	public void logAddition(JoinPoint jp, Object returnValue) {
		Object[] signatureArgs = jp.getArgs();
		log.info(signatureArgs[0] + " + " + signatureArgs[1] + " = " + returnValue);
		
	}
	@AfterReturning(value="execution(* subtract(..))", returning="returnValue")
	public void logSubtraction(JoinPoint jp, Object returnValue) {
		Object[] signatureArgs = jp.getArgs();
		log.info(signatureArgs[0] + " - " + signatureArgs[1] + " = " + returnValue);
		
	}
	@AfterReturning(value="execution(* multiply(..))", returning="returnValue")
	public void logMultiplication(JoinPoint jp, Object returnValue) {
		Object[] signatureArgs = jp.getArgs();
		log.info(signatureArgs[0] + " * " + signatureArgs[1] + " = " + returnValue);
		
	}
	
	@Around("execution(* divide(..))")
	public int preventDivisionByZero(ProceedingJoinPoint pjp) throws Throwable {
		Object[] signatureArgs = pjp.getArgs();
		if ((int) signatureArgs[1] == 0) {
			log.error("Cannot divide by zero");
		} else {
			Object result = pjp.proceed();
			log.info(signatureArgs[0] + " / " + signatureArgs[1] + " = " + result);
			return (int) result;
		}
		return 0;
	}
}
