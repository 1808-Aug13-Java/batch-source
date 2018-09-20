package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.revature.beans.Calculator;

@Aspect
@Component
public class LoggingAspect {
	private static Logger log = Logger.getRootLogger();
//	log in a separate file the method signature of each operation as it is executed...so after returning
	@AfterReturning( "within(com.revature.beans.*)" )
	public void logAfterOperation(JoinPoint jp) 
	{
		log.info(jp.getSignature() + " was called.");
	}
//	log the operation arithmetically as it is performed - include the result (obtained by the join point, 
	//not from repeating the operation)
//	E.g. if add(2, 3) is called, “2 + 3 = 5” is logged
	//what are argNames, pointcut, returning, value???
	@AfterReturning(pointcut="execution(* divide())", returning = "returned")
	public void logDivision(JoinPoint jp, Object returned) {
		Calculator c = (Calculator) jp.getThis();
		log.info(c.getA()+" / "+c.getB()+" = "+returned);
	}
	
	@AfterReturning(pointcut = "execution(* add())", returning="returningValue")
	public void logOperationAdd(JoinPoint jp, Object returningValue)
	{
		Calculator c = (Calculator) jp.getTarget();
		log.info(c.getA() + " + " + c.getB() +" = " + returningValue);
	}
	 							//without the " " between * and multiply, will throw an exception :O
	@AfterReturning(pointcut="execution(* multiply())", returning="returnValue")
	public void logMultiplication(JoinPoint jp, int returnValue)
	{
		Calculator c = (Calculator) jp.getThis();
		log.info(c.getA()+" * "+c.getB()+" = "+returnValue);
	}
	
	@AfterReturning(pointcut="execution(* multiply())", returning="returnValue")
	public void logSubtraction(JoinPoint jp, int returnValue)
	{
		Calculator c = (Calculator) jp.getThis();
		log.info(c.getA()+" - "+c.getB()+" = "+returnValue);
	}

//	prevent the division method from executing if division is attempted by 0 and log an error
	@Around("execution(* divide())")
	public Object stopDivisionByZero(ProceedingJoinPoint pjp) throws Throwable {
		Calculator c = (Calculator) pjp.getTarget();
		if(c.getB() == 0)
		{
			try {
				throw new ArithmeticException();
			} catch(ArithmeticException e) {
				log.error(e.getMessage());
			}
			return null;
		}
		else {
			return pjp.proceed();
		}
	}

}
