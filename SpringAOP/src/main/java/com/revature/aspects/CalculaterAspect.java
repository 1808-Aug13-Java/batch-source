package com.revature.aspects;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.aspectj.lang.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import com.revature.beans.Calculater;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculaterAspect {

  private static Logger log = LogManager.getLogger(CalculaterAspect.class.getName()); 
  @AfterReturning("execution(* *Operation(..))") 
  public void operate(JoinPoint jp) throws Throwable {
    Calculater c = (Calculater) jp.getTarget();
    log.info(jp.getArgs()[0] + " " + c.getOp() + " "  + jp.getArgs()[1] + " = " + c.getResult());
  }

}
