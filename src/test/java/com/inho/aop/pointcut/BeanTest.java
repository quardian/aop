package com.inho.aop.pointcut;

import com.inho.aop.order.OrderService;
import com.inho.aop.order.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(BeanTest.BeanAspect.class)
public class BeanTest {

    @Autowired
    OrderService orderService;

    @Test
    void success(){
        log.info("orderService Proxy={}", orderService.getClass());
        orderService.orderItem("itemA");
    }

    @Slf4j
    @Aspect
    static class BeanAspect {
        @Around( "bean(orderService) || bean(*Repository)")
        public Object doAtAnnotion(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@bean]={}", joinPoint.getSignature() );
            return joinPoint.proceed();
        }
    }

}
