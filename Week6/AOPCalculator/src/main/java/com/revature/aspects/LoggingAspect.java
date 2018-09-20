package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	
	

//		private Logger log = Logger.getRootLogger();
//		
////		@AfterReturning(pointcut = "execution(* compute(..))")
//		@AfterReturning("within(com.revature.beans.*)")
//		public void add(JoinPoint jp) {
//			log.info("working" +jp.getSignature());
//		}
//		@AfterReturning("within(com.revature.beans.Operator.*)")
//		public void comp(JoinPoint jp) {
//			log.info("computed" +jp.getSignature());
//		}
		
		
	
}
