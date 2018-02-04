package com.miner.weixin.service;

import com.miner.weixin.dataobject.WeixinUserInfo;

/**
 * @author hongf
 * @create 2018-02-04 14:29
 * @desc
 **/

public interface WeixinUserInfoService {

    //存储修改方法
    WeixinUserInfo save(WeixinUserInfo weixinUserInfo);

    //根据userId查询
    WeixinUserInfo findById(String userId);
}
