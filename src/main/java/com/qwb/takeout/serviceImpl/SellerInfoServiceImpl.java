package com.qwb.takeout.serviceImpl;

import com.qwb.takeout.dao.SellerInfoMapper;
import com.qwb.takeout.model.entity.SellerInfo;
import com.qwb.takeout.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "sellerInfoService")
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoMapper sellerInfoMapper;

    @Override
    public List<SellerInfo> findAllList() {
        return sellerInfoMapper.findAllList();
    }
}
