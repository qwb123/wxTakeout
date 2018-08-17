package com.qwb.takeout.dao;

import com.qwb.takeout.TakeoutApplicationTests;
import org.junit.Assert;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class OrderDetailMapperTest extends TakeoutApplicationTests {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    public void findOrderDetailByIdTest(){
        Assert.assertNotEquals(0,orderDetailMapper.findOrderDetailByOrderId("1"));
    }
}