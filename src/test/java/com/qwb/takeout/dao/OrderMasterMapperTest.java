package com.qwb.takeout.dao;

import com.qwb.takeout.TakeoutApplicationTests;
import com.qwb.takeout.enumCode.OrderEnum;
import com.qwb.takeout.enumCode.OrderPayEnum;
import com.qwb.takeout.model.entity.OrderMaster;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderMasterMapperTest extends TakeoutApplicationTests {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Test
    public void addOrderMaster(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerName("小明");
        orderMaster.setBuyerPhone("13268220202");
        orderMaster.setBuyerAddress("中山大道西293号");
        orderMaster.setBuyerOpenid("d1a32d13a2d1a483d01ad");
        orderMaster.setOrderAmount(BigDecimal.valueOf(123));
        orderMaster.setPayStatus(OrderPayEnum.NoPay.getCode());
        orderMaster.setOrderStatus(OrderEnum.VAILD.getCode());
        Assert.assertNotEquals(0,orderMasterMapper.addOrderMaster(orderMaster));
    }
}