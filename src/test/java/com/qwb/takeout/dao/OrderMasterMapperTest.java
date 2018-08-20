package com.qwb.takeout.dao;

import com.qwb.takeout.TakeoutApplicationTests;
import com.qwb.takeout.model.vo.OrderListVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class OrderMasterMapperTest extends TakeoutApplicationTests {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Test
    public void findOrderTest(){
        OrderListVo orderListVo = orderMasterMapper.findOrder("5");
        System.out.println(orderListVo);
    }
}