package com.qwb.takeout.serviceImpl;

import com.qwb.takeout.dao.OrderDetailMapper;
import com.qwb.takeout.dao.OrderMasterMapper;
import com.qwb.takeout.dao.ProductInfoMapper;
import com.qwb.takeout.enumCode.ExceptionEnum;
import com.qwb.takeout.enumCode.OrderEnum;
import com.qwb.takeout.enumCode.OrderPayEnum;
import com.qwb.takeout.exception.SellException;
import com.qwb.takeout.model.dto.OrderDto;
import com.qwb.takeout.model.entity.OrderDetail;
import com.qwb.takeout.model.entity.OrderMaster;
import com.qwb.takeout.model.entity.ProductInfo;
import com.qwb.takeout.model.vo.OrderListVo;
import com.qwb.takeout.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    /**
     *创建订单
     *
     * @author SpringR
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createOrder(OrderDto orderDto) {
        //总价
        double count = 0;
        //购买了哪些商品
        List<ProductInfo> productInfos = new ArrayList<>();
        for (int i = 0 ; i<orderDto.getItems().size() ; i++){
            //1.查询商品
            ProductInfo productInfo = productInfoMapper.findOneById(orderDto.getItems().get(i).getProductId());
            if(productInfo == null){
                throw new SellException(ExceptionEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价
            count = count + orderDto.getItems().get(i).getProductQuantity()*productInfo.getProductPrice().doubleValue();
            double a =productInfo.getProductPrice().doubleValue();
            //3.扣除库存
            if (orderDto.getItems().get(i).getProductQuantity()<productInfo.getProductStock()){
                productInfo.setProductStock(productInfo.getProductStock()-orderDto.getItems().get(i).getProductQuantity());
                productInfoMapper.updateProduct(productInfo);
                productInfos.add(productInfo);
            }else{          //库存不足
                throw new SellException(ExceptionEnum.PRODUCT_STOCK_LACK);
            }
        }
        //4.写入订单（OrderMaster和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerName(orderDto.getName());
        orderMaster.setBuyerPhone(orderDto.getPhone());
        orderMaster.setBuyerAddress(orderDto.getAddress());
        orderMaster.setBuyerOpenid(orderDto.getOpenid());
        orderMaster.setOrderAmount(BigDecimal.valueOf(count));
        orderMaster.setOrderStatus(OrderEnum.VAILD.getCode());
        orderMaster.setPayStatus(OrderPayEnum.NoPay.getCode());
        orderMasterMapper.addOrderMaster(orderMaster);

        List<OrderDetail> orderDetails = new ArrayList<>();
        //该变量用于获取需要商品的数量
        int z = 0;
        for(ProductInfo productInfo: productInfos){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderMaster.getOrderId().toString());
            orderDetail.setProductId(productInfo.getProductId().toString());
            orderDetail.setProductName(productInfo.getProductName());
            orderDetail.setProductPrice(productInfo.getProductPrice());
            orderDetail.setProductQuantity(orderDto.getItems().get(z).getProductQuantity());
            orderDetail.setProductIcon(productInfo.getProductIcon());
            orderDetails.add(orderDetail);
        }
        orderDetailMapper.addOrderDetailList(orderDetails);
        return orderMaster.getOrderId().toString();
    }

    /**
     *查询订单
     *
     * @author SpringR
     * @param
     * @return
     */
    @Override
    public List<OrderMaster> findOrderMaster() {

        List<OrderMaster> list = orderMasterMapper.findOrderMasterList();
        return list;
    }

    @Override
    public List<OrderMaster> findOrderMasterByOpenid(String openid) {
        return orderMasterMapper.findOrderMasterListByOpenid(openid);
    }

    /**
     *查询订单详情
     *
     * @author SpringR
     * @param
     * @return
     */
    @Override
    public OrderListVo findOrderDetail(String orderId) {
        OrderMaster orderMaster = orderMasterMapper.findOrder(orderId);
        if(orderMaster==null){
            throw new SellException(ExceptionEnum.ORDER_ISNOT_EXIST);
        }
        return orderMasterMapper.findOrder(orderId);
    }

    /**
     *取消订单
     *
     * @author SpringR
     * @param
     * @return
     */
    @Override
    public int cancelOrder(String orderId) {
        //判断订单状态
        OrderMaster orderMaster = orderMasterMapper.findOrder(orderId);
        if(orderMaster.getOrderStatus()==0){
            throw new SellException(ExceptionEnum.ORDER_CANCEL_FAIL);
        }
        //1.订单查看商品
        List<OrderDetail> orderDetails = orderDetailMapper.findOrderDetailByOrderId(orderId);
        for(OrderDetail orderDetail:orderDetails){
            ProductInfo productInfo = productInfoMapper.findOneById(Long.valueOf(orderDetail.getProductId()));
            //2.返回库存
            productInfo.setProductStock(productInfo.getProductStock()+orderDetail.getProductQuantity());
            productInfoMapper.updateProduct(productInfo);
        }
        //3.更改订单状态
        return orderMasterMapper.cancelOrderMaster(orderId);
    }

    /**
     *完成订单
     *
     * @author SpringR
     * @param
     * @return
     */
    @Override
    public int finishOrder(String orderId) {
        return 0;
    }

    /**
     *支付订单
     *
     * @author SpringR
     * @param
     * @return
     */
    @Override
    public int payOrder(String orderId) {
        //判断订单支付状态
        OrderMaster orderMaster = orderMasterMapper.findOrder(orderId);
        if(orderMaster.getPayStatus()==1){
            throw new SellException(ExceptionEnum.PRODUCT_PAY_FAIL);
        }
        //更改订单状态
        return orderMasterMapper.payOrder(orderId);
    }
}
