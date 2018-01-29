package com.miner.sell.service.impl;

import com.miner.sell.dataobject.SellerInfo;
import com.miner.sell.repository.SellerInfoRepository;
import com.miner.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 洪峰
 * @create 2018-01-29 19:59
 **/
@Service
public class SellerInfoServiceImpl implements SellerInfoService{

    @Autowired
    SellerInfoRepository sellerInfoRepository;

    /**
     * 根据微信ID查询信息
     * @param openId
     * @return
     */
    @Override
    public SellerInfo findByOpenId(String openId) {
        return sellerInfoRepository.findByOpenId(openId);
    }
}
