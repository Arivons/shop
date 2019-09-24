package com.common.redis.service;

public interface OrderService {
    /**
     * 生成订单ID
     * @return
     */
    Long selectOrderId();
}
