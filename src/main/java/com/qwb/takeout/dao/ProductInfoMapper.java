package com.qwb.takeout.dao;

import com.qwb.takeout.model.entity.ProductInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品
 */
@Repository
public interface ProductInfoMapper {

    /**
     * 通过id寻找商品信息
     * @Param id 商品id
     * @return
     */
    ProductInfo findOneById(Long id);

    /**
     * 获取商品列表
     * @return
     */
    List<ProductInfo> findAll();

    /**
     * 获取上架商品
     * @return
     */
    List<ProductInfo> findUpProduct();

    /**
     * 添加新商品
     * @return
     */
    int saveProduct(ProductInfo productInfo);

}