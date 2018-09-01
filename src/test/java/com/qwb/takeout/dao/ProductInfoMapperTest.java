package com.qwb.takeout.dao;

import com.qwb.takeout.TakeoutApplicationTests;
import com.qwb.takeout.enumCode.ProductStatusEnum;
import com.qwb.takeout.model.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductInfoMapperTest extends TakeoutApplicationTests {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Test
    public void findOneByIdTest(){
        Assert.assertNotEquals(0,productInfoMapper.findOneById(1423113435324L));
    }

    @Test
    public void findAll(){

    }

    @Test
    public void findUpProductTest(){

    }

    @Test
    @Transactional
    public void saveProductTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductName("绿豆海带");
        productInfo.setProductPrice(BigDecimal.valueOf(8));
        productInfo.setProductStock(30);
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setProductDescription("清热解毒好吃~");
        productInfo.setProductIcon("http://xxxxxx.png");
        productInfo.setCategoryType(3);
        Assert.assertEquals(1,productInfoMapper.saveProduct(productInfo));
    }

    @Test
    public void updateSaleStatusTest(){
        productInfoMapper.updateProductStatus(1,Long.valueOf(8));
    }

}