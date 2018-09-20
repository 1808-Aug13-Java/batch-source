package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExtraAspect {
	private static Logger log = Logger.getRootLogger();

//	@AfterReturning(
//			pointcut="execution(* divide*(..)) && args(a,b)", 
//			returning ="res")
//			public void divStatus(int a, int b, int res) throws Throwable{
//				System.out.println("hello from divStatus");
//				System.out.println("divide is called "+a+" / "+b+" = " + res);
//				log.info("divide is called "+a+" / "+b+" = " + res);
//			}

}
