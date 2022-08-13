package com.inho.aop.internalcall;

import com.inho.aop.order.aop.internalcall.CallServiceV2;
import com.inho.aop.order.aop.internalcall.CallServiceV3;
import com.inho.aop.order.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest("spring.main.allow-circular-references=true")
@Import(CallLogAspect.class)
public class InternalcallV3Test {

    @Autowired
    CallServiceV3 callService;


    @Test
    void external(){
        callService.external();
    }

}
