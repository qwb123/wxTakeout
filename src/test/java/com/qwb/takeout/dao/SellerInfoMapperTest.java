package com.qwb.takeout.dao;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.qwb.takeout.TakeoutApplicationTests;
import com.qwb.takeout.model.entity.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class SellerInfoMapperTest extends TakeoutApplicationTests {

    @Autowired
    private SellerInfoMapper sellerInfoMapper;

    @Test
    public void findAllListTest(){
        Assert.assertNotEquals(null,sellerInfoMapper.findAllList().size());
    }

    @Test
    public void findOneByIdTest(){
        Assert.assertNotEquals(null,sellerInfoMapper.findOneById(1));
    }

    @Test
    @Transactional
    public void addSeller(){
        SellerInfo sellerInfo =new SellerInfo();
        sellerInfo.setUsername("广州糖水");
        sellerInfo.setPassword("654321");
        sellerInfo.setOpenid("220");
        Assert.assertEquals(1,sellerInfoMapper.addSeller(sellerInfo));
    }

    @Test
    @Transactional
    public void updateSellerTest(){
        SellerInfo sellerInfo =new SellerInfo();
        sellerInfo.setId(2);
        sellerInfo.setUsername("大卡司");
        sellerInfo.setPassword("123456");
        sellerInfo.setOpenid("120");
        Assert.assertEquals(1,sellerInfoMapper.updateSeller(sellerInfo));
    }

    @Test
    @Transactional
    public void delete(){
        Assert.assertEquals(1,sellerInfoMapper.deleteSellerById(2));
    }
}