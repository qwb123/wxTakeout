package com.qwb.takeout.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qwb.takeout.annotation.JsonFilter;
import com.qwb.takeout.enumCode.OrderEnum;
import com.qwb.takeout.enumCode.OrderPayEnum;
import com.qwb.takeout.model.BaseResponse;
import com.qwb.takeout.model.Items;
import com.qwb.takeout.model.entity.OrderDetail;
import com.qwb.takeout.model.entity.OrderMaster;
import com.qwb.takeout.model.entity.ProductInfo;
import com.qwb.takeout.model.vo.CreateOrderVo;
import com.qwb.takeout.model.vo.OrderListVo;
import com.qwb.takeout.service.OrderDetailService;
import com.qwb.takeout.service.OrderMasterService;
import com.qwb.takeout.service.ProductInfoService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单Controller
 */
@Controller("orderController")
@ResponseBody
@RequestMapping("/sell/buyer/order")
public class OrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailService orderDetailService;



    /**
     * 创建订单
     *
     * @param createOrderVo
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @Transactional
    public BaseResponse<String> createOrder(@RequestBody CreateOrderVo createOrderVo){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerName(createOrderVo.getName());
        orderMaster.setBuyerPhone(createOrderVo.getPhone());
        orderMaster.setBuyerAddress(createOrderVo.getAddress());
        orderMaster.setBuyerOpenid(createOrderVo.getOpenid());
        orderMaster.setOrderStatus(OrderEnum.VAILD.getCode());
        orderMaster.setPayStatus(OrderPayEnum.NoPay.getCode());
        BigDecimal amount = BigDecimal.ZERO;
        List<ProductInfo> productInfos = new ArrayList<>();
        for(Items items:createOrderVo.getItems()){
            ProductInfo productInfo =  productInfoService.findOneById(items.getProductId());
            productInfos.add(productInfo);
            //商品库存的修改
            productInfo.setProductStock(productInfo.getProductStock()-items.getProductQuantity());
            productInfoService.updateProductInfo(productInfo);
            amount = amount.add(productInfo.getProductPrice().multiply(BigDecimal.valueOf(items.getProductQuantity())));
        }
        orderMaster.setOrderAmount(amount);
        //在订单总表添加订单
        orderMasterService.addOrderMaster(orderMaster);
        //在订单详情表中添加订单
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderMaster.getOrderId().toString());
        int k = 0;
        for(ProductInfo productInfo: productInfos){
            orderDetail.setProductId(String.valueOf(productInfo.getProductId()));
            orderDetail.setProductName(productInfo.getProductName());
            orderDetail.setProductIcon(productInfo.getProductIcon());
            orderDetail.setProductPrice(productInfo.getProductPrice());
            orderDetail.setProductQuantity(createOrderVo.getItems().get(k).getProductQuantity());
            k++;
            orderDetails.add(orderDetail);
        }
        orderDetailService.addOrderdetailList(orderDetails);
        return BaseResponse.success(orderMaster.getBuyerOpenid());
    }


    /**
     * 查询订单列表
     * @return
     */
    @JsonFilter(type = PageInfo.class , include = {"pageNum", "pageSize", "pages", "total", "list"})
    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    public BaseResponse<PageInfo<OrderListVo>> getOrderList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, String openid ){
        Page<OrderListVo> page = PageHelper.startPage(pageNum, pageSize);
        List<OrderListVo> orderListVos = new ArrayList<>();
        List<OrderMaster> orderMasters = orderMasterService.findOrderMasterListByOpenid(openid);
        for(int i = 0 ; i<orderMasters.size();i++){
            orderListVos.add(new OrderListVo(orderMasters.get(i)));
        }
        return BaseResponse.success(page.toPageInfo());
    }

    /**
     * 查询订单详情
     * @return
     */
    @RequestMapping("detail")
    public BaseResponse<OrderListVo> getOrderDetail(String openid,String orderId){
        OrderMaster orderMaster = orderMasterService.findOrderMasterById(orderId);
        OrderListVo orderListVo = new OrderListVo(orderMaster);
        List<OrderDetail> orderDetails = orderDetailService.findOrderDetailByOrderId(orderId);
        orderListVo.setOrderDetailList(orderDetails);
        return BaseResponse.success(orderListVo);
    }

    /**
     * 删除订单
     * @return
     */
    @RequestMapping("/delete")
    @Transactional
    public BaseResponse<String> deleteOrder(String openid,String orderId){
        orderMasterService.deleteOrderMasterById(orderId);
        orderDetailService.deleteOrderDetailByOrderId(orderId);
        return BaseResponse.success(null);
    }

    /**
     * 取消订单
     * @return
     */
    @RequestMapping("/cancel")
    @Transactional
    public BaseResponse<String> cancelOrder(String openid,String orderId){
        orderMasterService.cancelOrderMaster(orderId);
        return BaseResponse.success(null);
    }
}
