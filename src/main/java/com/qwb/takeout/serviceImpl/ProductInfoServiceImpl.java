package com.qwb.takeout.serviceImpl;

import com.qwb.takeout.dao.ProductInfoMapper;
import com.qwb.takeout.model.entity.ProductInfo;
import com.qwb.takeout.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> findAllProduct() {
        return productInfoMapper.findAll();
    }

    @Override
    public ProductInfo findOneById(Long id) {
        return productInfoMapper.findOneById(id);
    }

    @Override
    public int updateProductInfo(ProductInfo productInfo) {
        return productInfoMapper.updateProduct(productInfo);
    }

    @Override
    public int saveProductInfo(ProductInfo productInfo) {
        return productInfoMapper.saveProduct(productInfo);
    }

    @Override
    public int changeProductStatus(int productStatus, Long productId) {
        return productInfoMapper.updateProductStatus(productStatus,productId);
    }


}
