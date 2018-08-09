package com.qwb.takeout.dao;

import com.qwb.takeout.model.entity.SellerInfo;

import java.util.List;

public interface SellerInfoMapper {

    List<SellerInfo> findAllList();
}