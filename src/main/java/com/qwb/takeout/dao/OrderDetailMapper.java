package com.qwb.takeout.dao;

import com.qwb.takeout.model.entity.OrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailMapper {

    List<OrderDetail> findOrderDetailByOrderId(String orderId);

    int addOrderDetailList(List<OrderDetail> orderDetails);

    int deleteOrderDetail(String order_id);

}