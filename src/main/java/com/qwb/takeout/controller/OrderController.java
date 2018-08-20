package com.qwb.takeout.controller;



import com.github.pagehelper.PageInfo;
import com.qwb.takeout.annotation.JsonFilter;
import com.qwb.takeout.model.BaseResponse;
import com.qwb.takeout.model.dto.OrderDto;
import com.qwb.takeout.model.entity.ProductInfo;
import com.qwb.takeout.model.vo.CreateOrderVo;
import com.qwb.takeout.model.vo.OrderListVo;
import com.qwb.takeout.service.OrderService;
import com.qwb.takeout.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 订单Controller
 */
@Controller("orderController")
@ResponseBody
@RequestMapping("/sell/buyer/order")
public class OrderController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public BaseResponse<String> createOrder(@RequestBody OrderDto orderDto){
       String orderId = orderService.createOrder(orderDto);
       return BaseResponse.success(orderId);
    }


    /**
     * 查询订单列表
     * @return
     */
    @JsonFilter(type = PageInfo.class , include = {"pageNum", "pageSize", "pages", "total", "list"})
    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    public BaseResponse<PageInfo<OrderListVo>> getOrderList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, String openid ){
        return null;
    }

    /**
     * 查询订单详情
     * @return
     */
    @RequestMapping("detail")
    public BaseResponse<OrderListVo> getOrderDetail(String openid,String orderId){

       return null;
    }

    /**
     * 删除订单
     * @return
     */
    @RequestMapping("/delete")
    @Transactional
    public BaseResponse<String> deleteOrder(String openid,String orderId){

       return null;
    }

    /**
     * 取消订单
     * @return
     */
    @RequestMapping("/cancel")
    @Transactional
    public BaseResponse<String> cancelOrder(String openid,String orderId){

        return null;
    }
}
