package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalcAspect {
	
	Logger loggy = Logger.getRootLogger();
	
	@Before("execution(* *(..))")
	public void beforeCall(JoinPoint jp)
	{
		loggy.info(jp.getSignature() + "\n");
	}

	@Around("execution(* div(..))")
	public Object catchDiv(ProceedingJoinPoint pjp) throws Throwable
	{
		
		if(pjp.getArgs()[1].equals(Integer.valueOf(0)))
		{
			System.out.println("ERROR! Cannot divide by 0!");
			loggy.error("ERROR! Cannot divide by 0!\n");
			return new Integer(0);
		}
		else
		{
			Integer ret = (Integer)pjp.proceed();
			Object[] args = pjp.getArgs();
			String print = args[0].toString() + " / " + args[1].toString() + " = " + ret;
			System.out.println(print);
			loggy.info(":::   " + print + "\n");
			return ret;
		}
	}
	
	@Around("execution(* mod(..))")
	public Object catchMod(ProceedingJoinPoint pjp) throws Throwable
	{
		
		if(pjp.getArgs()[1].equals(Integer.valueOf(0)))
		{
			System.out.println("ERROR! Cannot Mod by 0!");
			loggy.error("ERROR! Cannot Mod by 0!\n");
			return new Integer(0);	
		}
		else
		{
			Integer ret = (Integer)pjp.proceed();
			Object[] args = pjp.getArgs();
			String print = args[0].toString() + " % " + args[1].toString() + " = " + ret;
			System.out.println(print);
			loggy.info(":::   " + print + "\n");
			return ret;
		}
	}
	

	
	@AfterReturning(pointcut = "execution(* add(..))",
			returning = "val")
	public void reportCalculationAdd(JoinPoint jp, int val)
	{
		
		Object[] args = jp.getArgs();
		String print = args[0].toString() + " + " + args[1].toString() + " = " + val;
		System.out.println(print);
		loggy.info(":::   " + print + "\n");
	}
	
	@AfterReturning(pointcut = "execution(* sub(..))",
			returning = "val")
	public void reportCalculationSub(JoinPoint jp, int val)
	{
		Object[] args = jp.getArgs();
		String print = args[0].toString() + " - " + args[1].toString() + " = " + val;
		System.out.println(print);
		loggy.info(":::   " + print + "\n");
	}
	
	@AfterReturning(pointcut = "execution(* mul(..))",
			returning = "val")
	public void reportCalculationMul(JoinPoint jp, int val)
	{
		Object[] args = jp.getArgs();
		String print = args[0].toString() + " * " + args[1].toString() + " = " + val;
		System.out.println(print);
		loggy.info(":::   " + print + "\n");
		
	}
	
//	@AfterReturning(pointcut = "execution(* div(..))",
//			returning = "val")
//	public void reportCalculationDiv(JoinPoint jp, int val)
//	{
//		Object[] args = jp.getArgs();
//		loggy.info(":::   " + args[0].toString() + " / " + args[1].toString() + " = " + val + "\n");
//	}
	
//	@AfterReturning(pointcut = "execution(* mod(..))",
//			returning = "val")
//	public void reportCalculationMod(JoinPoint jp, int val)
//	{
//		Object[] args = jp.getArgs();
//		loggy.info(":::   " + args[0].toString() + " % " + args[1].toString() + " = " + val + "\n");
//	}
	
	@AfterReturning(pointcut = "execution(* pow(..))",
			returning = "val")
	public void reportCalculationPow(JoinPoint jp, int val)
	{
		Object[] args = jp.getArgs();
		String print = args[0].toString() + " ^ " + args[1].toString() + " = " + val;
		System.out.println(print);
		loggy.info(":::   " + print + "\n");
	}
	
}
