package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.revature.beans.Operator;

@Aspect
@Component
public class OperatorAspect {
	public static Logger log= Logger.getRootLogger();
	
//	@Around("execution (* compute(..))")
//	@Around(pointcut = "execution(* compute(..))")
//	@Around("execution(* com.howtodoinjava.app.service.impl.EmployeeManagerImpl.*(..))")
//	@Around("execution(* com.howtodoinjava.app.service.impl.EmployeeManagerImpl.getEmployeeById(..))")
	
	  @Around("execution(* com.revature.beans.Operator.compute(..))")
	public void checkCompute(ProceedingJoinPoint pjp) throws Throwable {
		
		Operator o = (Operator) pjp.getTarget();
		
		if( o.getInput2() ==0 ) {
			log.error("Cannot divide by zero");
		}
		else {
			pjp.proceed();
			switch (o.getChoice()) {
			case "addition":{				
				log.info(o.getChoice()+" "+o.getInput1() +"+"+o.getInput2()+"="+o.getResult());
				break;
			}
			case "multiplication":{
				log.info(o.getChoice()+" "+o.getInput1() +"*"+o.getInput2()+"="+o.getResult());
				break;
			}
			case "division":{
				log.info(o.getChoice()+" "+o.getInput1() +"/"+o.getInput2()+"="+o.getResult());
				break;
				}
			case "subtraction":{
				log.info(o.getChoice()+" "+o.getInput1() +"-"+o.getInput2()+"="+o.getResult());
				break;
			}
			default:{
				log.info("Please select a valid option");
			}
			}
			
		}
	}
	

}
