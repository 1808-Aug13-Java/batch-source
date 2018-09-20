package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.revature.beans.Calculator;

@Aspect
@Component
public class LoggingAspect {
	
	private static Logger log = Logger.getRootLogger();
	
//	@AfterReturning("within(com.revature.beans.Calculator)")
//	public void logAfter(JoinPoint jp) {
//		log.info(jp.getSignature()+" method was called.");
//		Object[] o = jp.getArgs();
//		if(jp.toShortString().contains("add")) {
//			log.info(o[0].toString()+" + "+o[1].toString()+" = "+jp.getThis());
//		}
//	}
	
	@AfterReturning(pointcut = "execution(* *(..)) && args(a, b)", returning = "total")
	public void add(JoinPoint jp, double a, double b, double total) {
		if(jp.toShortString().contains("add")) {
			log.info(a + "+" + b + " = " + total);
		}else if(jp.toShortString().contains("subtract")) {
			log.info(a + "-" + b + " = " + total);
		}else if(jp.toShortString().contains("multiply")) {
			log.info(a + "*" + b + " = " + total);
		}else if(jp.toShortString().contains("divide")) {
			log.info(a + "/" + b + " = " + total);
		}
	}
	
//	@Around("execution(* divide(..)) && args(a,b)")
//	public void logAfterThrowing(ProceedingJoinPoint jp,double a, double b) throws Throwable{
//		if(b!=0.0) {
//			jp.proceed();
//		}else {
//			log.error(jp.getSignature()+" Can't divide by zero.");
//		}
//	}
}