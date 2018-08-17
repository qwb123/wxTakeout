package com.qwb.takeout.model.vo;

import com.qwb.takeout.model.entity.OrderDetail;
import com.qwb.takeout.model.entity.OrderMaster;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderListVo extends OrderMaster {

    public OrderListVo(Long orderId, String buyerName, String buyerPhone, String buyerAddress, String buyerOpenid, BigDecimal orderAmount, Byte orderStatus, Byte payStatus, Date createTime, Date updateTime) {
        super(orderId, buyerName, buyerPhone, buyerAddress, buyerOpenid, orderAmount, orderStatus, payStatus, createTime, updateTime);
    }
    public OrderListVo(OrderMaster orderMaster) {
        super(orderMaster.getOrderId(), orderMaster.getBuyerName(), orderMaster.getBuyerPhone(), orderMaster.getBuyerAddress(), orderMaster.getBuyerOpenid(), orderMaster.getOrderAmount(), orderMaster.getOrderStatus(), orderMaster.getPayStatus(), orderMaster.getCreateTime(), orderMaster.getUpdateTime());
    }


    public OrderListVo() {
    }

    private List<OrderDetail> orderDetailList;

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
