package com.qwb.takeout.service;

import com.qwb.takeout.model.entity.SellerInfo;

import java.util.List;

/**
 * 商家Service
 */
public interface SellerInfoService {

    List<SellerInfo> findAllList();

    SellerInfo findOneById(Long id);

    SellerInfo findOneByOpenid(String openid);

    int addSeller(SellerInfo sellerInfo);

    int updateSeller(SellerInfo sellerInfo);

    int deleteSeller(Long id);
}
