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

	@Before("within(com.revature.beans.Bear) && execution(void *earHibernate*())")
	public void stockedUpForWinter(JoinPoint jp) {
		Bear b = (Bear) jp.getTarget();
		if( b.isFull() ) {
			System.out.println("Bear was stocked up and ready for winter");
		} else {
			System.out.println("Bear is going to be hungry when he wakes up");
		}
	}
	
	@Around("execution(* wake*(..))") //* is wild card for values .. is a wild card for parameters
	public void wakeAnimal(ProceedingJoinPoint jpx) throws Throwable {
		Bear b = (Bear) jpx.getTarget();
		//jpx.proceed();
		if(b.isFull() || !b.isAwake()) {
			System.out.println("You got lucky");
		} else {
			System.out.println("You became lunch");
		}
		jpx.proceed();
	}
	
}
