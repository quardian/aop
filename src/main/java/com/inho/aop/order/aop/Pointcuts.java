package com.inho.aop.order.aop;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;


/*
    포인트컷 지시자 종류
    - execution : 메소드 실행 조인 포인트 매칭
    - within    : 특정 타입 내의 조인 포인트 매칭
    - args      : 인자가 주어진 타입의 인스턴스인 조인 포인트
    - this      : 스프링 빈 객체(스프링AOP프록시)를 대상으로 하는 조인 포인트
    - target    : Target객체(스프링AOP프록시가 가리키는 실제 대상)을 대상으로 하는 조인 포인트
    - @target   : 실행 객체의 클래스에 주어진 타입의 애노테이션이 있는 조인 포인트
    - @within   : 주어진 애노테이션이 있는 타입 내 조인 포인트
    - @annotation   : 메서드가 주어진 애노테이션을 가지고 있는 조인 포인트 매칭
    - @args     : 전달된 실제 인수의 런타임 타입이 주어진 타입의 애노테이션을 갖는 조인 포인트
    - bean      : 스프링 전용 포인트컷 지시자, ㅂ빈의 이름으로 포인트컷 지정

* */
public class Pointcuts {
    @Pointcut( "execution(* com.inho.aop.order..*(..))" )
    public void allOrder(){}

    @Pointcut( "execution(* *..*Service.*(..))" )
    public void allService(){}

    @Pointcut( "allOrder() && allService()" )
    public void allOrderAndService(){}
}
