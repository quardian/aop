package com.inho.aop;

import com.inho.aop.order.OrderRepository;
import com.inho.aop.order.OrderService;
import com.inho.aop.order.aop.AspectV5Order;
import com.inho.aop.order.aop.AspectV6Advice;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import({AspectV6Advice.class})
public class AopTest {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo()
    {
        log.info("isAopProxy, orderService={}, orderRepository={}"
                , AopUtils.isAopProxy(orderService)
                , AopUtils.isAopProxy(orderRepository));
    }
    @Test
    void success(){
        orderService.orderItem("itemA");
    }

    @Test
    void exception(){
        orderService.orderItem("ex");
    }
}
