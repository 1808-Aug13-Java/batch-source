package com.revature.aspects;

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
	
	//we are targeting our bear hibernates method and we are able to 
	//access the bear object it's being called on using the JoinPoint object
	@Before("execution(* bearHibernates())")
	public void stockedUpForWinter(JoinPoint jp) {
		Bear b = (Bear) jp.getTarget();
		if(b.isFull()) {
			System.out.println("Bear was stocked up and ready for winter");
		} else {
			System.out.println("Bear is going to be hungry when he wakes up");
		}
	}

	// * is our wildcard in method declarations (..) is a wildcard for arguments
	@Around("execution(* wake*())") 
	public void wakeAnimal(ProceedingJoinPoint pjp) throws Throwable {
		Bear b = (Bear) pjp.getTarget();
		//pjp.proceed();
		if(b.isFull() || !b.isAwake()) {
			System.out.println("You got lucky");
		} else {
			System.out.println("You became lunch");
		}
		pjp.proceed();
	}
	
	@Before("execution(* setFull(..)) && args(isFull)")
	public void printStatus(boolean isFull) {
		System.out.println("setFull called... isFull = "+isFull);
	}
	
	
}
