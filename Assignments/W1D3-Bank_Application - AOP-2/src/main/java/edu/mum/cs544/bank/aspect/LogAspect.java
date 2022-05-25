package edu.mum.cs544.bank.aspect;

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
public class LogAspect {

    @Autowired
    ILogger logger;

    @Pointcut("execution(public void edu.mum.cs544.bank.dao..*.*(..))")
    public void logEveryDao(){}

    @Pointcut("execution (* edu.mum.cs544.bank.service..*.*(..))")
    public void logService(){}

    @Before("logEveryDao()")
    public void logBeforeEveryDaoCalls(JoinPoint joinPoint){
        logger.log("class = "+joinPoint.getTarget().getClass().getSimpleName()+", method = "+joinPoint.getSignature().getName());
    }

    @Around("logService()")
    public Object logServiceCalls(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(proceedingJoinPoint.getSignature().getName());
        Object retVal = proceedingJoinPoint.proceed();
        sw.stop();
        logger.log("class= "+proceedingJoinPoint.getTarget().getClass().getSimpleName()+
                ", Execution time: "+ sw.getLastTaskTimeMillis()+" ms for method="+proceedingJoinPoint.getSignature().getName());
        return retVal;
    }

    @Before("execution(* edu.mum.cs544.bank.jms.JMSSender.sendJMSMessage(String))")
    public void logJmsSender(JoinPoint joinPoint){
        logger.log("JMS ADVICE message= "+joinPoint.getArgs()[0]);
    }
}
