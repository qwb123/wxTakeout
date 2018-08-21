package com.qwb.takeout.dao;

import com.qwb.takeout.model.entity.OrderMaster;
import com.qwb.takeout.model.vo.OrderListVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMasterMapper {

    int addOrderMaster(OrderMaster orderMaster);

    List<OrderMaster> findOrderMasterList(String openid);

    List<OrderMaster> findOrderMasterListByOpenid(String openId);

    OrderMaster findOrderMasterById(String id);

    int deleteOrderMasterById(String id);

    int cancelOrderMaster(String orderId);

    OrderListVo findOrder(String orderId);

    int payOrder(String orderId);

}