package com.qwb.takeout.serviceImpl;

import com.qwb.takeout.dao.OrderDetailMapper;
import com.qwb.takeout.model.entity.OrderDetail;
import com.qwb.takeout.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public int addOrderdetailList(List<OrderDetail> orderDetails) {
        return orderDetailMapper.addOrderDetailList(orderDetails);
    }

    @Override
    public List<OrderDetail> findOrderDetailByOrderId(String orderId) {
        return orderDetailMapper.findOrderDetailByOrderId(orderId);
    }

    @Override
    public int deleteOrderDetailByOrderId(String orderId) {
        return orderDetailMapper.deleteOrderDetail(orderId);
    }
}
