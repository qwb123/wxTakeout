package com.qwb.takeout.dao;

import com.qwb.takeout.model.entity.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryMapper {

    List<ProductCategory> findAllCategory();
}