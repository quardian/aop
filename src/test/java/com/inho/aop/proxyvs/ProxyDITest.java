package com.inho.aop.proxyvs;

import com.inho.aop.order.aop.member.MemberService;
import com.inho.aop.order.aop.member.MemberServiceImpl;
import com.inho.aop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.lang.reflect.Member;

@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"})
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"})
@Import(ProxyDIAspect.class)

public class ProxyDITest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;


    @Test
    void go()
    {
        log.info( "memberServcie class={}", memberService.getClass() );
        log.info( "memberServiceImpl class={}", memberServiceImpl.getClass() );
        memberServiceImpl.hello("A");
    }

}
