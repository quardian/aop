package com.inho.aop.internalcall;

import com.inho.aop.order.aop.internalcall.CallServiceV0;
import com.inho.aop.order.aop.internalcall.CallServiceV1;
import com.inho.aop.order.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(CallLogAspect.class)
public class InternalcallTest {

    @Autowired
    CallServiceV0 callService;


    @Test
    void external(){
        callService.external();
    }

    @Test
    void internal(){
        callService.internal();
    }
}
