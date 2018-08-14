package com.qwb.takeout.dao;

import com.qwb.takeout.TakeoutApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class ProductCategoryMapperTest extends TakeoutApplicationTests {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Test
    public void findAllCategoryTest(){
        Assert.assertNotEquals(0,productCategoryMapper.findAllCategory());
    }

}