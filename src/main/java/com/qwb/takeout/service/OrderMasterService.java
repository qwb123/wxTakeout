package com.qwb.takeout.service;

import com.qwb.takeout.model.entity.OrderMaster;

import java.util.List;

/**
 * 订单
 * @author qwb
 */
public interface OrderMasterService {
    int addOrderMaster(OrderMaster orderMaster);

    List<OrderMaster> findAllOrderMasterList();

    List<OrderMaster> findOrderMasterListByOpenid(String openId);

    OrderMaster findOrderMasterById(String orderId);

    int deleteOrderMasterById(String order);

    int cancelOrderMaster(String orderId);
}
