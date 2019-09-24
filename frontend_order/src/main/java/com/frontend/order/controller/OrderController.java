package com.frontend.order.controller;

import com.common.utils.Result;
import com.frontend.order.service.OrderService;
import com.pojo.TbOrderItem;
import com.pojo.TbOrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 保存订单
     * @param orderItem
     * @param userId
     * @param tbOrderShipping
     * @return
     */
    @RequestMapping("/insertOrder")
    public Result insertOrder(String orderItem, String userId, TbOrderShipping tbOrderShipping){
        try {
            Result result = Result.formatObjectToList(orderItem, TbOrderItem.class);
            List<TbOrderItem> orderItems = (List<TbOrderItem>) result.getData();
            return  orderService.insertOrder(orderItems, userId, tbOrderShipping);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("error");
    }
}
