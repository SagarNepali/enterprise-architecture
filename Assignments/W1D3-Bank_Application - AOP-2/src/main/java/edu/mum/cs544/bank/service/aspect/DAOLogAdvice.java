package edu.mum.cs544.bank.service.aspect;

import edu.mum.cs544.bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class DAOLogAdvice {

    @Autowired
    ILogger logger;

    @Pointcut("execution(public void edu.mum.cs544.bank.dao..*.*(..))")
    public void logEveryDao(){}


    @Before("logEveryDao()")
    public void logBeforeEveryDaoCalls(JoinPoint joinPoint){
        logger.log("class = "+joinPoint.getTarget().getClass().getSimpleName()+", method = "+joinPoint.getSignature().getName());
    }




}
