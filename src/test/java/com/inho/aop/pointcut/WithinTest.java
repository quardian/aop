package com.inho.aop.pointcut;

import com.inho.aop.order.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

/**

    execution(modifier-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)

    execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)

    public java.lang.String com.inho.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)

 * */

@Slf4j
public class WithinTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    void beforeEach() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void pintMethod()
    {
        log.info("helloMethod={}", helloMethod);
    }

    @Test
    void withinMatch()
    {
        // within 내에 지정된 모든 메서드
        pointcut.setExpression( "within(com.inho.aop.order.aop.member.MemberServiceImpl)" );
        Assertions.assertThat( pointcut.matches(helloMethod, MemberServiceImpl.class) ).isTrue();
    }

    @Test
    void withinSubpackageMatch()
    {
        // within 내에 지정된 모든 메서드
        pointcut.setExpression( "within(com.inho.aop..*)" );
        Assertions.assertThat( pointcut.matches(helloMethod, MemberServiceImpl.class) ).isTrue();
    }

    @Test
    void withinSuperTypeFalse()
    {
        // 타겟의 타입에만 직접 적용, 인터페이스 선정하면 안됨 ==> execution은 성공
        pointcut.setExpression( "within(com.inho.aop.order.aop.member.MemberService)" );
        Assertions.assertThat( pointcut.matches(helloMethod, MemberServiceImpl.class) ).isFalse();
    }
}
