package com.miner.sell.repository;

import com.miner.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hongf
 * @create 2018-01-29 19:40
 * @desc 卖家信息dao层
 **/


public interface SellerInfoRepository extends JpaRepository<SellerInfo,String>{

    /**
     * 根据openId查询卖家信息
     * @param OpenId
     * @return
     */
    SellerInfo findByOpenId(String OpenId);


}
