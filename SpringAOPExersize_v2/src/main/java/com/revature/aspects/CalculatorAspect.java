package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.revature.models.Calculator;

@Aspect
@Component
public class CalculatorAspect {
	private static Logger log = Logger.getRootLogger();
	
	@AfterReturning(value="execution(* com.revature.models.Calculator.*(..)) && args(x,y)", returning="retVal")
	public double calcCallGeneral(JoinPoint jp, double x, double y, Object retVal) {
		String msg = "";
		
		switch(jp.getSignature().toShortString().substring(11)) {
		case"add(..)":
			msg += x + " + " + y + " = ";
			break;
		case"minus(..)":
			msg += x + " - " + y + " = ";
			break;
		case"multiply(..)":
			msg += x + " * " + y + " = ";
			break;
		case"divide(..)":
			msg += x + " / " + y + " = ";
			break;
		case"modulus(..)":
			msg += x + " % " + y + " = ";
			break;
		case"exponent(..)":
			msg += x + " ^ " + y + " = ";
			break;
		default:
			msg += jp.getSignature().toShortString().substring(10);
		}
		log.info(msg + retVal);
		return (Double) retVal;
	}
	
	@Around("execution(* divide(..)) && args(x, y)")
	public double calcCatchDivisionByZero(ProceedingJoinPoint pjp, double x, double y) throws Throwable{
		Calculator c = (Calculator) pjp.getTarget();
		try {
			if (y != 0.0)
				log.info("PJP.toString(): " + pjp.proceed().toString());
			else 
				throw new NumberFormatException();
		} catch (Throwable e) {
			log.error("DIVIDE BY ZERO ERROR");
		}
		return 0.0;
	}
}
