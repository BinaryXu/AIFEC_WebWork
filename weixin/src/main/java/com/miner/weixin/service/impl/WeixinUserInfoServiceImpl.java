package com.miner.weixin.service.impl;

import com.miner.weixin.dataobject.WeixinUserInfo;
import com.miner.weixin.repository.WeixinUserInfoRepository;
import com.miner.weixin.service.WeixinUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 洪峰
 * @create 2018-02-04 14:31
 **/
@Service
public class WeixinUserInfoServiceImpl implements WeixinUserInfoService {

    @Autowired
    WeixinUserInfoRepository userInfoRepository;

    @Override
    public WeixinUserInfo save(WeixinUserInfo weixinUserInfo) {
        return userInfoRepository.save(weixinUserInfo);
    }

    @Override
    public WeixinUserInfo findById(String userId) {
        return userInfoRepository.findOne(userId);
    }
}
