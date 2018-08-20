package com.qwb.takeout.serviceImpl;

import com.qwb.takeout.dao.OrderDetailMapper;
import com.qwb.takeout.dao.OrderMasterMapper;
import com.qwb.takeout.dao.ProductInfoMapper;
import com.qwb.takeout.enumCode.OrderEnum;
import com.qwb.takeout.enumCode.OrderPayEnum;
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
            //2.计算总价
            count = count + orderDto.getItems().get(i).getProductQuantity()*productInfo.getProductPrice().doubleValue();
            double a =productInfo.getProductPrice().doubleValue();
            //3.扣除库存
            if (orderDto.getItems().get(i).getProductQuantity()<productInfo.getProductStock()){
                productInfo.setProductStock(productInfo.getProductStock()-orderDto.getItems().get(i).getProductQuantity());
                productInfoMapper.updateProduct(productInfo);
                productInfos.add(productInfo);
            }else{          //库存不足
                return null;
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

    @Override
    public OrderMaster findOrderMaster(String openid) {
        return null;
    }

    @Override
    public OrderListVo findOrderDetail(String orderId) {
        return null;
    }

    @Override
    public int cancelOrder(String orderId) {
        return 0;
    }

    @Override
    public int finishOrder(String orderId) {
        return 0;
    }

    @Override
    public int payOrder(String orderId) {
        return 0;
    }
}