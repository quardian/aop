package com.inho.aop.pointcut;

import com.inho.aop.order.aop.member.MemberService;
import com.inho.aop.order.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.lang.reflect.Method;

/**

    execution(modifier-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)

    execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)

    public java.lang.String com.inho.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)

 * */

@Slf4j
//@SpringBootTest(properties = "spring.aop.proxy-target-class=false") // JDK동적프록시
@SpringBootTest(properties = "spring.aop.proxy-target-class=true") // CGLIB 프록세
@Import(ThisTargetTest.ThisTargetAspect.class)
public class ThisTargetTest {

    @Autowired
    MemberService memberService;

    @Test
    void success(){
        log.info("memberService Proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ThisTargetAspect {

        @Around( "this(com.inho.aop.order.aop.member.MemberService)")
        public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-interface]={}", joinPoint.getSignature() );
            return joinPoint.proceed();
        }

        @Around( "target(com.inho.aop.order.aop.member.MemberService)")
        public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-interface]={}", joinPoint.getSignature() );
            return joinPoint.proceed();
        }


        @Around( "this(com.inho.aop.order.aop.member.MemberServiceImpl)")
        public Object doThisImple(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-impl]={}", joinPoint.getSignature() );
            return joinPoint.proceed();
        }

        @Around( "target(com.inho.aop.order.aop.member.MemberServiceImpl)")
        public Object doTargetImpl(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-impl]={}", joinPoint.getSignature() );
            return joinPoint.proceed();
        }
    }



}
