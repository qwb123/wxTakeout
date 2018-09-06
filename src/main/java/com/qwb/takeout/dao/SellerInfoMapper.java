package com.qwb.takeout.dao;

import com.qwb.takeout.model.entity.SellerInfo;

import java.util.List;

public interface SellerInfoMapper {

    /**
     *  获取商家列表
     * @return 商家列表
     */
    List<SellerInfo> findAllList();

    /**
     * 通过商家id查找商家
     *
     * @param id 商家id
     * @return  获取商家信息
     */
    SellerInfo findOneById(long id);

    /**
     * 通过商家openid查找商家
     *
     * @param openid 商家openid
     * @return  获取商家信息
     */
    SellerInfo findOneByOpenid(String openid);

    /**
     * 添加商家
     * @param sellerInfo 商家实体
     * @return
     */
    int addSeller(SellerInfo sellerInfo);

    /**
     * 更新商家信息
     * @param sellerInfo  商家实体
     * @return
     */
    int updateSeller(SellerInfo sellerInfo);

    /**
     * 删除商家
     * @param id 商家id
     * @return
     */
    int deleteSellerById(long id);
}