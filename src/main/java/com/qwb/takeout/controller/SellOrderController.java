package com.qwb.takeout.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qwb.takeout.model.entity.SellerInfo;
import com.qwb.takeout.model.vo.OrderListVo;
import com.qwb.takeout.service.OrderService;
import com.qwb.takeout.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

/**
 *商家后台管理Controller
 *
 * @author SpringR
 * @param
 * @return
 */
@Controller("backProductController")
@RequestMapping("back")
@Slf4j
public class SellOrderController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderService orderService;

    /**
     *获取订单列表
     *
     * @author SpringR
     * @param
     * @return
     */
    @RequestMapping(value = "/order/list",method = RequestMethod.GET)
    public ModelAndView orderList(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize){
        ModelAndView modelAndView = new ModelAndView("order/list");
        Page<SellerInfo> page = PageHelper.startPage(pageNum, pageSize);
        orderService.findOrderMaster();
        modelAndView.addObject("orderMasterPage",page.toPageInfo());
        return modelAndView;
    }

    /**
     *订单详情
     *
     * @author SpringR
     * @param
     * @return
     */
    @RequestMapping(value = "/order/detail",method = RequestMethod.GET)
    public ModelAndView orderDetail(String orderId){
        ModelAndView modelAndView = new ModelAndView("order/detail");
        OrderListVo orderListVo = new OrderListVo();
        try{
            orderListVo = orderService.findOrderDetail(orderId);
        }catch (Exception e){
            modelAndView.addObject("msg",e.getMessage());
            modelAndView.addObject("url","list");
            modelAndView.setViewName("common/error");
            return modelAndView;
        }

        modelAndView.addObject("orderDTO",orderListVo);
        return modelAndView;
    }

    /**
     *取消订单
     *
     * @author SpringR
     * @param
     * @return
     */
    @RequestMapping(value = "/order/cancel",method = RequestMethod.GET)
    public ModelAndView orderCancel(String orderId){
        ModelAndView modelAndView = new ModelAndView();
        try {
            orderService.cancelOrder(orderId);
        }catch (Exception e){
            log.error("订单取消失败");
            modelAndView.addObject("msg",e.getMessage());
            modelAndView.addObject("url","list");
            modelAndView.setViewName("common/error");
            return modelAndView;
        }
        modelAndView.addObject("msg","订单取消成功");
        modelAndView.addObject("url","list");
        modelAndView.setViewName("common/success");
        return modelAndView;
    }
}
