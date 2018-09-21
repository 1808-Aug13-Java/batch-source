package com.chandrika.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.chandrika.beans.Calculator;

@Aspect
@Component
public class CalculatorAspect {
	@Before("execution(* add())")
	public void executeAdd(JoinPoint jp) {
		Calculator c = (Calculator) jp.getTarget();
		System.out.println("3 + 6 = "+c.add(3, 6));
	}

	@Before("execution(* subtract())")
	public void executeSubtract(JoinPoint jp) {
		Calculator c = (Calculator) jp.getTarget();
		System.out.println("3 + 6 = "+c.subtract(3, 6));
	}
	
	@Before("execution(* multiply())")
	public void executeMultiply(JoinPoint jp) {
		Calculator c = (Calculator) jp.getTarget();
		System.out.println("3 + 6 = "+c.multiply(1.5, 1.5));
	}
	
	@Before("execution(* divides())")
	public void executeDivide(JoinPoint jp) {
		Calculator c = (Calculator) jp.getTarget();
		System.out.println("3 + 6 = "+c.divide(3, 6));
	}
}
