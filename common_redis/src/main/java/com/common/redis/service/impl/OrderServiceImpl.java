package com.common.redis.service.impl;

import com.common.redis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Value("${order_redis_key}")
    private String ORDER_REDIS_KEY;
    @Value("${order_init_value}")
    private Long ORDER_INIT_VALUE;
    /**
     * 生成订单ID
     * @return
     */
    @Override
    public Long selectOrderId() {
        Integer orderId = (Integer) redisTemplate.opsForValue().get(this.ORDER_REDIS_KEY);
        if(orderId==null||orderId<0){
            redisTemplate.opsForValue().set(this.ORDER_REDIS_KEY, this.ORDER_INIT_VALUE);
        }
        return redisTemplate.opsForValue().increment(this.ORDER_REDIS_KEY);
    }
}
