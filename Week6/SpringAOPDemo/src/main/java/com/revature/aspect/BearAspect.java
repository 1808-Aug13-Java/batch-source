package com.revature.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.revature.beans.Bear;

@Aspect
@Component
public class BearAspect {

	@Before("execution(* bearHibernates())")
	public void stockedUpForWinter(JoinPoint jp) {
		Bear b= (Bear) jp.getTarget();
		if (b.isFull()) {
			System.out.println("bear is ready for winter");
		}
		else {
			System.out.println("He wasn't ready...");
			
		}
	}
	 
	@Around("execution (* wake*())")
	public void wakeAnimal(ProceedingJoinPoint pjp) throws Throwable {
		
		Bear b =(Bear) pjp.getTarget();
//		pjp.proceed();
		if(b.isFull()) {
			System.out.println("you lucky");
		}
		else {
			System.out.println("you dead");
		}
		pjp.proceed();
	}
	
	@Before("execution (* setFull(..))&& args(isFull)")
	public void printStatus(boolean isFull) {
		System.out.println("setFull called"+ isFull);
	}
	
	
}
