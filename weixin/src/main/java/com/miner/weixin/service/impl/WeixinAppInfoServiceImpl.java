package com.miner.weixin.service.impl;

import com.miner.weixin.dataobject.WeixinAppInfo;
import com.miner.weixin.repository.WeixinAppInfoRepository;
import com.miner.weixin.service.WeixinAppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 洪峰
 * @create 2018-02-04 15:52
 **/
@Service
public class WeixinAppInfoServiceImpl implements WeixinAppInfoService {

    @Autowired
    WeixinAppInfoRepository appInfoRepository;

    @Override
    public WeixinAppInfo findByAppid(String appId) {
        return appInfoRepository.findOne(appId);
    }
}
