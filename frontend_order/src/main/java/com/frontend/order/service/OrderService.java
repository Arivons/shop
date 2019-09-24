package com.frontend.order.service;

import com.common.utils.Result;
import com.pojo.TbOrderItem;
import com.pojo.TbOrderShipping;

import java.util.List;

public interface OrderService {
    /**
     * 保存订单
     * @param orderItems
     * @param userId
     * @param tbOrderShipping
     * @return
     */
    Result insertOrder(List<TbOrderItem> orderItems, String userId, TbOrderShipping tbOrderShipping);
}
