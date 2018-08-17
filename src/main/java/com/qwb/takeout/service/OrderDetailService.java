package com.qwb.takeout.service;

import com.qwb.takeout.model.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    int addOrderdetailList(List<OrderDetail> orderDetails);

    List<OrderDetail> findOrderDetailByOrderId(String orderId);

    int deleteOrderDetailByOrderId(String orderId);
}
