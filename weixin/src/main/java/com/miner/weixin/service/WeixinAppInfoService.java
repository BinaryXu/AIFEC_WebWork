package com.miner.weixin.service;

import com.miner.weixin.dataobject.WeixinAppInfo;

/**
 * @author hongf
 * @create 2018-02-04 15:51
 * @desc
 **/

public interface WeixinAppInfoService {

    /**
     * 根據appid查询
     * @param appId
     * @return
     */
    WeixinAppInfo findByAppid(String appId);
}
