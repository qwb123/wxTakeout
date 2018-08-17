package com.qwb.takeout.dao;

import com.qwb.takeout.model.entity.OrderMaster;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMasterMapper {

    int addOrderMaster(OrderMaster orderMaster);

    List<OrderMaster> findOrderMasterList();

    List<OrderMaster> findOrderMasterListByOpenid(String openId);

    OrderMaster findOrderMasterById(String id);

    int deleteOrderMasterById(String id);

    int cancelOrderMaster(String orderId);

}