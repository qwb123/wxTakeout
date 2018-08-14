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

    @Override
    public SellerInfo findOneById(Long id) {
        return sellerInfoMapper.findOneById(id);
    }

    @Override
    public int addSeller(SellerInfo sellerInfo) {
        return sellerInfoMapper.addSeller(sellerInfo);
    }

    @Override
    public int updateSeller(SellerInfo sellerInfo) {
        return sellerInfoMapper.updateSeller(sellerInfo);
    }

    @Override
    public int deleteSeller(Long id) {
        return sellerInfoMapper.deleteSellerById(id);
    }
}
