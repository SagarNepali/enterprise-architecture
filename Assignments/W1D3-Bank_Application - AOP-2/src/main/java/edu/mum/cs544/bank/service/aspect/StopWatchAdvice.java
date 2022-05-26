package edu.mum.cs544.bank.service.aspect;


import edu.mum.cs544.bank.logging.ILogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class StopWatchAdvice {

    @Autowired
    ILogger logger;

    @Pointcut("execution (* edu.mum.cs544.bank.service..*.*(..))")
    public void logService(){}

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
}
