package com.inho.aop.pointcut;

import com.inho.aop.order.OrderService;
import com.inho.aop.order.aop.member.MemberService;
import com.inho.aop.order.aop.member.MemberServiceImpl;
import com.inho.aop.order.aop.member.annotation.ClassAop;
import com.inho.aop.order.aop.member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(ParameterTest.ParameterAspect.class)
public class ParameterTest {

    @Autowired
    MemberService memberService;

    @Test
    void success(){
        log.info("orderService Proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ParameterAspect {

        @Pointcut( "execution(* com.inho.aop.order.aop.member..*.*(..))" )
        public void allMember(){}

        @Around( "allMember()")
        public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {

            Object arg1 = joinPoint.getArgs()[0];
            log.info("[logArgs1] {}, arg={} ", joinPoint.getSignature(), arg1 );
            return joinPoint.proceed();
        }

        @Around( "allMember() && args(arg,..)")
        public Object logArgs2(ProceedingJoinPoint joinPoint, Object arg) throws Throwable {
            log.info("[logArgs2] {}, arg={} ", joinPoint.getSignature(), arg );
            return joinPoint.proceed();
        }

        @Before( "allMember() && args(arg,..)")
        public void logArgs3(JoinPoint joinPoint, String arg) throws Throwable {
            log.info("[logArgs3] {}, arg={} ", joinPoint.getSignature(), arg );
        }

        @Before( "allMember() && this(obj)")
        public void thisArgs(JoinPoint joinPoint, MemberService obj){
            log.info("[this] {}, obj={} ", joinPoint.getSignature(), obj.getClass() );
        }

        @Before( "allMember() && target(obj)")
        public void targetArgs(JoinPoint joinPoint, MemberService obj){
            log.info("[target] {}, obj={} ", joinPoint.getSignature(), obj.getClass() );
        }

        @Before( "allMember() && @target(annotation)")
        public void atTargetArgs(JoinPoint joinPoint, ClassAop annotation){
            log.info("[@target] {}, obj={} ", joinPoint.getSignature(), annotation);
        }

        @Before( "allMember() && @within(annotation)")
        public void atWithinArgs(JoinPoint joinPoint, ClassAop annotation){
            log.info("[@within] {}, obj={} ", joinPoint.getSignature(), annotation);
        }

        @Before( "allMember() && @annotation(annotation)")
        public void atAnnotation(JoinPoint joinPoint, MethodAop annotation){
            log.info("[@annotation] {}, annotationValue={} ", joinPoint.getSignature(), annotation.value());
        }
    }

}
