package com.qwb.takeout.serviceImpl;

import com.qwb.takeout.dao.ProductCategoryMapper;
import com.qwb.takeout.model.entity.ProductCategory;
import com.qwb.takeout.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategory> findAllCategory() {
        return productCategoryMapper.findAllCategory();
    }
}
