package edu.mum.cs544.bank.service.aspect;

import edu.mum.cs544.bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JMSLogAdvice {

    @Autowired
    ILogger logger;

    @Before("execution(* edu.mum.cs544.bank.jms.JMSSender.sendJMSMessage(String))")
    public void logJmsSender(JoinPoint joinPoint){
        logger.log("JMS ADVICE message= "+joinPoint.getArgs()[0]);
    }
}
