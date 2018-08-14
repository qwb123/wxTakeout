package com.qwb.takeout.service;

import com.qwb.takeout.model.entity.ProductInfo;

import java.util.List;

/**
 * 商品service
 */
public interface ProductInfoService {

    List<ProductInfo> findAllProduct();

}
