package com.qwb.takeout.controller;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qwb.takeout.annotation.JsonFilter;
import com.qwb.takeout.enumCode.ExceptionEnum;
import com.qwb.takeout.exception.SellException;
import com.qwb.takeout.model.BaseResponse;
import com.qwb.takeout.model.dto.OrderDto;
import com.qwb.takeout.model.entity.OrderMaster;
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
@RequestMapping("buyer/order")
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
    public BaseResponse<PageInfo<OrderMaster>> getOrderList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, String openid ){
        Page<OrderMaster> page = PageHelper.startPage(pageNum,pageSize);
        orderService.findOrderMasterByOpenid(openid);
        return BaseResponse.success(page.toPageInfo());
    }

    /**
     * 查询订单详情
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public BaseResponse<OrderListVo> getOrderDetail(String openid ,String orderId){
       return BaseResponse.success(orderService.findOrderDetail(orderId));
    }

    /**
     * 支付订单
     * @return
     */
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @Transactional
    public BaseResponse<String> payOrder(String openid,String orderId){
       int code = orderService.payOrder(orderId);
       if(code == 1){
           return BaseResponse.success("支付成功");
       }else{
        throw new SellException(ExceptionEnum.PRODUCT_PAY_FAIL);
       }
    }

    /**
     * 取消订单
     * @return
     */
    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    @Transactional
    public BaseResponse<String> cancelOrder(String openid,String orderId){
        int code = orderService.cancelOrder(orderId);
        if(code == 0){
            throw  new SellException(ExceptionEnum.ORDER_CANCEL_FAIL);
        }else {
            return BaseResponse.success("成功取消");
        }
    }
}
