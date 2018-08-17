package com.qwb.takeout.serviceImpl;

import com.qwb.takeout.dao.OrderMasterMapper;
import com.qwb.takeout.model.entity.OrderMaster;
import com.qwb.takeout.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Override
    public int addOrderMaster(OrderMaster orderMaster) {
        return orderMasterMapper.addOrderMaster(orderMaster);
    }

    @Override
    public List<OrderMaster> findAllOrderMasterList() {
        return orderMasterMapper.findOrderMasterList();
    }

    @Override
    public List<OrderMaster> findOrderMasterListByOpenid(String openId) {
        return orderMasterMapper.findOrderMasterListByOpenid(openId);
    }

    @Override
    public OrderMaster findOrderMasterById(String orderId) {
        return orderMasterMapper.findOrderMasterById(orderId);
    }

    @Override
    public int deleteOrderMasterById(String order) {
        return orderMasterMapper.deleteOrderMasterById(order);
    }

    @Override
    public int cancelOrderMaster(String orderId) {
        return orderMasterMapper.cancelOrderMaster(orderId);
    }
}
