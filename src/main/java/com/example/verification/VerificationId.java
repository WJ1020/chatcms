package com.example.verification;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by WangShiXiang on 2017/5/13.
 * 主要是对id的验证
 */
@Aspect
@Component
public class VerificationId {
    @Pointcut("execution(* com.example.controller.IndexController.*(..))")
    public void verificationid(){}

    @Before("verificationid()")
   public void before(JoinPoint joinPoint){

   }
}
