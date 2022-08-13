package com.inho.aop.pointcut;

import com.inho.aop.order.aop.member.annotation.ClassAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**

    execution(modifier-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)

    execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)

    public java.lang.String com.inho.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)

 * */

@Slf4j
@SpringBootTest
@Import( {AtTargetAtWitinTest.Config.class} )
public class AtTargetAtWitinTest {

    @Autowired
    Child child;

    @Test
    void success()
    {
        log.info("child Proxy={}", child.getClass() );
        child.childMethod();
        child.parentMethod();
    }


    static class Config{
        @Bean
        public Parent parent() { return new Parent() ;}

        @Bean
        public Child child() { return new Child() ;}

        @Bean
        public AtTargetAtWithinAspect atTargetAtWithinAspect() { return new AtTargetAtWithinAspect() ;}
    }

    static class Parent {
        public void parentMethod(){}
    }

    @ClassAop
    static class Child extends Parent{
        public void childMethod(){}
    }

    @Slf4j
    @Aspect
    static class AtTargetAtWithinAspect
    {
        // 인스턴스 기준 부모 타입 메서드도 적용
        @Around( "execution(* com.inho.aop..*(..)) && @target(com.inho.aop.order.aop.member.annotation.ClassAop)" )
        public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info(joinPoint.toShortString());
            log.info("[@target] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        // 선택된 클래스 내부 메서드만 조인 포인트로 선정(부모 메서드 제외)
        @Around( "execution(* com.inho.aop..*(..)) && @within(com.inho.aop.order.aop.member.annotation.ClassAop)" )
        public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@within] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }


}
