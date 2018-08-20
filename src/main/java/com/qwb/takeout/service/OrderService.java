package com.qwb.takeout.service;

import com.qwb.takeout.model.dto.OrderDto;
import com.qwb.takeout.model.entity.OrderMaster;
import com.qwb.takeout.model.vo.OrderListVo;

/**
 *订单Service
 *
 * @author SpringR
 * @param
 * @return
 */
public interface OrderService {

    /**
     *创建订单
     *
     * @author SpringR
     * @param
     * @return
     */
    String createOrder(OrderDto orderDto);

    /**
     *查询订单
     *
     * @author SpringR
     * @param
     * @return
     */
    OrderMaster findOrderMaster(String openid);

    /**
     *查询订单详情
     *
     * @author SpringR
     * @param
     * @return
     */
    OrderListVo findOrderDetail(String orderId);

    /**
     *取消订单
     *
     * @author SpringR
     * @param
     * @return
     */
    int cancelOrder(String orderId);

    /**
     *完成订单
     *
     * @author SpringR
     * @param
     * @return
     */
    int finishOrder(String orderId);

    /**
     *支付订单
     *
     * @author SpringR
     * @param
     * @return
     */
    int payOrder(String orderId);
}
