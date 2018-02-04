package com.miner.weixin.repository;

import com.miner.weixin.dataobject.WeixinAppInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hongf
 * @create 2018-02-04 15:50
 * @desc
 **/

public interface WeixinAppInfoRepository extends JpaRepository<WeixinAppInfo,String> {
}
