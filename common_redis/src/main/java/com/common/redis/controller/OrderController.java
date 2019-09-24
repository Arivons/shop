package com.common.redis.controller;

import com.common.redis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    /**
     * 生成订单ID
     * @return
     */
    @RequestMapping("/selectOrderId")
    public Long selectOrderId(){
        return orderService.selectOrderId();
    }
}
