package com.miner.sell.service;

import com.miner.sell.dataobject.SellerInfo;

/**
 * @author hongf
 * @create 2018-01-29 19:57
 * @desc 卖家信息
 **/

public interface SellerInfoService {

    SellerInfo findByOpenId(String openId);
}
