package com.inho.aop.proxyvs;

import com.inho.aop.order.aop.member.MemberService;
import com.inho.aop.order.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j

public class ProxyCastingTest {

    @Test
    void jdkProxy()
    {
        MemberService target = new MemberServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); // JDK 동적 프록시 사용

        // 프록시를 인터페이스 캐스팅 성공
        MemberService proxy = (MemberService)proxyFactory.getProxy();

        // 구체 타입으로는 캐스팅 불가 : 인터페이스를 구현한 프록시라 Impl 클래스에 대한 정보를 전혀 알지 못한다.
        // MemberServiceImpl memberService = (MemberServiceImpl)proxy;
    }

    @Test
    void jdkCGLIB()
    {
        MemberService target = new MemberServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // CGLIB 동적 프록시 사용

        // 프록시를 인터페이스 캐스팅 성공
        MemberService proxy = (MemberService)proxyFactory.getProxy();

         // 구체 타입으로는 캐스팅 성공
         MemberServiceImpl memberService = (MemberServiceImpl)proxy;
    }
}
