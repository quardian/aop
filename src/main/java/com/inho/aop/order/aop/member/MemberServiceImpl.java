package com.inho.aop.order.aop.member;

import com.inho.aop.order.aop.member.annotation.ClassAop;
import com.inho.aop.order.aop.member.annotation.MethodAop;
import org.springframework.stereotype.Component;

import java.lang.reflect.Member;


@ClassAop
@Component
public class MemberServiceImpl implements MemberService {
    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String name)
    {
        return "ok";
    }
}
