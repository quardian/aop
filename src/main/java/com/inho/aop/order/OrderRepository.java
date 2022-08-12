package com.inho.aop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepository {
    public String save(String itemId)
    {
        log.info("[orderRepository] 실행");
        // 저장로직
        if ( "ex".equals(itemId) ){
            throw new IllegalStateException("예외 발생!");
        }
        return "ok";
    }
}
