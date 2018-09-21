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
public class ArithmeticAspect {
	private static Logger log = Logger.getRootLogger();
	
	@AfterReturning(pointcut= "execution(* add(..)) && args(x, y)", returning = "result")
	public void sum(JoinPoint jp, double x, double y, double result) {
		Calculator c = (Calculator) jp.getTarget();
		log.info(x + " + " + y + " = " + result);
	}
	
	@AfterReturning(pointcut = "execution(* subtract(..)) && args(x, y)", returning = "result")
	public void difference(JoinPoint jp, double x, double y, double result) {
		Calculator c = (Calculator) jp.getTarget();
		log.info(x + " - " + y + " = " + result);
	}
	
	@AfterReturning(pointcut = "execution(* multiply(..)) && args(x, y)", returning = "result")
	public void product(JoinPoint jp, double x, double y, double result) {
		Calculator c = (Calculator) jp.getTarget();
		log.info(x + " * " + y + " = " + result);
	}
	
	@Around("execution(* divide(..)) && args(x, y)")
	public double quotient(ProceedingJoinPoint pjp, double x, double y) throws Throwable {
		Calculator c = (Calculator) pjp.getTarget();
		try {
			if(y == 0) {
				throw new Exception();
				//return pjp.proceed();
			} else {
				log.info(x + " / " + y + " = " + pjp.proceed());
			}
		} catch(Exception e) {
			log.error("Divide by zero error! Stop it!");
		}
	return 0;
	}
}
