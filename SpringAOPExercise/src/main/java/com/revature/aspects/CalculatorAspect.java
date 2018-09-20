package com.revature.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.revature.beans.Calculator;

@Aspect
@Component
public class CalculatorAspect {
	

	@AfterReturning("execution(* add(..))")
	public void add(JoinPoint jp)
	{
		Calculator c = (Calculator) jp.getTarget();
		System.out.println(c.getX() + " + " + c.getY() + " = " + c.getResult());
	}
	
	@AfterReturning("execution(* subtract(..))")
	public void subtract(JoinPoint jp)
	{
		Calculator c = (Calculator) jp.getTarget();
		System.out.println(c.getX() + " - " + c.getY() + " = " + c.getResult());
	}
	
	@AfterReturning("execution(* multiply(..))")
	public void multiply(JoinPoint jp)
	{
		Calculator c = (Calculator) jp.getTarget();
		System.out.println(c.getX() + " * " + c.getY() + " = " + c.getResult());
	}
	
	@AfterReturning("execution(* divide(..))")
	public void divide(JoinPoint jp)
	{
		Calculator c = (Calculator) jp.getTarget();
		System.out.println(c.getX() + " / " + c.getY() + " = " + c.getResult());
	}
	
	
	
	
	
}
