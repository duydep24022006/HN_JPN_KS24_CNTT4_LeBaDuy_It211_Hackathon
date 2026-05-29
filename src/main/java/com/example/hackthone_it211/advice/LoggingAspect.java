package com.example.hackthone_it211.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.hackthone_it211.service.Impl.CarServiceImpl.create(..)) || " +
            "execution(* com.example.hackthone_it211.service.Impl.CarServiceImpl.update*(..))")
    public void carWriteMethods() {}

    @Before("carWriteMethods()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info(">>> AOP LOG: Có luồng dữ liệu chuẩn bị gọi vào hệ thống tại Method: {}()", joinPoint.getSignature().getName());
    }
}
