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
public class ArgsTest {


    Method helloMethod;

    @BeforeEach
    void beforeEach() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    private AspectJExpressionPointcut pointcut(String expression)
    {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }

    @Test
    void pintMethod()
    {
        log.info("helloMethod={}", helloMethod);
    }

    @Test
    void argsMatch()
    {
        Assertions.assertThat( pointcut("args(String)")
                            .matches(helloMethod, MemberServiceImpl.class) ).isTrue();

        // args는 부모타입도 허용한다.
        Assertions.assertThat( pointcut("args(Object)")
                .matches(helloMethod, MemberServiceImpl.class) ).isTrue();

        // args는 부모타입도 허용한다.
        Assertions.assertThat( pointcut("args()")
                .matches(helloMethod, MemberServiceImpl.class) ).isFalse();

        Assertions.assertThat( pointcut("args(..)")
                .matches(helloMethod, MemberServiceImpl.class) ).isTrue();

        Assertions.assertThat( pointcut("args(*)")
                .matches(helloMethod, MemberServiceImpl.class) ).isTrue();

    }

    /**
        execution(* *(java.io.Serializable) ) : 메서드의 시그니처로 판단(정적)
        args(java.io.Serializable) : 런타임에 전달된 인수로 판단(동적)
     */
    @Test
    void argsVsExcution(){
        // Args
        Assertions.assertThat( pointcut("args(String)")
                .matches(helloMethod, MemberServiceImpl.class) ).isTrue();
        Assertions.assertThat( pointcut("args(java.io.Serializable)")
                .matches(helloMethod, MemberServiceImpl.class) ).isTrue();
        Assertions.assertThat( pointcut("args(Object)")
                .matches(helloMethod, MemberServiceImpl.class) ).isTrue();

        //Execution
        Assertions.assertThat( pointcut("execution(* *(String) )")
                .matches(helloMethod, MemberServiceImpl.class) ).isTrue();
        Assertions.assertThat( pointcut("execution(* *(java.io.Serializable) )") //매칭 실패
                .matches(helloMethod, MemberServiceImpl.class) ).isFalse();
        Assertions.assertThat( pointcut("execution(* *(Object) )")//매칭 실패
                .matches(helloMethod, MemberServiceImpl.class) ).isFalse();
    }

}
