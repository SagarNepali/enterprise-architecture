package edu.mum.cs544;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class AspectAOP {

    @Pointcut("execution (public void edu.mum.cs544.EmailSender.sendEmail(*,*))")
    public void logAfterEmailSendPoint(){}

    @After("logAfterEmailSendPoint()")
    public void lofAfterEmailSend(JoinPoint joinPoint){
        System.out.println(new Date()+" method= "+joinPoint.getSignature().getName());
    }
}
