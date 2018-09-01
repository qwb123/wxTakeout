package com.qwb.takeout.service;

import com.qwb.takeout.model.entity.ProductInfo;

import java.util.List;

/**
 * 商品service
 */
public interface ProductInfoService {

    List<ProductInfo> findAllProduct();

    ProductInfo findOneById(Long id);

    int updateProductInfo(ProductInfo productInfo);

    int saveProductInfo (ProductInfo productInfo);

    int changeProductStatus(int productStatus,Long productId);
}
