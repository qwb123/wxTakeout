package com.qwb.takeout.dao;

import com.qwb.takeout.model.entity.ProductInfo;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 更新商品详情
     * @return
     */

    int updateProduct(ProductInfo productInfo);

    /**
     *更改库存
     *
     * @author SpringR
     * @param
     * @return
     */
    int updateProductStatus(@Param("product_status") int productStatus, @Param("product_id") Long productId);
}