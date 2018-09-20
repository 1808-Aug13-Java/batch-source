package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
	
	Logger log = Logger.getRootLogger();
	
	@Before("within(com.revature.math.*)")
	public void beforeMethod(JoinPoint jp) {
		System.out.println("Method Executed: " + jp.getSignature());
		log.info("Method Executed: " + jp.getSignature());
	}
	
	
	
}
