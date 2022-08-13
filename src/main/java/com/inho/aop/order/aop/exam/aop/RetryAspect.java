package com.inho.aop.order.aop.exam.aop;

import com.inho.aop.order.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class RetryAspect {

    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        int retryCount = retry.value();
        Exception exceptionHolder = null;
        log.info("[retry] {} retry={}", joinPoint.getSignature(), retryCount);
        for( int r = 1; r < retryCount; r++)
        {
            try {
                log.info("[retry] try count={}/{}", r, retryCount);
                return  joinPoint.proceed();
            } catch (Exception e) {
                exceptionHolder = e;
                continue;
            }
        }
        throw  exceptionHolder;
    }
}
