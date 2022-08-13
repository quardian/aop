package com.inho.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect

public class AspectV6Advice {

    @Around( "com.inho.aop.order.aop.Pointcuts.allOrderAndService()" )
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Object[] args = joinPoint.getArgs();            // 메서드 인수
        Object aThis = joinPoint.getThis();             // 프록시 객체
        Object target = joinPoint.getTarget();          // 대상 객체
        Signature signature = joinPoint.getSignature(); // 조언되는 메소드에 대한 설명
        String s = joinPoint.toString();                // 조언되는 방법에 대한 유용한 설명

        try{
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());

            //@Before
            Object result = joinPoint.proceed();
            //@AfterReturn

            log.info("[트랜잭션 종료] {}", joinPoint.getSignature());
            return result;
        }catch (Exception e)
        {
            //@AfterThrowing
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw  e;
        }
        finally {
            //@After
            log.info("[리소스 정리] {}", joinPoint.getSignature());
        }
    }

    @Before( "com.inho.aop.order.aop.Pointcuts.allOrderAndService()" )
    public void doBefore(JoinPoint joinPoint)
    {
        log.info("[before] {}", joinPoint.getSignature());
    }

    @AfterReturning( value = "com.inho.aop.order.aop.Pointcuts.allOrderAndService()",
                     returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result)
    {
        log.info("[return] {} return ={}", joinPoint.getSignature(), result);
    }


    @AfterThrowing(  value = "com.inho.aop.order.aop.Pointcuts.allOrderAndService()"
                    , throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex)
    {
        log.info("[throwing] {} message={}", joinPoint.getSignature(), ex);
    }

    @After( "com.inho.aop.order.aop.Pointcuts.allOrderAndService()" )
    public void doAfter(JoinPoint joinPoint)
    {
        log.info("[after] {}", joinPoint.getSignature());
    }


}
