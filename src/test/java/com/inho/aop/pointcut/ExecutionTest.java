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
public class ExecutionTest {

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
    void exactMatch()
    {
        //execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)
        //public java.lang.String com.inho.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)
        /*
            접근제어자?  : public
            반환타입     : String
            선언타입?    : com.inho.aop.order.aop.member.MemberServiceImpl ( 패키지명과 클래스명 포함)
            메서드명     : hello
            파라미터     : (String)
            예외?       : 생략
        * */
        pointcut.setExpression( "execution(public String com.inho.aop.order.aop.member.MemberServiceImpl.hello(String))" );
        Assertions.assertThat( pointcut.matches(helloMethod, MemberServiceImpl.class) ).isTrue();
    }


    @Test
    void allMatch()
    {
        //execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)
        //public java.lang.String com.inho.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)
        /*
            접근제어자?  : 생략
            반환타입     : *
            선언타입?    : 생략
            메서드명     : *
            파라미터     : (..)
            예외?       : 생략

            파라미터에서 '..'은 파라미터의 타입과 파라미터 수가 상관없다는 뜻이다.
            ㄴ (0..*')
        * */
        pointcut.setExpression( "execution(* *(..))" );
        Assertions.assertThat( pointcut.matches(helloMethod, MemberServiceImpl.class) ).isTrue();
    }


    @Test
    void nameMatch()
    {
        //execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)
        //public java.lang.String com.inho.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)
        /*
            접근제어자?  : 생략
            반환타입     : *
            선언타입?    : 생략
            메서드명     : hello
            파라미터     : (..)
            예외?       : 생략

            파라미터에서 '..'은 파라미터의 타입과 파라미터 수가 상관없다는 뜻이다.
            ㄴ (0..*')
        * */
        pointcut.setExpression( "execution(* hello(..))" );
        Assertions.assertThat( pointcut.matches(helloMethod, MemberServiceImpl.class) ).isTrue();
    }

    @Test
    void nameMatchStart1()
    {
        //execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)
        //public java.lang.String com.inho.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)
        /*
            접근제어자?  : 생략
            반환타입     : *
            선언타입?    : 생략
            메서드명     : hel*
            파라미터     : (..)
            예외?       : 생략

            파라미터에서 '..'은 파라미터의 타입과 파라미터 수가 상관없다는 뜻이다.
            ㄴ (0..*')
        * */
        pointcut.setExpression( "execution(* hel*(..))" );
        Assertions.assertThat( pointcut.matches(helloMethod, MemberServiceImpl.class) ).isTrue();
    }

    @Test
    void nameMatchStart2()
    {
        //execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)
        //public java.lang.String com.inho.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)
        /*
            접근제어자?  : 생략
            반환타입     : *
            선언타입?    : 생략
            메서드명     : *el*
            파라미터     : (..)
            예외?       : 생략

            파라미터에서 '..'은 파라미터의 타입과 파라미터 수가 상관없다는 뜻이다.
            ㄴ (0..*')
        * */
        pointcut.setExpression( "execution(* *el*(..))" );
        Assertions.assertThat( pointcut.matches(helloMethod, MemberServiceImpl.class) ).isTrue();
    }


    @Test
    void nameMatchFalse()
    {
        //execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)
        //public java.lang.String com.inho.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)
        /*
            접근제어자?  : 생략
            반환타입     : *
            선언타입?    : 생략
            메서드명     : nono
            파라미터     : (..)
            예외?       : 생략

            파라미터에서 '..'은 파라미터의 타입과 파라미터 수가 상관없다는 뜻이다.
            ㄴ (0..*')
        * */
        pointcut.setExpression( "execution(* nono(..))" );
        Assertions.assertThat( pointcut.matches(helloMethod, MemberServiceImpl.class) ).isFalse();
    }


    @Test
    void packageExactMach1()
    {
        //execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)
        //public java.lang.String com.inho.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)
        /*
            접근제어자?  : 생략
            반환타입     : *
            선언타입?    : com.inho.aop.order.aop.member.MemberServiceImpl
            메서드명     : hello
            파라미터     : (..)
            예외?       : 생략

            파라미터에서 '..'은 파라미터의 타입과 파라미터 수가 상관없다는 뜻이다.
            ㄴ (0..*')
        * */
        pointcut.setExpression( "execution(* com.inho.aop.order.aop.member.MemberServiceImpl.hello(..))" );
        Assertions.assertThat( pointcut.matches(helloMethod, MemberServiceImpl.class) ).isTrue();
    }

    @Test
    void packageExactMach2()
    {
        //execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)
        //public java.lang.String com.inho.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)
        /*
            접근제어자?  : 생략
            반환타입     : *
            선언타입?    : com.inho.aop.order.aop.member.MemberServiceImpl
            메서드명     : hello
            파라미터     : (..)
            예외?       : 생략

            파라미터에서 '..'은 파라미터의 타입과 파라미터 수가 상관없다는 뜻이다.
            ㄴ (0..*')
        * */
        pointcut.setExpression( "execution(* com.inho.aop.order.aop.member.*.*(..))" );
        Assertions.assertThat( pointcut.matches(helloMethod, MemberServiceImpl.class) ).isTrue();
    }

    @Test
    void packageExactFalse()
    {
        //execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)
        //public java.lang.String com.inho.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)
        /*
            접근제어자?  : 생략
            반환타입     : *
            선언타입?    : com.inho.aop.order.aop.member.MemberServiceImpl
            메서드명     : hello
            파라미터     : (..)
            예외?       : 생략

            파라미터에서 '..'은 파라미터의 타입과 파라미터 수가 상관없다는 뜻이다.
            ㄴ (0..*')
        * */
        pointcut.setExpression( "execution(* com.inho.aop.*.*(..))" );
        Assertions.assertThat( pointcut.matches(helloMethod, MemberServiceImpl.class) ).isFalse();
    }

    @Test
    void packageExactMachSubPackage1()
    {
        //execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)
        //public java.lang.String com.inho.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)
        /*
            접근제어자?  : 생략
            반환타입     : *
            선언타입?    : com.inho.aop.order..*  (.. 의 의미는 서브패키지)
            메서드명     : hello
            파라미터     : (..)
            예외?       : 생략

            파라미터에서 '..'은 파라미터의 타입과 파라미터 수가 상관없다는 뜻이다.
            ㄴ (0..*')
        * */
        pointcut.setExpression( "execution(* com.inho.aop.order..*.*(..))" );
        Assertions.assertThat( pointcut.matches(helloMethod, MemberServiceImpl.class) ).isTrue();
    }




    @Test
    void typeExactMatch()
    {
        pointcut.setExpression( "execution(* com.inho.aop.order.aop.member.MemberServiceImpl.*(..))" );
        Assertions.assertThat( pointcut.matches(helloMethod, MemberServiceImpl.class) ).isTrue();
    }

    @Test
    void typeMatchSubType()
    {
        // 부모 타입으로도 성공한다
        pointcut.setExpression( "execution(* com.inho.aop.order.aop.member.MemberService.*(..))" );
        Assertions.assertThat( pointcut.matches(helloMethod, MemberServiceImpl.class) ).isTrue();
    }

    @Test
    void typeMatchInternal() throws NoSuchMethodException {
        pointcut.setExpression( "execution(* com.inho.aop.order.aop.member.MemberServiceImpl.*(..))" );
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        Assertions.assertThat( pointcut.matches(internalMethod, MemberServiceImpl.class) ).isTrue();
    }

    @Test
    void typeMatchInternalFalse() throws NoSuchMethodException {
        // 부모타입의 메서드만 됨 : 본 예제는 False
        pointcut.setExpression( "execution(* com.inho.aop.order.aop.member.MemberService.*(..))" );
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        Assertions.assertThat( pointcut.matches(internalMethod, MemberServiceImpl.class) ).isFalse();
    }


    @Test
    void argsMatch() throws NoSuchMethodException {
        //String 타입의 파라미터 허용
        pointcut.setExpression( "execution(* *(String))" );
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        Assertions.assertThat( pointcut.matches(internalMethod, MemberServiceImpl.class) ).isTrue();
    }

    @Test
    void argsNoMatch() throws NoSuchMethodException {
        //String 타입의 파라미터 허용
        pointcut.setExpression( "execution(* *())" );
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        Assertions.assertThat( pointcut.matches(internalMethod, MemberServiceImpl.class) ).isFalse();
    }

    @Test
    void argsMatchStar() throws NoSuchMethodException {
        // 파라메타 타입은 상관없이 1개 파라메타 매칭
        pointcut.setExpression( "execution(* *(*))" );
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        Assertions.assertThat( pointcut.matches(internalMethod, MemberServiceImpl.class) ).isTrue();
    }

    @Test
    void argsMatchAll() throws NoSuchMethodException {
        // 숫자와 무관하게 모든 파라미터, 모든 타입 허용
        pointcut.setExpression( "execution(* *(..))" );
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        Assertions.assertThat( pointcut.matches(internalMethod, MemberServiceImpl.class) ).isTrue();
    }

    @Test
    void argsMatchComplex() throws NoSuchMethodException {
        // String 타입으로 시작하고, 다음 타입과 갯수와 상관없을 떄
        pointcut.setExpression( "execution(* *(String, ..))" );
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        Assertions.assertThat( pointcut.matches(internalMethod, MemberServiceImpl.class) ).isTrue();
    }
}
