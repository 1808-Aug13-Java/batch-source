package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.revature.beans.Calculator;

@Aspect
@Component
public class CalculatorAspect {
	
	private static Logger log = Logger.getRootLogger();
	
	// we are targeting our calculator add method and we are able to
	// access the calculator object it's being called on using the JoinPoint object
	
	// * is wildcard in method declarations
	@Before("execution(* add(..))") // .. is wildcard for arguments
	public void readyToCalculate(JoinPoint jp) {
		// if we get target of join point that is our calculator object
		Calculator c = (Calculator) jp.getTarget();
		if(c.isCalculate()) {
			log.info("Calculator is ready");
		}
		else {
			log.info("Calculator is not ready");
		}
	}
	

}
